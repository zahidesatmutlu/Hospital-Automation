package Helper;

import java.sql.*;

public class databaseConnection {
	Connection c = null;

	public Connection connectDatabase() {
		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hospital?user=root&password=1234");
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
