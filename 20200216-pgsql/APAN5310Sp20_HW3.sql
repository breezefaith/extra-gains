-- APAN 5310: SQL & RELATIONAL DATABASES SPRING 2020

   -------------------------------------------------------------------------
   --                                                                     --
   --                            HONOR CODE                               --
   --                                                                     --
   --  I affirm that I will not plagiarize, use unauthorized materials,   --
   --  or give or receive illegitimate help on assignments, papers, or    --
   --  examinations. I will also uphold equity and honesty in the         --
   --  evaluation of my work and the work of others. I do so to sustain   --
   --  a community built around this Code of Honor.                       --
   --                                                                     --
   -------------------------------------------------------------------------

/*
 *    You are responsible for submitting your own, original work. We are
 *    obligated to report incidents of academic dishonesty as per the
 *    Student Conduct and Community Standards.
 */


-------------------------------------------------------------------------------
-------------------------------------------------------------------------------


-- HOMEWORK ASSIGNMENT 3

/*
 *  NOTES:
 *
 *    - Type your SQL statements between the START and END tags for each
 *      question. Do not alter this template .sql file in any way other than
 *      adding your answers. Do not delete the START/END tags. The .sql file
 *      you submit will be validated before grading and will not be graded if
 *      it fails validation due to any alteration of the commented sections.
 *
 *    - Our course is using PostgreSQL which has been preinstalled for you in
 *      Codio. We grade your assignments in Codio and PostgreSQL. You risk
 *      losing points if you prepare your SQL queries for a different database
 *      system (MySQL, MS SQL Server, Oracle, etc).
 *
 *    - It is highly recommended that you insert additional, appropriate data
 *      to test the results of your queries. You do not need to include your
 *      sample data in your answers.
 *
 *    - Make sure you test each one of your answers in pgAdmin. If a query
 *      returns an error it will earn no points.
 *
 *    - In your CREATE TABLE statements you must provide data types AND
 *      primary/foreign keys (if applicable).
 *
 *    - You may expand your answers in as many lines as you find appropriate
 *      between the START/END tags.
 *
 */



-------------------------------------------------------------------------------



/*
 * QUESTION 1 (10 points)
 * ----------------------------------------------------------------------------
 *
 * You are tasked to create a simplified database of a registration system for
 * a hotel. Provide the SQL statements that create the six (6)
 * tables with data types of your design. Implement integrity constraints
 * (primary/foreign keys, NOT NULL) as needed.
 * Note: since underlining is not supported in this file format, primary keys
 * for each relation below are shown within '*'.
 *
 *   Amenity (*amenity_id*, amenity_name, coordinator)
 *   Building (*building_id*, amenity_id, building_name, number_of_floors)
 *   Beds (*bed_code*, number_of_beds, type_of_beds)
 *   Room (*room_id*, building_id, bed_code, room_number)
 *   Customer (*customer_id*, first_name, last_name, email, dob, address)
 *   Registration (*customer_id*, *room_id*)
 *
 *
 * Type the CREATE TABLE statements in the order they have to be executed so
 * that there is no error in PostgreSQL. Expand the space between the START/END
 * tags to fit all of your CREATE TABLE statements.
 *
 * IMPORTANT: Make sure to implement the schema with exactly the provided
 *            relation and attribute names. Do not rename relations or
 *            attributes, either by accident (typos) or on purpose.
 *
 *
 * Schema Description:
 * -------------------
 *
 * Amenity
 * -----
 *  amenity_id: unique ID for amenity (PK)
 *  amenity_name: full name of the amenity (i.e. "Pool", "Sym", etc.)
 *  coordinator: coordinator of this activity category
 *
 * Building
 * -------
 *  building_id: unique ID for building (PK)
 *  amenity_id: unique ID for amenity (FK)
 *  building_name: name of the building (i.e. "East Wing", "Ocean Front", etc)
 *  number_of_floors: Number of floors of the building
 *
 * Beds
 * ---------------
 *  bed_code: unique ID for bed (PK)
 *  number_of_beds: number of beds in a room
 *  type_of_beds: type of beds (i.e., "King", "Queen", etc)
 *
 * Room
 * -----
 *  room_id: unique ID for rooms across all buildings (PK)
 *  building_id: unique ID for building (FK)
 *  bed_code: unique ID for beds (FK)
 *  room_number: room number (i.e. 341, 1055, 8001 etc)
 *
 * Customer
 * ----------
 *  customer_id: unique ID for customer (PK)
 *  first_name: a customer's first name
 *  last_name: a customer's last name
 *  email: a customer's email
 *  dob: a customer's date of birth
 *  address: a customer's home address (full address in one field)
 *
 * Registration
 * ------------
 *  customer_id: unique ID for customer (PK, FK)
 *  room_id: unique ID for room across all buildings (PK, FK)
 *
 */

