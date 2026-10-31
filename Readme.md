# Smart Inventory and Sales Management System

A Java-based inventory management project that I am building step by step while learning Java and backend development.

The idea is to gradually develop this project from a simple console application into a complete inventory and sales management system.

## Current Version — Version 1

Version 2 – JDBC + MySQL Integration

Version 2 upgrades the original console-based application by integrating a MySQL database through Java Database Connectivity (JDBC).

Unlike Version 1, where product information was stored in an in-memory ArrayList, this version stores product data permanently in a MySQL database.

Features

1. Product Management

Add new products.

View all available products.

Search products by ID.

Search products by name or category.

Update product price.

Update product quantity.

Update product category.

Update minimum stock level.

Delete products.

2. Stock Management

View products with low stock.

Identify products whose quantity is less than or equal to their minimum stock level.

Allow zero quantity while preventing negative stock values.

3. Database Integration

Connect Java application to MySQL using JDBC.

Perform CRUD operations using SQL queries.

Use PreparedStatement for parameterized queries.

Retrieve and map database records into Java Product objects.

Persist product information across application restarts.

4. Error Handling

Handle duplicate product IDs.

Validate product information.

Distinguish between products not found and database errors.

Use result enums to communicate operation outcomes between application layers.

Technologies Used

Programming Language: Java

Database: MySQL

Database Connectivity: JDBC

IDE: IntelliJ IDEA

Version Control: Git and GitHub

