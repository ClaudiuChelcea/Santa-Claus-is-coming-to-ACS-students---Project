package dataobjects;

import enums.Category;

public class Gift {
    /* Fields */
    String productName = "";
    double price = 0d;
    Category giftCategory;

    /* Constructor */
    public Gift(String productName, double price, Category giftCategory) {
        this.productName = productName;
        this.price = price;
        this.giftCategory = giftCategory;
    }

    /* Getters and setters */
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getGiftCategory() {
        return giftCategory;
    }

    public void setGiftCategory(Category giftCategory) {
        this.giftCategory = giftCategory;
    }
}
