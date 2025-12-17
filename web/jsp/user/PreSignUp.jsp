<b>Sign Up:</b>
<form action="MyController" method="post">
      <table border="0"> 
        <tr>
          <td>Username:</td>  
          <td><input type="text" name="username"  value=""  required></td>  
       </tr>
        
         <tr>
           <td>Password:</td>
            <td><input type="password" name="password" value=""  required></td>
       </tr>
        
         <tr>
            <td>Confirm:</td>
            <td><input type="password" name="confirm" value=""  required></td>
       </tr>
        
         <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value=""  required></td>
       </tr>
        
         <tr>
            <td>Email:</td>
            <td><input type="email" name="email" value=""  required></td>
       </tr>
       
         <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" value=""  required></td>
       </tr>
        
         <tr>
            <td>Address:</td>
            <td><input type="text" name="address" value=""  required></td>
       </tr>
        
         <tr>
           <td> <a href="MyController?cmd=NotSignedIn">Cancel</a></td>
           <td> <input type="submit" value="Sign Up"></td>
           <td> <input type="hidden" name="cmd" value="SignedUp"></td>  
       </tr>
   </table>
</form>