SET ECHO ON
SPOOL C:\Users\Kunlaya\workspace\COMP2714\Assignments\Assignment3\Asn3_KobunnoiK.txt
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
SELECT AVG(r.price) AS "Average price In London"
     FROM Room r
     INNER JOIN Hotel h
          ON h.hotelNo = r.hotelNo
     WHERE h.hotelAddress LIKE'%London%'
;
-- B) Do this using IN subquery?
SELECT AVG(price) AS "Average price in London"
    FROM  Room
    WHERE hotelNo IN
         (SELECT hotelNo
          FROM   Hotel
          WHERE  hotelAddress LIKE'%London%'
         );

----------------------------------------------------------
-- Q2) How many different guests have made bookings for August?
--     Use the month of 2016-10 instead of August, and do the
--     query for each hotel, listing in hotelName order:
--     i.e) How many different guests have made bookings for 2016-10
--          for each hotel.
----------------------------------------------------------
SELECT h.hotelName, COUNT(DISTINCT b.guestNo) AS "Number of guest"
     FROM Booking b
     JOIN Hotel h ON h.hotelNo = b.hotelNo
     WHERE (dateTo IS NULL OR dateTo >= DATE'2016-10-01')
        AND dateFrom < DATE'2016-11-01'
     GROUP BY h.hotelName
     ORDER BY h.hotelName ASC
;
--
----------------------------------------------------------
-- Q3) List the details of all rooms at the Grosvenor Hotel, including
--     the name of the guest staying in the room, if the room is occpied
--     Use 2016-10-06 as today's date. Include all 'Grosvenor' hotels.
--     List in hotelNo, roomNo order.
----------------------------------------------------------
SELECT h.hotelNo,
       r.roomNo,
       r.type,
       r.price,
       g.guestname
     FROM Room r
         INNER JOIN
         (SELECT *
          FROM  Hotel
          WHERE hotelName LIKE '%Grosvenor%'
         ) h ON r.hotelNo = h.hotelNo
         LEFT JOIN
         (SELECT *
          FROM  Booking
          WHERE dateFrom <= DATE'2016-10-06'
           AND (dateTo IS NULL OR dateTo >= DATE'2016-10-06')
         ) b ON h.hotelNo = b.hotelNo AND r.roomNo = b.roomNo
         LEFT JOIN
              Guest g ON b.guestNo = g.guestNo
         ORDER BY r.hotelNo, r.roomNo
;
--
----------------------------------------------------------
--Q4)List the rooms that are currently unocupied at all 'Grosvenor' hotels
--List in hotelNo, roomNo oder.use NOT IN
----------------------------------------------------------
SELECT h.hotelNo, r.roomNo
      FROM Hotel h
      JOIN Room r
    	 ON h.hotelNo = r.hotelNo
      WHERE h.hotelName LIKE '%Grosvenor%'
    	 AND r.roomNo NOT IN
    	  (SELECT b.roomNo
    	   FROM Booking b
    	   WHERE dateFrom <= DATE'2016-10-06'
   	      AND (dateTo IS NULL OR dateTo >= DATE'2016-10-06')
  	      AND b.hotelNo = r.hotelNo)
       ORDER BY hotelNo ASC, roomNo ASC;
----------------------------------------------------------
--Q5)List the rooms that are currently unocupied at all 'Grosvenor' hotels
--List in hotelNo, roomNo oder.use NOT EXISTS.
----------------------------------------------------------
SELECT h.hotelNo, r.roomNo
      FROM Hotel h
      JOIN Room r
   	 ON h.hotelNo = r.hotelNo
     WHERE h.hotelName LIKE '%Grosvenor%'
    	AND NOT EXISTS
    	  (SELECT *
    	     FROM Booking b
    	    WHERE dateFrom <= DATE'2016-10-06'
   	      AND (dateTo IS NULL OR dateTo >= DATE'2016-10-06')
   	      AND b.hotelNo = r.hotelNo
   	      AND b.roomNo = r.roomNo)
    ORDER BY hotelNo ASC, roomNo ASC
