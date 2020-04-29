-- create database
DROP DATABASE IF EXISTS `airline`;
CREATE DATABASE `airline`;
USE `airline`;

-- create tables
DROP TABLE IF EXISTS `airport`;
CREATE TABLE `airport`  (
  `Airport_code` varchar(10) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `City` varchar(255) NOT NULL,
  `State` varchar(255) NOT NULL,
  PRIMARY KEY (`Airport_code`)
);

DROP TABLE IF EXISTS `flight`;
CREATE TABLE `flight`  (
  `Flight_number` varchar(36) NOT NULL,
  `Airline` varchar(255) NOT NULL,
  `Weekdays` enum('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday') NOT NULL,
  PRIMARY KEY (`Flight_number`)
);

DROP TABLE IF EXISTS `flight_leg`;
CREATE TABLE `flight_leg`  (
  `Flight_number` varchar(36) NOT NULL,
  `Leg_number` int(11) NOT NULL,
  `Departure_airport_code` varchar(10) NOT NULL,
  `Sheduled_departure_time` time NOT NULL,
  `Arrival_airport_code` varchar(10) NOT NULL,
  `Sheduled_arrival_time` time NOT NULL,
  PRIMARY KEY (`Flight_number`, `Leg_number`),
  CONSTRAINT `fk_fl_aac` FOREIGN KEY (`Arrival_airport_code`) REFERENCES `airport` (`Airport_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_fl_dac` FOREIGN KEY (`Departure_airport_code`) REFERENCES `airport` (`Airport_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_fl_fn` FOREIGN KEY (`Flight_number`) REFERENCES `flight` (`Flight_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ck_fl_sdsa` CHECK(Sheduled_departure_time <= Sheduled_arrival_time)
);

DROP TABLE IF EXISTS `leg_instance`;
CREATE TABLE `leg_instance`  (
  `Flight_number` varchar(36) NOT NULL,
  `Leg_number` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Number_of_available_seats` int(11) NOT NULL,
  `Airplane_id` varchar(36) NOT NULL,
  `Departure_airport_code` varchar(10) NOT NULL,
  `Departure_time` time NOT NULL,
  `Arrival_airport_code` varchar(10) NOT NULL,
  `Arrival_time` time NOT NULL,
  PRIMARY KEY (`Flight_number`, `Leg_number`, `Date`),
  CONSTRAINT `fk_li_aac` FOREIGN KEY (`Arrival_airport_code`) REFERENCES `airport` (`Airport_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_li_dac` FOREIGN KEY (`Departure_airport_code`) REFERENCES `airport` (`Airport_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_li_fn` FOREIGN KEY (`Flight_number`, `Leg_number`) REFERENCES `flight_leg` (`Flight_number`, `Leg_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ck_li_da` CHECK(Departure_time <= Arrival_time)
);

-- Use triggers instead of check constraints
DROP TRIGGER IF EXISTS `tri_fl_before_insert_time`;
DELIMITER $
CREATE TRIGGER `tri_fl_before_insert_time` BEFORE INSERT ON flight_leg FOR EACH ROW 
BEGIN
	DECLARE msg VARCHAR(200);
	IF new.Sheduled_departure_time > new.Sheduled_arrival_time 
	THEN
		SET msg = CONCAT('Invalid Time: ', NEW.Sheduled_departure_time, '>', NEW.Sheduled_arrival_time);
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END$
DELIMITER ;

DROP TRIGGER IF EXISTS `tri_fl_before_update_time`;
DELIMITER $
CREATE TRIGGER `tri_fl_before_update_time` BEFORE UPDATE ON flight_leg FOR EACH ROW
BEGIN
	DECLARE msg VARCHAR(200);
	IF new.Sheduled_departure_time > new.Sheduled_arrival_time 
	THEN
		SET msg = CONCAT('Invalid Time: ', NEW.Sheduled_departure_time, '>', NEW.Sheduled_arrival_time);
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END$
DELIMITER ;

DROP TRIGGER IF EXISTS `tri_li_before_insert_time`;

DELIMITER $
CREATE TRIGGER `tri_li_before_insert_time` BEFORE INSERT ON leg_instance FOR EACH ROW
BEGIN
	DECLARE msg VARCHAR(200);
	IF new.Departure_time > new.Arrival_time 
	THEN
		SET msg = CONCAT('Invalid Time: ', NEW.Departure_time, '>', NEW.Arrival_time);
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END$
DELIMITER ;

DROP TRIGGER IF EXISTS `tri_li_before_update_time`;

DELIMITER $
CREATE TRIGGER `tri_li_before_update_time` BEFORE UPDATE ON leg_instance FOR EACH ROW
BEGIN
	DECLARE msg VARCHAR(200);
	IF new.Departure_time > new.Arrival_time 
	THEN
		SET msg = CONCAT('Invalid Time: ', NEW.Departure_time, '>', NEW.Arrival_time);
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	END IF;
END$
DELIMITER ;

-- insert data
INSERT INTO `airport` VALUES ('BDL', 'Bradley International Airport', 'Hartford', 'Connecticut');
INSERT INTO `airport` VALUES ('IAH', 'George Bush Intercontinental Airport', 'Houston', 'Texas');
INSERT INTO `airport` VALUES ('JFK', 'John F. Kennedy International Airport', 'New York', 'New York');
INSERT INTO `airport` VALUES ('DTW', 'Detroit Metropolitan Wayne County Airport', 'Detroit', 'State of Michigan');
INSERT INTO `airport` VALUES ('MSP', 'Minneapolis-Saint Paul International Airport', 'Minneapolis', 'Minnesota');
INSERT INTO `airport` VALUES ('SFO', 'San Francisco International Airport', 'San Francisco', 'State of California');
INSERT INTO `airport` VALUES ('SAN', 'San Diego International Airport', 'San Diego', 'State of California');
INSERT INTO `airport` VALUES ('LAX', 'Los Angeles International Airport', 'Los Angeles', 'State of California');

INSERT INTO `flight` VALUES ('AA201', 'American Airlines', 'Monday');
INSERT INTO `flight` VALUES ('AA202', 'American Airlines', 'Wednesday');
INSERT INTO `flight` VALUES ('AA203', 'American Airlines', 'Friday');
INSERT INTO `flight` VALUES ('TWA023', 'Trans World Airlines', 'Tuesday');

INSERT INTO `flight_leg` VALUES ('AA201', 1, 'BDL', '08:00', 'DTW', '09:30');
INSERT INTO `flight_leg` VALUES ('AA201', 2, 'DTW', '10:30', 'MSP', '11:30');
INSERT INTO `flight_leg` VALUES ('AA201', 3, 'MSP', '12:30', 'SFO', '14:30');
INSERT INTO `flight_leg` VALUES ('TWA023', 1, 'IAH', '09:30', 'SAN', '11:00');
INSERT INTO `flight_leg` VALUES ('TWA023', 2, 'SAN', '12:00', 'LAX', '13:00');

INSERT INTO `leg_instance` VALUES ('AA201', 1, '2019-09-02', 6, 'B1234', 'BDL', '08:15', 'DTW', '09:32');
INSERT INTO `leg_instance` VALUES ('AA201', 2, '2019-09-02', 10, 'B1234', 'DTW', '10:35', 'MSP', '11:30');
INSERT INTO `leg_instance` VALUES ('AA201', 3, '2019-09-02', 21, 'B1234', 'MSP', '13:02', 'SFO', '14:45');
INSERT INTO `leg_instance` VALUES ('TWA023', 1, '2019-09-03', 20, 'A3301', 'IAH', '09:25', 'SAN', '10:45');
INSERT INTO `leg_instance` VALUES ('TWA023', 2, '2019-09-03', 17, 'A3301', 'SAN', '11:45', 'LAX', '12:51');

-- 1
DELETE FROM `airport`;
DELETE FROM `flight`;
DELETE FROM `flight_leg`;
DELETE FROM `leg_instance`;
SELECT * FROM `airport`;
SELECT * FROM `flight`;
SELECT * FROM `flight_leg`;
SELECT * FROM `leg_instance`;


-- 2
-- AIRPORT
INSERT INTO airport VALUES('BDL', 'Bradley International Airport', 'Hartford', 'Connecticut');
INSERT INTO airport VALUES('JFK', 'John F. Kennedy International Airport', 'New York', 'New York');
INSERT INTO airport VALUES('DTW', 'Detroit Metropolitan Wayne County Airport', 'Detroit', 'Michigan');
INSERT INTO airport VALUES('MSP', 'Minneapolis-Saint Paul International Airport', 'St. Paul', 'Minnesota');
INSERT INTO airport VALUES('SFO', 'San Francisco International Airport', 'San Francisco', 'California');
INSERT INTO airport VALUES('SAN', 'San Diego International Airport', 'San Diego', 'California');
INSERT INTO airport VALUES('LAX', 'Los Angeles International Airport', 'Los Angeles', 'California');
INSERT INTO airport VALUES('SJU', 'Luis Munoz Marin International Airport', 'Carolina', 'Puerto Rico');
INSERT INTO airport VALUES('MCO', 'Orlando International Airport', 'Orlando', 'Florida');
INSERT INTO airport VALUES('IAH', 'George Bush Intercontinental Airport', 'Houston', 'Texas');
INSERT INTO airport VALUES('HOU', 'William P. Hobby Airport', 'Houston', 'Texas');
INSERT INTO airport VALUES('PHX', 'Phoenix Sky Harbor International Airport', 'Phoenix', 'Arizona');


-- FLIGHT
INSERT INTO flight VALUES('AA201', 'American Airlines', 'Monday');
INSERT INTO flight VALUES('AA202', 'American Airlines', 'Wednesday');
INSERT INTO flight VALUES('AA203', 'American Airlines', 'Friday');
INSERT INTO flight VALUES('TWA021', 'Trans World Airlines', 'Tuesday');
INSERT INTO flight VALUES('TWA022', 'Trans World Airlines', 'Wednesday');
INSERT INTO flight VALUES('TWA023', 'Trans World Airlines', 'Thursday');
INSERT INTO flight VALUES('SW1385', 'South West Airline', 'Thursday');
INSERT INTO flight VALUES('SW1386', 'South West Airline', 'Sunday');
INSERT INTO flight VALUES('SW1387', 'South West Airline', 'Monday');

-- FLIGHT_LEG
INSERT INTO flight_leg VALUES('AA201', 1, 'BDL', '08:00', 'DTW', '09:30');
INSERT INTO flight_leg VALUES('AA201', 2, 'DTW', '10:30', 'MSP', '11:30');
INSERT INTO flight_leg VALUES('AA201', 3, 'MSP', '12:30', 'SFO', '14:30');
INSERT INTO flight_leg VALUES('AA202', 1, 'DTW', '08:00', 'BDL', '09:30');
INSERT INTO flight_leg VALUES('AA203', 1, 'SFO', '10:00', 'LAX', '10:45');
INSERT INTO flight_leg VALUES('AA203', 2, 'LAX', '14:00', 'BDL', '23:00');
INSERT INTO flight_leg VALUES('TWA023', 1, 'IAH', '09:30', 'SAN', '11:00');
INSERT INTO flight_leg VALUES('TWA023', 2, 'SAN', '12:00', 'LAX', '13:00');
INSERT INTO flight_leg VALUES('SW1385', 1, 'HOU', '09:30', 'LAX', '10:30');
INSERT INTO flight_leg VALUES('SW1386', 1, 'BDL', '07:00', 'MCO', '10:00');
INSERT INTO flight_leg VALUES('SW1386', 2, 'MCO', '12:30', 'SJU', '15:30');
INSERT INTO flight_leg VALUES('SW1387', 1, 'BDL', '09:30', 'IAH', '12:30');
INSERT INTO flight_leg VALUES('SW1387', 2, 'IAH', '13:30', 'PHX', '14:30');
INSERT INTO flight_leg VALUES('SW1387', 3, 'PHX', '15:30', 'LAX', '16:30');

-- LEG_INSTANCE
INSERT INTO leg_instance VALUES('TWA023', 1, '2019-08-29', 5, 'A3301', 'IAH', '09:35', 'SAN', '11:00');
INSERT INTO leg_instance VALUES('TWA023', 2, '2019-08-29', 7, 'A3301', 'SAN', '12:05', 'LAX', '12:50');
INSERT INTO leg_instance VALUES('SW1386', 1, '2019-09-01', 8, 'A3305', 'BDL', '07:25', 'MCO', '10:05');
INSERT INTO leg_instance VALUES('SW1386', 2, '2019-09-01', 3, 'A3305', 'MCO', '12:35', 'SJU', '15:30');
INSERT INTO leg_instance VALUES('AA201', 1, '2019-09-02', 6, 'B1234', 'BDL', '08:15', 'DTW', '09:32');
INSERT INTO leg_instance VALUES('AA201', 2, '2019-09-02', 10, 'B1234', 'DTW', '10:35', 'MSP', '11:30');
INSERT INTO leg_instance VALUES('AA201', 3, '2019-09-02', 11, 'B1234', 'MSP', '13:02', 'SFO', '14:45');
INSERT INTO leg_instance VALUES('AA202', 1, '2019-09-04', 12, 'B1235', 'DTW', '08:01', 'BDL', '09:25');
INSERT INTO leg_instance VALUES('SW1385', 1, '2019-09-05', 2, 'A3309', 'HOU', '09:30', 'LAX', '10:25');
INSERT INTO leg_instance VALUES('TWA023', 1, '2019-09-05', 5, 'A3301', 'IAH', '09:25', 'SAN', '10:45');
INSERT INTO leg_instance VALUES('TWA023', 2, '2019-09-05', 1, 'A3301', 'SAN', '11:45', 'LAX', '12:51');
INSERT INTO leg_instance VALUES('AA203', 1, '2019-09-06', 7, 'B1236', 'MSP', '13:02', 'SFO', '14:45');
INSERT INTO leg_instance VALUES('AA203', 2, '2019-09-06', 9, 'B1236', 'MSP', '13:02', 'SFO', '14:45');
INSERT INTO leg_instance VALUES('SW1386', 1, '2019-09-08', 10, 'A3311', 'BDL', '07:05', 'MCO', '09:50');
INSERT INTO leg_instance VALUES('SW1386', 2, '2019-09-08', 17, 'A3311', 'MCO', '12:30', 'SJU', '15:15');

-- query
flush tables;
-- 3(a) List all the flight numbers by American Airlines.
SELECT Flight_number flight_number FROM flight WHERE Airline = 'American Airlines';

-- 3(b) How many flights does American Airlines offer on Mondays?
SELECT COUNT(DISTINCT Flight_number) FROM flight WHERE Airline = 'American Airlines' AND Weekdays = 'Monday';

-- 3(c) List the flight numbers and weekdays of all flights or flight legs that depart from Houston International Airport and arrive in Los Angeles International Airport.
SELECT f.Flight_number flight_number, f.Weekdays weekdays FROM flight as f WHERE f.Flight_number IN (SELECT fl.Flight_number FROM flight_leg as fl, (SELECT fld.Flight_number, fld.Leg_number FROM flight_leg fld WHERE fld.Departure_airport_code IN (SELECT Airport_code FROM airport WHERE `Name`='George Bush Intercontinental Airport')) as t1, (SELECT fla.Flight_number, fla.Leg_number FROM flight_leg fla WHERE fla.Arrival_airport_code IN (SELECT Airport_code FROM airport WHERE `Name`='Los Angeles International Airport')) as t2 WHERE t1.Flight_number = t2.Flight_number AND t1.Leg_number <= t2.Leg_number AND fl.Flight_number = t1.Flight_number);

-- 3(d) 
SELECT t1.Flight_number flight_number, t1.Departure_airport_code DAC, DATE_FORMAT(t1.Sheduled_departure_time,"%H%i") SDT, t2.Arrival_airport_code AAC, DATE_FORMAT(t2.Sheduled_arrival_time,"%H%i") SAT, t1.Weekdays weekdays FROM (SELECT fl.Flight_number, fl.Departure_airport_code, fl.Sheduled_departure_time, fl.Arrival_airport_code, fl.Sheduled_arrival_time, f.Weekdays FROM flight as f, flight_leg as fl, (SELECT fld.Flight_number, fld.Leg_number FROM flight_leg fld WHERE fld.Departure_airport_code IN (SELECT Airport_code FROM airport WHERE City='Houston')) as t1, (SELECT fla.Flight_number, fla.Leg_number FROM flight_leg fla WHERE fla.Arrival_airport_code IN (SELECT Airport_code FROM airport WHERE City='Los Angeles')) as t2 WHERE t1.Flight_number = t2.Flight_number AND t1.Leg_number <= t2.Leg_number AND fl.Flight_number = t1.Flight_number AND f.Flight_number=fl.Flight_number AND fl.Leg_number>=(SELECT Leg_number FROM flight_leg WHERE Flight_number=t1.Flight_number AND Leg_number=t1.Leg_number)) AS t1, (SELECT fl.Flight_number, fl.Leg_number, fl.Departure_airport_code, fl.Sheduled_departure_time, fl.Arrival_airport_code, fl.Sheduled_arrival_time, f.Weekdays FROM flight as f, flight_leg as fl, (SELECT fld.Flight_number, fld.Leg_number FROM flight_leg fld WHERE fld.Departure_airport_code IN (SELECT Airport_code FROM airport WHERE City='Houston')) as t1, (SELECT fla.Flight_number, fla.Leg_number FROM flight_leg fla WHERE fla.Arrival_airport_code IN (SELECT Airport_code FROM airport WHERE City='Los Angeles')) as t2 WHERE t1.Flight_number = t2.Flight_number AND t1.Leg_number <= t2.Leg_number AND fl.Flight_number = t1.Flight_number AND f.Flight_number=fl.Flight_number AND fl.Leg_number>=(SELECT Leg_number FROM flight_leg WHERE Flight_number=t1.Flight_number AND Leg_number=t1.Leg_number)) AS t2 WHERE t1.Flight_number=t2.Flight_number AND t1.Departure_airport_code IN (SELECT Airport_code FROM airport WHERE City='Houston') AND t2.Arrival_airport_code IN (SELECT Airport_code FROM airport WHERE City='Los Angeles');

-- 3(e)
SELECT t1.Airline airline, t1.sum_seats as total_empty_seats FROM (SELECT flight.Airline,SUM(leg_instance.Number_of_available_seats) as sum_seats FROM flight,leg_instance WHERE flight.Flight_number = leg_instance.Flight_number AND leg_instance.Date BETWEEN '2019-09-01' AND '2019-09-30' GROUP BY flight.Airline) AS t1 WHERE t1.sum_seats=(SELECT MAX(t1.sum_seats) FROM (SELECT flight.Airline,SUM(leg_instance.Number_of_available_seats) as sum_seats FROM flight,leg_instance WHERE flight.Flight_number = leg_instance.Flight_number AND leg_instance.Date BETWEEN '2019-09-01' AND '2019-09-30' GROUP BY flight.Airline) as t1 WHERE 1);

-- 3(f)
SELECT flight.Airline airline, SUM(leg_instance.Number_of_available_seats) total_empty_seats FROM flight,leg_instance WHERE flight.Flight_number = leg_instance.Flight_number AND leg_instance.Date BETWEEN '2019-09-01' AND '2019-09-30' GROUP BY flight.Airline HAVING total_empty_seats<30;

-- 3(g)
SELECT t2.Airline,t1.Airline FROM (SELECT * FROM (SELECT DISTINCT flight.Airline, fl.Arrival_airport_code FROM flight_leg fl, (SELECT Flight_number, Leg_number FROM flight_leg WHERE Departure_airport_code IN (SELECT Airport_code FROM airport WHERE City ='Houston')) t1, flight WHERE t1.Flight_number = fl.Flight_number AND t1.Leg_number <= fl.Leg_number AND t1.Flight_number=flight.Flight_number) t1 
WHERE EXISTS (
	SELECT * FROM (SELECT DISTINCT flight.Airline, fl.Arrival_airport_code FROM flight_leg fl, (SELECT Flight_number, Leg_number FROM flight_leg WHERE Departure_airport_code IN (SELECT Airport_code FROM airport WHERE City ='Houston')) t1, flight WHERE t1.Flight_number = fl.Flight_number AND t1.Leg_number <= fl.Leg_number AND t1.Flight_number=flight.Flight_number) t3 WHERE t3.Arrival_airport_code = t1.Arrival_airport_code AND t3.Airline <> t1.Airline
)) t1 INNER JOIN (SELECT * FROM (SELECT DISTINCT flight.Airline, fl.Arrival_airport_code FROM flight_leg fl, (SELECT Flight_number, Leg_number FROM flight_leg WHERE Departure_airport_code IN (SELECT Airport_code FROM airport WHERE City ='Houston')) t1, flight WHERE t1.Flight_number = fl.Flight_number AND t1.Leg_number <= fl.Leg_number AND t1.Flight_number=flight.Flight_number) t1 
WHERE EXISTS (
	SELECT * FROM (SELECT DISTINCT flight.Airline, fl.Arrival_airport_code FROM flight_leg fl, (SELECT Flight_number, Leg_number FROM flight_leg WHERE Departure_airport_code IN (SELECT Airport_code FROM airport WHERE City ='Houston')) t1, flight WHERE t1.Flight_number = fl.Flight_number AND t1.Leg_number <= fl.Leg_number AND t1.Flight_number=flight.Flight_number) t3 WHERE t3.Arrival_airport_code = t1.Arrival_airport_code AND t3.Airline <> t1.Airline
)) t2 ON t1.Arrival_airport_code=t2.Arrival_airport_code AND t1.Airline<>t2.Airline GROUP BY t2.Arrival_airport_code;

-- 3(h)
SELECT f.Airline airline, f.Weekdays weekdays, f.Flight_number FN, fl.Leg_number LN, fl.Departure_airport_code DAC, DATE_FORMAT(fl.Sheduled_departure_time,"%H%i") SDT, fl.Arrival_airport_code AAC, DATE_FORMAT(fl.Sheduled_arrival_time,"%H%i") SAT FROM flight as f LEFT OUTER JOIN flight_leg AS fl ON f.Flight_number=fl.Flight_number;

-- 4(a)
DROP VIEW IF EXISTS `4a`;
CREATE VIEW `4a` AS (SELECT fl1.Flight_number, fl1.Departure_airport_code, fl2.Arrival_airport_code FROM (SELECT f.Flight_number, f.Weekdays FROM flight as f WHERE f.Flight_number IN (SELECT fl.Flight_number FROM flight_leg as fl, (SELECT fld.Flight_number, fld.Leg_number FROM flight_leg fld WHERE fld.Departure_airport_code IN (SELECT Airport_code FROM airport WHERE `Name`='George Bush Intercontinental Airport')) as t1, (SELECT fla.Flight_number, fla.Leg_number FROM flight_leg fla WHERE fla.Arrival_airport_code IN (SELECT Airport_code FROM airport WHERE `Name`='Los Angeles International Airport')) as t2 WHERE t1.Flight_number = t2.Flight_number AND t1.Leg_number <= t2.Leg_number AND fl.Flight_number = t1.Flight_number)) as t1, flight_leg as fl1, flight_leg as fl2 WHERE t1.Flight_number = fl1.Flight_number AND fl1.Flight_number = fl2.Flight_number AND fl1.Leg_number = (SELECT MIN(Leg_number) FROM flight_leg WHERE Flight_number = t1.Flight_number) AND fl2.Leg_number = (SELECT MAX(Leg_number) FROM flight_leg WHERE Flight_number = t1.Flight_number));

-- 4(b)
SELECT * FROM information_schema.views WHERE information_schema.views.TABLE_NAME = '4a';

-- 4(c)
DROP VIEW IF EXISTS `4c`;
CREATE VIEW `4c` AS (SELECT t1.Flight_number flight_number, t1.Departure_airport_code DAC, DATE_FORMAT(t1.Sheduled_departure_time,"%H%i") SDT, t2.Arrival_airport_code AAC, DATE_FORMAT(t2.Sheduled_arrival_time,"%H%i") SAT, t1.Weekdays weekdays FROM (SELECT fl.Flight_number, fl.Departure_airport_code, fl.Sheduled_departure_time, fl.Arrival_airport_code, fl.Sheduled_arrival_time, f.Weekdays FROM flight as f, flight_leg as fl, (SELECT fld.Flight_number, fld.Leg_number FROM flight_leg fld WHERE fld.Departure_airport_code IN (SELECT Airport_code FROM airport WHERE City='Houston')) as t1, (SELECT fla.Flight_number, fla.Leg_number FROM flight_leg fla WHERE fla.Arrival_airport_code IN (SELECT Airport_code FROM airport WHERE City='Los Angeles')) as t2 WHERE t1.Flight_number = t2.Flight_number AND t1.Leg_number <= t2.Leg_number AND fl.Flight_number = t1.Flight_number AND f.Flight_number=fl.Flight_number AND fl.Leg_number>=(SELECT Leg_number FROM flight_leg WHERE Flight_number=t1.Flight_number AND Leg_number=t1.Leg_number)) AS t1, (SELECT fl.Flight_number, fl.Leg_number, fl.Departure_airport_code, fl.Sheduled_departure_time, fl.Arrival_airport_code, fl.Sheduled_arrival_time, f.Weekdays FROM flight as f, flight_leg as fl, (SELECT fld.Flight_number, fld.Leg_number FROM flight_leg fld WHERE fld.Departure_airport_code IN (SELECT Airport_code FROM airport WHERE City='Houston')) as t1, (SELECT fla.Flight_number, fla.Leg_number FROM flight_leg fla WHERE fla.Arrival_airport_code IN (SELECT Airport_code FROM airport WHERE City='Los Angeles')) as t2 WHERE t1.Flight_number = t2.Flight_number AND t1.Leg_number <= t2.Leg_number AND fl.Flight_number = t1.Flight_number AND f.Flight_number=fl.Flight_number AND fl.Leg_number>=(SELECT Leg_number FROM flight_leg WHERE Flight_number=t1.Flight_number AND Leg_number=t1.Leg_number)) AS t2 WHERE t1.Flight_number=t2.Flight_number AND t1.Departure_airport_code IN (SELECT Airport_code FROM airport WHERE City='Houston') AND t2.Arrival_airport_code IN (SELECT Airport_code FROM airport WHERE City='Los Angeles'));
SELECT * FROM `4c`;

-- 5(a)(1)
DROP PROCEDURE IF EXISTS del_idx;  
DELIMITER $
create procedure del_idx(IN p_tablename varchar(200), IN p_idxname VARCHAR(200))  
begin  
DECLARE str VARCHAR(250);  
  set @str=concat(' drop index ',p_idxname,' on ',p_tablename);   
    
  select count(*) into @cnt from information_schema.statistics where table_name=p_tablename and index_name=p_idxname ;  
  if @cnt >0 then   
    PREPARE stmt FROM @str;  
    EXECUTE stmt ;  
  end if;  
  
end $
DELIMITER ;

call del_idx('airport','airport_name_unique');

CREATE UNIQUE INDEX airport_name_unique ON airport(`Name`);

SELECT * FROM mysql.`innodb_index_stats` WHERE mysql.`innodb_index_stats`.database_name='airline' AND mysql.`innodb_index_stats`.table_name='airport';
SHOW INDEX FROM airport;

-- 5(a)(2)
-- don't run this
-- CREATE UNIQUE INDEX airline_unique ON flight(Airline);

-- 5(b)
-- don't run this
-- INSERT INTO flight_leg VALUES('TWA023', 3, 'LAX', '14:00', 'YVR', '15:00');