SET ECHO ON
SPOOL \\Mac\Home\Desktop\COMP_2714\Assignments\Assign2\Asn2_HerraduraL.txt
 --
 -- ---------------------------------------------------------
 --
 --  COMP 2714
 --  SET 2C
 --  Assignment Asn02
 --  Kobunnoi, Kunlaya    A00959419
 --  Lancelei, Herradura  A00960501
 --  email: kkobunnoi@my.bcit.ca
 --  email: lherradura@my.bcit.ca
 --
 -- ---------------------------------------------------------
 --  LOAD DATABASE
 --  START C:\Users\Kunlaya\workspace\COMP2714\Assignments\Assignment2\Asn2SetupHotels.sql
 --  ASSIGNMENT
 --  START C:\Users\Kunlaya\workspace\COMP2714\Assignments\Assignment2\Asn02_KobunnoiK.sql
 -- ---------------------------------------------------------
Session altered.
ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD';
SELECT SYSDATE
FROM DUAL;
-- Q1.
-- For all hotels in London, list hotel name, hotelAddress, room type and price 
-- of all Single, Double or Family rooms with a price below 100.00 per night, 
-- in descending order of hotelName, ascending order of price and descending order
-- of type. List each result entry only once.
--
SELECT DISTINCT hotelName, hotelAddress, type, price
FROM Hotel 
	INNER JOIN Room
	ON Hotel.hotelNo = Room.hotelNo
WHERE hotelAddress LIKE '%London'
	AND price BETWEEN 0 AND 100.00
	AND type IN('Single', 'Double', 'Family')
ORDER BY hotelName DESC,
	price ASC,
	type DESC
;
-- Q2.
-- List the Vancouver hotel bookings for which no dateTo has been specified. 
-- List the hotelName, hotelAddress, room number, dateFrom and dateTo. 
-- Note: Hotels in West and North Vancouver should be excluded.
--
SELECT hotelName, hotelAddress, roomNo, dateTo, dateFrom
FROM Booking
	NATURAL JOIN Room
	NATURAL JOIN Hotel
WHERE dateTo IS NULL
	AND hotelAddress LIKE '%,_Vancouver'
;
-- Q3.
-- What is the average price of a room for each hotel? 
-- List it with the hotelName and in hotelName order.
--	
SELECT hotelName, 
	AVG(DISTINCT price) AS "AVG(price)"
FROM Hotel
	NATURAL JOIN Room
GROUP BY hotelName
;
-- Q4.
-- Do this for each hotel and in one single query. 
-- List the total revenue with the hotelName, and only if the total
-- revenue is between $500 to $1000 [in SQL context]. List in hotelName order.
--
SELECT hotelName, SUM(price) AS "Total Revenue"
FROM Hotel
	INNER JOIN Room
	ON Hotel.hotelNo = Room.hotelNo
WHERE type = 'Double'
GROUP BY hotelName
HAVING SUM(price) > 500
	AND SUM(price) < 1000
;
-- Q5
-- List the type and price of all rooms at the hotels with 'Grosvenor' 
-- in name, including the number of rooms for each combination of type
-- and price. List only if the number of rooms for each type and price 
-- combination is greater than 3.
SELECT type,
	price,
	COUNT(roomNo) AS "Number of Rooms"
FROM Hotel
	INNER JOIN Room
	ON Hotel.hotelNo = Room.hotelNo
WHERE hotelName LIKE 'Grosvenor%'
GROUP BY type, price
HAVING COUNT(type) > 3
;
-- Q6
-- Include the roomNo in the output. Use 2016-09-29 as the current date.
SELECT g.guestName, b.roomNo
  FROM Hotel h
  JOIN Booking b ON b.hotelNo = h.hotelNo
  JOIN Guest   g ON g.guestNo = b.guestNo
  WHERE (dateTo IS NULL OR dateTo >= '2016-09-29')
	AND (dateFrom <= '2016-09-29')
	AND hotelName = 'Grosvenor Hotel'
;
-- Q7
-- Do this question for each of the hotels with 'Grosvenor' in name. 
-- Use 2016-09-29 as current date.
SELECT h.hotelName,
       SUM(r.price) AS "Total Income"
  FROM Booking b
  JOIN Hotel h ON h.hotelNo = b.hotelNo
  JOIN Room  r ON r.hotelNO = h.hotelNo
              AND r.roomNO  = b.roomNo
  WHERE h.hotelName LIKE 'Grosvenor%'
  AND   b.dateFrom  <= DATE '2016-09-29'
  AND  (b.dateTo Is NULL OR dateTo >= DATE '2016-09-29')
  GROUP BY h.hotelName
;
-- Q8
-- What is the total income for each room type from bookings for each hotel (listing hotel names) today?
-- Use 2016-09-29 as today's date. List in ascending order of hotel name and room type.
SELECT h.hotelName,
      r.type     ,
      SUM(r.price) AS "Price"
  FROM Booking b
  JOIN Hotel h ON h.hotelNo = b.hotelNo
  JOIN Room  r ON r.hotelNo = h.hotelNo
       AND        r.roomNo  = b.roomNo
  WHERE b.dateFrom <= DATE '2016-09-29'
  AND  ( dateTo IS NULL OR dateto >= DATE '2016-09-29')
  GROUP BY h.hotelName, r.type
  ORDER BY h.hotelName ASC, r.type ASC;
-- Q9
-- List the new hotels that are still under construction, i.e. no room data in the database as yet for these hotels.
--
SELECT h.hotelName
FROM Hotel h
 LEFT JOIN Room r 
	ON h.hotelNo = r.hotelNo
  WHERE r.hotelNo IS NULL;
-- Q10
-- What is the percentage of hotels still under construction? List the total number of hotels, number of hotels
-- completed, the number of hotels under construction, and the percentage of hotels under construction. NO SUBQUERY.
 SELECT COUNT(DISTINCT h.hotelNo) AS " Number Of Hotels ",
  	    COUNT(DISTINCT r.hotelNo) AS "Completed Hotels",
  	    ((COUNT  (DISTINCT h.hotelNo))
  	     -(COUNT (DISTINCT r.hotelNo))
  	    ) AS Construction,
  	    (((COUNT(DISTINCT h.hotelNo)) -(COUNT (DISTINCT r.hotelNo)))
   	       /(COUNT (DISTINCT h.hotelNo))
  	       * 100
  	    ) AS PercentConst
    FROM Hotel h
  	 LEFT JOIN Room r ON h.hotelNo = r.hotelNo
  ;
SPOOL OFF