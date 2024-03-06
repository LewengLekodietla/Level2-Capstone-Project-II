package CapstoneProjectOOP;

import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		// Capture details to create a new Customer object, storing user input for the name and address (lines 19 to 22)
		System.out.print("Please enter your name?: ");
		String customerName = scanner.nextLine();
		System.out.print("Please enter your location?: ");
		String customerCityLocation = scanner.nextLine();
		//Create a customer object from input stored in variables 'customerName' and 'customerAddress' - enabled by the Customer Class constructors
		Customer customer = new Customer(customerName, customerCityLocation);

		// Capture details to create a new Restaurant object, storing user input for the name and address (lines 26 to 30)
		System.out.print("Enter restaurant name: ");
		String restaurantName = scanner.nextLine();
		System.out.print("Enter restaurant area: ");
		String restaurantLocation = scanner.nextLine();
		//Create a customer object from input stored in variables 'restaurantName' and 'restaurantArea' - enabled by the Customer Class constructors
		Restaurant restaurant = new Restaurant(restaurantName, restaurantLocation);
		System.out.println(restaurant.getArea());
		//Store the driver's file in a variable to enable reading of the file
		String driverFileName = "drivers.txt";
		//Each line in the driver file will be stored in a variable, to allow iteration
		String line;
		//The suitable driver, according to location and load will be stored in variable suitableDriver once driver is found
		String suitableDriver = null;
		//The driver with the smallest load will have their load compared against load in variable leastLoad
		int leastLoad = Integer.MAX_VALUE;

		//Read the driver file, split the driver's name, location and load - to enable driver requirements (lines 43 to 60)
		try (BufferedReader driverFileReader = new BufferedReader(new FileReader(driverFileName))) {
			//Controlling reading of file as long as there is  content on each line - to help if we do not know the length if the content
			while ((line = driverFileReader.readLine()) != null) {
				String[] driverDetails = line.split(", ");
				String driverArea = driverDetails[1];

				if (driverArea.equals(restaurant.getArea())) {
					int load = Integer.parseInt(driverDetails[2]);
					if (load < leastLoad) {
						leastLoad = load;
						suitableDriver = driverDetails[0];
					}
				}
			}
			// ...
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Write up the invoice to a new text file for the customer called invoice.txt and print content in structure in lines 65 to 69)
		if (suitableDriver != null) {
			String invoiceFileName = "invoice.txt";
			try (PrintWriter writer = new PrintWriter(new FileWriter(invoiceFileName))) {
				writer.println("Delivery Details:");
				writer.println("Customer: " + customer.getName());
				writer.println("Restaurant: " + restaurant.getName());
				writer.println("Driver: " + suitableDriver);
				writer.println("Delivery Area: " + restaurant.getArea());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			//Else confirm there are no drivers near customer's location
			System.out.println("Sorry! Our drivers are too far away from you to be able to deliver to your location.");
		}
		scanner.close();
	}

}
