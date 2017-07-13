SET ECHO ON
SPOOL C:\Users\Kunlaya\workspace\COMP2714\Assignments\Asn1_HerraduraL_KobunnoiK (1).txt
-- ---------------------------------------------------------
--  COMP 2714
--  SET 2C
--  LAB01 Exercise / Assignment Asn01
--  Herradura, Lancelei    A00960501
--  Kobunnoi, Kunlaya	   A00959419
--  email: lherradura@my.bcit.ca
--  email: kkobunnoi@my.bcit.ca
-- ---------------------------------------------------------
--  START C:\Users\Kunlaya\workspace\COMP2714\Assignments\Asn1_HerraduraL_KobunnoiK (1).sql
-- ---------------------------------------------------------
--
ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD';
SELECT SYSDATE
FROM DUAL;

-- ---------------------------------------------------------
-- Q1) (5 marks) Write the DDL statement to remove all 4 tables,
--      plus the OldBooking archive table.
-- ---------------------------------------------------------
DROP TABLE OldBooking;
DROP TABLE Booking;
DROP TABLE Guest;
DROP TABLE Room;
DROP TABLE Hotel;
-- ---------------------------------------------------------
-- Q2) Create the Hotel (from Q.7.10 - 3 marks) and Room (7 marks)
-- tables with all relevant integrity constraints, including:
--
-- a. type (Room table) must be one of Single, Double, or Family.
-- b. price (Room table) must be between $10.00 and $100.00.
-- c. room number (Room table) must be between 1 and 100.
-- ---------------------------------------------------------
--CREATE Hotel Table
CREATE TABLE Hotel
 (hotelNo   CHAR(8)      NOT NULL
 ,hotelName VARCHAR2(20) NOT NULL
 ,city      VARCHAR2(30) NOT NULL
 ,CONSTRAINT PKHotel PRIMARY KEY (hotelNo)
 )
 ;
--CREATE Room Table
 CREATE TABLE Room
  (roomNo  CHAR(8)      NOT NULL
  ,hotelNo CHAR(8)      NOT NULL
  ,type    VARCHAR2(6)  NOT NULL
  ,price   DECIMAL(5,2) NOT NULL
  ,CONSTRAINT PKRoom    PRIMARY KEY (roomNo)
  ,CONSTRAINT FKRoom    FOREIGN KEY (hotelNo)  REFERENCES Hotel (hotelNo)
  ,CONSTRAINT chkType 	CHECK(type IN('Single', 'Double', 'Family'))
  ,CONSTRAINT chkPrice 	CHECK(price BETWEEN 10 AND 100)
  ,CONSTRAINT chkRoomNo CHECK(roomNo BETWEEN 1 AND 100)
  )
;
-- ---------------------------------------------------------
--  Q3) Create the Guest (from Q.7.11 - 3 marks) and Booking
--  (7 marks) tables with all relevant integrity constraints.
-- ---------------------------------------------------------

-- Create Guest Table
CREATE TABLE Guest
 (guestNo      CHAR(8)       NOT NULL
 ,guestName    VARCHAR2(30)  NOT NULL
 ,guestAddress VARCHAR2(75)  NOT NULL
 ,CONSTRAINT PKGuest PRIMARY KEY (guestNo)
 )
;
-- Create Booking Table
CREATE TABLE Booking
 (hotelNo  CHAR(8)   NOT NULL
 ,guestNo  CHAR(8)   NOT NULL
 ,dateFrom DATE      NOT NULL
 ,dateTo   DATE      NOT NULL
 ,roomNo   CHAR(8)   NOT NULL
 ,CONSTRAINT PKBooking  PRIMARY KEY (hotelNo,guestNo,dateFrom)
 ,CONSTRAINT FKBooking  FOREIGN KEY (hotelNo) REFERENCES Hotel (hotelNo)
 ,CONSTRAINT FKBooking2 FOREIGN KEY (guestNo) REFERENCES Guest (guestNo)
 ,CONSTRAINT CKRoomNo 	CHECK (roomNo >= 1 OR roomNo <= 100)
 )
;
--
-- Display the tables info
--
DESC Hotel;
DESC Room;
DESC Guest;
DESC booking;
-- ---------------------------------------------------------
-- 4) Insert 3 rows of sample data to each of the 4 tables.
-- ---------------------------------------------------------
--
-- Insert HOTEL Data
INSERT INTO Hotel
 VALUES('J001','Marriot','Vancouver')
;
INSERT INTO Hotel
   VALUES('J002','Inn','Chilliwack')
;
INSERT INTO Hotel
   VALUES('J003','Fraser','Victoria')
;
--Insert Room Data
INSERT INTO Room
  VALUES ('9','J001','Single',19.00)
