CREATE TABLE Users (
    email VARCHAR(30) PRIMARY KEY,
    password VARCHAR(30),
    lastName VARCHAR(30),
    firstName VARCHAR(30),
    type ENUM("employee", "admin")
);

CREATE TABLE Items (
	itemID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    price FLOAT,
    stock INT,
    description VARCHAR(120)
);

CREATE TABLE Orders (
	orderID INT AUTO_INCREMENT PRIMARY KEY,
    status ENUM("cart", "awaiting", "processing", "failed", "shipped", "completed"),
    lastName VARCHAR(30),
    firstName VARCHAR(30),
    email VARCHAR (60),
    isPickup BOOL,
    street VARCHAR(60),
    city VARCHAR(30),
    state VARCHAR(30),
    zip VARCHAR(30)
);

CREATE TABLE OrderItems (
	orderID INT,
    itemID INT,
    price FLOAT,
    quantity INT,
    PRIMARY KEY (orderID, itemID),
    FOREIGN KEY (orderID) REFERENCES Orders (orderID) ON DELETE CASCADE,
    FOREIGN KEY (itemID) REFERENCES Items (itemID) ON DELETE CASCADE
);

CREATE TABLE Tags (
	tagID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) UNIQUE,
    isSelected BOOL
);

CREATE TABLE ItemTags (
	itemID INT,
    tagID INT,
    PRIMARY KEY (itemID, tagID),
    FOREIGN KEY (itemID) REFERENCES Items (itemID) ON DELETE CASCADE,
    FOREIGN KEY (tagID) REFERENCES Tags (tagID) ON DELETE CASCADE
);