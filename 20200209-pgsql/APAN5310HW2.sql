
/*
 *  NOTES:
 *
 *    - Type your SQL statements between the START and END tags for each
 *      question, as shown in the example. Do not alter this template .sql file
 *      in any other way other than adding your answers. Do not delete the
 *      START/END tags. The .sql file you submit will be validated before
 *      grading and will not be graded if it fails validation due to any
 *      alteration of the commented sections.
 *
 *    - Our course is using PostgreSQL which has been preinstalled for you in
 *      Codio. We grade your assignments in PostgreSQL. You risk losing points
 *      if you prepare your SQL queries for a different database system
 *      (MySQL, MS SQL Server, Oracle, etc).
 *
 *    - It is highly recommended that you insert additional, appropriate data
 *      to test the results of your queries. You do not need to include your
 *      sample data in your answers.
 *
 *    - Make sure you test each one of your answers. If a query returns an
 *      error it will earn no points.
 *
 */


/*
 * EXAMPLE
 * -------
 *
 * Provide the SQL statement that returns all attributes and tuples from
 * a relation named "table1".
 *
 */

-- START EXAMPLE --

SELECT * FROM table1;

-- END EXAMPLE --



-------------------------------------------------------------------------------



/*
 * QUESTION 1 (1 point)
 * --------------------
 *
 * Provide the SQL statement that creates a table named "products" with the
 * following attributes:
 *
 *   - "product_id" : fixed length character string of length 10
 *   - "product_name" : variable length character string of length 100
 *   - "category" : variable length character string of length 30
 *   - "price" : fixed point number with 10 digits, 2 decimals
 *   - "cost"  : fixed point number with 10 digits, 2 decimals
 *   - "description" : variable length character string of maximum length
 *   - "weight" : fixed point number with 7 digits, 1 decimal (stored in ounces)
 *   - "date_listed" : timestamp without time zone
 *   - "last_update" : timestamp without time zone
 *
 * Do not implement integrity constraints.
 *
 */

-- START ANSWER 1 --

DROP TABLE IF EXISTS products;

CREATE TABLE products (
	product_id CHAR(10),
	product_name VARCHAR(100),
	category VARCHAR(30),
	price NUMERIC(10,2),
	cost NUMERIC(10,2),
	description VARCHAR,
	weight NUMERIC(7,1),
	date_listed TIMESTAMP,
	last_update TIMESTAMP
);


-- END ANSWER 1 --

-------------------------------------------------------------------------------

/*
 * QUESTION 2 (1 point)
 * --------------------
 *
 * Provide the SQL statement that populates the "products" table with values as
 * shown in the following table. (Note: try to run this in one statement, not
 * multiple INSERT statements)
 *
 * +-----------+-----------------------------------------+---------------------------+-------+-------------------------------------------------------------------------------------------------------------------
 * | product_id| product_name                            | category                  | price | cost		|description                                   | weight 	 | date_listed         | last update         |
 * +-----------+-----------------------------------------+---------------------------+-------+--------------------------------------------------------------------------------------------------------------------
 * |           |                                         |                           |       | 			|  Indulge in our dark chocolate ice cream 	   | 16          | 2016-02-20 09:12:00 | 2020-01-12 15:23:00 |
 * |           |                                         |                           |       | 			|	with chocolatey chips swirled perfectly    |             |                     |                     |
 * | ICE992	   |Breyers Original Ice Cream Chocolate Mint| Icecream       	         | 5.52  | 4.30		|	with mint ice cream.    				   |             |                     |                     |
 * |           |                                         |                           |       | 			|											   |             |                     |                     |
 * |           |                                         |                           |       | 	        |                                              |             |                     |
 * +-----------+-----------------------------------------+---------------------------+-------+-------------------------------------------------------------------------------------------------------------------
 * |           |                                         |                           |       | 			| Friendlys has a rich heritage of deliciously| 14          | 2015-12-01 07:34:50 | 2019-12-01 07:34:50 |
 * |           |                                         |                           |       | 			|  creamy ice cream with quality ingredients   |             |                     |                     |
 * | ICE237    | Friendlys Black Raspberry Ice Cream	 | Icecream                  | 3.99  | 	3.50	|   										   |             |                     |                     |
 * |           |                                         |                           |       | 			|   										   |             |                     |                     |
 * |           |                                         |                           |       | 			|  											   |             |                     |                     |
 * |           |                                         |                           |       | 			|             								   |             |                     |                     |
 * +-----------+-----------------------------------------+---------------------------+-------+-------------------------------------------------------------------------------------------------------------------
 * | FRY509    | Ben & Jerry s Greek Blueberry Vanilla   | Frozen Yogurt 			 | 5.79  |  4.48    |                                              | 10          | 2019-04-15 15:05:23 |                     |
 * +		   |Graham Frozen Yogurt 
    -----------+-----------------------------------------+---------------------------+-------+--------------------------------------------------------------------------------------------------------------------
 *
 */

