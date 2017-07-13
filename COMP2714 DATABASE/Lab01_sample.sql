-- Q1: Dropped tables first before creating
DROP TABLE OldBooking;
DROP TABLE Booking;
DROP TABLE Room;
DROP TABLE Hotel;
DROP TABLE Guest;

-- Q2: Create Hotel and Room Tables
CREATE TABLE Hotel
	(hotelNo	CHAR(8)		NOT NULL,
	hotelName	VARCHAR2(30)	NOT NULL,
	city		  VARCHAR2(30) NOT NULL,
	PRIMARY KEY (hotelNo)
  -- * Come back later
	);
CREATE TABLE Room
	(roomNo		INTEGER(100)	NOT NULL,
	hotelNo   CHAR(8)			NOT NULL,
	type		  VARCHAR2(6)		NOT NULL,
	price		  DECIMAL(5,2)	NOT NULL,
	PRIMARY KEY (roomNo, hotelNo),
	CONSTRAINT chkType CHECK(type	IN('Single', 'Double', 'Family'))
	CONSTRAINT chkPrice CHECK(price	BETWEEN 10 AND 100)
	CONSTRAINT chkRoomNo CHECK(roomNo 	BETWEEN 1 AND 100)
	);
-- Q3: Create Guest and Booking Table
CREATE TABLE Guest
	(guestNo		CHAR(8)			NOT NULL,
	guestName		VARCHAR2(30)	NOT NULL,
	guestAddress	VARCHAR2(100)	NOT NULL,
	PRIMARY KEY (guestNo)
	);
CREATE TABLE Booking
	(hotelNo	CHAR(8)		NOT NULL,
	guestNo		CHAR(8)		NOT NULL,
	dateFrom	DATE,
	dateTo		DATE,
	roomNo 		CHAR(8)		NOT NULL,
	PRIMARY KEY (hotelNo, guestNo, dateFrom),
	FOREIGN KEY (hotelNo) REFERENCES (Hotel (hotelNo)),
	FOREIGN KEY (roomNo) REFERENCES (Room (roomNo))
	);
-- Q4: Insert sample data
-- INSERT Hotel Data
INSERT INTO Hotel
   VALUES('1','Marriot','Vancouver')
;
INSERT INTO Hotel
   VALUES('2','Inn','Chilliwack')
;
INSERT INTO Hotel
   VALUES('3','Fraser','Victoria')
;
-- Insert Room DATA
INSERT INTO Room
  VALUES ('9','1','Single','19.00')
;
INSERT INTO Room
  VALUES ('99','2','Double','99.00')
;
INSERT INTO Room
  VALUES ('100','3','Family','29.00')
;

--Insert Guest DATA
--
INSERT INTO Guest
  VALUES ('00111100','Dan','402 Fraser st',)
;
INSERT INTO Guest
  VALUES ('00111102','Ben','521 Rupert st',)
;
INSERT INTO Guest
  VALUES ('00111104','Hannah','109 Willindon Ave.',)
;

-- Insert Booking DATA
INSERT INTO Booking
  VALUES ('1','00111100',DATE'2016-01-23',DATE'2016-01-30','10')
;
INSERT INTO Booking
  VALUES ('2','00111102','Ben',DATE'2016-02-04',DATE'2016-02-10','20')
;
INSERT INTO Booking
  VALUES ('3','00111104','Hannah',DATE'2016-03-23',DATE'2016-03-25-','40')
;
COMMIT;

--Q5. The hotel
--Q6.
UPDATE Hotel
SET price = price *0.15;
WHERE type = 'Double'
AND hotelNo = (SELECT hotelNo
               FROM   Hotel
               WHERE  hotelName = 'Marriot')
;

-- 6b
UPDATE Booking
  SET dateFrom  =  DATE'2015-01-23',
      dateTo    =  DATE'2015-01-30'
  WHERE guestNo = (SELECT guestNo
                   FROM Guest
                   WHERE guestName = 'Dan')
  AND hotelNo =   (SELECT hotelNo
                   FROM Hotel
                   WHERE hotelName = 'Marriot')
  AND dateFrom =   DATE'2016-01-22'
      dateTo   =   DATE'2015-01-31'

;
-- 7a
CREATE TABLE OldBooking
  (hotelNo	CHAR(8)		NOT NULL,
   guestNo		CHAR(8)		NOT NULL,
   dateFrom	DATE,
   dateTo		DATE,
   roomNo 		CHAR(8)		NOT NULL,
PRIMARY KEY (hotelNo, guestNo, dateFrom),
FOREIGN KEY (hotelNo) REFERENCES (Hotel (hotelNo)),
FOREIGN KEY (roomNo) REFERENCES (Room (roomNo))
;

--7b

INSERT INTO OldBooking
  (SELECT *
  FROM Booking
  WHERE dateTo < DATE'2016-09-01')
;
--7c
DELETE FROM Booking
  WHERE dateTo < DATE'2016-09-01'

;
