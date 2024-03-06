package CapstoneProjectOOP;

public class Customer {
    //Data Fields/Attributes
	private String name;
    private String address;
    //Constructor
    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }
    //Methods
    public String getAddress() {
        return address;
    }
    public String getName() {
    	return name;
    }

}
