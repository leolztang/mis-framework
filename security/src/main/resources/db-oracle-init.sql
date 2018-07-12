--oracle
DROP SEQUENCE auth_user_seq;
CREATE SEQUENCE auth_user_seq increment by 1 start with 1;

DROP TABLE auth_user;
CREATE TABLE auth_user(
id number(20) NOT NULL,
login_id varchar2(32) NOT NULL,
user_name varchar2(32) NOT NULL,
password varchar2(255) NOT NULL,
enabled number(1) NOT NULL,
locked number(1) NOT NULL,
locked number(1) NOT NULL,
create_timestamp timestamp NOT NULL,
update_timestamp timestamp NOT NULL,
PRIMARY KEY (id),
CONSTRAINT uniq_auth_user UNIQUE (login_id)
);

DROP SEQUENCE auth_role_seq increment;
CREATE SEQUENCE auth_role_seq increment by 1 start with 1;

DROP TABLE auth_role;
CREATE TABLE auth_role(
    id number(20) NOT NULL,
    name VARCHAR(64) NOT NULL,
    description VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uniq_auth_role UNIQUE (name)
);

DROP SEQUENCE auth_permission_seq;
CREATE SEQUENCE auth_permission_seq increment by 1 start with 1;

--融合了新RBAC中的permission和resource两个表的功能
DROP TABLE auth_permission;
CREATE TABLE auth_permission(
    id number(20) NOT NULL,
    name VARCHAR(64) NOT NULL,
    description VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uniq_auth_permission UNIQUE (name)
);

DROP SEQUENCE auth_role_user_seq;
CREATE SEQUENCE auth_role_user_seq increment by 1 start with 1;

DROP TABLE auth_role_user;
CREATE TABLE auth_role_user(
    id number(20) NOT NULL,
    auth_user_id number(20) NOT NULL,
    auth_role_id number(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uniq_auth_role_user UNIQUE (auth_user_id, auth_role_id)
);

DROP SEQUENCE auth_role_permission_seq;
CREATE SEQUENCE auth_role_permission_seq increment by 1 start with 1;

DROP TABLE auth_role_permission;
CREATE TABLE auth_role_permission(
    id number(20) NOT NULL,
    auth_role_id number(20) NOT NULL,
    auth_permission_id number(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uniq_auth_permission_role UNIQUE (auth_role_id, auth_permission_id)
);