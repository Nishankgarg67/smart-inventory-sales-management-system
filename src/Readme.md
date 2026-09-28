# Smart Inventory and Sales Management System

A Java-based inventory management project that I am building step by step while learning Java and backend development.

The idea is to gradually develop this project from a simple console application into a complete inventory and sales management system.

## Current Version — Version 1

Version 1 is a console-based Java application focused on product and inventory management.

The main purpose of this version was to practice Java fundamentals, Object-Oriented Programming, Collections, CRUD operations, and basic input validation.

### Features

- Add a new product
- Prevent duplicate product IDs
- Validate product price, quantity, and minimum stock
- View all products
- Search product by ID
- Search product by name
- Update product price
- Update product quantity
- Update product category
- Update minimum stock level
- Delete a product
- View low-stock products

## Technologies Used

- Java
- Object-Oriented Programming
- Java Collections (`ArrayList`)
- IntelliJ IDEA
- Git
- GitHub

## Project Structure

```text
SmartInventorySystem
└── src
    ├── Main.java
    └── com.inventory
        ├── ProductManager.java
        ├── model
        │   └── Product.java
        └── result
            ├── AddProductResult.java
            ├── UpdateCategoryResult.java
            ├── UpdateMinimumStockResult.java
            ├── UpdatePriceResult.java
            └── UpdateQuantityResult.java