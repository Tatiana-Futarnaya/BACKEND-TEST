package by.clevertec.model.beans.products;

public abstract class AbstractProducts {
    private String name;
    private int amount;
    private double price;

    public AbstractProducts() {
    }

    public AbstractProducts(String name,double price) {
        this.name = name;
        this.price = price;
    }

    public AbstractProducts(String name, int amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract double cost();

    public abstract double getDiscountAmount();

    public double getPriceWithoutDiscount(){
        return getAmount()*getPrice();
    }


    @Override
    public String toString() {
        return "AbstractProducts{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
