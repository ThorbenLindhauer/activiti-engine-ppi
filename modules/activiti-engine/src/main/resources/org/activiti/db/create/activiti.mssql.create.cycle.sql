create table ACT_CY_CONN_CONFIG (
	ID_ nvarchar(255),
	PLUGIN_ID_ nvarchar(255),
	INSTANCE_NAME_ nvarchar(255),
	INSTANCE_ID_ nvarchar(255),
	USER_ nvarchar(255),
	GROUP_ nvarchar(255),
	VALUES_ nvarchar(1024),
	primary key (ID_)
);

create table ACT_CY_CONFIG (
	ID_ nvarchar(255),
	GROUP_ nvarchar(255),
	KEY_ nvarchar(255),
	VALUE_ clob,
	primary key (ID_)
);

create table ACT_CY_LINK (
	ID_ nvarchar(255) NOT NULL,
	SOURCE_CONNECTOR_ID_ nvarchar(255),
	SOURCE_ARTIFACT_ID_ nvarchar(550),
	SOURCE_ELEMENT_ID_ nvarchar(255) DEFAULT NULL,
	SOURCE_ELEMENT_NAME_ nvarchar(255) DEFAULT NULL,
	SOURCE_REVISION_ numeric(19,0) DEFAULT NULL,
	TARGET_CONNECTOR_ID_ nvarchar(255),	
	TARGET_ARTIFACT_ID_ nvarchar(550),
	TARGET_ELEMENT_ID_ nvarchar(255) DEFAULT NULL,
	TARGET_ELEMENT_NAME_ nvarchar(255) DEFAULT NULL,
	TARGET_REVISION_ numeric(19,0) DEFAULT NULL,
	LINK_TYPE_ nvarchar(255) ,
	COMMENT_ nvarchar(1000),
	LINKED_BOTH_WAYS_ bit,
	primary key(ID_)
);

create table ACT_CY_PEOPLE_LINK (
	ID_ nvarchar(255) NOT NULL,
	SOURCE_CONNECTOR_ID_ nvarchar(255),
	SOURCE_ARTIFACT_ID_ nvarchar(550),
	SOURCE_REVISION_ numeric(19,0) DEFAULT NULL,
	USER_ID_ nvarchar(255),
	GROUP_ID_ nvarchar(255),
	LINK_TYPE_ nvarchar(255),
	COMMENT_ nvarchar(1000),
	primary key(ID_)
);

create table ACT_CY_TAG (
	ID_ nvarchar(255),
	NAME_ nvarchar(255),
	CONNECTOR_ID_ nvarchar(255),
	ARTIFACT_ID_ nvarchar(550),
	ALIAS_ nvarchar(255),
	primary key(ID_)	
);

create table ACT_CY_COMMENT (
	ID_ nvarchar(255) NOT NULL,
	CONNECTOR_ID_ nvarchar(255) NOT NULL,
	NODE_ID_ nvarchar(550) NOT NULL,
	ELEMENT_ID_ nvarchar(255) DEFAULT NULL,
	CONTENT_ nvarchar(1024) NOT NULL,
	AUTHOR_ nvarchar(255),
	DATE_ datetime NOT NULL,
	ANSWERED_COMMENT_ID_ nvarchar(255) DEFAULT NULL,
	primary key(ID_)
);

create index ACT_CY_IDX_COMMENT on ACT_CY_COMMENT(ANSWERED_COMMENT_ID_);
alter table ACT_CY_COMMENT 
    add constraint FK_CY_COMMENT_COMMENT 
    foreign key (ANSWERED_COMMENT_ID_) 
    references ACT_CY_COMMENT (ID_);
    
create table ACT_CY_PROCESS_SOLUTION (
	ID_ nvarchar(255) NOT NULL,
	LABEL_ nvarchar(255) NOT NULL,
	STATE_ nvarchar(255) NOT NULL,
	primary key(ID_)
);

create table ACT_CY_V_FOLDER (
	ID_ nvarchar(255) NOT NULL,
	LABEL_ nvarchar(255) NOT NULL,
	CONNECTOR_ID_ nvarchar(255) NOT NULL,
	REFERENCED_NODE_ID_ nvarchar(255) NOT NULL,
	PROCESS_SOLUTION_ID_ nvarchar(255) NOT NULL,
	TYPE_ nvarchar(255) NOT NULL,
	primary key(ID_)
);
alter table ACT_CY_V_FOLDER 
    add constraint FK_CY_PROCESS_SOLUTION_ID 
    foreign key (PROCESS_SOLUTION_ID_) 
    references ACT_CY_PROCESS_SOLUTION;