-- START ANSWER 2 --

DELETE FROM products WHERE 1=1;

INSERT INTO products(product_id, product_name, category, price, cost, description, weight, date_listed, last_update) VALUES 
(null, null, null, null, null, 'Indulge in our dark chocolate ice cream', '16', '2016-02-20 09:12:00', '2020-01-12 15:23:00'),
(null, null, null, null, null, 'with chocolatey chips swirled perfectly', null, null, null),
('ICE992', 'Breyers Original Ice Cream Chocolate Mint', 'Icecream', '5.52', '4.30', 'with mint ice cream.', null, null, null),
(null, null, null, null, null, null, null, null, null),
(null, null, null, null, null, null, null, null, null),
(null, null, null, null, null, 'Friendlys has a rich heritage of deliciously', '14', '2015-12-01 07:34:50', '2019-12-01 07:34:50'),
(null, null, null, null, null, 'creamy ice cream with quality ingredients', null, null, null),
('ICE237', 'Friendlys Black Raspberry Ice Cream', 'Icecream', '3.99', '3.50', null, null, null, null),
(null, null, null, null, null, null, null, null, null),
(null, null, null, null, null, null, null, null, null),
(null, null, null, null, null, null, null, null, null),
('FRY509', 'Ben & Jerry s Greek Blueberry Vanilla', 'Frozen Yogurt', '5.79', '4.48', null, '10', '2019-04-15 15:05:23', null),
(null, 'Graham Frozen Yogurt', null, null, null, null, null, null, null);

-- END ANSWER 2 --

-------------------------------------------------------------------------------

/*
 * QUESTION 3 (1 point)
 * --------------------
 *
 * Provide the SQL statement that returns all attributes and tuples from table
 * "products", of category "Icecream" with price less than $4.00.
 *
 */

-- START ANSWER 3 --

SELECT * FROM products WHERE category = 'Icecream' AND price < '4.00';

-- END ANSWER 3 --

-------------------------------------------------------------------------------

/*
 * QUESTION 4 (1 point)
 * --------------------
 *
 * Remember that the "weight" attribute stores values in ounces. Provide
 * the SQL statement that returns the product_id, product_name, category, price, and lastly
 * weight in grams (note: 1 ounce = 28.3495 grams). Rename weight
 * to weight_grams.
 *
 */

-- START ANSWER 4 --

SELECT product_id, product_name, category, price, (28.3495 * weight) weight_grams FROM products;

-- END ANSWER 4 --

-------------------------------------------------------------------------------

/*
 * QUESTION 5 (1 point)
 * --------------------
 *
 * Provide the SQL statement that returns the "product_name" and "price" attributes
 * of all "Icecream" products that contain the word "chocolate" anywhere in
 * their desription.
 *
 */

-- START ANSWER 5 --

SELECT "product_name", "price" FROM products WHERE category = 'Icecream' AND description LIKE '%chocolate%';

-- END ANSWER 5 --

-------------------------------------------------------------------------------

/*
 * QUESTION 6 (1 point)
 * --------------------
 *
 * Provide the SQL statement that creates a relation named "supplier" with
 * attributes "supplier_id" (integer), supplier_name (variable length character string of length 30), "product_id" (character string of length 6), "country" (variable length character string of length 30).
 * Do not implement integrity constraints.
 *
 */

-- START ANSWER 6 --

DROP TABLE IF EXISTS supplier;

CREATE TABLE supplier (
	supplier_id INTEGER,
	supplier_name VARCHAR(30),
	product_id CHAR(6),
	country VARCHAR(30)
);

-- END ANSWER 6 --


-------------------------------------------------------------------------------

/*
You may use data similar to this, for the supplier table, and/or more data

1	"supplier-1"	"ICE992"	"USA"
1	"supplier-1"	"FRY509"	"USA"
1	"supplier-1"	"FRY509"	"UK"
2	"supplier-2"	"ICE237"	"UK"
2	"supplier-2"	"ICE237"	"USA"
*/

INSERT INTO supplier(supplier_id, supplier_name, product_id, country) VALUES
(1, 'supplier-1', 'ICE992', 'USA'),
(2, 'supplier-1', 'FRY509', 'USA'),
(3, 'supplier-1', 'FRY509', 'UK'),
(4, 'supplier-2', 'ICE237', 'UK'),
(5, 'supplier-2', 'ICE237', 'USA');
-------------------------------------------------------------------------------

/*
 * QUESTION 7 (1 point)
 * --------------------
 *
 * Provide the SQL statement that alters relation "products" adding an attribute
 * called "supplier_id" (integer).
 *
 */

-- START ANSWER 7 --

