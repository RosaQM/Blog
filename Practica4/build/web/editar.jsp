<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.publicacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    publicacion publicacio =(publicacion)request.getAttribute("publicacio");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${item.id ==0}"> Nuevo Registro </c:if>
            <c:if test="${item.id !=0}"> Editar Registro </c:if>
        </h1>
        
        <form action="BlogControlador" method="post">
            <table>
                <input type="hidden" name="id" value="${item.id}">
                <tr>
                    <td>Titulo</td>
                    <td><input type="text" name="titulo" value="${item.titulo}"</td>
                </tr>
                <tr>
                    <td>Descripcion</td>
                    <td><input type="text" name="descripcion" value="${item.descripcion}"</td>
                </tr>
                <tr>
                    <td>Autor</td>
                    <td><input type="text" name="autor" value="${item.autor}"</td>
                </tr>
                <tr>
                    <td>Fecha</td>
                    <td><input type="text" name="fecha" value="${item.fecha}"</td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="enviar" </td>
                </tr>
            </table>
        </form>
</html>
