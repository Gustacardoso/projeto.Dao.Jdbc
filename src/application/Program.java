package application;

import java.util.Date;
import java.util.List;

import model.Dao.DaoFactory;
import model.Dao.SellerDao;
import model.Dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		/*Department obj = new Department(1, "Car");
		
		Seller seller = new Seller(21, "gu", "Gus@", new Date(), 2000.0, obj);
		System.out.println(seller);*/
		
		/*desta forma o meu  pragrama nao  conhece a implemetação,
           conhece so mente a interface, é tambem uma forma de mostrar as dependecia sem espliciatar a implementação   */
	System.out.println("=== Teste 1 findById====");
		SellerDao sellerDao = DaoFactory.createSellerDao();
	
	Seller seller = sellerDao.findById(3);
	
	System.out.println(seller);
	
	System.out.println("=== Teste 2 findByIdDepartment === ");
	  Department department = new Department(2, null);
	  List<Seller> list = sellerDao.findByDepartment(department);
	 
	  for (Seller obj : list) {
		System.out.println(obj);
	}
	  
	  System.out.println("==== Teste 3 findAll====");
	  
	  List<Seller> list2 = sellerDao.findAll();
	  
	  for (Seller obj : list2) {
		System.out.println(obj);
	}
	  
	  System.out.println("==== Teste 4 Seller insert ====");
	  Seller newSeller = new Seller(null, "gustavo", "gusta@", new Date(), 2600.0, department);
	  
	  sellerDao.insert(newSeller);
	  
	  System.out.println("Inserted! new Name= " + newSeller.getName());
	
	
	 System.out.println("==== Teste  5 Seller update ====");
     //seller findbyid para ver qual id eu quero  mudar
	 seller = sellerDao.findById(1);
	 //o que eu  quero  mudar, neste caso  irei  mudar so  no name
	 seller.setName("deu certo errou");
	 seller.setEmail("email modificado");
	 sellerDao.update(seller);
	 System.out.println("Update Completed");
	}
}
