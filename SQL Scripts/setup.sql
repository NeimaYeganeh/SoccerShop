<<<<<<< HEAD:setup.sql
CREATE TABLE Users (
	userID INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(30) UNIQUE,
    password VARCHAR(30),
    lastName VARCHAR(30),
    firstName VARCHAR(30),
    type ENUM("customer", "employee", "admin")
);

CREATE TABLE Items (
	itemID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    price DOUBLE,
    stock INT,
    description VARCHAR(100)
);

CREATE TABLE Orders (
	orderID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT,
    price DOUBLE,
    status ENUM("current", "awaiting", "processing", "failed", "shipped", "completed"),
    isPickup BOOL,
    FOREIGN KEY (userID) REFERENCES Users (userID)
);

CREATE TABLE OrderItems (
	orderID INT,
    itemID INT,
    PRIMARY KEY (orderID, itemID),
    FOREIGN KEY (orderID) REFERENCES Orders (orderID),
    FOREIGN KEY (itemID) REFERENCES Items (itemID)
);

CREATE TABLE Tags (
	tagID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30)
);

CREATE TABLE ItemTags (
	itemID INT,
    tagID INT,
    PRIMARY KEY (itemID, tagID),
    FOREIGN KEY (itemID) REFERENCES Items (itemID),
    FOREIGN KEY (tagID) REFERENCES Tags (tagID)
=======
CREATE TABLE Users (
	userID INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(30) UNIQUE,
    password VARCHAR(30),
    lastName VARCHAR(30),
    firstName VARCHAR(30),
    type ENUM("customer", "employee", "admin")
);

CREATE TABLE Items (
	itemID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    price DOUBLE,
    stock INT,
    description VARCHAR(100)
);

CREATE TABLE Orders (
	orderID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT,
    price DOUBLE,
    status ENUM("placed", "confirmed", "shipping", "shipped", "delivered", "at store"),
    FOREIGN KEY (userID) REFERENCES Users (userID)
);

CREATE TABLE OrderItems (
	orderID INT,
    itemID INT,
    PRIMARY KEY (orderID, itemID),
    FOREIGN KEY (orderID) REFERENCES Orders (orderID),
    FOREIGN KEY (itemID) REFERENCES Items (itemID)
);

CREATE TABLE Tags (
	tagID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30)
);

CREATE TABLE ItemTags (
	itemID INT,
    tagID INT,
    PRIMARY KEY (itemID, tagID),
    FOREIGN KEY (itemID) REFERENCES Items (itemID),
    FOREIGN KEY (tagID) REFERENCES Tags (tagID)
>>>>>>> 64d3fedb9125538c7e62c43194d6cd10be327e97:SQL Scripts/setup.sql
);