ALTER TABLE products ADD COLUMN supplier_id INTEGER;


-- END ANSWER 7 --

-------------------------------------------------------------------------------

/*
You may update supplier_id column values of the product table such that the
supplier_id exist in the supplier table.

*/
UPDATE products SET supplier_id = 2 WHERE product_id = 'FRY509';
UPDATE products SET supplier_id = 4 WHERE product_id = 'ICE237';
-------------------------------------------------------------------------------

/*
 * QUESTION 8 (1 point)
 * --------------------
 *
 * Provide the SQL statement that returns the product name with the maximum
 * price of "Icecream" products. (note: for this and the
 * following questions, it would be good do insert additional data in order to
 * test your answers)
 *
 */

-- START ANSWER 8 --

SELECT product_name FROM products WHERE price = (SELECT max(price) FROM products);


-- END ANSWER 8 --

-------------------------------------------------------------------------------

/*
 * QUESTION 9 (1 point)
 * --------------------
 *
 * Provide the SQL statement that returns the category and average price per
 * product category, average profit margin (price-cost) of all products listed after January 1st, 2018.
 * (hint: use GROUP BY)
 *
 */

-- START ANSWER 9 --

SELECT category, AVG(price) avg_price, AVG(price-cost) avg_profit FROM products WHERE date_listed > '2018-01-01'::date GROUP BY category;

-- END ANSWER 9 --

-------------------------------------------------------------------------------

/*
 * QUESTION 10 (1 point)
 * ---------------------
 *
 * Provide the SQL statement that returns the /product_id, product_name, category, price and
 * supplier name of all products supplied by suppliers from the "USA".
 * (Hint: we are not implementing joins yet, use the cartesian product. Also,
 * assume that the "supplier_id" in the "supplier" relation can correlate with the
 * new "supplier_id" attribute of the "products" relation).
 *
 */

-- START ANSWER 10 --

SELECT products.product_id, products.product_name, products.category, products.price, supplier.supplier_name FROM products, supplier WHERE products.supplier_id = supplier.supplier_id AND supplier.country='USA';

-- END ANSWER 10 --

-------------------------------------------------------------------------------

/*
 * QUESTION 11 (1 point)
 * ---------------------
 *
 * Provide the SQL statement that returns the product name, category, price,
 * weight and supplier country of all products. Display the results by
 * descending weight and date_listed. (Hint: same as Q10)
 *
 */

-- START ANSWER 11 --

SELECT products.product_name, products.category, products.price, products.weight, supplier.country FROM products, supplier WHERE products.supplier_id = supplier.supplier_id ORDER BY weight DESC, date_listed DESC;


-- END ANSWER 11 --

-------------------------------------------------------------------------------

/*
 * QUESTION 12 (1 point)
 * ---------------------
 *
 * Provide the SQL statement that returns the category, supplier_id, supplier_name and total
 * number of products, across the countries, for each category/supplier_id combination.
 *
 */

-- START ANSWER 12 --

SELECT products.category, supplier.supplier_id,supplier.supplier_name, COUNT(*) FROM products, supplier WHERE products.supplier_id = supplier.supplier_id GROUP BY products.category, supplier.supplier_id;


-- END ANSWER 12 --

-------------------------------------------------------------------------------

/*
 * QUESTION 13 (1 point)
 * ---------------------
 * Provide a single SQL statement that returns all supplier_id, supplier_name and 
 * the total profit per weight ratio of all the products they sell, across the product
 * categories and across the countries. Formula: profit = price - cost
 
 e.g. result (result may vary depending on the data you entered)
 1	"supplier-1"	"2.38877192982456140350"
 2	"supplier-2"	"0.42608695652173913044"
 */
 
-- START ANSWER 13 --

SELECT t1.supplier_id, su.supplier_name, t1.avg_profit FROM (SELECT supplier.supplier_id, SUM(products.price-products."cost")/SUM(weight) avg_profit FROM products, supplier WHERE products.supplier_id = supplier.supplier_id GROUP BY supplier.supplier_id) t1, supplier su WHERE t1.supplier_id = su.supplier_id; 

-- END ANSWER 13 --

-------------------------------------------------------------------------------

/*
 * QUESTION 14 (1 point)
 * ---------------------
 *
 * Provide the SQL statement that deletes all tuples from relation "products"
 * of products listed prior to 2016.
 *
 */

-- START ANSWER 14 --

DELETE FROM products WHERE to_char(date_listed, 'YYYY') < '2016';

-- END ANSWER 14 --

-------------------------------------------------------------------------------

/*
 * QUESTION 15 (1 point)
 * ---------------------
 *
 * Provide the SQL statement that removes the "supplier" relation from the
 * database.
 *
 */

-- START ANSWER 15 --

DROP TABLE supplier;

-- END ANSWER 15 --

------------------------------------------------------------------------------

