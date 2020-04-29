-- create database
DROP DATABASE IF EXISTS `airport`;
CREATE DATABASE `airport`;
USE `airport`;

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
  `Sheduled_departure_time` time(4) NOT NULL,
  `Arrival_airport_code` varchar(10) NOT NULL,
  `Sheduled_arrival_time` time(4) NOT NULL,
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
  `Departure_time` time(4) NOT NULL,
  `Arrival_airport_code` varchar(10) NOT NULL,
  `Arrival_time` time(4) NOT NULL,
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