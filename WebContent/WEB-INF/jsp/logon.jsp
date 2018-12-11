<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="../../css/overlaystyle.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/foundation/6.4.1/css/foundation.min.css">
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../css/style.css" />

<script>

function login() {

 	var email = document.getElementById('email');
     	var password = document.getElementById('password');

	var jsonData = {};
	  jsonData['username'] = email.value;
	  jsonData['password'] = password.value;

	$.ajax({

     type: "POST",
        url : "/shell/api/a/authorize",
        cache: false,
        contentType: 'application/json;',
        dataType: "json",
        data:JSON.stringify(jsonData),
         success: function(data) {
            alert(JSON.stringify(data));
	    if (data == '')
            {
       			
               return;
            }
     	  var resultJson = $.parseJSON(JSON.stringify(data));
          alert(resultJson.result);

        }
     });
 }

</script>

<html>
<head>
    <title>logon</title>
<style>
</style>
</head>
<body>
  <h1>Shell Login</h1>

</br>
   email: <input type="text" id="email"/>
   password: <input type="text" id="password"/>
  
  <input type="button" onClick="return login()" value="Logon" />
  
</body>
</html>
