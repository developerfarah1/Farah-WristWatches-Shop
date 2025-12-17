/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnection {

public static void main(String[] args) {

try {
Connection con = DBCon.getConnection();
Statement st = con.createStatement();

ResultSet rs = st.executeQuery("select * from users");
while (rs.next()) {
System.out.println(
rs.getInt("uid") + " " +
rs.getString("uname") + " " +
rs.getString("upass")
);
}

st.close();
con.close();

} catch (Exception e) {
e.printStackTrace();
}
}
}

