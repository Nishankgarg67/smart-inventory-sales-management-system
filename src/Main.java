import com.inventory.model.Product;
import com.inventory.ProductManager;
import com.inventory.result.*;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);

        ProductManager pm = new ProductManager();
        System.out.println("=====Inventory System=====");
        while(true){
                System.out.println("1. Add Product");
                System.out.println("2. View Product");
                System.out.println("3. Search Product");
                System.out.println("4. Update Product");
                System.out.println("5. Delete Product");
                System.out.println("6. ViewLowStockProducts");
                System.out.println("7. Exit");
                System.out.print("Enter your choice : ");

                int choice = readInt(in);
                if(choice ==7){
                    break;
                }
            switch (choice){
                case 1 : {
                    System.out.print("Enter id : ");
                    int id = readInt(in);
                    in.nextLine();
                    System.out.print("Enter name : ");
                    String name = in.nextLine();
                    System.out.print("Enter category : ");
                    String category = in.nextLine();
                    System.out.print("Enter price : ");
                    int price = readInt(in);
                    System.out.print("Enter quantity : ");
                    int quantity = readInt(in);
                    System.out.print("Enter minimumStock : ");
                    int minimumStock = readInt(in);
                    Product p = new Product(id, name, category, price, quantity, minimumStock);
                    AddProductResult result = pm.addProduct(p);

                    switch (result){
                        case SUCCESS -> System.out.println("Product added successfully");
                        case INVALID_PRICE -> System.out.println("Enter valid and positive price");
                        case INVALID_QUANTITY -> System.out.println("Quantity must not be negative");
                        case INVALID_MINIMUMSTOCK -> System.out.println("Stock cannot be negative");
                        case DUPLICATE_ID -> System.out.println("Product Id is already exists");
                    }
                }
                break;
                case 2 : pm.viewProducts();
                break;
                case 3 : {
                    System.out.println("1.Search by Id");
                    System.out.println("2.Search by Name");
                    System.out.println("Enter your choice : ");
                    int search =readInt(in);
                    switch (search){
                        case 1 : {
                            System.out.print("Enter product id : ");
                            int id = readInt(in);
                            Product p = pm.searchProduct(id);
                            if (p != null) {
                                System.out.println(p);
                            } else {
                                System.out.println("Product not found");
                            }
                        }
                        break;
                        case 2 : {
                            System.out.print("Enter product name : ");
                            in.nextLine();
                            String name = in.nextLine();
                            Product p = pm.searchProductByName(name);
                            if(p!=null) System.out.println(p);
                            else System.out.println("Product not found");
                        }
                        break;
                        default:
                            System.out.println("Enter correct number.");
                            break;
                    }
                }
                break;

                case 4 : {
                    System.out.print("Enter id : ");
                    int id = readInt(in);
                    System.out.println("1. Category");
                    System.out.println("2. Price");
                    System.out.println("3. Quantity");
                    System.out.println("4.MinimumStock");
                    System.out.print("Enter choice for update : ");
                    int select = readInt(in);
                    in.nextLine();
                    switch(select){
                        case 1 : {
                            System.out.print("Enter updated category : ");
                            String category = in.nextLine();
                            UpdateCategoryResult result = pm.updateCategory(id,category);
                            switch(result){
                                case SUCCESS -> System.out.println("Updated successfully");
                                case PRODUCT_NOT_FOUND -> System.out.println("Product is not found");
                                case INVALID_CATEGORY -> System.out.println("Enter valid Category");
                            }
                        }
                        break;
                        case 2 : {
                            System.out.print("Enter updated price : ");
                            int price = readInt(in);
                            UpdatePriceResult result = pm.updatePrice(id,price);
                            switch (result){
                                case SUCCESS -> System.out.println("Updated successfully");
                                case INVALID_PRICE -> System.out.println("Price is invalid");
                                case PRODUCT_NOT_FOUND -> System.out.println("Product is not found");
                            }
                           }
                        break;
                        case 3 : {
                            System.out.print("Enter updated quantity : ");
                            int quantity = readInt(in);
                            UpdateQuantityResult result = pm.updateQuantity(id, quantity);
                            switch (result){
                                case SUCCESS -> System.out.println("Updated Successfully");
                                case PRODUCT_NOT_FOUND -> System.out.println("Product not found");
                                case INVALID_QUANTITY -> System.out.println("Quantity must not be negative");
                            }
                        }
                        break;
                        case 4 : {
                            System.out.print("Enter updated minimumStock : ");
                            int minimumStock = readInt(in);
                            UpdateMinimumStockResult result = pm.updateMinimumStock(id,minimumStock);
                            switch(result){
                                case SUCCESS -> System.out.println("Updated Successfully");
                                case INVALID_MINIMUMSTOCK -> System.out.println("Minimumstock must not be negative");
                                case PRODUCT_NOT_FOUND -> System.out.println("product not found");
                            }
                        }
                        break;
                        default:
                            System.out.println("Enter valid number");
                            break;
                    }
                }break;
                case 5: {
                    System.out.println("Enter id : ");
                    int id = readInt(in);
                    Product p = pm.searchProduct(id);
                    if(p==null){
                        System.out.println("Product not found");
                    }
                   else  {
                       pm.deleteProduct(id);
                        System.out.println("Product deleted successfully");}
                }
                break;
                case 6 : pm.viewLowStockProducts();
                break;
                default:
                    System.out.println("Enter correct number.");
                    break;
            }
            }
    }
static int readInt(Scanner in) {
    while (true) {
        String input = in.next();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}
}



