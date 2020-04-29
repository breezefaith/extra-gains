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


-- HOMEWORK ASSIGNMENT 5 

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
*
* NOTE: For Questions 1 through 4, you need to use the following relation:
*
*          online_retail_data (
*                  *StockCode*, 
*                  Description, 
*                  Quantity, 
*                  *InvoiceDate*, 
*                  UnitPrice, 
*                  Country
*          )
*
*      This data comes from the following source:
*      Online Retail Data Set
*      Dr Daqing Chen, Director: Public Analytics group. chend@lsbu.ac.uk, 
*          School of Engineering, London South Bank University, 
*          London SE1 0AA, UK.
*      https://archive.ics.uci.edu/ml/datasets/Online+Retail
*     
*      The CREATE TABLE and INSERT INTO statements for this schema are
*      provided as a separate file in the same announcement.
*
*/

/*
*
* QUESTION 1 (2 points)
* ---------------------
* Provide the SQL statement that returns the stockcode, invoice date, quantity
* and rank of quantity. Display the results in proper rank order.
* The highest quantity across all stocks should be ranked first and the lowest
* should be last. Apply normal ranking (not dense).
*
* Hint:
* Ranking can be done using the RANK function.
* Example: “Find the rank of students in the school.”
* 
* SELECT ID, student_name,
*     RANK() OVER (ORDER BY GPA DESC) AS total_rank
* FROM student_grades
* ORDER BY GPA DESC;
* 
*/

-- START ANSWER 1 --
SELECT stockcode, invoicedate, quantity, RANK() OVER (order by quantity desc) qrank FROM online_retail_data ORDER BY quantity DESC;
-- END ANSWER 1 --

-------------------------------------------------------------------------------

/*
* QUESTION 2 (2 points)
* ---------------------
* Provide the SQL statement that returns the stockcode, date, quantity
* and dense rank of quantity for each stockcode. Display the results in
* proper rank order. The highest quantity for each stockcode should be ranked 
* first and the lowest last. Sort by stockcode and rank.
* 
* 
* Hint:
* Ranking can be done within partition of the data.
* Example: “Find the rank of students within each department.”
* 
* SELECT ID, dept_name,
*     RANK() OVER (PARTITION BY dept_name ORDER BY GPA DESC) AS dept_rank
* FROM dept_grades
* ORDER BY dept_name, dept_rank;
* 
*
*/

-- START ANSWER 2 --
SELECT stockcode, invoicedate, quantity, DENSE_RANK() OVER (PARTITION BY stockcode ORDER BY quantity DESC) AS qdrank FROM online_retail_data ORDER BY stockcode ASC, quantity DESC;
-- END ANSWER 2 --

-------------------------------------------------------------------------------

/*
* QUESTION 3 (2 points)
* ---------------------
* Provide the SQL statement that returns the stockcode, date, and moving
* average of quantity on a 5 day window. Calculation must be per
* stockcode.
*
* Hint:
* Moving average can be done using PostgreSQL Windows function. 
* Check the following link for example: 
*     https://www.compose.com/articles/metrics-maven-calculating
*         -a-moving-average-in-postgresql/
* 
*   SELECT ad.date,  
*       AVG(ad.downloads)
*           OVER(ORDER BY ad.date ROWS BETWEEN 29 PRECEDING AND CURRENT ROW) 
*               AS avg_downloads
*   FROM app_downloads_by_date ad  
* 
*/

-- START ANSWER 3 --
SELECT stockcode, invoicedate, AVG(quantity) OVER(partition by stockcode ORDER BY invoicedate DESC ROWS BETWEEN 5 PRECEDING AND CURRENT ROW) AS moving_avg FROM online_retail_data ORDER BY stockcode ASC, invoicedate DESC;
-- END ANSWER 3 --

-------------------------------------------------------------------------------

