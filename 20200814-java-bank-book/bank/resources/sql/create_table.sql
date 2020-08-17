CREATE TABLE "BRANCHES" (
  "BRANCH_NUM" VARCHAR2(255 BYTE) VISIBLE NOT NULL,
  "branch_name" VARCHAR2(255 BYTE) VISIBLE,
  "address" VARCHAR2(255 BYTE) VISIBLE
);

-- ----------------------------
-- Table structure for CHECKING_ACCOUNTS
-- ----------------------------
CREATE TABLE "CHECKING_ACCOUNTS" (
  "ACCOUNT_NUM" VARCHAR2(255 BYTE) VISIBLE NOT NULL,
  "branch_num" VARCHAR2(255 BYTE) VISIBLE,
  "date_opened" DATE VISIBLE,
  "balance" NUMBER(38,2) VISIBLE,
  "overdraft_amount" NUMBER(38,2) VISIBLE,
  "check_limit" NUMBER(38,2) VISIBLE
);

-- ----------------------------
-- Table structure for CUSTOMERS
-- ----------------------------

CREATE TABLE "CUSTOMERS" (
  "CUSTOMER_NUM" VARCHAR2(255 BYTE) VISIBLE NOT NULL,
  "name" VARCHAR2(255 BYTE) VISIBLE,
  "address" VARCHAR2(255 BYTE) VISIBLE,
  "phone" VARCHAR2(255 BYTE) VISIBLE
);

-- ----------------------------
-- Table structure for HAS_ACCOUNT
-- ----------------------------

CREATE TABLE "HAS_ACCOUNT" (
  "CUSTOMER_NUM" VARCHAR2(255 BYTE) VISIBLE NOT NULL,
  "ACCOUNT_TYPE" NUMBER VISIBLE NOT NULL,
  "ACCOUNT_NUM" VARCHAR2(255 BYTE) VISIBLE NOT NULL
);

-- ----------------------------
-- Table structure for LOAN_ACCOUNTS
-- ----------------------------

CREATE TABLE "LOAN_ACCOUNTS" (
  "ACCOUNT_NUM" VARCHAR2(255 BYTE) VISIBLE NOT NULL,
  "branch_num" VARCHAR2(255 BYTE) VISIBLE,
  "date_opened" DATE VISIBLE,
  "loan_type" NUMBER VISIBLE,
  "interest_rate" NUMBER(38,2) VISIBLE
);

-- ----------------------------
-- Table structure for SAVINGS_ACCOUNTS
-- ----------------------------

CREATE TABLE "SAVINGS_ACCOUNTS" (
  "ACCOUNT_NUM" VARCHAR2(255 BYTE) VISIBLE NOT NULL,
  "branch_num" VARCHAR2(255 BYTE) VISIBLE,
  "date_opened" DATE VISIBLE,
  "balance" NUMBER(38,2) VISIBLE,
  "interest_rate" NUMBER(38,2) VISIBLE
);

-- ----------------------------
-- Table structure for TRANSACTIONS
-- ----------------------------

CREATE TABLE "TRANSACTIONS" (
  "ACCOUNT_TYPE" NUMBER VISIBLE NOT NULL,
  "ACCOUNT_NUM" VARCHAR2(255 BYTE) VISIBLE NOT NULL,
  "TRANS_DATE" DATE VISIBLE NOT NULL,
  "TRANS_AMT" NUMBER(38,2) VISIBLE NOT NULL,
  "TRANS_TYPE" NUMBER VISIBLE NOT NULL,
  "trans_comments" VARCHAR2(255 BYTE) VISIBLE
);

CREATE TRIGGER "BEFORE_ADD_CHECKING_ACCOUNT" BEFORE INSERT ON "CHECKING_ACCOUNTS" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW
BEGIN
  :NEW."date_opened" := sysdate;
END;
/

CREATE TRIGGER "BEFORE_ADD_LOAN_ACCOUNTS" BEFORE INSERT ON "LOAN_ACCOUNTS" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW
BEGIN
  :NEW."date_opened" := sysdate;
END;
/

CREATE TRIGGER "BEFORE_ADD_SAVINGS_ACCOUNTS" BEFORE INSERT ON "SAVINGS_ACCOUNTS" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW
BEGIN
  :NEW."date_opened" := sysdate;
END;
/

-- ----------------------------
-- Primary Key structure for table BRANCHES
-- ----------------------------
ALTER TABLE "BRANCHES" ADD PRIMARY KEY ("BRANCH_NUM");

-- ----------------------------
-- Primary Key structure for table CHECKING_ACCOUNTS
-- ----------------------------
ALTER TABLE "CHECKING_ACCOUNTS" ADD PRIMARY KEY ("ACCOUNT_NUM");

-- ----------------------------
-- Primary Key structure for table CUSTOMERS
-- ----------------------------
ALTER TABLE "CUSTOMERS" ADD PRIMARY KEY ("CUSTOMER_NUM");

-- ----------------------------
-- Primary Key structure for table HAS_ACCOUNT
-- ----------------------------
ALTER TABLE "HAS_ACCOUNT" ADD PRIMARY KEY ("CUSTOMER_NUM", "ACCOUNT_TYPE", "ACCOUNT_NUM");

-- ----------------------------
-- Primary Key structure for table LOAN_ACCOUNTS
-- ----------------------------
ALTER TABLE "LOAN_ACCOUNTS" ADD PRIMARY KEY ("ACCOUNT_NUM");

-- ----------------------------
-- Primary Key structure for table SAVINGS_ACCOUNTS
-- ----------------------------
ALTER TABLE "SAVINGS_ACCOUNTS" ADD PRIMARY KEY ("ACCOUNT_NUM");

-- ----------------------------
-- Primary Key structure for table TRANSACTIONS
-- ----------------------------
ALTER TABLE "TRANSACTIONS" ADD PRIMARY KEY ("ACCOUNT_TYPE", "ACCOUNT_NUM", "TRANS_DATE", "TRANS_AMT", "TRANS_TYPE");

-- ----------------------------
-- Foreign Keys structure for table CHECKING_ACCOUNTS
-- ----------------------------
ALTER TABLE "CHECKING_ACCOUNTS" ADD FOREIGN KEY ("branch_num") REFERENCES "BRANCHES" ("BRANCH_NUM") NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Foreign Keys structure for table HAS_ACCOUNT
-- ----------------------------
ALTER TABLE "HAS_ACCOUNT" ADD FOREIGN KEY ("CUSTOMER_NUM") REFERENCES "CUSTOMERS" ("CUSTOMER_NUM") NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Foreign Keys structure for table LOAN_ACCOUNTS
-- ----------------------------
ALTER TABLE "LOAN_ACCOUNTS" ADD FOREIGN KEY ("branch_num") REFERENCES "BRANCHES" ("BRANCH_NUM") NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Foreign Keys structure for table SAVINGS_ACCOUNTS
-- ----------------------------
ALTER TABLE "SAVINGS_ACCOUNTS" ADD FOREIGN KEY ("branch_num") REFERENCES "BRANCHES" ("BRANCH_NUM") NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
