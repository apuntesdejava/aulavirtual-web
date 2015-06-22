<%-- 
    Document   : alumno_form
    Created on : Jun 1, 2015, 2:41:45 PM
    Author     : dsilva
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>    </head>
    <body>
        <div class="container">
            <h1>Registro de alumno</h1>
            <form method="post" action="<%= request.getContextPath() %>/RegistroAlumnoServlet" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${alumno.id}"/>
                <div class="form-group" >                
                    <label for="nombre"> 
                        Nombre completo
                    </label>
                    <input id="nombre" value="${alumno.nombre}"  class="form-control" name="nombre" type="text" placeholder="Escriba su nombre" required="true"/>
                </div>
                <div  class="form-group">                
                    <label for="email">
                        Correo electrónico
                    </label>
                    <input id="email" value="${alumno.email}" class="form-control" name="email" type="email" placeholder="Escriba su correo electrónico" required="true"/>
                </div>
                <div class="checkbox">
                    Sexo:
                    <label for="sexoH">
                        <input type="radio" ${alumno.sexo eq 'H'?'checked':''}  value="H" name="sexo" id="sexoH" />
                        Hombre
                    </label>
                    
                    

                    <label for="sexoM">
                        <input type="radio" ${alumno.sexo eq 'M'?'checked':''} value="M" name="sexo" id="sexoM" />
                        Mujer
                    </label>                
                  

                </div>

                <div  class="form-group">                
                    <label for="fechaNacimiento">
                        Fecha nacimiento
                    </label>
                    <input id="fechaNacimiento" type="text" value="<fmt:formatDate value='${alumno.fechaNacimiento}' pattern="dd/MM/yyyy"/>" class="form-control"  name="fechaNacimiento"  placeholder="Escriba su fecha de nacimiento" required="true"/>
                </div>
                
                <div class="form-group">
                    <label for="foto">Foto</label>
                    <input type="file" name="foto" id="foto"/><br/>
                    <c:if test="${alumno.foto ne null}">
                        <img src="<%=request.getContextPath()%>/MostrarFotoServlet?id=${alumno.id}" style="width: 100px;"/>
                    </c:if>
                </div>
                
                <div>
                    <input type="submit" value="Registrar" class="btn btn-primary"/>
                </div>

            </form>
            <script>
                $("input.date").datepicker({dateFormat:"dd/mm/yy"});
            </script>
         </div>
    </body>
</html>
