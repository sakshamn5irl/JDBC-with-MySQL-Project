/*						#Customer Table
						#To add customer in the database
*/
create database assignment;
use assignment;

CREATE TABLE `assignment`.`customers` (
  `Customers_ID` INT NOT NULL AUTO_INCREMENT,
  `Customers_FirstName` VARCHAR(45) NOT NULL,
  `Customers_LastName` VARCHAR(45) NOT NULL,
  `Customers_Address` VARCHAR(45) NOT NULL,
  `Customers_Number` BIGINT(20) NOT NULL,
  PRIMARY KEY (`Customers_ID`));

							#Employee Table
							#To add employee in the database
CREATE TABLE `assignment`.`employee` (
  `employee_ID` INT NOT NULL AUTO_INCREMENT,
  `employee_FirstName` VARCHAR(45) NOT NULL,
  `employee_LastName` VARCHAR(45) NOT NULL,
  `employee_Gender` VARCHAR(45) NOT NULL,
  `employee_Designation` VARCHAR(45) NOT NULL,
  `employee_Department` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`employee_ID`));

							#Item Table
							#To add item in the database
CREATE TABLE `assignment`.`items` (
  `Item_ID` INT NOT NULL AUTO_INCREMENT,
  `item_Name` VARCHAR(45) NOT NULL,
  `item_Type` VARCHAR(45) NOT NULL,
  `item_Price` INT NOT NULL,
  `item_Quantity` INT NOT NULL,
  PRIMARY KEY (`Item_ID`));

							#Login Table
							#To give user priveledge to login into system
CREATE TABLE `assignment`.`login` (
  `Login_Username` VARCHAR(45) NOT NULL,
  `Login_Password` VARCHAR(45) NOT NULL,
  `Login_Type` TINYINT(2) NOT NULL);
  
							#Orders
							#To generate the bill
CREATE TABLE `assignment`.`order` (
  `order_Id` INT NOT NULL AUTO_INCREMENT,
  `customer_Name` VARCHAR(100) NOT NULL,
  `item_Name` VARCHAR(45) NOT NULL,
  `item_Quantity` INT NOT NULL,
  `billing_Amount` INT NOT NULL,
  PRIMARY KEY (`order_Id`));

#			******		Create Table BackUp to Save all the delete    ******
DROP TABLE IF EXISTS backup;

CREATE TABLE backup
(
      Item_ID    INT     NOT NULL,
    Item_Name VARCHAR(50),
    action_type     VARCHAR(50),
    action_date     DATETIME NOT NULL
);


							#show Columns
							#To return the name of the columns from the table
show Columns from employee;

#            *****       Trigger on employee to change the gender into UpperCase    *****

DELIMITER //
CREATE TRIGGER employee_before_update
  BEFORE UPDATE ON employee
  FOR EACH ROW 
BEGIN
  SET NEW.Employee_Gender= UPPER(NEW.Employee_Gender);
END// 



						# Trigger to save the deleted row from the table to another table
DELIMITER //
DROP TRIGGER IF EXISTS save_after_delete;
CREATE  TRIGGER save_after_delete
    AFTER DELETE on items
    FOR EACH ROW
BEGIN
    INSERT INTO backup VALUES
    (OLD.Item_ID, OLD.Item_Name, "DELETED", NOW());   
END//

						#Preapared Satement to write into csv file
SET @TS = DATE_FORMAT(NOW(),'_%Y_%m_%d_%H_%i_%s');
SET @FOLDER = 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/csvFile';
SET @PREFIX = 'orders';
SET @EXT    = '.csv';
 
SET @CMD = CONCAT("SELECT * FROM employee INTO OUTFILE '",@FOLDER,@PREFIX,@TS,@EXT,
    "' FIELDS ENCLOSED BY '\"' TERMINATED BY ';' ESCAPED BY '\"'",
    "  LINES TERMINATED BY '\r\n';");
PREPARE statement FROM @CMD;
EXECUTE statement;


/*

					**************   STORED PROCEDURE   ******************


*/
					#Store procedure to calculate the salary of the employee
