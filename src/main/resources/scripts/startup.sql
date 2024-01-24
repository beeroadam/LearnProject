-- Delete all records from the tables
DELETE FROM attribute;
DELETE FROM productDetails;
DELETE FROM product;

-- Add more Products
INSERT INTO product(id, name, category) VALUES (1, 'iPhone 12', 'ELECTRONICS');
INSERT INTO product(id, name, category) VALUES (2, 'Levis Jeans', 'CLOTHING');
INSERT INTO product(id, name, category) VALUES (3, 'Samsung Galaxy S21', 'ELECTRONICS');
INSERT INTO product(id, name, category) VALUES (4, 'T-Shirt', 'CLOTHING');
INSERT INTO product(id, name, category) VALUES (5, 'Pants', 'CLOTHING');
INSERT INTO product(id, name, category) VALUES (6, 'The Alchemist', 'BOOKS');
INSERT INTO product(id, name, category) VALUES (7, 'Microwave Oven', 'HOME_APPLIANCES');

-- Add more ProductDetails
INSERT INTO productDetails(id, description, leadPrice, strikeOutPrice, quantity, imageUrl, isActive, createdAt, updatedAt, product_id) VALUES (1, 'Latest iPhone Model', 1150.99, NULL, 10, 'www.iphone12.com', true, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 1);
INSERT INTO productDetails(id, description, leadPrice, strikeOutPrice, quantity, imageUrl, isActive, createdAt, updatedAt, product_id) VALUES (2, 'Comfortable blue jeans', 80.00, 100.00, 50, 'www.levis.com', true, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 2);
INSERT INTO productDetails(id, description, leadPrice, strikeOutPrice, quantity, imageUrl, isActive, createdAt, updatedAt, product_id) VALUES (3, 'Samsung smartphone model', 850.99, NULL, 15, 'www.samsungs21.com', true, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 3);
INSERT INTO productDetails(id, description, leadPrice, strikeOutPrice, quantity, imageUrl, isActive, createdAt, updatedAt, product_id) VALUES (4, 'Casual T-shirt', 25.00, NULL, 100, 'www.tshirt.com', true, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 4);
INSERT INTO productDetails(id, description, leadPrice, strikeOutPrice, quantity, imageUrl, isActive, createdAt, updatedAt, product_id) VALUES (5, 'Comfortable Pants', 35.00, NULL, 80, 'www.pants.com', true, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 5);
INSERT INTO productDetails(id, description, leadPrice, strikeOutPrice, quantity, imageUrl, isActive, createdAt, updatedAt, product_id) VALUES (6, 'Best-seller book by Paulo Coelho', 15.00, NULL, 500, 'www.alchemist.com', true, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 6);
INSERT INTO productDetails(id, description, leadPrice, strikeOutPrice, quantity, imageUrl, isActive, createdAt, updatedAt, product_id) VALUES (7, 'Kitchen appliance', 120.00, NULL, 30, 'www.microwave.com', true, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 7);
INSERT INTO productDetails(id, description, leadPrice, strikeOutPrice, quantity, imageUrl, isActive, createdAt, updatedAt, product_id) VALUES (8, 'Latest iPhone Model', 1000.00, 1200.00, 8, 'www.iphone12.com', true, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 1);
INSERT INTO productDetails(id, description, leadPrice, strikeOutPrice, quantity, imageUrl, isActive, createdAt, updatedAt, product_id) VALUES (9, 'Comfortable white jeans', 75.00, NULL, 20, 'www.whitejeans.com', true, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 2);

-- Add more Attributes
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (1, 'color', 'blue', 2);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (2, 'size', 'Medium', 2);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (3, 'material', 'denim', 2);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (4, 'Battery', '4500mAh',3 );
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (5, 'RAM', '8GB', 3);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (6, 'color', 'red', 4);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (7, 'size', 'Large', 4);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (8, 'material', 'cotton', 4);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (9, 'length', '32in', 5);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (10, 'color', 'black', 5);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (11, 'Author', 'Paulo Coelho', 6);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (12, 'Language', 'English', 6);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (13, 'capacity', '25L', 7);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (14, 'color', 'silver', 7);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES(15, 'Display', '6.2in', 8);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (16, 'RAM', '12GB', 8);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (17, 'color', 'white', 9);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (18, 'size', 'Small', 9);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (19, 'material', 'denim', 9);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (20, 'Display', '6.2in', 1);
INSERT INTO attribute(id, name, attribute_value, product_details_id) VALUES (21, 'RAM', '12GB', 1);

-- Add Users
INSERT INTO users (id, username, password) VALUES (1, 'adminUser', 'adminPass');
INSERT INTO users (id, username, password) VALUES (2, 'normalUser', 'userPass');
-- Insert the roles of the user into the user_roles table
INSERT INTO user_roles (user_id, roles) VALUES (1, 'USER');
INSERT INTO user_roles (user_id, roles) VALUES (1, 'ADMIN');
INSERT INTO user_roles (user_id, roles) VALUES (2, 'USER');