package lucasdasilvac.dev.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPostgres {
	private final String ip = "localhost";
	private final Integer port = 5432;
	private final String user = "postgres";
	private final String password = "postgres";
	private final String database = "Events";
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://"+ip+":"+port+"/"+database, user, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
