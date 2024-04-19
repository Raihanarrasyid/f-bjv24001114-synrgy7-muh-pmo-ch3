CREATE TABLE
    User (
        id INT PRIMARY KEY AUTO_INCREMENT,
        username VARCHAR(255) NOT NULL,
        email_address VARCHAR(255) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL
    );

CREATE TABLE
    `Order` (
        id INT PRIMARY KEY AUTO_INCREMENT,
        order_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        destination_address VARCHAR(255) NOT NULL,
        user_id INT,
        completed BOOLEAN DEFAULT FALSE,
        FOREIGN KEY (user_id) REFERENCES User (id)
    );

CREATE TABLE
    Merchant (
        id INT PRIMARY KEY AUTO_INCREMENT,
        merchant_name VARCHAR(255) NOT NULL,
        merchant_location VARCHAR(255) NOT NULL,
        open BOOLEAN DEFAULT TRUE
    );

CREATE TABLE
    Product (
        id INT PRIMARY KEY AUTO_INCREMENT,
        product_name VARCHAR(255) NOT NULL,
        price DECIMAL(10, 2) NOT NULL,
        merchant_id INT,
        FOREIGN KEY (merchant_id) REFERENCES Merchant (id)
    );

CREATE TABLE
    OrderDetail (
        id INT PRIMARY KEY AUTO_INCREMENT,
        order_id INT,
        product_id INT,
        quantity INT NOT NULL,
        total_price DECIMAL(10, 2) NOT NULL,
        FOREIGN KEY (order_id) REFERENCES `Order` (id),
        FOREIGN KEY (product_id) REFERENCES Product (id)
    );