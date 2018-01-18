/* Drop all existing tables */
DROP TABLE Course;
DROP TABLE Code;
DROP TABLE Student;
DROP TABLE Professor;
DROP TABLE Users;
DROP TABLE Present;



/*
 * Users
 */
CREATE TABLE Users(
    userId VARCHAR(255) PRIMARY KEY,
    salt VARCHAR(10)
);
CREATE TABLE Student(
    userId VARCHAR(255) PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    tpGroup CHAR(2) NOT NULL,
    tdGroup CHAR(2) NOT NULL,

    FOREIGN KEY(userId) REFERENCES Users(userId)
);
CREATE TABLE Professor(
    userId VARCHAR(255) PRIMARY KEY,

    FOREIGN KEY(userId) REFERENCES Users(userId)
);

/*
 * Course
 */
CREATE TABLE Course(
    courseId VARCHAR(255) PRIMARY KEY,
    courseName VARCHAR(255) NOT NULL
);

/*
 * Stores randomly generated codes
 */
CREATE TABLE Code(
    code VARCHAR(255),
    creationDate TIMESTAMP CHECK(creationDate <= CURRENT_TIMESTAMP) DEFAULT CURRENT_TIMESTAMP,
    durationSec TIMESTAMP NOT NULL CHECK(durationSec > 0) DEFAULT 300, /* Default to 5min */
    courseId VARCHAR(255),

    PRIMARY KEY(code, creationDate),
    FOREIGN KEY(courseId) REFERENCES Course(courseId)
);

/*
 * Link table between students and code
 */
CREATE TABLE Present(
    userId VARCHAR(255),
    code VARCHAR(255),
    creationDate TIMESTAMP,
    courseId VARCHAR(255),
    timestamp VARCHAR(255) NOT NULL CHECK(timestamp <= CURRENT_TIMESTAMP) DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY(userId, code, creationDate),
    FOREIGN KEY(userId) REFERENCES Users(userId),
    FOREIGN KEY(code, creationDate) REFERENCES Code(code, creationDate)
);



/*
 * Triggers (sqlite)
 */
 CREATE TRIGGER Trigg_DeleteStudent
AFTER DELETE ON Student
FOR EACH ROW
BEGIN
    DELETE FROM Users WHERE userId = old.userId;
END;
CREATE TRIGGER Trigg_DeleteProfessor
AFTER DELETE ON Professor
FOR EACH ROW
BEGIN
    DELETE FROM Users WHERE userId = old.userId;
END;

/* TODO: delete course removes all codes(Code) linked to it and presence(Present) linked to it */
/* TODO: deleting a code removes all presence(Present) */
