package com.inventory.dao;
import com.inventory.model.Product;
import com.inventory.result.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private  Connection getconnection() throws  SQLException {
        String url = "jdbc:mysql://localhost:3306/smart_inventory";
        String username = "root";
        String password = System.getenv("DB_PASSWORD");

        if (password == null || password.isBlank()) {
            throw new IllegalStateException(
                    "DB_PASSWORD environment variable is not set."
            );
        }
      return DriverManager.getConnection(url, username, password);

    }

    public AddProductResult addProduct(Product product) {
        FindProductResponse response = findById(product.getId());
        if(response.getResult() == FindProductResult.SUCCESS){
            return AddProductResult.DUPLICATE_ID;
        }
        if(response.getResult() == FindProductResult.DATABASE_ERROR){
            return AddProductResult.DATABASE_ERROR;
        }
        if(response.getResult() == FindProductResult.PRODUCT_NOT_FOUND)  {
            String sql = "INSERT INTO products(id , name , category , price , quantity , minimum_stock) VALUES (?,?,?,?,?,?)";
            try (
                    Connection connect = getconnection();
                    PreparedStatement pstmt = connect.prepareStatement(sql)) {

                pstmt.setInt(1, product.getId());
                pstmt.setString(2, product.getName());
                pstmt.setString(3, product.getCategory());
                pstmt.setInt(4, product.getPrice());
                pstmt.setInt(5, product.getQuantity());
                pstmt.setInt(6, product.getMinimumStock());

                int rowaffect = pstmt.executeUpdate();
                if (rowaffect == 1) {
                    return AddProductResult.SUCCESS;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return AddProductResult.DATABASE_ERROR;
            }
        }
        return AddProductResult.DATABASE_ERROR;
    }

    public FindProductResponse findById(int id){
        String sql = "select * from products where id = ?";
        try(Connection connect = getconnection();
            PreparedStatement pstmt = connect.prepareStatement(sql);) {
            pstmt.setInt(1,id);
           try( ResultSet rs =pstmt.executeQuery();) {
               if (rs.next()) {
                   Product product = new Product(
                           rs.getInt("id"),
                           rs.getString("name"),
                           rs.getString("category"),
                           rs.getInt("price"),
                           rs.getInt("quantity"),
                           rs.getInt("minimum_stock")
                   );
                   return new FindProductResponse(FindProductResult.SUCCESS,product);
               }
           }
        } catch (SQLException e) {
            e.printStackTrace();
            return new FindProductResponse(FindProductResult.DATABASE_ERROR,null);
        }
        return new FindProductResponse(FindProductResult.PRODUCT_NOT_FOUND , null);
    }

    public ViewProductResponse viewProduct() {
        List<Product> item = new ArrayList<>();
        String sql = "SELECT * FROM products";
        Product product = null;
        try (Connection connect = getconnection();
             PreparedStatement pstmt = connect.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        rs.getInt("quantity"),
                        rs.getInt("minimum_stock")
                );
                item.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ViewProductResponse(ViewProductResult.DATABASE_ERROR, null);
        }
        if (item.isEmpty()) {
            return new ViewProductResponse(ViewProductResult.PRODUCT_NOT_FOUND , item);
        } else {
            return new ViewProductResponse(ViewProductResult.SUCCESS, item);
        }
    }

    public List<Product> findByName(String name){

        List<Product> item1 = new ArrayList<>();
        String sql = "SELECT * FROM products where name like ? or category like ? ";
        try(Connection connect = getconnection();
        PreparedStatement pstmt = connect.prepareStatement(sql);) {
            pstmt.setString(1,  "%" +name + "%");
            pstmt.setString(2,  "%" +name + "%");
        try(ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        rs.getInt("quantity"),
                        rs.getInt("minimum_stock")
                );
                item1.add(product);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item1;
    }

    public UpdatePriceResult updatePrice(int id , int newPrice){

        FindProductResponse response = findById(id);
        if(response.getResult() == FindProductResult.PRODUCT_NOT_FOUND){
            return UpdatePriceResult.PRODUCT_NOT_FOUND;
        }
        if(response.getResult() == FindProductResult.DATABASE_ERROR){
            return UpdatePriceResult.DATABASE_ERROR;
        }
        if(response.getResult() != FindProductResult.SUCCESS){
            return UpdatePriceResult.DATABASE_ERROR;
        }
        String sql = "UPDATE products SET price = ? where id = ? ";
        try(Connection connect = getconnection();
        PreparedStatement pstmt = connect.prepareStatement(sql);) {
            pstmt.setInt(1,newPrice);
            pstmt.setInt(2,id);

            int rowaffect =  pstmt.executeUpdate();
            if(rowaffect ==1 ) {
                return UpdatePriceResult.SUCCESS;
            }
        }
        catch (SQLException e) {
                e.printStackTrace();
                return UpdatePriceResult.DATABASE_ERROR;
        }
        return UpdatePriceResult.DATABASE_ERROR;
    }

    public UpdateQuantityResult updateQuantity(int id , int newQuantity){
        FindProductResponse response = findById(id);
        if(response.getResult() == FindProductResult.PRODUCT_NOT_FOUND){
            return UpdateQuantityResult.PRODUCT_NOT_FOUND;
        }
        if(response.getResult() == FindProductResult.DATABASE_ERROR){
            return UpdateQuantityResult.DATABASE_ERROR;
        }
        if(response.getResult() != FindProductResult.SUCCESS){
            return UpdateQuantityResult.DATABASE_ERROR;
        }
        String sql = "UPDATE products SET quantity = ? where id = ? ";
        try(Connection connect = getconnection();
            PreparedStatement pstmt = connect.prepareStatement(sql);) {
            pstmt.setInt(1,newQuantity);
            pstmt.setInt(2,id);

            int rowaffect = pstmt.executeUpdate();
            if(rowaffect ==1) {
                return UpdateQuantityResult.SUCCESS;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return UpdateQuantityResult.DATABASE_ERROR;
        }
        return UpdateQuantityResult.DATABASE_ERROR;
    }

    public UpdateCategoryResult updateCategory(int id , String newCategory){
        FindProductResponse response = findById(id);
        if(response.getResult() == FindProductResult.PRODUCT_NOT_FOUND){
            return UpdateCategoryResult.PRODUCT_NOT_FOUND;
        }
        if(response.getResult() == FindProductResult.DATABASE_ERROR){
            return UpdateCategoryResult.DATABASE_ERROR;
        }
        if(response.getResult() != FindProductResult.SUCCESS){
            return UpdateCategoryResult.DATABASE_ERROR;
        }
        String sql = "UPDATE products SET category = ? where id = ? ";
        try(Connection connect = getconnection();
            PreparedStatement pstmt = connect.prepareStatement(sql);) {
            pstmt.setString(1,newCategory);
            pstmt.setInt(2,id);

            int rowaffect = pstmt.executeUpdate();
              if(rowaffect ==1 ) {
                  return UpdateCategoryResult.SUCCESS;
              }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return UpdateCategoryResult.DATABASE_ERROR;
        }
        return UpdateCategoryResult.DATABASE_ERROR;
    }

    public UpdateMinimumStockResult updateMinimumStock(int id , int newMinimumStock){
        FindProductResponse response = findById(id);
        if(response.getResult() == FindProductResult.PRODUCT_NOT_FOUND){
            return UpdateMinimumStockResult.PRODUCT_NOT_FOUND;
        }
        if(response.getResult() == FindProductResult.DATABASE_ERROR){
            return UpdateMinimumStockResult.DATABASE_ERROR;
        }
        if(response.getResult() != FindProductResult.SUCCESS){
            return UpdateMinimumStockResult.DATABASE_ERROR;
        }
        String sql = "UPDATE products SET minimum_stock = ? where id = ? ";
        try(Connection connect = getconnection();
            PreparedStatement pstmt = connect.prepareStatement(sql);) {
            pstmt.setInt(1,newMinimumStock);
            pstmt.setInt(2,id);

            int rowaffect = pstmt.executeUpdate();
            if(rowaffect ==1) {
                return UpdateMinimumStockResult.SUCCESS;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return UpdateMinimumStockResult.DATABASE_ERROR;
        }
        return UpdateMinimumStockResult.DATABASE_ERROR;
    }

    public DeleteProductResult deleteProduct(int id){
        FindProductResponse response = findById(id);
        if(response.getResult() == FindProductResult.PRODUCT_NOT_FOUND){
            return DeleteProductResult.PRODUCT_NOT_FOUND;
        }
        if(response.getResult() == FindProductResult.DATABASE_ERROR){
            return DeleteProductResult.DATABASE_ERROR;
        }
        if(response.getResult() != FindProductResult.SUCCESS){
            return DeleteProductResult.DATABASE_ERROR;
        }
        String sql = "DELETE FROM products where id = ?";
        try(Connection connect = getconnection();
        PreparedStatement pstmt = connect.prepareStatement(sql);) {
            pstmt.setInt(1,id);
            int rowaffect = pstmt.executeUpdate();
            if(rowaffect == 1){
                return DeleteProductResult.SUCCESS;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return DeleteProductResult.DATABASE_ERROR;
        }
        return DeleteProductResult.DATABASE_ERROR;
    }

    public List<Product> findLowProduct(){
        List<Product> item2 = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE quantity <= minimum_stock";
        try(Connection connect =getconnection();
        PreparedStatement pstmt = connect.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        rs.getInt("quantity"),
                        rs.getInt("minimum_stock")
                );
                item2.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item2;
    }

}

