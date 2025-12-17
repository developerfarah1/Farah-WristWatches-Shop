/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author PC
 */
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Model;

/**
 *
 * @author PC
 */
//public class log {
    
//}
public class log extends javax.swing.JFrame{
Connection con ;
PreparedStatement pst;
ResultSet rs;
String  database_address = "//localhost:3306/shop";
String  user_name = "root";
String  pass = "root";
public  log (){
    initComponents();
    setBackground(new Color(0,0,0,0));
    try{
     con =DriverManager.getConnection("jdbc:mysql"+database_address+user_name+pass);
    System.out.println("success");
} catch (SQLException ex) {
    Logger.getLogger(log.class.getName()).log(Level.SEVERE , null , ex);
}System.out.println("error...");
}

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

