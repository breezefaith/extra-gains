-- ----------------------------
-- Table structure for employee
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[employees].[employee]') AND type IN ('U'))
	DROP TABLE [employees].[employee]
GO

CREATE TABLE [employees].[employee] (
  [ID] varchar(32) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [Login] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [Password] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [FirstName] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [LastName] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [employees].[employee] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO [employees].[employee]  VALUES (N'user1', N'user1', N'aaaaaa', N'user', N'first')
GO

INSERT INTO [employees].[employee]  VALUES (N'user2', N'user2', N'aaaaaa', N'user', N'second')
GO


-- ----------------------------
-- Primary Key structure for table employee
-- ----------------------------
ALTER TABLE [employees].[employee] ADD CONSTRAINT [PK__employee__3214EC27E6AC1751] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

