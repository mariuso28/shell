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

function getSuccess() {

     $.ajax({

    type: "GET",
         url : '/shell/api/anon/getSuccess',
    cache: false,
 	 contentType: 'application/json;',
         dataType: "json",
        // data: {key:key }, 
         success: function(data) {
            alert(JSON.stringify(data));
	    if (data == '')
            {
       			
               return;
            }
	  //  jdata = JSON.parse(data)
            
     	    var resultJson = $.parseJSON(JSON.stringify(data));
          alert(resultJson.result);

        }
     });
 }

</script>

<html>
<head>
    <title>success</title>
<style>
</style>
</head>
<body>
  Shell Hello

  <script>
    getSuccess();
  </script>

</body>
</html>
