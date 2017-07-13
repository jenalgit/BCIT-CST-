SET ECHO ON
SPOOL C:\Users\Kunlaya\workspace\COMP2714\Assignments\Assignment2\KobunnoiK.txt
 --
 -- ---------------------------------------------------------
 --
 --  COMP 2714
 --  SET 2C
 --  Assignment Asn02
 --  Kobunnoi, Kunlaya    A00959419
 --  email: kkobunnoi@my.bcit.ca
 --
 -- ---------------------------------------------------------
 --  LOAD DATABASE
 --  START C:\Users\Kunlaya\workspace\COMP2714\Assignments\Assignment2\Asn2SetupHotels.sql
 --  ASSIGNMENT
 --  START C:\Users\Kunlaya\workspace\COMP2714\Assignments\Assignment2\Asn02_KobunnoiK.sql
 -- ---------------------------------------------------------
 -- ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD';

Session altered.
ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD';
SELECT SYSDATE
FROM DUAL;

--Q6
SELECT g.guestName, b.roomNo
  FROM Hotel h
  JOIN Booking b ON b.hotelNo = h.hotelNo
  JOIN Guest   g ON g.guestNo = b.guestNo
  WHERE b.dateTo <= DATE '2016-09-29'
        AND (b.dateTo IS NULL or dateTO >= DATE '2016-09-29')
        AND  h.hotelName = 'Grosvenor Hotel'
;
--Q7
SELECT h.hotelName,
       SUM(r.price) AS "Total Income"
  FROM Booking b
  JOIN Hotel h ON h.hotelNo = b.hotelNo
  JOIN Room  r ON r.hotelNO = h.hotelNo
              AND r.roomNO  = b.roomNo
  WHERE h.hotelName LIKE '%Grosvenor%'
  AND   b.dateFrom  <= DATE '2016-09-29'
  AND  (b.dateTo Is NULL OR dateTo >= DATE '2016-09-29')
  GROUP BY h.hotelName;

--Q8
SELECT h.hotelName,
      r.type     ,
      SUM(r.price) AS "Price"
  FROM Booking b
  JOIn Hotel h ON h.hotelNo = b.hotelNo
  JOIN Room  r ON r.hotelNo = h.hotelNo
       AND        r.roomNo  = b.roomNo
  WHERE b.dateFrom <= DATE '2016-09-29'
  AND  ( dateTo IS NULL OR dateto >= DATE '2016-09-29')
  GROUP BY h.hotelName, r.type
  ORDER BY h.hotelName ASC, r.type ASC;

--Q9
SELECT h.hotelName
FROM Hotel h
 LEFT JOIN Room r ON h.hotelNo = r.hotelNo
  WHERE r.hotelNo IS NULL
 GROUP BY h.hotelName;


-- Q10
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