/*
* QUESTION 4 (1 point)
* --------------------
* Provide the SQL statement that returns the stockcode, month, and
* monthly average quantity per stockcode. Order results by stockcode and month.
*
* Hint: Month can be calculated as EXTRACT(MONTH FROM invoice_date)
*/

-- START ANSWER 4 --
SELECT stockcode, EXTRACT(MONTH FROM cast(invoicedate as timestamp)) as month, avg(quantity) maq_per_s FROM online_retail_data WHERE 1 =1 GROUP BY stockcode, month ORDER BY stockcode, month;
-- END ANSWER 4 --

-------------------------------------------------------------------------------

/*
* QUESTION 5 (8 points)
* ---------------------
* 
* A health service clinic uses an Excel spreadsheet to track charges for 
* each customer. Each customer must have an insurance plan. Each insurance 
* plan belongs to some type of group (PPO, HMO). Each customers may have
* several services under his/her name, which has a service charge. Service 
* charge is fixed for each service. E.g., Physical Therapy will always be 
* $200 no matter who the customer is.

============================================================================
   Insurance Plan    | Group | Member |      Service     | Service charge
----------------------------------------------------------------------------
 Empire State PPO I     PPO     Mike    Physical Therapy        $200
                                        Infrared Therapy        $100
----------------------------------------------------------------------------
 Garden State PPO II    PPO     Maria   Motion Exercises        $400
                                        Joint Motion            $100
----------------------------------------------------------------------------
 NYC Freedom HMO        HMO     Erin    Acupunture              $140
----------------------------------------------------------------------------
 Empire State PPO I     PPO     Sam     Physical Therapy        $200
----------------------------------------------------------------------------

* Please use normalization process to make the spreadsheet into 1NF, 2NF, 
* and 3NF compliant relations step by step. For each NF, write down the 
* resulting tables' structure and data based on the data above. For example, 
* if you create a table called Insurance Plan, you should have such a table 
* in your answer for 3NF:

============================================================================
   Insurance Plan    | Group 
----------------------------------------------------------------------------
 Empire State PPO I     PPO     
 Garden State PPO II    PPO     
 NYC Freedom HMO        HMO     
----------------------------------------------------------------------------

*
*/

-- START ANSWER 5 --
-- 1NF
DROP TABLE IF EXISTS nf1_insurance_plan;
CREATE TABLE nf1_insurance_plan (
	insurance_plan VARCHAR(50),
	"group" VARCHAR(50),
	member VARCHAR(50),
	service VARCHAR(50),
	service_charge DECIMAL(10,2),
	PRIMARY KEY (insurance_plan, member, service)
);

INSERT INTO nf1_insurance_plan(insurance_plan, "group", member, service, service_charge) VALUES('Empire State PPO I','PPO','Mike','Physical Therapy','200');
INSERT INTO nf1_insurance_plan(insurance_plan, "group", member, service, service_charge) VALUES('Empire State PPO I','PPO','Mike','Infrared Therapy','100');
INSERT INTO nf1_insurance_plan(insurance_plan, "group", member, service, service_charge) VALUES('Garden State PPO II','PPO','Maria','Motion Exercises','400');
INSERT INTO nf1_insurance_plan(insurance_plan, "group", member, service, service_charge) VALUES('Garden State PPO II','PPO','Maria','Joint Motion','100');
INSERT INTO nf1_insurance_plan(insurance_plan, "group", member, service, service_charge) VALUES('NYC Freedom HMO','HMO','Erin','Acupunture','140');
INSERT INTO nf1_insurance_plan(insurance_plan, "group", member, service, service_charge) VALUES('Empire State PPO I','PPO','Sam','Physical Therapy','200');

-- 2NF
DROP TABLE IF EXISTS nf2_insurance_plan_group;
DROP TABLE IF EXISTS nf2_service_charge;
DROP TABLE IF EXISTS nf2_member_ins_service;

