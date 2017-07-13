--  To comment a line, start line with double hyphens --
-- 
--  Use comments to label exercise or assignment questions
--    plus any documentation as relevant.
--
-- ---------------------------------------------------------
--  The next SQL*Plus command line sets SQL*Plus echo on.
--  With echo on, commands inside script files like this one
--    will be echoed to the screen, together with any output 
--    results.
--  If spool is set to an output text file at the same 
--    time, the same output will be sent to the spool
--    file also. This spool file is for submission.
SET ECHO ON
--
-- ---------------------------------------------------------
--  The next SQL*Plus command line starts spooling all on-screen 
--    displays to the designated SPOOL text file, and includes all 
--    commands from the SQL script file, and all output from Oracle.
--    Ideal for exercise and assignment submissions.
--  Make sure that the referred to folder is first created; in this
--    example, folder D:\WORK\2714 must forst be created.
--  ** Change D:\WORK\2714 path to the folder path you are using **
--
SPOOL C:\Users\Kunlaya\workspace\COMP2714\Lab01_KobunnoiK.txt
--
-- ---------------------------------------------------------
--  The following comments will also be sent / spooled to the 
--    output text file to identify the ownership of the
--    Oracle SQL*Plus work.
--
--  COMP 2714 
--  SET 2C
--  LAB01 Exercise / Assignment Asn00
--  Kobunnoi, Kunlaya    A00959419
--  email: kunlaya_kobunnoi@bcit.ca
--
-- ---------------------------------------------------------
--  By default, Oracle uses the Windows Regional Option settings
--    for the date and time format. To avoid confusion and to keep
--    a consistent format, use Oracle's ALTER SESSION statement to
--    change the current session's date format to YYYY-MM-DD, and 
--    then verify by displaying the current system date SYSDATE.
--
ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD';
--
SELECT SYSDATE
FROM DUAL;
--
-- ---------------------------------------------------------
--  The next line uses the SQL*Plus START command to run a 
--    SQL script file (assumed to be located in D:\WORK\2714).
--  ** If needed, change D:\... path to the folder you are using **
--
--  Once you have made all the necessary changes, save this current
--    script file to disk. Then inside the SQL*Plus command window
--    command prompt, type the following START command to execute.
--
--  START C:\Users\Kunlaya\workspace\COMP2714\Lab01_KobunnoiK.sql
-- ---------------------------------------------------------
-- Drop tables first before creating
DROP TABLE OrdProd;
DROP TABLE OrderTbl;
DROP TABLE Product;
DROP TABLE Customer;
--
-- Q1. Create Customer Table  - Note sample coding style
CREATE TABLE Customer
(CustNo           CHAR(8)       NOT NULL
,CustFirstName    VARCHAR2(20)  NOT NULL
,CustLastName     VARCHAR2(30)  NOT NULL
,CustStreet       VARCHAR2(50)  NOT NULL
,CustCity         VARCHAR2(30)  NOT NULL
,CustState        CHAR(2)       NOT NULL
,CustZip          CHAR(10)      NOT NULL
,CustBal          DECIMAL(12,2) DEFAULT 0 NOT NULL
,CONSTRAINT PKCustomer PRIMARY KEY (CustNo)
);
-- Q2. Create Product Table
CREATE TABLE Product
(ProdNo            CHAR(8)       NOT NULL
,ProdName          VARCHAR2(50)  NOT NULL
,ProdMfg           VARCHAR2(20)  NOT NULL
,ProdQOH           DECIMAL(10)   NOT NULL
,ProdPrice         DECIMAL(12,2) NOT NULL
,ProdNextShipDate  DATE
,CONSTRAINT PKProduct PRIMARY KEY (ProdNo)
);
-- Q3. Create Order Table
CREATE TABLE OrderTbl
(OrdNo      CHAR(8)  NOT NULL
,OrdDate    DATE     NOT NULL
,CustNo     CHAR(8)  NOT NULL
,CONSTRAINT PKOrder PRIMARY KEY (OrdNo)
,CONSTRAINT FKCustNo FOREIGN KEY (CustNo) REFERENCES Customer (CustNo)
);
-- Q4. Create Ordered Product Table
CREATE TABLE OrdProd
(OrdNo    CHAR(8)     NOT NULL
,ProdNo   CHAR(8)     NOT NULL
,Qty      DECIMAL(10) DEFAULT 1 NOT NULL
,CONSTRAINT PKOrdProd PRIMARY KEY (OrdNo, ProdNo)
,CONSTRAINT FKOrdNo FOREIGN KEY (OrdNo) REFERENCES OrderTbl (OrdNo)
  ON DELETE CASCADE 
,CONSTRAINT FKProdNo FOREIGN KEY (ProdNo) REFERENCES Product (ProdNo)
);
--
--  ... Additional sections of SQL commands / statements.
--      Note the sample coding style.
--
-- The sQL*Plus command DESCRIBE can be used to display the table info
DESCRIBE Customer;
DESCRIBE Product;
DESCRIBE OrderTbl;
DESCRIBE OrdProd;
--  
--  Q5. Insert some sample data - Note sample coding style
--
-- Insert Customer Data
INSERT INTO Customer
  VALUES('C0954327','Sheri','Gordon','336 Hill St.','Littleton','CO',
         '80129-5543',230.00);
