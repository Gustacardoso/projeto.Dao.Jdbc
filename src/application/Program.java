package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1, "Car");
		
		Seller seller = new Seller(21, "gu", "Gus@", new Date(), 2000.0, obj);
		System.out.println(seller);

	}

}