CREATE DEFINER=`root`@`localhost` PROCEDURE `caculateSalary`( 
	
Fname varchar(50),
Lname varchar(50)
)
BEGIN
	Declare Edes char(50);
    Declare Edep char(50);
	
      /*  select Employee_Designation,Employee_Department from employee
		where Employee_FirstName = Fname and Employee_LastName = Lname
        */
        set Edes = (select Employee_Designation from employee
		where Employee_FirstName = Fname and Employee_LastName = Lname);
        set Edep = (select Employee_Department from employee
		where Employee_FirstName = Fname and Employee_LastName = Lname);
        
	
        
	If (Edes = 'Store Person' and Edep = 'Store Keeping')
		then
        select Employee_FirstName, 10 * 2 as salary from employee where Employee_FirstName = Fname and Employee_LastName = Lname ;
	elseif (Edes = 'Billing Person' and Edep = 'Accounts')
		then
        select Employee_FirstName, 8 * 8 as salary from employee where Employee_FirstName = Fname and Employee_LastName = Lname ;
    elseif (Edes = 'Stock Person' and Edep = 'Inventory')
		then
        select Employee_FirstName, 7 * 7 as salary from employee where Employee_FirstName = Fname and Employee_LastName = Lname ;
	elseif (Edes = 'Billing Person' and Edep = 'Maintenance')
		then
        select Employee_FirstName, 10 * 5 as salary from employee where Employee_FirstName = Fname and Employee_LastName = Lname ;
	end if;
END


	/*
    *
    *					********************   Delete Employee Stored Procedure   ***************
    *
    * 
    */
    CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteEmployeeSP`(

	  IN FName    varchar(50),
      IN LName    varchar(50)
)
BEGIN
     DELETE 
     FROM  employee
     WHERE  
     Employee_FirstName = FName and
     Employee_LastName = LName;
END

/*
*
*							************   Delete Item Stored Procedure  *************
*
*
*/
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteItemSP`(
IN iID    integer
)
BEGIN
	 DELETE 
     FROM  items
     WHERE  
     Item_ID = iID;
  
END

/*
*
*
*							++++++++++  View For Stock ++++++++++++
*
*/
USE `assignment`;
CREATE  OR REPLACE VIEW  stock1 AS
SELECT Item_ID,Item_Name,Item_Type ,Item_Price,Item_Quantity, Item_Price * Item_Quantity as 'Stock_value'
FROM items;


select * from stock1;

/*
*
*							Triggers To trim the input taken from the customer
*
*
*/
DELIMITER 
CREATE TRIGGER `trimCustomersTrigger` 
BEFORE INSERT 
ON customers FOR EACH ROW
BEGIN
SET NEW.Customers_FirstName = TRIM(NEW.Customers_FirstName);
SET NEW.Customers_LastName = TRIM(NEW.Customers_LastName);
SET NEW.Customers_Address = TRIM(NEW.Customers_Address);
END;

/*
*
*						Triggers To trim the input taken from the employee
*
*
*/

DELIMITER 
CREATE TRIGGER `trimEmployeeTrigger` 
BEFORE INSERT 
ON employee FOR EACH ROW
BEGIN
SET NEW.Employee_FirstName = TRIM(NEW.Employee_FirstName);
SET NEW.Employee_LastName = TRIM(NEW.Employee_LastName);
SET NEW.Employee_Designation = TRIM(NEW.Employee_Designation);
SET NEW.Employee_Department = TRIM(NEW.Employee_Department);
END;
  
#   employee view
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `employee_view` AS
    SELECT 
        `employee`.`Employee_ID` AS `Employee_ID`,
        `employee`.`Employee_FirstName` AS `Employee_FirstName`,
        `employee`.`Employee_LastName` AS `Employee_LastName`,
        `employee`.`Employee_Gender` AS `Employee_Gender`,
        `employee`.`Employee_Designation` AS `Employee_Designation`,
        `employee`.`Employee_Department` AS `Employee_Department`
    FROM
        `employee`
        
#customer view
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `customer_view` AS
    SELECT 
        `customers`.`Customer_ID` AS `Customer_ID`,
        `customers`.`Customer_FirstName` AS `Customer_FirstName`,
        `customers`.`Customer_LastName` AS `Customer_LastName`,
        `customers`.`Customer_Address` AS `Customer_Address`,
        `customers`.`Customer_Number` AS `Customer_Number`
    FROM
        `customers`