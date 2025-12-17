
<% int index; %>

<%for(int i=0; i<cat.prodsPage;i++){%>

<% 
    index =(cat.currentPage -1 )*cat.prodsPage + i;
    if (index >= cat.products.size()) { break ;}      
%>




<div id="product" style="width:<%=100.0/cat.prodsLine%>%">
    <a href="MyController?cmd=GetProduct&pid=<%=cat.products.get(index).pid %>">
    <image width="200" height="150" src="images/products/<%=cat.products.get(index).image%>"><br>
    <%=cat.products.get(index).name%></a> <br>
    <%=String.format(cat.priceFormat,cat.products.get(index).price)%>
    
    
 </div>
 

                                <%}%>