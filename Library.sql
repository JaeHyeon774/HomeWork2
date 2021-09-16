CREATE TABLE LIBRARY (
    ISBN    number(5)           NOT NULL,
    title   varchar2(50)      NOT NULL,
    author  varchar2(250)    NOT NULL,
    publisher   varchar2(50)    NOT NULL,
    price   number(6)   NOT NULL,
    DES    varchar2(200),
    publish_date    varchar2(10),
    constraint  book_pk primary key (isbn)
);