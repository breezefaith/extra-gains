/*
 Navicat Premium Data Transfer

 Source Server         : PostgresSQL
 Source Server Type    : PostgreSQL
 Source Server Version : 100004
 Source Host           : localhost:5432
 Source Catalog        : 20200131
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100004
 File Encoding         : 65001

 Date: 01/02/2020 08:42:59
*/


-- ----------------------------
-- Table structure for capital_transactions
-- ----------------------------
DROP TABLE IF EXISTS "public"."capital_transactions";
CREATE TABLE "public"."capital_transactions" (
  "acct_no" int4 NOT NULL,
  "transaction_date_time" timestamp(8),
  "capital_payment_type" varchar(255) COLLATE "pg_catalog"."default",
  "total_loan_amount" int4,
  "remaining_balance" int4
)
;

-- ----------------------------
-- Table structure for payments_accounts
-- ----------------------------
DROP TABLE IF EXISTS "public"."payments_accounts";
CREATE TABLE "public"."payments_accounts" (
  "acct_no" int4 NOT NULL,
  "open_date" date,
  "close_date" date,
  "close_reason" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "business_name" varchar(255) COLLATE "pg_catalog"."default",
  "owner_name" varchar(255) COLLATE "pg_catalog"."default",
  "employees" int4,
  "phone_number" int4,
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "established_date" date,
  "aml_risk_rating" int4,
  "merchant_category_code" int4
)
;

