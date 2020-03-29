package model.Dao;

import model.Dao.impl.SellerDaoJDBC;

public class DaoFactory {
   
	//classe tera operção static para instanciar os daos
	
	 public static SellerDao createSellerDao() {
		 return new SellerDaoJDBC();
	 }
}
