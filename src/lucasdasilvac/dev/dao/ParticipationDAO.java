package lucasdasilvac.dev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lucasdasilvac.dev.connection.ConnectionPostgres;
import lucasdasilvac.dev.entity.Event;
import lucasdasilvac.dev.entity.Participation;
import lucasdasilvac.dev.entity.User;

public class ParticipationDAO {
	private Connection conn;
	private ConnectionPostgres connPostgres;
	
	public ParticipationDAO(ConnectionPostgres connPostgres) {
		this.connPostgres = connPostgres;
	}
	
	public boolean create(Participation participation) {
		String sql = "insert into participation (id_event, user_id) VALUES(?, ?)";
		
		try {
			this.conn = connPostgres.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, participation.getEvent().getId_event());
			stmt.setLong(2, participation.getUser().getId());
			
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
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean update(Participation participation) {
		String sql = "update participation set id_event = ?, user_id = ? WHERE id_event = ? AND user_id = ?";
		
		try {
			this.conn = connPostgres.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, participation.getEvent().getId_event());
			stmt.setLong(2, participation.getUser().getId());
			stmt.setLong(3, participation.getEvent().getId_event());
			stmt.setLong(4, participation.getUser().getId());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) return true;
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

	public boolean delete(Long code) {
		String sql = "delete from participation where code = ?";
		
		try {
			this.conn = connPostgres.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, code);
			
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
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Participation read(Long code) {
		String sql = "select * from participation where code = ?";
		Participation participation = null;
		
		try {
			this.conn = connPostgres.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, code);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				participation = new Participation();
				participation.setCode(rs.getLong("code"));
				EventDAO eventDAO = new EventDAO(connPostgres);
				Event event = eventDAO.read(rs.getLong("id_event"));
				participation.setEvent(event);
				UserDAO userDAO = new UserDAO(connPostgres);
				User user = userDAO.read(rs.getLong("user_id"));
				participation.setUser(user);
			}
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println("");
		} finally {
			try {
				this.conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return participation;
	}
	
	public Long getLastParticipationCode() {
		String sql = "select code from participation where code in (select max(code) from participation);";
		Long lastCodeParticipation = 0L;
		try {
			this.conn = connPostgres.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				lastCodeParticipation = rs.getLong("code");
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println("");
		} finally {
			try {
				this.conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return lastCodeParticipation;
	}
}
