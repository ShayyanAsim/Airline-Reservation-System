
public class Payment{

    private double price;

    public Payment(double val){
        price = val;
        

    }

    public double calculatePrice() {
        return price;

    }

    public void setPrice(double entered_val){
        this.price = entered_val;
    }

}
