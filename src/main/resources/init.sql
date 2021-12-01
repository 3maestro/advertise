USE [eltovdid]
GO

/****** Object:  Table [dbo].[user]    Script Date: 2021-11-06 오후 6:01:26 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[user](
	[user_no] [bigint] IDENTITY(1,1) NOT NULL,
	[id] [nvarchar](20) NOT NULL,
	[password] [nvarchar](100) NOT NULL,
	[name] [nvarchar](20) NULL,
 CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED 
(
	[user_no] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


USE [eltovdid]
GO

/****** Object:  Table [dbo].[role]    Script Date: 2021-11-06 오후 6:01:41 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[role](
	[role_no] [int] IDENTITY(1,1) NOT NULL,
	[role_name] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_role] PRIMARY KEY CLUSTERED 
(
	[role_no] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


USE [eltovdid]
GO

/****** Object:  Table [dbo].[user_role]    Script Date: 2021-11-06 오후 6:01:50 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[user_role](
	[user_no] [bigint] NOT NULL,
	[role_no] [int] NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [role_no] FOREIGN KEY([role_no])
REFERENCES [dbo].[role] ([role_no])
GO

ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [role_no]
GO

ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [user_no] FOREIGN KEY([user_no])
REFERENCES [dbo].[user] ([user_no])
GO

ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [user_no]
GO


