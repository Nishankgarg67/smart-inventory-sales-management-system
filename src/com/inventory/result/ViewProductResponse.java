package com.inventory.result;

import com.inventory.model.Product;

import java.util.List;

public class ViewProductResponse {
    private  ViewProductResult result;
    private List<Product> products ;

    public ViewProductResponse(ViewProductResult result, List<Product> products) {
        this.result = result;
        this.products = products;
    }
    public ViewProductResult getResult(){
        return result;
    }
    public List<Product> getProducts(){
        return products;
    }


}
