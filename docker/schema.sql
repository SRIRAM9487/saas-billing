CREATE TABLE USERS(
	userId UUID PRIMARY KEY ,
	name VARCHAR(50),
	password VARCHAR(59),
	email VARCHAR(59),
	VerifyedType VARCHAR(59),
	PhoneNumber INTEGER,
	UserRole role,
);
