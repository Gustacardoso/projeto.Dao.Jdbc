package model.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import db.DB;
import db.DbException;
import model.Dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
    //criando um atribut para cone��o
	private Connection conn;
	//for�a a adependecia da conexao
	public  SellerDaoJDBC(Connection conn) {
		this.conn = conn;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"WHERE seller.Id = ?");
			
			//vamos configurar a '?' atraves do parametro usando  o setint 
			st.setInt(1, id);
			rs = st.executeQuery();
			//configurando o preenchimentos das colunas
			if(rs.next()) {
			  Department dep = new Department();
			  dep.setId(rs.getInt("DepartmentId"));
			  dep.setName(rs.getString("DepName"));
			  Seller obj = new Seller();
			  obj.setId(rs.getInt("Id"));
			  obj.setName(rs.getString("Name"));
			  obj.setEmail(rs.getString("Email"));
			  obj.setBaseSalary(rs.getDouble("BaseSalary"));
			  obj.setBirthDate(rs.getDate("BirthDate"));
			  obj.setDepartment(dep);
			  return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			//fechar 
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
