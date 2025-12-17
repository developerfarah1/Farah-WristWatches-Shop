      <% if (!u.name.equals("")){ %>
     <b style="color:green"><%=u.name%></b> <hr><br>

                      <%}%>

          <% if (!u.msg.equals("")){ %>
          <span style="color: red"><%=u.msg%></span><br> <br>
          <%u.msg="";%>
          
                             <%}%>

     <% if(u.status==0){%> 
     <%@include file="user/NotSignedIn.jsp"%>      
     <%} else if(u.status==1){%>
     <%@include file="user/PreSignIn.jsp"%> 
     <%} else if(u.status==2){%>
     <%@include file="user/SignedIn.jsp"%> 
     <%} else if(u.status==4){%>
     <%@include file="user/PreChangePassword.jsp"%> 
     <%} else if(u.status==6){%>
     <%@include file="user/PreSignUp.jsp"%> 
     <%} else if(u.status==8){%>
     <%@include file="user/PreForgotPassword.jsp"%>

                              <%}%>
    
