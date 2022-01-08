package dataobjects;

import enums.Category;

public class Gift {
    /* Fields */
    private String productName = "";
    private Double price = 0d;
    private Category giftCategory;

    /* Constructor */
    public Gift(String productName, Double price, Category giftCategory) {
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

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getGiftCategory() {
        return giftCategory;
    }

    public void setGiftCategory(Category giftCategory) {
        this.giftCategory = giftCategory;
    }

    @Override
    public String toString() {
        return "Gift: " + productName + " | Price: " + price + " | Category: " + giftCategory;
    }
}
