-- APAN 5310: SQL & RELATIONAL DATABASES SUMMER 2019
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

-- SOLUTION SET FOR HOMEWORK ASSIGNMENT 4

/*
 *  NOTES:

 *    - Type your SQL statements between the START and END tags for each
 *      question, as shown in the example. Do not alter this template .sql file
 *      in any other way other than adding your answers. Do not delete the
 *      START/END tags. The .sql file you submit will be validated before
 *      grading and will not be graded if it fails validation due to any
 *      alteration of the commented sections.
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
 */
-------------------------------------------------------------------------------

/*
 * QUESTION 1 (35 points) 
 * (15 point: Correct tables script, correct data types, correct Primary Keys;
 * (13 point: Correct relationships between tables, correct columns added for relations, correct Foreign Keys;
 * (7 point: Create related data and insert statement for the following:
 * 	At least 2 CATEGORIES; At least 5 PRODUCTS; At least 3 CUSTOMERS; 
 * 	At least 2 CARTs for each CUSTOMERs, At least 2 ITEMS (i.e. PRODUCTS) in each CART
 * ---------------------------------------------------------------------------------------------------------------
 *
 * Imagine you have an online store as a small business. You manage product inventory in various categories, keep track of your customers, 
 * and also track who is buying or interested in what product through online cart. Design a database, Understand the Shopping Cart schema described below, and
 * Type the CREATE TABLE and FOREIGN KEY creation statements in the correct order such that executing all the sql
 * in Answer 1 at the same time cause no error in PostgreSQL, and all tables and relations are created successfully. 
 * AND, provide 'insert' statements in correct order of execution for:
 * 	At least 2 CATEGORIES; At least 5 PRODUCTS; At least 3 CUSTOMERS; 
 * 	At least 2 CARTs for each CUSTOMERs, At least 2 ITEMS (i.e. PRODUCTS) in each CART
 *
 * Make sure to add (in the sql script) PRIMARY keys and FOREIGN_KEY to the tables, to establish relationships between tables
 * considering following scenario.
 * Add more columns (e.g. IDs) in any table if required to establish table relations
 *
 * Expand the space between the START/END tags to fit all of your CREATE TABLE statements.  
 *
 *
 * Schema Description:
 * -------------------
 *
 * Shpping Cart
 * 
 * Tables: Product, Product Category, Customer, Cart and Items (i.e. Products) in the cart
 *
 * ALL the columns in ALL the tables are NOT allowed to be empty in any record, except for the DESCRIPTION columns that can be empty
 *
 * CUSTOMER (CUSTOMER_ID number, CUSTOMER_NAME string, REGISTRATION_DATE date)
 *	Primary Key: What?
 * CATEGORY (CATEGORY_ID number, CATEGORY_NAME string, CATEGORY_DESCRIPTION long string)
 *	Primary Key: What?
 * PRODUCT (PRODUCT_ID number, PRODUCT_NAME string, PRODUCT_DESCRIPTION long string, PRICE number with two decimal, STOCK_QTY number, LAST_UPDATED date)
 *	Primary Key: What?
 * CART (CART_ID number, CUSTOMER_ID number, LAST_UPDATED date)
 *	Primary Key: What?
 * CART_ITEM (QTY number, LAST_UPDATED date)
 *	Primary Key: What column(s)?
 *
 * CART:
 	* Each CART must be uniquely identified
 	* Each CART belong to only one CUSTOMER
	* Each CART can have multiple ITEMS (i.e. PRODUCTS)
 * CUSTOMER:
	* Each CUSTOMER must be uniquely identified 
	* CUSTOMER can create a CART, can add more than one ITEM (i.e. PRODUCT) to the CART, or can leave the CART empty, 
	* or can create more CARTs any number of times
 * CATEGORY:
	* Each CATEGORY must be uniquely identified
 * PRODUCT:
	* Each PRODUCT must be uniquely identified
	* Each PRODUCT must be of one CATEGORY
 * CART_ITEM
	* Each CART_ITEM must belong to a CART
	* Each CART_ITEM is a PRODUCT
	* A CART can not have same ITEM (i.e. PRODUCT) multiple times as a record/row. 
	* When ITEM is added multiple time to the cart, CART_ITEM's QTY column value is incremented
 * 
 */


-- START ANSWER 1 --



-- END ANSWER 1 --

-------------------------------------------------------------------------------



/*

 * QUESTION 2 (5 points)
 * ---------------------
 *
* Provide brief reasoning on your selection of the table relations. 
* Include any additional assumptions you made beyond the provided schema 
* description.
* Type your answers (plain text) within the START/END tags. Expand your 
* answers in as many lines as you need.
*

*/



-- START ANSWER 2 --



-- END ANSWER 2 --

-------------------------------------------------------------------------------



