
package Model;

import java.util.*;
import java.sql.*;
import javax.servlet.http.*;

public class Cart {
    public int uid;
    public  ArrayList<Product> products ;
    public double totalPrice;
    public String msg;
    
 public  Cart(){
     uid =0;
     products= new ArrayList<Product>();
     totalPrice = 0;
     msg = "";
    
 }
 public void listCartProducts() throws Exception { 
     Connection con = DBCon.getConnection();
       
       PreparedStatement pst1 = con.prepareStatement(
       "update cart,product set cart.quantity=product.quantity "+ 
       " where product.pid=cart.pid and cart.quantity>product.quantity and uid=?");
       PreparedStatement pst2 = con.prepareStatement(
       "delete from cart where quantity=0 and uid=?" );
       PreparedStatement pst3 = con.prepareStatement(
       "select * from cart,product where product.pid=cart.pid and uid=?");
       
       pst1.setInt(1,uid);
       int rows =pst1.executeUpdate();
       if(rows>0){
           msg ="Some products quantity were updated!";
       }
        pst2.setInt(1,uid);
        rows =pst2.executeUpdate();
        if(rows>0){
        msg =" Some products were removed!";
        
       }
        pst3.setInt(1,uid);
        ResultSet rs =pst3.executeQuery();
        products.clear();
        totalPrice=0;
        while(rs.next()){
          products.add(new Product(rs.getInt("pid"),
                                   rs.getString("name"),
                                   rs.getString("company"),
                                   rs.getString("details"),
                                   rs.getDouble("price"),
                                   rs.getInt("cart.quantity"),
                                   rs.getString("image"),
                                   rs.getString("type"),
                                   rs.getInt("size")));
         totalPrice= totalPrice +  rs.getDouble("price")* rs.getInt("cart.quantity");
       }
       rs.close();
       pst1.close();
       pst2.close();
       pst3.close();
       con.close();
   }
   public void addToCart(HttpServletRequest request) throws Exception {     
    int pid = Integer.parseInt(request.getParameter("pid"));
    int quantity = Integer.parseInt(request.getParameter("quantity"));
     Connection con = DBCon.getConnection();
     
     PreparedStatement pst1 = con.prepareStatement(
    "select * from cart where pid=? and uid=?");
     PreparedStatement pst2 = con.prepareStatement(
    "update cart set quantity=quantity+? where pid=? and uid=?");
    PreparedStatement pst3 = con.prepareStatement(
    "insert into cart (uid,pid,quantity)values(?,?,?)"); 
    
    pst1.setInt(1,pid);
    pst1.setInt(2,uid);
   ResultSet rs = pst1.executeQuery();
    if(rs.next()){
        pst2.setInt(1,quantity);
        pst2.setInt(2,pid);  
        pst2.setInt(3,uid); 
        int rows = pst2.executeUpdate();
       if(rows==0){
           msg = "Please try again!";
                      
       }
    }else{
        pst3.setInt(1,uid);
        pst3.setInt(2,pid);  
        pst3.setInt(3,quantity); 
        int rows = pst3.executeUpdate();
       if(rows==0){
           msg = "Please try again!";    
    }
    }
       rs.close();
       pst1.close();
       pst2.close();
       pst3.close();
       con.close();
 }
   public void moreQ(HttpServletRequest request) throws Exception {
    int pid = Integer.parseInt(request.getParameter("pid"));
     Connection con = DBCon.getConnection();
    
    PreparedStatement pst1 = con.prepareStatement(
    "update cart set quantity=quantity+1 where pid=? and uid=?");
    
    pst1.setInt(1,pid);
    pst1.setInt(2,uid);
    int rows = pst1.executeUpdate();
            
    
    
    pst1.close();
    con.close();
    
} 
  public void lessQ(HttpServletRequest request) throws Exception {
    int pid = Integer.parseInt(request.getParameter("pid"));
     Connection con = DBCon.getConnection();
    
    Statement st1 = con.createStatement();
    
    int rows = st1.executeUpdate(
    "update cart set quantity=quantity-1 where pid="+pid+" and uid="+uid+" and quantity>1");
    
    st1.close();
    con.close();
    
}
public void deleteProduct(HttpServletRequest request) throws Exception {
    int pid = Integer.parseInt(request.getParameter("pid"));
    Connection con = DBCon.getConnection();
    
    Statement st1 = con.createStatement();
    
    int rows = st1.executeUpdate(
    "delete from cart where pid="+pid+" and uid="+uid);
    if(rows==0){
        msg = "Please try again!";
    }
    st1.close();
    con.close();
    
}
public void buy(){
    
}
}