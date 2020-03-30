package model.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.Dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
    //criando um atribut para coneção
	private Connection conn;
	//força a adependecia da conexao
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
			  Department dep = instantiateDepartment(rs);
			  Seller obj = instantiateSeller(rs, dep);
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

	private Seller instantiateSeller(ResultSet rs ,Department dep) throws SQLException {
	     Seller obj = new Seller();
		  obj.setId(rs.getInt("Id"));
		  obj.setName(rs.getString("Name"));
		  obj.setEmail(rs.getString("Email"));
		  obj.setBaseSalary(rs.getDouble("BaseSalary"));
		  obj.setBirthDate(rs.getDate("BirthDate"));
		  obj.setDepartment(dep);
		return obj;
	}
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep =  new Department();
		  dep.setId(rs.getInt("DepartmentId"));
		  dep.setName(rs.getString("DepName"));
		return dep;
	}
	@Override
	public List<Seller> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name ");
			
			//vamos configurar a '?' atraves do parametro usando  o setint 
			//st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			//teremos que criar uma lista
			List<Seller> list = new ArrayList<>();
			//criar uma instrotura map para nao repetir

			
			//configurando o preenchimentos das colunas
			//e vamos usar um while porque devemos ter tanto 0 buscas como mais de uma 
			while(rs.next()) {
				
		      Department dep = instantiateDepartment(rs);	
			  Seller obj = instantiateSeller(rs, dep);
			    list.add(obj);
			}
			
			
			return list;
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
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"WHERE DepartmentId = ? " + 
					"ORDER BY Name");
			
			//vamos configurar a '?' atraves do parametro usando  o setint 
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			//teremos que criar uma lista
			List<Seller> list = new ArrayList<>();
			//criar uma instrotura map para nao repetir
			Map<Integer, Department> map = new HashMap<>();
			
			//configurando o preenchimentos das colunas
			//e vamos usar um while porque devemos ter tanto 0 buscas como mais de uma 
			while(rs.next()) {
				//temos que testar se o department ja existe
				Department dep = map.get(rs.getInt("DepartmentId"));
				/*resumindo foi usado o map para nao ter dois ou meias department
				 * ja que tem que ser um deportment para muito seller
				 * num departament tem 1 para muitos vendedores*/
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
			  Seller obj = instantiateSeller(rs, dep);
			    list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			//fechar 
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
	

}