;
INSERT INTO Room
  VALUES ('40','J002','Double',30.00)
;
INSERT INTO Room
  VALUES ('80','J003','Family',50.00)
;
--Insert Guest Data
INSERT INTO Guest
  VALUES ('00111100','Dan','402 Fraser st')
;
INSERT INTO Guest
  VALUES ('00111102','Ben','521 Rupert st')
;
INSERT INTO Guest
  VALUES ('00111104','Hannah','109 Willindon Ave.')
;
--
--Insert Booking Data
INSERT INTO Booking
  VALUES ('J001', '00111100', DATE'2016-04-01', DATE'2016-04-04','9')
;
INSERT INTO Booking
  VALUES ('J002', '00111102', DATE'2016-05-05', DATE'2016-05-10','80')
;
INSERT INTO Booking
  VALUES ('J003', '00111104', DATE'2016-09-10', DATE'2016-09-15','40')
;
COMMIT;
--
-- ---------------------------------------------------------
-- Q5) a. The hotels now have a new room type 'Deluxe'. Modify
-- the check constraint to allow for this.
--
--    b. Need to offer discounts. Add column 'discount' to the
-- Room table, with default value of 0 (percent), max. value of 30.
-- ---------------------------------------------------------
-- Q5a)
ALTER TABLE Room
 DROP CONSTRAINT chkType
;
ALTER TABLE Room
 ADD CONSTRAINT chkType CHECK(type IN('Single', 'Double','Family', 'Deluxe'))
;
-- Q5b: Add column discount to table Room
ALTER TABLE Room
 ADD discount DECIMAL(5,2) DEFAULT 0.0 NOT NULL;
;
ALTER TABLE Room
 MODIFY discount DEFAULT 0
 ADD CONSTRAINT chkDiscount CHECK(discount BETWEEN 0 and 30)
;
-- ---------------------------------------------------------
-- Q6 a. One of the hotels (pick any one) increases the price of
-- its 'Double' room type by 15%. Implement this change.
--
--    b. One of the booking guest decided to arrive on an earlier date
-- and stay till a later dater. Modif the booking accordingly
-- ---------------------------------------------------------
--Q6a : Increase price of room type Double
UPDATE Room
  SET   price   = price * 1.15
  WHERE type    = 'Double'
  AND   hotelNo = 'J002'
;
-- Check if price increased
SELECT *
FROM Room
WHERE hotelNo = 'J002';
-- Q6b: Modify booking for guest who arrived earlier than expected date
--      and left later than expected
UPDATE Booking
      SET   dateFrom  =  DATE'2016-03-29'
      WHERE guestNo   =  '00111100';
UPDATE Booking
      SET   dateTo    =  DATE'2016-04-05'
      WHERE guestNo   =  '00111100';

-- Check if date was changed
SELECT *
   FROM  Booking
   WHERE hotelNo = 'J002'
   AND   guestNo = '00111100'
   AND   dateFrom > DATE'2016-09-01'
--
-- ---------------------------------------------------------
-- Q7 a. Create an OldBooking table to hold archive data rows from
--       the Booking table (from Q.7.12 - 2 marks).
--
--    b. Using the INSERT statement, copy the rows with dateTo before
--       2016-09-01 from the Booking table to the OldBooking archive
--       table (from Q.7.12 - 2 marks).
--
--    c. Then delete from Booking all booking rows with dateTo
--       before 2016-09-01 (2 marks).
-- --------------------------------------------------------
-- Q7a) Create OldBooking Archive Table
CREATE TABLE OldBooking
 (hotelNo	  CHAR(8)		NOT NULL
 ,guestNo		CHAR(8)		NOT NULL
 ,dateFrom	DATE      NOT NULL
 ,dateTo		DATE      NULL
 ,roomNo 		CHAR(8)		NOT NULL
 )
;
DESC OldBooking;
-- Q7b)
INSERT INTO OldBooking
 (SELECT *
  FROM Booking
  WHERE dateTo < DATE'2016-09-01')
;
-- Q7c)
DELETE FROM Booking
  WHERE dateTo < DATE'2016-09-01'
;
-- CHECK
SELECT * FROM Hotel;
SELECT * FROM Room;
SELECT * FROM Guest;
SELECT * FROM Booking;
SELECT * FROM OldBooking;

--  ** This last SQL*Plus SPOOL command line is MOST IMPORTANT !! **
--  It is usually the last line of the script file, and its purpose
--    is to close off the current spool output text file, so that all
--    output from this script will be flushed to disk and saved to the
--    SPOOL text file.
--  Otherwise, you may end up missing output content.
--
SPOOL OFF
