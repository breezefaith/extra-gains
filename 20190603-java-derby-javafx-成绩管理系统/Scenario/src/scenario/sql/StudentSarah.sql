drop table SARAH.ROLE;
drop table SARAH.SARAH;
drop table SARAH.GRADE;
drop schema SARAH restrict ;

create table SARAH.ROLE
(
  ID varchar(64)
    constraint ROLE_ID_PK
    primary key,
  ROLE_NAME VARCHAR(100)
);

create table SARAH.SARAH
(
  ID varchar(64)
    primary key,
  NAME VARCHAR(10),
  SEX VARCHAR(10),
  AGE INTEGER,
  RID varchar(64),
  LOCATION VARCHAR(200)
);

create table SARAH.GRADE
(
  GID varchar(64)
    constraint GRADE_GID_PK
    primary key,
  SID varchar(64),
  COURSE VARCHAR(200),
  TERM VARCHAR(200),
  MARK INTEGER
);

INSERT INTO SARAH.ROLE (ID,ROLE_NAME) VALUES ('r1','manager');
INSERT INTO SARAH.ROLE (ID,ROLE_NAME) VALUES ('r2','teacher');
INSERT INTO SARAH.ROLE (ID,ROLE_NAME) VALUES ('r3','student');

INSERT INTO SARAH.SARAH (ID,NAME, SEX, AGE, RID, LOCATION) VALUES ('s1','Sarah', 'female', 21, 'r3', 'Adelaide');
INSERT INTO SARAH.SARAH (ID,NAME, SEX, AGE, RID, LOCATION) VALUES ('s2','Alden', 'male', 23, 'r3', 'Adelaide');
INSERT INTO SARAH.SARAH (ID,NAME, SEX, AGE, RID, LOCATION) VALUES ('t1','Sam', 'male', 35, 'r2', 'Adelaide');
INSERT INTO SARAH.SARAH (ID,NAME, SEX, AGE, RID, LOCATION) VALUES ('m1','Tom', 'male', 45, 'r1', 'Adelaide');

INSERT INTO SARAH.GRADE (GID,SID, COURSE, TERM, MARK) VALUES ('g1','s1', 'Accounting', '2018-2', 90);
INSERT INTO SARAH.GRADE (GID,SID, COURSE, TERM, MARK) VALUES ('g2','s2', 'Accounting', '2018-2', 85);
INSERT INTO SARAH.GRADE (GID,SID, COURSE, TERM, MARK) VALUES ('g3','s2', 'Java', '2018-1', 100);
INSERT INTO SARAH.GRADE (GID,SID, COURSE, TERM, MARK) VALUES ('g4','s1', 'Java', '2018-1', 80);