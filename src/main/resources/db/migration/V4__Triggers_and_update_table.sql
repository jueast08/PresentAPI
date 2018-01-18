DROP TABLE IF EXISTS Code;
DROP TABLE IF EXISTS Status;
DROP TABLE IF EXISTS Groups;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Belongs;
DROP TABLE IF EXISTS Event;
DROP TABLE IF EXISTS Present;


CREATE TABLE Code(
    codeId INTEGER PRIMARY KEY,
    code VARCHAR(16),
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
    uid INTEGER PRIMARY KEY,
    firstname VARCHAR(128) NOT NULL,
    lastname VARCHAR(128) NOT NULL,
    mail VARCHAR(128) NOT NULL,
    salt VARCHAR(10) DEFAULT '0' /* TODO: change this to default random */,
    statusId INTEGER NOT NULL,

    FOREIGN KEY(statusId) REFERENCES Status(statusId)
);


CREATE TABLE Belongs(
    uid INTEGER,
    groupId INTEGER,

    PRIMARY KEY(uid, groupId),
    FOREIGN KEY(uid) REFERENCES Users(uid),
    FOREIGN KEY(groupId) REFERENCES Groups(groupId)
);

CREATE TABLE Event(
    eventId INTEGER PRIMARY KEY,
    uid INTEGER NOT NULL,
    codeId INTEGER NOT NULL,
    label VARCHAR(128) NOT NULL,

    FOREIGN KEY(uid) REFERENCES Users,
    FOREIGN KEY(codeId) REFERENCES Code(codeId)
);

CREATE TABLE Present(
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    uid INTEGER NOT NULL,
    eventId INTEGER NOT NULL,

    PRIMARY KEY(uid, eventId),
    FOREIGN KEY(uid) REFERENCES Users(uid),
    FOREIGN KEY(eventId) REFERENCES Event(eventId)
);

CREATE TABLE EventGroups(
    groupId INTEGER,
    eventId INTEGER,
    FOREIGN KEY(groupId) REFERENCES Groups(groupId),
    FOREIGN KEY(eventId) REFERENCES Event(eventId),
    PRIMARY KEY(groupId, eventId)
);

INSERT INTO Status(label) VALUES("student");
INSERT INTO Status(label) VALUES("prof");



-- /* SQLite specific */

CREATE TRIGGER Trigg_DeleteUser
BEFORE DELETE ON Users
FOR EACH ROW
BEGIN
    DELETE FROM Present WHERE uid = old.uid;
    DELETE FROM Belongs WHERE uid = old.uid;
END;

CREATE TRIGGER Trigg_DeleteGroup
BEFORE DELETE ON Groups
FOR EACH ROW
BEGIN
    /* Group deleted -> users in that group deleted */
    /* TODO: delete user only if they are no longer in a group */
    DELETE FROM Users WHERE uid IN (
        SELECT uid
        FROM Belongs
        WHERE groupId = old.groupId
    );
    /* Group deleted -> events referring to that groups deleted */
    DELETE FROM EventGroups WHERE groupId = old.groupId;
END;

CREATE TRIGGER Trigg_DeleteEvent
BEFORE DELETE ON Event
FOR EACH ROW
BEGIN
    DELETE FROM Code WHERE codeId = old.codeId;
    DELETE FROM Present WHERE eventId = old.eventId;
    DELETE FROM EventGroups WHERE eventId = old.eventId;
END;


