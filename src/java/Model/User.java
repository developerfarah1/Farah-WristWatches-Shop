package Model;

import javax.servlet.http.*;
import java.sql.*;
 public class User {
 public int uid;
 public String username;
 public String name;
 public String email;
 public String phone;
 public String address;
 public int status; // 0=NotSignedIn , 1= PreSignIn, 2 = SignedIn
                    // 3=SignedOut , 4= PreChangePassword ,5=PasswordChange
                    // 6 PreSigin Up , 7=SingedUp, 8=PreForgotPassword, 9=ForgotPassword
 public String msg ;
 
 public User (){
     uid =0;
     username ="";
     name="";
     email="";
     phone ="";
     address="";
     status=0;
     msg="";
 }   
 public void notSignedIn(){
     status=0;
 }
 
 public void preSignIn(){
     status=1;
 }
 
 public void signedIn(HttpServletRequest request)throws Exception{
     if(!username.equals("")){
           status=2;
     }else{
     String u =request.getParameter("username");
     String p =request.getParameter("password"); 
       if(u.equals("") || p.equals("")){
       msg = "Please fill all fields!"; 
         
       }else{ 
           
      Connection con = DBCon.getConnection();
     
      PreparedStatement pst1 = con.prepareStatement(
            "select* from user where username=? and password=sha(?)");

   pst1.setString(1,u);
   pst1.setString(2,p);
   ResultSet rs =pst1.executeQuery();
     if (rs.next()){
            uid=rs.getInt("uid");
            username=rs.getString("username");
            name=rs.getString("name");
            email=rs.getString("email");
            phone=rs.getString("phone");
            address=rs.getString("address");
            status=2;
            msg = "Welcome  "+name;
       
        
      }else{
  msg= "please check your username and password . or sign up" ;
       
   }
   
     
       rs.close();
       pst1.close();
       con.close();
   
 }
      }
 }
 public void signedOut(){
     
        msg="See you soon "+name;
        uid =0;
        username ="";
        name="";
        email="";
        phone ="";
        address="";
        status=0;
    
 }
 
 public void preChangePassword(){
        status=4;
 }
 
 public void passwordChanged(HttpServletRequest request)throws Exception{
         String cp =request.getParameter("currentpassword");
         String np =request.getParameter("newpassword");
         String c =request.getParameter("confirmpassword");
             if(cp.equals("") || np.equals("") || c.equals("")){
             msg= "Please fill all fields!";
            }else if(!np.equals(c)){
             msg="Please confirm your new password";
             
      }else{ 
          
      Connection con = DBCon.getConnection();
     
    PreparedStatement pst1 = con.prepareStatement(
    "update user set password=sha(?) where username=? and password=sha(?)");
    
         pst1.setString(1,np);
         pst1.setString(2,username);
         pst1.setString(3,cp);
             
    int rows = pst1.executeUpdate();
     if (rows>0){
  msg= "Password changed successfuly .Please use it next time to Sign in next time.";
         status=2;
     }else{
              msg="Please try again!";
 }
    
    pst1.close();
    con.close();
    
           }
 }
 
 public void preSignUp(){
        status=6;
 }
 
 public void signedUp(HttpServletRequest request)throws Exception{
        String u =request.getParameter("username");
        String p =request.getParameter("password");
        String c =request.getParameter("confirm");
        String n =request.getParameter("name");
        String e =request.getParameter("email");
        String ph =request.getParameter("phone");
        String a = request.getParameter("address");
      
        if(u.equals("") || p.equals("") || c.equals("") || n.equals("") 
                   || e.equals("") || ph.equals("")|| a.equals("")) {
                       msg = "Please fill all fields!";  
                        }else if(!p.equals(c)){
                        msg= "Please comfirm your password!";
                        
    }else{
                            
    Connection con = DBCon.getConnection();
    PreparedStatement pst1 = con.prepareStatement("select* from user where username=?");
                  
       PreparedStatement pst2 = con.prepareStatement(
  "insert into user(username,password,name,email,phone,address) values(?,sha(?),?,?,?,?)");
          
          pst1.setString(1,u);    
           ResultSet rs = pst1.executeQuery();
          if(rs.next()){
      msg = "Username exists ! Please choose another one or sign in !";
              
          }else{
              pst2.setString(1,u);
              pst2.setString(2,p);
              pst2.setString(3,n);
              pst2.setString(4,e);
              pst2.setString(5,ph);
              pst2.setString(6,a);
              int rows =pst2.executeUpdate();
              if (rows>0){
                  msg="sign up successful.Please sign in..";
                  status=0;
              }else{
                  msg= "Please try again!";
              }
          }
          
          
          
             rs.close();
             pst1.close();
             pst2.close();
             con.close();
      }
      }
 public void preForgotPassword(){
        status=8;
        
 }
 
 public void forgotPassword(HttpServletRequest request)throws Exception{
     
        String n =request.getParameter("name");
        String e =request.getParameter("email");
        if(n.equals("") || e.equals("")){
          msg= "Please fill all fields!";
          
      }else{
          
          Connection con = DBCon.getConnection();
     
    PreparedStatement pst1 = con.prepareStatement(
            "update user set password=sha('abc123') where name=? and email=?");
    
    pst1.setString(1,n);
    pst1.setString(2,e);
    int rows = pst1.executeUpdate();
     if (rows>0){
    msg= " Password was updated to 'abc123' Please use it to sign in change it ! ";
                  status=0;
           
     }else{
            msg=" Please try again! ";
            
     }
    
    pst1.close();
    con.close();
          
     }
               }
}