/*

 * QUESTION 3 (10 points)

 * ---------------------
 *
 * Draw the ER diagram for the schema detailed in Question 1 using 
 * textbook notation. Pay close attention to properly defining relationship 
 * and cardinalities. You may draw the ER diagram in any software you prefer,
 * Lucidchart is recommended. Hand drawn diagrams will not be accepted. 
 * Upload the ER diagram as a separate file.
 *

*/


-- No START/END tags here. Your answer is a separate PDF submitted along 
-- with this SQL file and the PDF file for the diagram in Question 3.

-------------------------------------------------------------------------------


/*

* QUESTION 4 (10 points)
 * (correct/error-free view creation, and correct output gets full point, regardless of how the select is written)
 * ---------------------
 *
 * Provide the SQL statement that creates a view called "forMarketing_CustCateg". 
 * Your marketing department should be able to use this view for targed ad
 * based on who is buying (or interested in) what category of items 
 * (hint: items in the carts will provide that information)
 * Provide only Customer ID, Customer Name, and Item Category columns as a result. 
 * Assume that your marketing department has each customer's contact details in seperate tables that are not accessible/visible to you
 *
 * IMPORTANT NOTE: This question must be answered with a single SQL query. If
 *                your answer involves more than one query, it will be marked
 *                 incorrect even if the output is appropriate.
 *

*/


-- START ANSWER 4 --


-- END ANSWER 4 --

-------------------------------------------------------------------------------

/*
 * QUESTION 5 (10 points)
 *  (2 point: correct select statement in the function; 3 point: correct function body/syntax regarless of select statement logic; 
 *	 3 point: sufficient attempt in right direction (return type, parameter, logic, select statements etc.)
 *   10 points: everything correct, no errors, and perfect result)
 * ---------------------
 *
 * Write a script to create a function inventory_check (P_ID int, n int), returns integer such that 
 * for given PRODUCT_ID, it returns n, if n number of items can still be added to the cart (hint: compares product stock quantity against total number of item in carts) 
 * if n number of items can't be added to the cart, then the function returns the correct number of items that can be added to the cart
 * 
 *
*/


-- START ANSWER 5 --




-- END ANSWER 5 --

-------------------------------------------------------------------------------

/*
 * QUESTION 6 (5 point)
 * --------------------
 *
 * Provide two seperate insert sql statements that uses the function (inventory_check (P_ID int, n int)) you created in Question 7  
 * First statement: insert small quantity into the CART_ITEM, such that your function would allow the same quantity to be inserted 
 * Second statement: try to insert large quantity into the CART_ITEM, but your function should limit the entry to not exceed the product stock
 * 
 * Use same PRODUCT_ID for both insert. Use no more sql then two insert statements to achieve this.
*/

									 
-- START ANSWER 6 –

-- END ANSWER 6 --


-------------------------------------------------------------------------------
																 
/*

 * QUESTION 7 (13 points)
 *  (3 point: successful creation of complete trigger using your script in correct sequence of objects
	  5 point: correct logic and correct use of objects even though minor problems
      	  2 point: correct example 'insert' and 'select' statements to show correctly working of the trigger and it's logic, with your test data
	  full points: if everything is perfect)
 *
 *
 *  Provide all necessary SQL statements that create necessary objects to crate a trigger on CART_ITEM table.   
 *  The trigger should result into following behavior while 'inserting' any new record in the CART_ITEM table
 *  If the insert statement tries to insert LAST_UPDATED date anything other than the current date, 
 *  then the trigger should automatically change the LAST_UPDATED to the current date
 *  Note the trigger should not generate any error but as an end result, the inserted value for LAST_UPDATED in the CART_ITEM table should be the current date
 															
 */


-- START ANSWER 7 –
																 



															
-- END ANSWER 7 --

							
-------------------------------------------------------------------------------


/*

 * QUESTION 8 (12 points)
 *
 *  Provide all necessary SQL statements that create a stored proccedure "CleanUp_Cart(dt)".   
 *  The stored procedure should remove all the cart entries that were created or updated before supplied date (dt)
 *  AND, write a sql statement to execute that stored procedure with some date such that it deletes a few records from the CART_ITEM table															
*/


-- START ANSWER 8 –

															
-- END ANSWER 8 --

							
-------------------------------------------------------------------------------


/*

 * QUESTION 9 (5 points) 
 *	(3 points for complete and correct statements; 2 point for the right sequence of execution)
 * ---------------------
 * Provide SQL statements, in right sequence to drop all the objects you have created in this assignment 
 *  (i.e. tables, views, triggers, stored procedures and functions).
 *
																 
*/
																 
-- START ANSWER 9 –
										 
																						 
																 
-- START ANSWER 9 –


-------------------------------------------------------------------------------

