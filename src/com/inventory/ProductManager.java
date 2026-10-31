package com.inventory;
import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;
import com.inventory.result.*;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    private ProductDAO productDAO = new ProductDAO();

    public AddProductResult addProduct(Product product) {
        if(product.getPrice() <=0){
            return AddProductResult.INVALID_PRICE;
        }
        if(product.getQuantity() <0){
            return AddProductResult.INVALID_QUANTITY;
        }
        if(product.getMinimumStock() < 0){
            return AddProductResult.INVALID_MINIMUMSTOCK;
        }
        return productDAO.addProduct(product);
    }

    public ViewProductResponse viewProducts(){
       return productDAO.viewProduct();
    }

    public FindProductResponse searchProduct(int id){

        return productDAO.findById(id);
    }

    public List<Product> searchProductByName(String searchname){

        return productDAO.findByName(searchname);
    }

    public UpdateCategoryResult updateCategory(int productid , String category ){

        if(category==null || category.isBlank() ){
            return UpdateCategoryResult.INVALID_CATEGORY;
        }
        return productDAO.updateCategory(productid,category);
    }

    public UpdatePriceResult updatePrice(int productid , int price ){
       if(price <= 0){
           return UpdatePriceResult.INVALID_PRICE;
       }
       return productDAO.updatePrice(productid,price);
    }

    public UpdateQuantityResult updateQuantity(int productid , int quantity){
        if(quantity<0){
            return UpdateQuantityResult.INVALID_QUANTITY;
        }
        return productDAO.updateQuantity(productid,quantity);
    }

    public UpdateMinimumStockResult updateMinimumStock(int productid , int minimumStock ){
      if(minimumStock <0){
          return UpdateMinimumStockResult.INVALID_MINIMUMSTOCK;
      }
      return productDAO.updateMinimumStock(productid,minimumStock);
    }

    public DeleteProductResult deleteProduct(int productId){

       return productDAO.deleteProduct(productId);
    }

    public void viewLowStockProducts(){

        List<Product> product= productDAO.findLowProduct();

        if(product.isEmpty()){
            System.out.println("No Product found.");
            return ;
        }
        for(Product item : product ) {
            System.out.println(item);
        }
        }

}
