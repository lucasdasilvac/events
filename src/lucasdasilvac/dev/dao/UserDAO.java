package lucasdasilvac.dev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lucasdasilvac.dev.connection.ConnectionPostgres;
import lucasdasilvac.dev.entity.User;

public class UserDAO {
	private Connection conn;
	private ConnectionPostgres connPostgres;
	
	public UserDAO(ConnectionPostgres connPostgres) {
		this.connPostgres = connPostgres;
	}
	
	public boolean create(User user) {
		String sql = "insert into _user (name, email, adress) values (?, ?, ?)";
		
		try {
			this.conn = connPostgres.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getName());;
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getAdress());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) {
				return true;
			}
			return false;
			
		} catch (SQLException e) {
			System.out.println("");
		} finally {
			try {
				this.conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public User read(Long id) {
		String sql = "select * from _user where id = ?";
		User user = null;
		
		try {
			this.conn = connPostgres.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(id);
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setAdress(rs.getString("adress"));
			}
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println("");
		} finally {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public boolean update(User user) {
		String sql = "update _user set name = ?, email = ?, adress = ? where id = ?";
		
		try {
			this.conn = connPostgres.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getAdress());
			stmt.setLong(4, user.getId());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) {
				return true;
			}
			return false;
			
		} catch (SQLException e) {
			System.out.println("");
		} finally {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean delete(Long id) {
		String sql = "delete from _user where id = ?";
		
		try {
			this.conn = connPostgres.getConnection();
			
			User user = new User();
			user.setId(id);
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) {
				return true;
			}
			return false;
			
		} catch (SQLException e) {
			System.out.println("");
		} finally {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
}
