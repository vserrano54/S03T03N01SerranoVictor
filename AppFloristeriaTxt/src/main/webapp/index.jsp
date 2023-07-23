<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
 <!-- Bootstrap core CSS -->
  <link href="botstrap/css/bootstrap.min.css" rel="stylesheet" type="txt/css"> 
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <link href="fontawesome/css/all.css"  rel="stylesheet"> 
  
</head>
<body>

<div class="container mt-4 col-lg-4">
	<div class="card col-sm-10">
		<div class="card-body">
			<form class="form-sign" action="LoginController" method="POST">
				<input type="hidden" name="accion" value="validar">
				<div class="form-group text-center">
					<h3>Login</h3>
					
					<label>Bienvenido</label>
				
				</div>
				<div class="form-group">
					<label>Usuario</label>
					<input type="text" name="txtuser" class="form-control">
				</div>
				<div class="form-group">
					<label>Password</label>
					<input type="password" name="txtpass" class="form-control">
				</div>
				<input type="submit" name="accion" value="Ingresar" class="btn btn-primary btn-block">
			</form>
		</div>
	</div>
</div>


</body>
</html>