CREATE TABLE nf2_insurance_plan_group(
	insurance_plan VARCHAR(50),
	"group" VARCHAR(50),
	PRIMARY KEY (insurance_plan)
);

CREATE TABLE nf2_service_charge(
	service VARCHAR(50),
	service_charge DECIMAL(10,2),
	PRIMARY KEY (service)
);

CREATE TABLE nf2_member_ins_service(
	member VARCHAR(50),
	insurance_plan VARCHAR(50),
	service VARCHAR(50)
);

INSERT INTO nf2_insurance_plan_group VALUES('Empire State PPO I','PPO');
INSERT INTO nf2_insurance_plan_group VALUES('Garden State PPO II','PPO');
INSERT INTO nf2_insurance_plan_group VALUES('NYC Freedom HMO','HMO');

INSERT INTO nf2_service_charge VALUES('Physical Therapy','200');
INSERT INTO nf2_service_charge VALUES('Infrared Therapy','100');
INSERT INTO nf2_service_charge VALUES('Motion Exercises','400');
INSERT INTO nf2_service_charge VALUES('Joint Motion','100');
INSERT INTO nf2_service_charge VALUES('Acupunture','140');

INSERT INTO nf2_member_ins_service VALUES('Mike','Empire State PPO I','Physical Therapy');
INSERT INTO nf2_member_ins_service VALUES('Mike','Empire State PPO I','Infrared Therapy');
INSERT INTO nf2_member_ins_service VALUES('Maria','Garden State PPO II','Motion Exercises');
INSERT INTO nf2_member_ins_service VALUES('Maria','Garden State PPO II','Joint Motion');
INSERT INTO nf2_member_ins_service VALUES('Erin','NYC Freedom HMO','Acupunture');
INSERT INTO nf2_member_ins_service VALUES('Sam','Empire State PPO I','Physical Therapy');

-- 3NF
DROP TABLE IF EXISTS nf3_insurance_plan_group;
DROP TABLE IF EXISTS nf3_service_charge;
DROP TABLE IF EXISTS nf3_member_ins_service;

CREATE TABLE nf3_insurance_plan_group(
	insurance_plan VARCHAR(50),
	"group" VARCHAR(50),
	PRIMARY KEY (insurance_plan)
);

CREATE TABLE nf3_service_charge(
	service VARCHAR(50),
	service_charge DECIMAL(10,2),
	PRIMARY KEY (service)
);

CREATE TABLE nf3_member_ins_service(
	member VARCHAR(50),
	insurance_plan VARCHAR(50),
	service VARCHAR(50)
);

INSERT INTO nf3_insurance_plan_group VALUES('Empire State PPO I','PPO');
INSERT INTO nf3_insurance_plan_group VALUES('Garden State PPO II','PPO');
INSERT INTO nf3_insurance_plan_group VALUES('NYC Freedom HMO','HMO');

INSERT INTO nf3_service_charge VALUES('Physical Therapy','200');
INSERT INTO nf3_service_charge VALUES('Infrared Therapy','100');
INSERT INTO nf3_service_charge VALUES('Motion Exercises','400');
INSERT INTO nf3_service_charge VALUES('Joint Motion','100');
INSERT INTO nf3_service_charge VALUES('Acupunture','140');

INSERT INTO nf3_member_ins_service VALUES('Mike','Empire State PPO I','Physical Therapy');
INSERT INTO nf3_member_ins_service VALUES('Mike','Empire State PPO I','Infrared Therapy');
INSERT INTO nf3_member_ins_service VALUES('Maria','Garden State PPO II','Motion Exercises');
INSERT INTO nf3_member_ins_service VALUES('Maria','Garden State PPO II','Joint Motion');
INSERT INTO nf3_member_ins_service VALUES('Erin','NYC Freedom HMO','Acupunture');
INSERT INTO nf3_member_ins_service VALUES('Sam','Empire State PPO I','Physical Therapy');
-- END ANSWER 5 --
