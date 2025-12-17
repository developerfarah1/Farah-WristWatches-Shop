
package Model;


public class Product {
  public int pid ;
  public String name;
  public String company;
  public String details; 
  public double  price ;
  public int quantity;
  public String image;
  public String type ;
  public int size ;
  
   public Product (){
      pid=0;
      name="";
      company="";
      details="";
      price=0;
      quantity=0;
      image="";
      type="";
      size=0;
  
   }
  public Product ( int i , String n ,String c,String d ,double p ,int q , String im , String t, int s ){
      pid=i;
      name=n;
      company=c;
      details=d;
      price=p;
      quantity=q;
      image=im;
      type=t;
      size=s;
  }
  public boolean equals (Object o){
     if (o==null) return false;
     if (o==null) return true;
     if (!(o instanceof Product)) return false;
     Product x = (Product) o;
     if(this.pid==x.pid){
         return true;
     }else{
         return false ;
         
     }
  }
  public int hashCode(){
   return((Integer)pid).hashCode();
  }
}
