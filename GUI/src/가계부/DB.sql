CREATE TABLE 가계부(
day VARCHAR2(20) NOT NULL,
num number NOT NULL,
money number NOT NULL,
value VARCHAR2(10),
useing VARCHAR2(50),
CONSTRAINT 가계부_pk PRIMARY KEY(day,num)
);