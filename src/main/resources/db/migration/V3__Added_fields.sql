DROP TABLE IF EXISTS Code;
DROP TABLE IF EXISTS Status;
DROP TABLE IF EXISTS Groups;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Belongs;
DROP TABLE IF EXISTS Event;
DROP TABLE IF EXISTS Present;


CREATE TABLE Code(
    code VARCHAR(16) PRIMARY KEY,
    creation TIMESTAMP NOT NULL,
    duration INTEGER DEFAULT 300 CHECK(duration > 0)
    /* Default duration set to 300sec = 5min */
);

CREATE TABLE Status(
    statusId INTEGER PRIMARY KEY,
    label VARCHAR(128) NOT NULL
);

CREATE TABLE Groups(
    groupId INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Users(
    numEtu INTEGER PRIMARY KEY,
    firstname VARCHAR(128) NOT NULL,
    lastname VARCHAR(128) NOT NULL,
    mail VARCHAR(128) NOT NULL,
    salt VARCHAR(10) DEFAULT '0' /* TODO: change this */,
    statusId INTEGER NOT NULL,

    FOREIGN KEY(statusId) REFERENCES Status(statusId)
);


CREATE TABLE Belongs(
    numEtu INTEGER,
    groupId INTEGER,

    PRIMARY KEY(numEtu, groupId),
    FOREIGN KEY(numEtu) REFERENCES Users(numEtu),
    FOREIGN KEY(groupId) REFERENCES Groups(groupId)
);

CREATE TABLE Event(
    eventId INTEGER PRIMARY KEY,
    numEtu INTEGER NOT NULL,
    label VARCHAR(128) NOT NULL,

    FOREIGN KEY(numEtu) REFERENCES Users
);

CREATE TABLE Present(
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    numEtu INTEGER NOT NULL,
    eventId INTEGER NOT NULL,
    code VARCHAR(16) NOT NULL,

    PRIMARY KEY(numEtu, eventId, code),
    FOREIGN KEY(numEtu) REFERENCES Users(numEtu),
    FOREIGN KEY(eventId) REFERENCES Event(eventId),
    FOREIGN KEY(code) REFERENCES Code(code)
);

INSERT INTO Status(label) VALUES("student");
INSERT INTO Status(label) VALUES("prof");



-- /* SQLite specific */

-- /* When code deleted, removes all row in present referencing that code */
-- CREATE TRIGGER Trigg_DeleteCode
-- BEFORE DELETE ON Code
-- FOR EACH ROW
-- BEGIN
    -- DELETE FROM Present WHERE code = old.code
-- END;

-- /* When event deleted, removes all row in present referencing that event */
-- CREATE TRIGGER Trigg_DeleteEvent
-- BEFORE DELETE ON Event
-- FOR EACH ROW
-- BEGIN
    -- DELETE FROM Present WHERE eventId = old.eventId
-- END;

-- /* When user deleted, removes all row in present, belongs and ... referencing that user */
-- CREATE TRIGGER Trigg_DeleteUser
-- BEFORE DELETE ON User
-- FOR EACH ROW
-- BEGIN
    -- DELETE FROM Present WHERE numEtu = old.numEtu;
    -- DELETE FROM Belongs WHERE numEtu = old.numEtu;
-- END;

/* Delete group */
