﻿CREATE TABLE [user](
	[user_no] [BIGINT] IDENTITY(1,1) NOT NULL,
	[id] [nvarchar](20) NOT NULL,
	[password] [nvarchar](100) NOT NULL,
	[name] [nvarchar](20) NULL,
 CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED 
(
	[user_no] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
CREATE TABLE [role](
	[role_no] [int] IDENTITY(1,1) NOT NULL,
	[role_name] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_role] PRIMARY KEY CLUSTERED 
(
	[role_no] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
CREATE TABLE [user_role](
	user_no BIGINT NOT NULL,
	role_no INT NOT NULL,
	CONSTRAINT user_no FOREIGN KEY (user_no) REFERENCES dbo.[user](user_no),
	CONSTRAINT role_no FOREIGN KEY (role_no) REFERENCES dbo.[role](role_no)	
) ON [PRIMARY]
