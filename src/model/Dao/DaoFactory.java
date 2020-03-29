package model.Dao;

import model.Dao.impl.SellerDaoJDBC;

public class DaoFactory {
   
	//classe tera oper��o static para instanciar os daos
	
	 public static SellerDao createSellerDao() {
		 return new SellerDaoJDBC();
	 }
}
