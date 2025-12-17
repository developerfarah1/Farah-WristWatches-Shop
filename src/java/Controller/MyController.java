package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//*****************
import Model.*;
import javax.servlet.*;
import javax.servlet.http.*;

//-----------------------------------------------

@WebServlet(name = "MyController", urlPatterns = {"/MyController"})
public class MyController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{  

   HttpSession sess = request.getSession();
   User u = (User) sess.getAttribute("u");
   Catalog cat =(Catalog) sess.getAttribute("cat");
   Cart cart =(Cart) sess.getAttribute("cart");
   
    if (u == null){
        u= new User();
        cat = new Catalog();
        cart = new Cart();
        
         sess.setAttribute("u", u);
         sess.setAttribute("cat", cat);
         sess.setAttribute("cart", cart);
         
    }
        String cmd = request.getParameter("cmd");
        
        if(cmd.equals("NotSignedIn")){
            u.notSignedIn();
        }else if(cmd.equals("PreSignIn")){
            u.preSignIn();  
        }else if(cmd.equals("SignedIn")){
            u.signedIn(request);  
        }else if(cmd.equals("SignedOut")){
            u.signedOut(); 
        }else if(cmd.equals("PreChangePassword")){
            u.preChangePassword();
        }else if(cmd.equals("PasswordChanged")){
            u.passwordChanged(request);  
        }else if(cmd.equals("PreSignUp")){
            u.preSignUp();
        }else if(cmd.equals("SignedUp")){
            u.signedUp(request);    
        }else if(cmd.equals("PreForgotPassword")){
            u.preForgotPassword();       
        }else if(cmd.equals("ForgotPassword")){
            u.forgotPassword(request);
        }else if(cmd.equals("Sort")){
            cat.sort(request);
        }else if(cmd.equals("Search")){
            cat.search(request);    
        }else if(cmd.equals("GetProduct")){
            cat.getProduct(request);    
        }else if(cmd.equals("GetPage")){
            cat.getPage(request);   
        }else if(cmd.equals("MainPage")){
            cat.mainPage();       
        }else if(cmd.equals("AddToCart")){
            cart.addToCart(request);    
        }else if(cmd.equals("MoreQ")){
            cart.moreQ(request);    
        }else if(cmd.equals("LessQ")){
            cart.lessQ(request);   
        }else if(cmd.equals("DeleteProduct")){
            cart.deleteProduct(request);       
        }else if(cmd.equals("Buy")){
            cart.buy();       
            
    
            
           }else{
            throw new Exception ("Command" +cmd+" not found!");
        }  
        
        String url = "/index.jsp";
        RequestDispatcher rd =this.getServletContext().getRequestDispatcher(url);
        rd.forward(request, response); 
        
        }catch(Exception e) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyController at " + request.getContextPath() + "</h1>");
            
            out.println("<pre>");
            e.printStackTrace(out);
            out.println("</pre>");
            
            out.println("</body>");
            out.println("</html>");
        }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
