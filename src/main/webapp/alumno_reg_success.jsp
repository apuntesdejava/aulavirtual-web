<%-- 
    Document   : alumno_reg_success
    Created on : Jun 2, 2015, 12:13:26 PM
    Author     : dsilva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <div class="container">
            <h1>Registro satisfactorio</h1>
            <p>Felicitaciones, su registro fue satisfactorio.</p>
            <p>Para editar sus datos, haga clic <a href='<%= request.getContextPath()+"/CargarAlumnoServlet?id="%>${alumno.id}'>aquí</a></p>
        </div>
    </body>
</html>
