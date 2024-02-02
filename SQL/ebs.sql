CREATE DATABASE EBS;
USE EBS;

CREATE TABLE AdminDetails (
    Id INT PRIMARY KEY,
    First_Name VARCHAR(50) NOT NULL,
    Last_Name VARCHAR(50) NOT NULL,
    Password VARCHAR(20) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Mob_Number VARCHAR(10) NOT NULL,
    Location VARCHAR(100) NOT NULL
);

INSERT INTO AdminDetails VALUES (10001,'John','Doe','John@10001','johndoe95@gmail.com','1234567890','Mumbai');
SELECT * FROM AdminDetails;

#-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE SecurityQuestions (
    Id INT PRIMARY KEY,
    Birth_Place VARCHAR(50) NOT NULL,
    Teachers_Name VARCHAR(50) NOT NULL,
    Sport VARCHAR(50) NOT NULL,
    FOREIGN KEY (Id) 
		REFERENCES AdminDetails (Id)
);

INSERT INTO SecurityQuestions VALUES (10001, 'place', 'teacher', 'sport');
SELECT * FROM securityQuestions;

#-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE ConsumerDetails (
    Consumer_Num VARCHAR(8) PRIMARY KEY,
    Full_Name VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL UNIQUE,
    Mob_Number VARCHAR(10) NOT NULL UNIQUE,
    Address VARCHAR(100) NOT NULL,
    Password VARCHAR(20) NOT NULL
);

INSERT INTO ConsumerDetails VALUES ('00000001','Kevin Ross','kross@gmail.com','1234567890','123,Astha apt,Andheri west, mumbai-400056','kevin123');
SELECT * FROM ConsumerDetails;

#CREATING TRIGGER                     
DELIMITER //  
CREATE TRIGGER tgr_consumer_no
BEFORE INSERT ON ConsumerDetails
FOR EACH ROW
BEGIN
    DECLARE next_id VARCHAR(10);
    SET next_id = LPAD((SELECT MAX(Consumer_Num) + 1 FROM ConsumerDetails), 8, '0');
    SET NEW.Consumer_Num = next_id;
END;
//
DELIMITER ;  
                    
#---------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE ConnectionDetails (
    Consumer_Num VARCHAR(8) NOT NULL,
    Meter_Num VARCHAR(8) NOT NULL UNIQUE,
	Full_Name VARCHAR(50) NOT NULL,
    Mob_Number VARCHAR(10) NOT NULL UNIQUE,
    Address VARCHAR(100) NOT NULL,
    Start_Date DATE NOT NULL,
    Type VARCHAR(30) NOT NULL
);

INSERT INTO ConnectionDetails VALUES ('00000001','G4252342','Kevin Ross', '8745642310','123,Astha apt,Andheri west, mumbai-400056','2014-07-15','Residential');
SELECT * FROM ConnectionDetails;
            
#---------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE BillDetails (
	Bill_No VARCHAR(10) PRIMARY KEY,
    Consumer_Num VARCHAR(8),
	Meter_Num VARCHAR(8) NOT NULL,
    Month VARCHAR(10) NOT NULL,
    Current_Reading INT NOT NULL,
    Previous_Reading INT NOT NULL,
    Total_Units INT NOT NULL,
    Previous_Balance DOUBLE NOT NULL,
    Current_Amount DOUBLE NOT NUll,
    Total_Amount DOUBLE NOT NULL,
    Due_Date DATE NOT NULL,
    Payment_Date DATE,
    Status ENUM('Paid', 'Unpaid', 'Pending'),
    
    PRIMARY KEY (Meter_Num,Due_Date)
);

insert into billdetails values('EBS0000001','00000001','G4252342','June',1000,800,200,0,900.00,900.00,'2023-07-21','2023-07-11','Pending');
select * from BillDetails;

#CREATING TRIGGER
DELIMITER //
CREATE TRIGGER tgr_bill_no
BEFORE INSERT
ON BillDetails
FOR EACH ROW
BEGIN
    DECLARE next_bill_no VARCHAR(20);
    
    -- Get the next bill number
    SELECT LPAD(MAX(CAST(SUBSTRING(bill_no, 4) AS UNSIGNED)) + 1, 7, '0')
    INTO next_bill_no
    FROM BillDetails;
    
    -- Set the new bill number
    SET NEW.bill_no = CONCAT('EBS', next_bill_no);
END //
DELIMITER ;

#CREATING EVENT : Create an event to update the status to 'Unpaid' after the due date
-- Enable event scheduling (if not enabled)
SET GLOBAL event_scheduler = ON;

CREATE EVENT update_status_event
ON SCHEDULE EVERY 1 DAY
DO
  UPDATE BillDetails
  SET status = 'Unpaid'
  WHERE Due_Date < CURDATE() AND status='Pending';

#---------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE CostPerUnit (
    0_to_100 DOUBLE NOT NULL,
    101_to_300 DOUBLE NOT NULL,
    301_to_500 DOUBLE NOT NULL,
    501_and_above DOUBLE NOT NULL
);

INSERT INTO CostPerUnit VALUES (3.45,5.55,7.45,8.55);
SELECT * FROM CostPerUnit;

#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------








