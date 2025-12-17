
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBCon {

public static Connection getConnection() throws Exception {
try {
Class.forName("com.mysql.cj.jdbc.Driver");

Connection con = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/shop",
"root",
"root" // خليه "" إذا ما عندك باسورد
);

return con;

} catch (Exception e) {
e.printStackTrace(); // 👈 مهم جدًا
throw e; // 👈 لا ترجع null أبداً
}

}
}