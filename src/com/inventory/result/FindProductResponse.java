package com.inventory.result;
import com.inventory.model.Product;

public class FindProductResponse {
        private final FindProductResult result;
        private final Product product;

    public FindProductResponse(FindProductResult result, Product product) {
        this.result = result;
        this.product = product;
    }
    public FindProductResult getResult(){
        return result;
    }
    public Product getProduct(){
        return product;
    }
}