-- ----------------------------
-- Records of payments_accounts
-- ----------------------------
INSERT INTO "public"."payments_accounts" VALUES (1, '2018-07-08', NULL, NULL, 'Active', 'acc1', 'own1', 100, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."payments_accounts" VALUES (2, '2018-08-23', '2020-01-01', 'Collusion', 'Closed', 'acc2', 'own1', 300, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."payments_accounts" VALUES (3, '2019-11-11', NULL, NULL, 'Active', 'acc3', 'own2', 200, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."payments_accounts" VALUES (4, '2018-07-22', NULL, NULL, 'Active', 'acc4', 'own3', 150, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for payments_transactions
-- ----------------------------
DROP TABLE IF EXISTS "public"."payments_transactions";
CREATE TABLE "public"."payments_transactions" (
  "acct_no" int4 NOT NULL,
  "transaction_date_time" timestamp(8),
  "transaction_type" varchar(255) COLLATE "pg_catalog"."default",
  "processing_network" varchar(255) COLLATE "pg_catalog"."default",
  "payment_amount" int4,
  "card_issuer_country" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of payments_transactions
-- ----------------------------
INSERT INTO "public"."payments_transactions" VALUES (2, '2018-09-11 12:00:00', 'Sale', 'Prepaid Card', 2000000, 'The United States');
INSERT INTO "public"."payments_transactions" VALUES (1, '2018-08-04 08:31:42', 'Sale', 'Prepaid Card', 1500000, 'China');

-- ----------------------------
-- Table structure for payroll_transactions
-- ----------------------------
DROP TABLE IF EXISTS "public"."payroll_transactions";
CREATE TABLE "public"."payroll_transactions" (
  "acct_no" int4 NOT NULL,
  "transaction_date_time" timestamp(8),
  "transaction_type" varchar(255) COLLATE "pg_catalog"."default",
  "payment_type" varchar(255) COLLATE "pg_catalog"."default",
  "payroll_account" int4
)
;

-- ----------------------------
-- Table structure for principal_owner
-- ----------------------------
DROP TABLE IF EXISTS "public"."principal_owner";
CREATE TABLE "public"."principal_owner" (
  "acct_no" int4 NOT NULL,
  "principal_account_holder" varchar(255) COLLATE "pg_catalog"."default",
  "date_of_birth" date,
  "ssn" int4,
  "dba" varchar(255) COLLATE "pg_catalog"."default",
  "business_address" varchar(255) COLLATE "pg_catalog"."default",
  "business_state" char(255) COLLATE "pg_catalog"."default",
  "business_zip" varchar(255) COLLATE "pg_catalog"."default",
  "business_country" char(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of principal_owner
-- ----------------------------
INSERT INTO "public"."principal_owner" VALUES (1, NULL, NULL, NULL, NULL, NULL, 'Montana                                                                                                                                                                                                                                                        ', NULL, 'The United States                                                                                                                                                                                                                                              ');
INSERT INTO "public"."principal_owner" VALUES (2, NULL, NULL, NULL, NULL, NULL, 'Montana                                                                                                                                                                                                                                                        ', NULL, 'The United States                                                                                                                                                                                                                                              ');
INSERT INTO "public"."principal_owner" VALUES (3, NULL, NULL, NULL, NULL, NULL, 'Florida                                                                                                                                                                                                                                                        ', NULL, 'The United States                                                                                                                                                                                                                                              ');
INSERT INTO "public"."principal_owner" VALUES (4, NULL, NULL, NULL, NULL, NULL, 'North Carolina                                                                                                                                                                                                                                                 ', NULL, 'The United States                                                                                                                                                                                                                                              ');

-- ----------------------------
-- Checks structure for table capital_transactions
-- ----------------------------
ALTER TABLE "public"."capital_transactions" ADD CONSTRAINT "check_capital_payment_type" CHECK (((capital_payment_type)::text = ANY ((ARRAY['Principle'::character varying, 'Interest'::character varying, 'Fee'::character varying])::text[])));

-- ----------------------------
-- Primary Key structure for table capital_transactions
-- ----------------------------
ALTER TABLE "public"."capital_transactions" ADD CONSTRAINT "capital_transactions_pkey" PRIMARY KEY ("acct_no");

-- ----------------------------
-- Checks structure for table payments_accounts
-- ----------------------------
ALTER TABLE "public"."payments_accounts" ADD CONSTRAINT "check_close_reason" CHECK (((close_reason)::text = ANY ((ARRAY['Customer Deactivated'::character varying, 'Fraud'::character varying, 'AML Violation'::character varying, 'AUP Violation'::character varying, 'Verified Identity Theft'::character varying, 'Collusion'::character varying])::text[])));
ALTER TABLE "public"."payments_accounts" ADD CONSTRAINT "check_status" CHECK (((status)::text = ANY ((ARRAY['Active'::character varying, 'Closed'::character varying, 'Pending'::character varying, 'Declined'::character varying, 'Cancelled'::character varying])::text[])));

-- ----------------------------
-- Checks structure for table payments_transactions
-- ----------------------------
ALTER TABLE "public"."payments_transactions" ADD CONSTRAINT "check_transaction_type" CHECK (((transaction_type)::text = ANY ((ARRAY['Sale'::character varying, 'Return'::character varying])::text[])));
ALTER TABLE "public"."payments_transactions" ADD CONSTRAINT "check_processing_network" CHECK (((processing_network)::text = ANY ((ARRAY['American Express'::character varying, 'Discover'::character varying, 'PayPal'::character varying, 'ApplePay'::character varying, 'Visa'::character varying, 'MasterCard'::character varying, 'Check'::character varying, 'Prepaid Card'::character varying])::text[])));

-- ----------------------------
-- Checks structure for table payroll_transactions
-- ----------------------------
ALTER TABLE "public"."payroll_transactions" ADD CONSTRAINT "check_transaction_type" CHECK (((transaction_type)::text = ANY ((ARRAY['Payroll'::character varying, 'Payroll Return'::character varying])::text[])));
ALTER TABLE "public"."payroll_transactions" ADD CONSTRAINT "check_payment_type" CHECK (((payment_type)::text = ANY ((ARRAY['Employee'::character varying, 'Contractor'::character varying])::text[])));

-- ----------------------------
-- Primary Key structure for table payroll_transactions
-- ----------------------------
ALTER TABLE "public"."payroll_transactions" ADD CONSTRAINT "payroll_transactions_pkey" PRIMARY KEY ("acct_no");
