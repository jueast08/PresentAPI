CREATE TABLE Users(
    userId VARCHAR(255) PRIMARY KEY,
    salt VARCHAR(10)
);

CREATE TABLE Student(
    userId VARCHAR(255) PRIMARY KEY,
    CONSTRAINT fk_userId FOREIGN KEY(userId) REFERENCES Users(userId)
);


CREATE TABLE Professor(
    userId VARCHAR(255) PRIMARY KEY,
    CONSTRAINT fk_userId FOREIGN KEY(userId) REFERENCES Users(userId)
);

CREATE TABLE Present(
    userId VARCHAR(255),
    courseId VARCHAR(255),
    timestamp VARCHAR(255),
    PRIMARY KEY(userId, courseId),
    CONSTRAINT fk_userId FOREIGN KEY(userId) REFERENCES Users,
    CONSTRAINT fk_courseId FOREIGN KEY(courseId) REFERENCES Course
);

CREATE TABLE Course(
    courseId VARCHAR(255) PRIMARY KEY
);

CREATE TABLE Code(
    id PRIMARY KEY,
    code VARCHAR(255),
    creation TIMESTAMP,
    duration TIMESTAMP
);

