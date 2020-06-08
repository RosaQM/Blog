<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.publicacion"%>
<%
    if (session.getAttribute("logueado") != "OK"){
        response.sendRedirect("login.jsp");
        
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<publicacion> lista = (List<publicacion>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Blog de salud</h1>
        <p>
            <a href="BlogControlador?opc=nuevo">Nuevo Registro</a>
        </p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Autor</th>
                <th>Fecha</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${lista}">
               <tr>
                <td>${item.id}</td>
                <td>${item.titulo}</td>
                <td>${item.descripcion}</td>
                <td>${item.autor}</td>
                <td>${item.fecha}</td>
                <td><a href="BlogControlador?opc=editar&id=${item.id}">Editar</a></td>
                <td><a href="BlogControlador?opc=eliminar&id=${item.id}" onclick="return(confirm('Esta seguro??'))">Eliminar</a></td>
            </tr>
            </c:forEach>
            
            
        </table>
        <p></p>
        <br>
        <a href="LoginControlador?action=logout"> Salir</a>

    </body>
</html>
