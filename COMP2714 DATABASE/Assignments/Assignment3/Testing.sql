SET ECHO ON
SPOOL C:\Users\Kunlaya\workspace\COMP2714\Assignments\Assignment3\testing.txt
-- ---------------------------------------------------------
--
--  COMP 2714
--  SET 2C
--  Assignment Asn03
--  Kobunnoi, Kunlaya A00959419
--  email: kkobunnoi@my.bcit.ca
--
-- ---------------------------------------------------------
--  BUILT DATABASE
--  START C:\Users\Kunlaya\workspace\COMP2714\Assignments\Assignment3\Asn3SetupHotels.sql
--
--  ASSIGNMENT
--  START C:\Users\Kunlaya\workspace\COMP2714\Assignments\Assignment3\Asn03_Kobunnoik.sql
-- ---------------------------------------------------------
--
ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD';
--
SELECT SYSDATE
FROM DUAL;
--
----------------------------------------------------------
--Q1) What is the average price of a room in london?
----------------------------------------------------------
-- A) Using join?
SELECT AVG(price) AS "Average Price"
FROM  Room r
 JOIN Hotel h ON r.hotelNo = h.hotelNo
WHERE hotelAddress LIKE '%London%';
--B) Do this using IN subquery?
SELECT AVG(price) AS "Average Price"
FROM  Room
WHERE hotelNo IN(SELECT hotelNo
                 FROM   Hotel
                 WHERE  hotelAddress LIKE '%London%');
----------------------------------------------------------
-- Q2) How many different guests have made bookings for August?
--     Use the month of 2016-10 instead of August, and do the
--     query for each hotel, listing in hotelName order:
--     i.e) How many different guests have made bookings for 2016-10
--          for each hotel.
----------------------------------------------------------
SELECT hotelName,
       COUNT(guestNo) AS "Total of guest"
 FROM Booking b
    NATURAL JOIN Hotel h 
WHERE (dateTo IS NULL OR dateTo >= DATE'2016-10-01')
    AND dateFrom < DATE'2016-11-01'
GROUP BY hotelName
ORDER BY hotelName;	
--
----------------------------------------------------------
-- Q3) List the details of all rooms at the Grosvenor Hotel, including
--     the name of the guest staying in the room, if the room is occpied
--     Use 2016-10-06 as today's date. Include all 'Grosvenor' hotels.
--     List in hotelNo, roomNo order.
----------------------------------------------------------
SELECT hotelNo,roomNo,type,price,guestName
FROM  Hotel h
 NATURAL JOIN Room r,
 NATURAL JOIN Booking b,
 ----------------------------------------------------------
--Q4)List the rooms that are currently unocupied at all 'Grosvenor' hotels
--List in hotelNo, roomNo oder.use NOT IN
----------------------------------------------------------
SELECT hotelNo,roomNo
FROM Room r
 NATURAL JOIN Hotel h
WHERE hotelName LIKE '%Grosvenor%'
  AND roomNo NOT IN(SELECT roomNo
                    FROM   bookings
					WHERE dateFrom <= DATE '2016-10-01'
					    AND (dateTo6)

--
--END--------------------------------------------------------
--
SPOOL OFF
