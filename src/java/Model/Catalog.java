
package Model;

import java.util.*;
import java.sql.*;
import javax.servlet.http.*;


public class Catalog {
   public  ArrayList<Product> products ;
   public Product currentProduct;
   public int sort; //0=ID , 1=Name , 2=Company ,3=Price
   public boolean sortDesc;// True=Descending, False=Ascending
   public String keyword;
   public int currentPage;
   public int status; //0=MainPage , 1=Details
   public String priceFormat; //"KD%.3f" , "$%.2f"
   public int prodsPage; // 6=Number of products in a page
   public int prodsLine; // 3=Number of products in a page
   
   public Catalog() throws Exception {
       products = new ArrayList<Product>();
       currentProduct = null;
       sort =0;
       sortDesc = false ;
       keyword= "";
       currentPage = 1;
       status =0;
       priceFormat ="KD%.3f";
       prodsPage =6;
       prodsLine =3;
       listAllProducts(keyword);
   }
   public void listAllProducts(String k) throws Exception{
       Connection con = DBCon.getConnection();
       if (con == null) {
     throw new Exception("Database connection is NULL");
}
       PreparedStatement pst1 = con.prepareStatement(
       "select * from product where name like ? or company like ? or details like ? ");
       
       pst1.setString(1,"%"+k+"%");
       pst1.setString(2,"%"+k+"%");
       pst1.setString(3,"%"+k+"%");
       ResultSet rs =pst1.executeQuery();
       products.clear();
       while(rs.next()){
           products.add(new Product(rs.getInt("pid"),
                                    rs.getString("name"),
                                    rs.getString("company"),
                                    rs.getString("details"),
                                    rs.getDouble("price"),
                                    rs.getInt("quantity"),
                                    rs.getString("image"),
                                    rs.getString("type"),
                                    rs.getInt("size")));
           
       }
       rs.close();
       pst1.close();
       con.close();
   }
   public void getProduct(HttpServletRequest request) throws Exception {
    int pid = Integer.parseInt(request.getParameter("pid"));
    
     Connection con = DBCon.getConnection();
    
    PreparedStatement pst1 = con.prepareStatement(
       "select * from product where pid=? ");
    
    pst1.setInt(1, pid);
    ResultSet rs = pst1.executeQuery();
    if(rs.next()){
         currentProduct=new Product(rs.getInt("pid"),
                                    rs.getString("name"),
                                    rs.getString("company"),
                                    rs.getString("details"),
                                    rs.getDouble("price"),
                                    rs.getInt("quantity"),
                                    rs.getString("image"),
                                    rs.getString("type"),
                                    rs.getInt("size"));
         
       int index = products.indexOf(currentProduct);
       products.set(index, currentProduct);
       status=1;
    }else{
        currentProduct=null;
    }
     rs.close();
     pst1.close();
     con.close();
            
    
   }
   public void mainPage() {
     status=0;  
   }
   public void getPage(HttpServletRequest request){
    int page = Integer.parseInt(request.getParameter("page"));
    currentPage = page;
   }
    public void sort(HttpServletRequest request) {
         int column;
         String desc;         
       if(request!=null){
       column = Integer.parseInt(request.getParameter("column"));  
      desc = request.getParameter("desc");
    
     sort =column;
     sortDesc= (desc!=null); 
       }
     currentPage=1;
     
     if(sort==0){ //ID
       if(!sortDesc){
         products.sort(new IDAsc());  
       }else{
         products.sort(new IDDesc());   
       }
     }else if (sort==1){ //Name
      if(!sortDesc){
        products.sort(new NameAsc());   
     }else{
        products.sort(new NameDesc());  
     }
     }else if (sort==2){ //Company
       if(!sortDesc){ 
       products.sort(new CompanyAsc());    
     }else{
        products.sort(new CompanyDesc());    
     }  
     }else if (sort==3){//Price
      if(!sortDesc){   
      products.sort(new PriceAsc());  
      }else{
       products.sort(new PriceDesc());      
     }   
     }
   }
    
   public void search(HttpServletRequest request)throws Exception{
    keyword =  request.getParameter("keyword");
    listAllProducts(keyword);
    currentPage =1;
    sort(null);
   }
}//End of class Catalog

class IDAsc implements Comparator<Product>{
    public int compare(Product a ,Product b ){
        return a.pid - b.pid;
    }
}
class IDDesc implements Comparator<Product>{
    public int compare(Product a ,Product b ){
        return b.pid - a.pid;
    }
}
class NameAsc implements Comparator<Product>{
    public int compare(Product a ,Product b ){
        return a.name.compareTo(b.name);
    }
}
class NameDesc implements Comparator<Product>{
    public int compare(Product a ,Product b ){
        return b.name.compareTo(a.name);
    }
}
class CompanyAsc implements Comparator<Product>{
    public int compare(Product a ,Product b ){
        return a.company.compareTo(b.company);
    }
}
class CompanyDesc implements Comparator<Product>{
    public int compare(Product a ,Product b ){
        return b.company.compareTo(a.company);
    }
}
class PriceAsc implements Comparator<Product>{
    public int compare(Product a ,Product b ){
        return Double.compare(a.price, b.price);
    }
}
class PriceDesc implements Comparator<Product>{
    public int compare(Product a ,Product b ){
        return Double.compare(b.price, a.price);
    }
}