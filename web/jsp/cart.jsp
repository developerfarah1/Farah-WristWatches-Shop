<b>Shopping Cart:</b><br><br>
 <% 
     cart.uid = u.uid;
     cart.listCartProducts();  
%>

<% if(u.name.equals("")){%>
  Sign in to use the shopping cart...
  <% }else { %>

<% if (!cart.msg.equals("")) {%>
  <b style="color: red"><%=cart.msg%></b>
  <%cart.msg="";%>
  <br> <br>
                                   <%}%>
  
  <br>Number of Product:<%=cart.products.size()%><br><br>
  
  <% for(int i=0; i<cart.products.size();i++){%>
  
  <img width="80" height="70" src="images/products/<%=cart.products.get(i).image%>"><br>
  ID:<%=cart.products.get(i).pid%><br>
  Name:<a href="MyController?cmd=GetProduct&pid=<%=cart.products.get(i).pid%>"><%=cart.products.get(i).name%></a><br>
  Price:<%=String.format(cat.priceFormat,cart.products.get(i).price)%></br>
  Quantity:<%=cart.products.get(i).quantity%>
  <a href="MyController?cmd=MoreQ&pid=<%=cart.products.get(i).pid%>">+</a>
  <a href="MyController?cmd=LessQ&pid=<%=cart.products.get(i).pid%>">-</a>
  <a href="MyController?cmd=DeleteProduct&pid=<%=cart.products.get(i).pid%>">x</a>
  <br>
  <hr>
  <br>
                                     <%}%>
  
  Total = <%=String.format(cat.priceFormat,cart.totalPrice)%><br> <br>
  
  <form action="MyController" method="post">
      <input type="submit" value="Buy" <%if(cart.products.size()==0){%>disabled<%}%>>
      <input type="hidden" name="cmd" value="Buy">           
  </form>
      
                                      <%}%>