-- START ANSWER 1 --
DROP TABLE IF EXISTS Registration;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Room;
DROP TABLE IF EXISTS Beds;
DROP TABLE IF EXISTS Building;
DROP TABLE IF EXISTS Amenity;

CREATE TABLE Amenity (
	amenity_id INTEGER NOT NULL,
	amenity_name VARCHAR(100),
	coordinator VARCHAR(100),
	PRIMARY KEY (amenity_id)
);

CREATE TABLE Building (
	building_id INTEGER NOT NULL,
	amenity_id INTEGER,
	building_name VARCHAR(100),
	number_of_floors INTEGER,
	PRIMARY KEY (building_id),
	FOREIGN KEY (amenity_id) REFERENCES Amenity(amenity_id)
);

CREATE TABLE Beds (
	bed_code INTEGER NOT NULL,
	number_of_beds INTEGER,
	type_of_beds VARCHAR(100),
	PRIMARY KEY (bed_code),
	CHECK (type_of_beds IN ('Double', 'King', 'Queen', 'Other'))
);

CREATE TABLE Room (
	room_id INTEGER NOT NULL,
	building_id INTEGER,
	bed_code INTEGER,
	room_number VARCHAR(50),
	PRIMARY KEY (room_id),
	FOREIGN KEY (building_id) REFERENCES Building,
	FOREIGN KEY (bed_code) REFERENCES Beds
);

CREATE TABLE Customer(
	customer_id INTEGER NOT NULL,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	email VARCHAR(100),
	dob DATE,
	address VARCHAR(200),
	PRIMARY KEY (customer_id)
);

CREATE TABLE Registration(
	customer_id INTEGER NOT NULL,
	room_id INTEGER NOT NULL,
	PRIMARY KEY (customer_id, room_id),
	FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
	FOREIGN KEY (room_id) REFERENCES room(room_id)
);
-- END ANSWER 1 --

-------------------------------------------------------------------------------

/*
 * QUESTION 2 (5 points)
 * ---------------------
 *
 * Provide a brief reasoning on your selection of each one of the data types
 * above as well as your implementation of any/all integrity constraints. Include
 * any additional assumptions you made beyond the provided schema description.
 * Explain relationships and cardinalities. Type your answers as plain text
 * within the START/END tags. Expand your answer in as many lines as you need.
 *
 */

-- START ANSWER 2 --
/*
a) Primary Keys
In this schema, the data type of all primary keys, amenity_id in Amenity, building_id, 
bed_code, room_id and customer_id is integer(or int4). The advantage of setting the 
primary key to an integer type is that it is easier to set the ID for each new entry 
(just add 1 for each entry) and the query is more concise.

b) Foreign keys
In this schema, the data type of every foreign key should be the same as the reference 
column.

c) Check Constraints
The field type_of_beds in Beds is enumerable(there are only a few fixed values), so I 
use a check constraint for it.

d) Name columns
Amenity_name and coordinator in Amenity, building_name in Building, first_name and 
last_name in Customer are both names and need to use a character data type for data 
storing. The data type varchar of length 100 is sufficient.

e) Number columns
number_of_floors in Building and number_of_beds in Beds are both number, so I choose 
integer as their data type.

f) other columns
The data type of room_number in Room is varchar, because for different buildings, their 
format are usually not the same, and sometimes don't have numbers only.
The field email in Customer is a string with variable length, so its type is varchar.
The field dob in Customer is a date, so its type is Date, and there is no need to use 
timestamp type.
The field address in Customer is a bit long string, so I choose varchar of length 200.

*/

-- END ANSWER 2 --

-------------------------------------------------------------------------------

/*
 * QUESTION 3 (10 points)
 * ----------------------
 *
 * Draw the ER diagram for the schema detailed in Question 1 using the same
 * notation as in Chapter 6 of your textbook, also the notation we presented
 * in the slides and lecture for this module. Pay close attention to properly
 * defining relationship and cardinalities. You may draw the ER diagram in
 * any software you prefer, Lucidchart is recommended. Hand drawn diagrams will
 * not be accepted. Upload the ER diagram as a separate file.
 *
 */

-- No START/END tags here. Your answer is a separate PDF submitted along with
-- this SQL file.


-------------------------------------------------------------------------------
