package collections;

import java.util.HashMap;
import java.util.Map;

public class Inventory {


    private final Map<String, Double> stock = new HashMap<>();

    public void addProduct(String name, double quantity) {
        this.stock.put(name, quantity);
    }

    public void checkStock(double minimumQuantity) {
        this.stock.forEach((name, quantity) -> {
            if (quantity >= minimumQuantity) {
                System.out.println("Sufficient stock for " + name);
            } else {
                System.out.println("Insufficient stock for " + name);
            }
        });
    }

    public void containsProduct(String name) {
        if (!this.stock.containsKey(name)){
            System.out.println(name + " does not exist in the inventory");
        } else{
            System.out.println("Reactant "+ name + " exist in the inventory. There is " + this.stock.get(name) + " units.");
        }
    }
}