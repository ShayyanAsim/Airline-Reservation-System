
public class Address{

    private String addressLine1;
    private String addressLine2;
    private String postalCode;

    public Address(String ad1, String ad2, String pc){  
        addressLine1 = ad1;
        addressLine2 = ad2;
        postalCode = pc;

    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String toString() {
        return this.addressLine1 + " " + this.addressLine2 + ", " + this.postalCode;
    }

}
