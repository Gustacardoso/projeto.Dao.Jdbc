package application;

import java.util.Date;

import model.Dao.DaoFactory;
import model.Dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		/*Department obj = new Department(1, "Car");
		
		Seller seller = new Seller(21, "gu", "Gus@", new Date(), 2000.0, obj);
		System.out.println(seller);*/
		
		/*desta forma o meu  pragrama nao  conhece a implemetação,
           conhece so mente a interface, é tambem uma forma de mostrar as dependecia sem espliciatar a implementação   */
	SellerDao sellerDao = DaoFactory.createSellerDao();
	
	Seller seller = sellerDao.findById(3);
	
	System.out.println(seller);
	  
	}

}
