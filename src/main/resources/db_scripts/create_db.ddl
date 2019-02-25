create sequence user_seq;

CREATE TABLE user(
	id bigint default user_seq.nextval primary key,
	username varchar(20),
	userrole varchar(20));