-- Insert Product Data
INSERT INTO Product
  VALUES ('P1445671','Color Laser Printer','Intersafe',33,14.99,NULL);
-- Insert Order Data
INSERT INTO OrderTbl
  VALUES ('O1116324',DATE'2015-01-23','C0954327');
-- Insert Ordered Product Data
INSERT INTO OrdProd
  VALUES('O1116324','P1445671',5);
COMMIT;
--
-- Q6. Display inserted data
--
SELECT * 
FROM Customer;
--
SELECT *
FROM Product;
--
SELECT *
FROM OrderTbl
WHERE OrdDate = DATE'2015-01-23'
  AND CustNo = 'C0954327';
--
SELECT *
FROM OrdProd;
-- 
-- Q.7  - Note sample coding style
--
SELECT OrdNo, OrdDate, CustNo
FROM OrderTbl
WHERE OrdDate = DATE'2015-01-23'
  AND CustNo = 'C0954327';
--
-- Q.8  - Note sample coding style
--
SELECT c.CustNo, CustFirstName, CustLastName, 
       o.OrdNo, OrdDate, p.ProdNo, ProdName, ProdPrice
FROM Customer c
       JOIN OrderTbl o 
         ON c.CustNo = o.CustNo
       JOIN OrdProd op
         ON o.OrdNo = op.OrdNo
       JOIN Product p
         ON op.ProdNo = p.ProdNo
WHERE OrdDate > DATE'2015-01-01'
  AND c.CustNo = 'C0954327';
--
-- Q.9  - Note use of SQL*Plus commands SET and COLUMN
--        Compare the output differences between Q.8 and Q.9
--
SET LINESIZE 100
SET PAGESIZE 60
COLUMN CustFirstName FORMAT A10
COLUMN CustLastName  FORMAT A15
COLUMN ProdName      FORMAT A15
COLUMN ProdPrice     FORMAT 9999.99
--
SELECT c.CustNo, CustFirstName, CustLastName, 
       o.OrdNo, OrdDate, p.ProdNo, ProdName, ProdPrice
FROM Customer c
       JOIN OrderTbl o 
         ON c.CustNo = o.CustNo
       JOIN OrdProd op
         ON o.OrdNo = op.OrdNo
       JOIN Product p
         ON op.ProdNo = p.ProdNo
WHERE OrdDate > DATE'2015-01-01'
  AND c.CustNo = 'C0954327';
--
--
--  ** This last SQL*Plus SPOOL command line is MOST IMPORTANT !! **
--  It is usually the last line of the script file, and its purpose 
--    is to close off the current spool output text file, so that all 
--    output from this script will be flushed to disk and saved to the 
--    SPOOL text file. 
--  Otherwise, you may end up missing output content.
--
SPOOL OFF
