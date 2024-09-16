import java.sql.*;

public class Connectivity {
    Connection c;
    Statement s;
    Connectivity() throws SQLException {
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Elec", "root" , "Kabburana7900");
        s = c.createStatement();
    }
}