;
--
----------------------------------------------------------
-- Q6) List the rooms that are currently unocupied at all 'Grosvenor' hotels
--     List in hotelNo, roomNo roder. Use LEFT JOIN
----------------------------------------------------------
SELECT h.hotelNo, r.roomNo
FROM Room r
  LEFT JOIN Hotel h ON h.hotelNo = r.hotelNo
  LEFT JOIN (
    SELECT *
    FROM Booking
    WHERE dateFrom <= DATE'2016-10-06'
    AND (dateTo IS NULL OR dateTo >= DATE'2016-10-06')
  ) b ON b.hotelNo = h.hotelNo
                      AND b.roomNo = r.roomNo
  LEFT JOIN Guest g ON g.guestNo = b.guestNo
WHERE g.guestNo is NULL
AND h.hotelName LIKE '%Grosvenor%'
ORDER BY r.hotelNo, r.roomNo
;
--
----------------------------------------------------------
--Q7) List the rooms that are currently unocupied at all 'Grosvenor' hotels
--    List in hotelNo, roomNo order. Use MINUS
----------------------------------------------------------
SELECT hotelNo, roomNo
    FROM Hotel
    NATURAL JOIN Room
    WHERE hotelName LIKE '%Grosvenor%'
    MINUS
    SELECT hotelNo, roomNo
    From Hotel
    NATURAL JOIN Room
    NATURAL JOIN Booking
    WHERE dateFrom <= DATE'2016-10-06'
    AND (dateTo IS NULL OR dateTo >= DATE'2016-10-06')
    ORDER By hotelNo, roomNo
    ;
--
----------------------------------------------------------
--Q8) What is the average number of bookings for each hotel in
--    2016-10 instead of August
----------------------------------------------------------
SELECT AVG(COUNT(guestNo)) AS "Average of Bookings"
  FROM Booking b
 WHERE (dateTo IS NULL OR dateTo >= DATE'2016-10-01')
   AND dateFrom <= DATE'2016-10-31'
 GROUP BY b.hotelNo;
--
----------------------------------------------------------
--Q9) WHat is the lost income from unocupied rooms at each hotels today??
--    2016-10-06
--    list HotelNo, hotelName, LostIncome  in hotelNo order.
----------------------------------------------------------
SELECT h.hotelNo,
       h.hotelName,
       SUM(r.price) AS "Lost Income"
FROM Room r
INNER JOIN Hotel h
  ON r.hotelNo = h.hotelNo
  WHERE (r.hotelNo, r.roomNo) NOT IN
      (SELECT hotelNo, roomNo
       FROM Booking b
       WHERE dateFrom <= DATE'2016-10-06'
       AND (dateTo IS NULL OR dateTo >= DATE'2016-10-06')
       )
  GROUP BY h.hotelNo, h.hotelName
  ORDER BY h.hotelNo;

------------------------------------------------------------
-- 10. Create a view containing the guests for each guest at the Grosvenors.
--     Use 2016-10-06 as today, and guestAccount as view name. [The account info
--     for each guest means: Room (roomNo), Name (guestName), Check-in
--     (dateFrom), Check-out (dateTo=today), Rate (price), #Days (calculate from
--     dateTo and dateFrom), and Total (calculate from dateTo, dateFrom and price]
--     Add a query to test the view.
-------------------------------------------------------------
DROP VIEW guestAccount;

CREATE VIEW guestAccount
    (Room, Name, "Check-In", "Check-Out", Rate, Days, Total)
    AS
    SELECT b.roomNo, g.guestName, b.dateFrom, DATE'2016-10-06', r.price,
  	      (DATE'2016-10-06' - b.dateFrom), (DATE'2016-10-06' - b.dateFrom)*r.price
  	 FROM Booking b
  	INNER JOIN Room r
  	   ON b.hotelNo = r.hotelNo
  	  AND b.roomNo = r.roomNo
  	INNER JOIN Guest g
  	   ON b.guestNo = g.guestNo
  	WHERE dateFrom <= DATE'2016-10-06'
  	  AND (b.dateTo >= DATE'2016-10-06' OR b.dateTo IS NULL)
  	  AND b.hotelNo IN
  	  (SELECT h.hotelNo
  	     FROM Hotel h
  	    WHERE h.hotelName LIKE 'Grosvenor Hotel');


SELECT *
FROM guestAccount
ORDER BY Room;
;


--
--END--------------------------------------------------------
--
SPOOL OFF
