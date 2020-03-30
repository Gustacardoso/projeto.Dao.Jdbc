package model.Dao;

import db.DB;
import model.Dao.impl.SellerDaoJDBC;

public class DaoFactory {
   
	//classe tera oper��o static para instanciar os daos
	// internamento ela implemetar um interfaces retornando uma classe de implementa��o
	 public static SellerDao createSellerDao() {
		 return new SellerDaoJDBC(DB.getConnection());
	 }
}
