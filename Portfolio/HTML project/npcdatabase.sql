
CREATE TABLE Doctors (
  doctorID INT PRIMARY KEY,
  name VARCHAR(50) NOT NULL ,
  officeNo INT,
  telephoneNo INT,
  email VARCHAR(50) UNIQUE,
  shiftPattern CHAR CHECK (shiftPattern IN ('F','f','P','p'))
);
CREATE TABLE CONTACTS (
	name VARCHAR(80),
	email VARCHAR (80),
	query TEXT
) ;

CREATE TABLE Pets (
  petID INT PRIMARY KEY AUTO_INCREMENT CHECK (petID BETWEEN 1000 AND 3000),
  ownerName VARCHAR(50),
  ownerAddress VARCHAR(255),
  petName VARCHAR(50),
  petSpecies VARCHAR(50),
  petBreed VARCHAR(50),
  petGender CHAR CHECK (petGender IN ('M','m','F','f')),
  petAge INT CHECK (petAge BETWEEN 1 AND 12),
  petColour VARCHAR(50),
  petWeight DECIMAL(3,1),
  UNIQUE (ownerName, ownerAddress, petName)
);
 

CREATE TABLE Appointments (
  appointmentID INT AUTO_INCREMENT PRIMARY KEY,
  apptDate DATE CHECK ((TO_CHAR(apptDate, 'd')) IN ('2', '6')),
  petID INT,
  doctorID INT,
  attendance CHAR CHECK (attendance IN ('Y', 'y', 'N', 'n')),
  feeDue DECIMAL(3, 2),
  cancellationFeePaid CHAR CHECK (cancellationFeePaid IN ('Y', 'y', 'N', 'n')),
  UNIQUE (petID, apptDate),
  FOREIGN KEY (petID) REFERENCES Pets(petID) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (doctorID) REFERENCES Doctors(doctorID) ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE Diagnosis (
  diagnosisID INT AUTO_INCREMENT PRIMARY KEY,
  appointmentID INT,
  diagnosisDesc VARCHAR(255),
  medicationRequired VARCHAR(255),
  medicationQuantity INT,
  cost DECIMAL(4,2),
  referralDeferral CHAR CHECK (referralDeferral IN ('R','r','D','d')),
  referralDeferralDesc VARCHAR(255),
FOREIGN KEY (appointmentID) REFERENCES Appointments(appointmentID) ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE Nurses (
  nurseID INT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  telephoneNo INT,
  email VARCHAR(50),
  shiftPattern CHAR CHECK (shiftPattern IN ('F', 'f', 'P', 'p'))
);


CREATE TABLE NurseAssignment (
  nurseID INT,
  appointmentID INT,
  PRIMARY KEY (nurseID, appointmentID),
FOREIGN KEY (appointmentID) REFERENCES Appointments(appointmentID) ON UPDATE CASCADE ON DELETE RESTRICT,
FOREIGN KEY (nurseID) REFERENCES Nurses(nurseID) ON UPDATE CASCADE ON DELETE RESTRICT

);

ALTER TABLE Pets AUTO_INCREMENT=1000;

ALTER TABLE Appointments AUTO_INCREMENT=1000;

ALTER TABLE Diagnosis AUTO_INCREMENT=1000;




INSERT INTO Pets (ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ('David', 'chappyDog', 'Dog', 'Alsation', 'M', '2', 'Beige', '3');
 
INSERT INTO Pets (ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ( 'Sam', 'chiwado', 'Dog', 'Chiwawa', 'F', '10', 'Black', '1');
 
INSERT INTO Pets (ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ('Craig', 'bullyTom', 'Dog', 'Bull Dog', 'F', '6', 'Grey', '4.5');
 
INSERT INTO Pets (ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ('George', 'terryToe', 'Dog', 'Terrier', 'F', '4', 'White', '1.2');
 
INSERT INTO Pets (ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ('Kylie', 'poody', 'Dog', 'Boxer', 'M', '8', 'Black', '1');
 
INSERT INTO Pets ( ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ( 'Chabbu', 'dood', 'Dog', 'Dalmation', 'F', '3', 'Spotted', '7');
 
INSERT INTO Pets ( ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ( 'Sarah', 'dood', 'Dog', 'SheepWolf', 'M', '11', 'Brown', '10');
 
INSERT INTO Pets ( ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ( 'Kylie', 'labbyDee', 'Dog', 'Labrador', 'M', '12', 'White', '11');
 
INSERT INTO Pets ( ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ( 'Kylie', 'shiTzo', 'Dog', 'Shih Tzu', 'F', '7', 'Mixed Brown', '1');
 
INSERT INTO Pets ( ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ( 'Gabby', 'jake', 'Dog', 'Shih Tzu', 'M', '3', 'Greyish White', '4');
 
INSERT INTO Pets ( ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ('Kylie', 'gotty', 'Dog', 'Shih Tzu', 'M', '4', 'Mixed Brown', '1');
 
 

INSERT INTO Doctors (doctorID, name, officeNo, shiftPattern, email)
VALUES ('2200', 'Cleverly', '12', 'P', 'cleverly_cl@noahs.com');
 
INSERT INTO Doctors (doctorID, name, officeNo, shiftPattern, email)
VALUES ('2201', 'Mike', '34', 'F', 'mikeK@noahs.com');
 
INSERT INTO Doctors (doctorID, name, officeNo, shiftPattern, email)
VALUES ('2202', 'Farraday', '34', 'F', 'farradayF@noahs.com');
 
INSERT INTO Doctors (doctorID, name, officeNo, shiftPattern, email)
VALUES ('2203', 'Fred', '41', 'F', 'fredF@noahs.com');
 
INSERT INTO Doctors (doctorID, name, officeNo, shiftPattern, email)
VALUES ('2204', 'Watson', '1', 'F', 'watsS@noahs.com');
 
INSERT INTO Doctors (doctorID, name, officeNo, shiftPattern, email)
VALUES ('2205', 'Freeman', '2', 'F', 'freemanF');
 
INSERT INTO Doctors (doctorID, name, officeNo, shiftPattern, email)
VALUES ('2206', 'Crowley', '10', 'F', 'crowleyC@noahs.com');
 
INSERT INTO Doctors (doctorID, name, officeNo, shiftPattern, email)
VALUES ('2207', 'Rahib', '16', 'F', 'rahibB@noahs.com');
 


INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1000', '2201', Date('2019/08/05'));
 
INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1003', '2200', Date('2019/08/05'));
 
INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1004', '2201', Date('2019/08/09'));
 
INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1001', '2202', Date('2019/08/16'));
 
INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1006', '2204', Date('2019/10/07'));
 
INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1002', '2203', Date('2019/10/11'));
 
INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1005', '2206', Date('2019/09/02'));
 
INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1008', '2202', Date('2019/09/06'));
 
INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1006', '2204', Date('2019/09/27'));
 
INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1007', '2203', Date('2019/10/04'));
 


INSERT INTO Diagnosis ( appointmentID, diagnosisDesc, cost, referralDeferral, referralDeferralDesc)
VALUES ( '1000', 'Needs socialisation treats', '10', 'D', 'Bring him Tuesdays 10 to 12.00pm');
 
INSERT INTO Diagnosis ( appointmentID, diagnosisDesc, cost, referralDeferral, referralDeferralDesc)
VALUES ( '1001', 'Ultrasonic dental scaling in two weeks', '10', 'R', 'Get Drug AA from Smiths Pharmacy');
 
INSERT INTO Diagnosis ( appointmentID, diagnosisDesc, cost, referralDeferralDesc)
VALUES ( '1002', 'Overgrown Skin', '20','Take Park walks every evening');
 
INSERT INTO Diagnosis ( appointmentID, diagnosisDesc, cost, referralDeferral, referralDeferralDesc)
VALUES ('1003', 'Needs socialisation treats', '15', 'D', 'Surgery on 21-Nov-2019');
 



INSERT INTO Pets ( ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ( 'Robbie', 'Moses', 'Dog', 'Border Collie', 'M', '6', 'Black and white', '25');
 
INSERT INTO Pets ( ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ( 'Robbie', 'Panucci', 'Gecko', 'Crested Gecko', 'M', '2', 'Orange', '0.2');
 
INSERT INTO Pets ( ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ( 'Marcus', 'Angelo', 'Cat', 'Maine Coon', 'M', '4', 'Ginger', '3');
 
INSERT INTO Pets ( ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ('Jeremiah', 'Dennis', 'Dog', 'French Bulldog', 'M', '6', 'Greyish White', '4');
 
INSERT INTO Pets ( ownerName, petName, petSpecies, petBreed, petGender, petAge, petColour, petWeight)
VALUES ( 'Kylie', 'Jess', 'Dog', 'Greyhound', 'F', '10', 'Brown', '15');



INSERT INTO Doctors (doctorID, name, officeNo, shiftPattern, email)
VALUES ('2208', 'Holmes', '8', 'P', 'holmesS@noahs.com');
 
INSERT INTO Doctors (doctorID, name, officeNo, shiftPattern, email)
VALUES ('2209', 'Sheila', '30', 'F', 'sheilaK@noahs.com');
 
INSERT INTO Doctors (doctorID, name, officeNo, shiftPattern, email)
VALUES ('2210', 'Khan', '34', 'F', 'khanS@noahs.com');
 
INSERT INTO Doctors (doctorID, name, officeNo, shiftPattern, email)
VALUES ('2211', 'Freddie', '40', 'F', 'freddieG@noahs.com');
 
INSERT INTO Doctors (doctorID, name, officeNo, shiftPattern, email)
VALUES ('2212', 'Wilson', '2', 'P', 'wilsonW@noahs.com');



INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1011', '2208', Date('2019/07/01'));
 
INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1012', '2208', Date('2019/07/08'));
 
INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1005', '2210', Date('2019/12/13'));
 
INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1001', '2208', Date('2019/04/05'));
 
INSERT INTO Appointments ( petID, doctorID, apptDate)
VALUES( '1006', '2201', Date('2019/12/02'));






INSERT INTO Nurses (nurseID, name, telephoneNo, email, shiftPattern)
VALUES ('3000', 'Robbie', '4592', 'robbie@noahs.com', 'P');
 
INSERT INTO Nurses (nurseID, name, telephoneNo, email, shiftPattern)
VALUES ('3001', 'Chris', '2927', 'chris@noahs.com', 'P');
 
INSERT INTO Nurses (nurseID, name, telephoneNo, email, shiftPattern)
VALUES ('3002', 'Olivia', '1819', 'olivia@noahs.com', 'F');
 
INSERT INTO Nurses (nurseID, name, telephoneNo, email, shiftPattern)
VALUES ('3003', 'Rob', '9921', 'rob@noahs.com', 'F');
 
INSERT INTO Nurses (nurseID, name, telephoneNo, email, shiftPattern)
VALUES ('3004', 'Joy', '1192', 'joy@noahs.com', 'F');
 








