<b>Change Password:</b>
<form action="MyController" method="post">
      <table border="0">
        
     <tr>
      <td>Current Password:</td>
      <td><input type="password" name="currentpassword" required></td>  
    </tr>
        
     <tr>
      <td>New Password:</td>
      <td><input type="password" name="newpassword" required></td>
     </tr>
        
      <tr>
       <td>Confirm Password:</td>
       <td><input type="password" name="confirmpassword" required></td> 
      </tr>
        
      <tr>
       <td> <a href="MyController?cmd=SignedIn">Cancel</a></td>
       <td> <input type="submit" value="Change"></td>
       <td> <input type="hidden" name="cmd" value="PasswordChanged"></td> 
     </tr>
   </table> 
</form>
