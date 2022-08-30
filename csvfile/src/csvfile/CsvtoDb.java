package csvfile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class CsvtoDb {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//initialization
		Connection conn = null;
        Statement stmt = null;
        
        //establishing connection
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        stmt = conn.createStatement();
        
        //queries
        stmt.execute("drop table if exists csvdata");
        stmt.execute("create table csvdata (id int, name varchar(100), age int)");
        stmt.execute("insert into csvdata ( id, name, age )     select \"id\", \"name\", \"age\" from CSVREAD( '/home/samcouser/Documents/csv/CsvFile', 'id,name,age', null ) ");
        ResultSet rs = stmt.executeQuery("select * from csvdata");
        
		/*
		 * //display while (rs.next()) { System.out.println("id " + rs.getInt("id") +
		 * " name " + rs.getString("name") + " age " + rs.getInt("age") ); }
		 */
        stmt.close();
	}
}