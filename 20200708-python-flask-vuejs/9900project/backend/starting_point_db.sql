CREATE TABLE user_info(
    user_id INT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL UNIQUE,
	nick_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    history INT DEFAULT 0,
    portrait VARCHAR(255),
    follower_num INT DEFAULT 0,
    follow_num INT DEFAULT 0,
    user_tags VARCHAR(255),
    PRIMARY KEY ( `user_id` )
);

alter table user_info auto_increment=10000;


CREATE TABLE collection_list(
    collection_id INT NOT NULL AUTO_INCREMENT,
    collection_name VARCHAR(255) NOT NULL UNIQUE,
	user_id INT NOT NULL,
	collection_pics VARCHAR(255),
	collection_comments VARCHAR(255),
    PRIMARY KEY ( `collection_id` ),
	FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
);

CREATE TABLE book_info(
    book_id INT NOT NULL AUTO_INCREMENT,
    book_name VARCHAR(255) NOT NULL,
	book_pics INT NOT NULL,
	book_type VARCHAR(255),
	publisher VARCHAR(255),
	publishing_date DATE,
	book_rate FLOAT,
	read_count INT DEFAULT 0,
	author_name VARCHAR(255) NOT NULL,
	price INT NOT NULL,
	content VARCHAR(999),
    PRIMARY KEY ( `book_id` ),
);




CREATE TABLE collection_info(
    collection_id INT NOT NULL,
    book_id INT NOT NULL,
	add_date DATE,
	due_date DATE, 
	goal_state VARCHAR(2),
    PRIMARY KEY ( `collection_id`,`book_id` ),
	FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
);