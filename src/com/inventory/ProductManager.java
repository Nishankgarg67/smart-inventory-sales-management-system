package com.inventory;
import com.inventory.model.Product;
import com.inventory.result.*;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> products =  new ArrayList<>();
    public AddProductResult addProduct(Product product) {
        Product p = searchProduct(product.getId());
        if (p == null) {
            if (product.getPrice() > 0) {
                if(product.getQuantity() >= 0){
                    if(product.getMinimumStock() >=0 ){
                products.add(product);
                return AddProductResult.SUCCESS;}
                    else {
                        return AddProductResult.INVALID_MINIMUMSTOCK;
                    }
                }
                else {
                    return AddProductResult.INVALID_QUANTITY;
                }
            }
            else{
                return AddProductResult.INVALID_PRICE;
            }
        }
        else {
            return AddProductResult.DUPLICATE_ID;
        }
    }
    public void viewProducts(){
        products.forEach(System.out::println);
    }
    public Product searchProduct(int id){
        for(Product product:products){
            if(product.getId() ==id){
                return product;
            }
        }
        return null;
    }
    public Product searchProductByName(String searchname){
        for(Product p : products){
            if(p.getName().toLowerCase().contains(searchname.toLowerCase()) ){
                return p;
            }
        }
        return null;
    }
    public UpdatePriceResult updatePrice(int productid , int price ){
        Product p = searchProduct(productid);
        if(p!=null){
            if(price>0){
            p.setPrice(price);
             return  UpdatePriceResult.SUCCESS;
            }
            else {
                return UpdatePriceResult.INVALID_PRICE;
            }
        }else {
            return UpdatePriceResult.PRODUCT_NOT_FOUND;
        }
    }
    public UpdateQuantityResult updateQuantity(int productid , int quantity){
        Product p = searchProduct(productid);
        if(p!=null){
            if(quantity>=0) {
                p.setQuantity(quantity);
                return UpdateQuantityResult.SUCCESS;
            }
            else {
                return UpdateQuantityResult.INVALID_QUANTITY;
            }
        }
        else {
            return UpdateQuantityResult.PRODUCT_NOT_FOUND;
        }
    }
    public UpdateMinimumStockResult updateMinimumStock(int productid , int minimumStock ){
        Product p = searchProduct(productid);
        if(p!=null){
            if(minimumStock >=0){
                p.setMinimumStock(minimumStock);
                return UpdateMinimumStockResult.SUCCESS;
            }
            else {
                return UpdateMinimumStockResult.INVALID_MINIMUMSTOCK;
            }
        }
        else {
            return UpdateMinimumStockResult.PRODUCT_NOT_FOUND;
        }
    }
    public UpdateCategoryResult updateCategory(int productid , String category ){
        Product p = searchProduct(productid);
        if(p!=null){
            if(!category.isBlank()){
            p.setCategory(category);
            return UpdateCategoryResult.SUCCESS;
            }
            else {
                return UpdateCategoryResult.INVALID_CATEGORY;
            }
        }
        else {
            return UpdateCategoryResult.PRODUCT_NOT_FOUND;
        }
    }
    public void deleteProduct(int productId){
        Product p = searchProduct(productId);
        if(p!=null){
            products.remove(p);
        }
    }
    public void viewLowStockProducts(){
        for(Product product : products){
            if(product.getQuantity() <= product.getMinimumStock()){
                System.out.println(product);
            }
        }
        }

}
