package lucasdasilvac.dev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lucasdasilvac.dev.connection.ConnectionPostgres;
import lucasdasilvac.dev.entity.Event;

public class EventDAO {
	private Connection conn;
	private ConnectionPostgres connPostgres;
	
	public EventDAO(ConnectionPostgres connPostgres) {
		this.connPostgres = connPostgres;
	}
	
	public boolean create(Event event) {
		String sql = "insert into event (id_event, name, adress, attraction) values (?, ?, ?, ?)";
		
		try {
			this.conn = connPostgres.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, event.getId_event());
			stmt.setString(2, event.getName());
			stmt.setString(3, event.getAdress());
			stmt.setString(4, event.getAttraction());
			
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
		return true;
	}

	public Event read(Long id_event) {
		String sql = "select * from event where id_event = ?";
		Event event = null;
		
		try {
			this.conn = connPostgres.getConnection();
			PreparedStatement  stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id_event);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				event = new Event();
				event.setId_event(rs.getLong("id_event"));
				event.setName(rs.getString("name"));
				event.setAdress(rs.getString("adress"));
				event.setAttraction(rs.getString("attraction"));
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
		return event;
	}
	
	public boolean update(Event event) {
		String sql = "update event set id_event = ?, name = ?, adress = ?, attraction = ? where id_event = ?";
		
		try {
			this.conn = connPostgres.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, event.getId_event());
			stmt.setString(2, event.getName());
			stmt.setString(3, event.getAdress());
			stmt.setString(4, event.getAttraction());
			stmt.setLong(5, event.getId_event());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0)
				return true;
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
	
	public boolean delete(Long id_event) {
		String sql = "delete from event where id_event = ?";
		
		try {
			this.conn = connPostgres.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id_event);
			
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
