<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>web-floristeria</title>

  <!-- Bootstrap core CSS -->
  <link href="botstrap/css/bootstrap.min.css" rel="stylesheet" type="txt/css"> 
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="fontawesome/css/all.css"  rel="stylesheet"> 
   

  <!-- Custom styles for this template -->
  <link href="css/simple-sidebar.css" rel="stylesheet">
</head>

<body>

  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">Web Floristería</div>
      <div class="list-group list-group-flush">
		<a href="principal.jsp" class="list-group-item list-group-item-action bg-light"><i class="fa-solid fa-house" style="color: #0080ff;"></i> Inicio</a>
        <a href="ArbolController?accion=listar"  class="list-group-item list-group-item-action bg-light"><i class="fa-solid fa-tree" style="color: #0080ff;"></i> Catálogo de Arboles</a>
        <a href="FlorController?accion=listar" class="list-group-item list-group-item-action bg-light"><i class="fa-solid fa-seedling" style="color: #0080ff;"></i> Catálogo de Flores</a>
        <a href="DecoracionController?accion=listar" class="list-group-item list-group-item-action bg-light"><i class="fa-solid fa-gem" style="color: #0080ff;"></i> Catálogo de Decoraciones</a>
        <a href="CompraController?accion=listar" class="list-group-item list-group-item-action bg-light"><i class="fa-solid fa-store" style="color: #0080ff;"></i> Compras</a>
        <a href="VentaController?accion=listar" class="list-group-item list-group-item-action bg-light"><i class="fa-solid fa-money-bill-trend-up" style="color: #0080ff;"></i> Ventas</a>
        <a href="StockController?accion=listar" class="list-group-item list-group-item-action bg-light"><i class="fa-solid fa-boxes-stacked" style="color: #0080ff;"></i> Stock</a>
        <a href="UsuarioController?accion=listar"  class="list-group-item list-group-item-action bg-light"><i class="fa-solid fa-user-tie" style="color: #0080ff;"></i> Usuarios</a>
      </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
        <button class="btn btn-light" id="menu-toggle"><i class="fa-solid fa-bars" style="color: #0080ff;"></i></button>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item active">
              <a class="nav-link" href="principal.jsp"><i class="fa-solid fa-house" style="color: #0080ff;"></i> inicio <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="index.jsp"><i class="fa-solid fa-user-tie" style="color: #0080ff;"></i> Iniciar Sesion <span class="sr-only">(current)</span></a>
            </li>
           
          </ul>
        </div>
      </nav>
 	  <!-- ----------------------------------------------------------------------------------------------- -->
      <div class="container-fluid">
      
       <h3 class="mb-3 mt-3 ml-5">Editar Arbol</h3>
       
      <div class="col-sm-6 ml-5">
        <form name="frmUsuario" method="post" action="ArbolController?accion=editar" >
        
     
        
		<input type="hidden" class="form-control" id="codigo" name="codigo"
				value="<C:out value="${arbol.getCodigo()}"></C:out>">
				
		<div class="form-group">
			<label for="txtCodigo">Código</label> 
			<input type="text" class="form-control" id="codigo" name="txtcodigo" disabled
				value="<C:out value="${arbol.getCodigo()}"></C:out>">
		</div>
		
		
		

		<div class="form-group">
			<label for="nombre">Nombre</label> <input
				type="text" class="form-control" id="nombre" name="txtNombre"
				placeholder="Ingresa el nombre"
				value="<C:out value="${arbol.getNombre()}"></C:out>">
		</div>
		
		<div class="form-group">
			<label for="txtPrecio">Precio</label> <input
				type="text" class="form-control" id="precio" name="txtPrecio" 
				placeholder="Ingresa el precio"
				value="<C:out value="${arbol.getPrecio()}"></C:out>">
		</div>
		
		<div class="form-group">
			<label for="txtAltura">Altura</label> <input
				type="text" class="form-control" id="altura" name="txtAltura" 
				placeholder="Ingrese la altura"
				value="<C:out value="${arbol.getAltura()}"></C:out>">
		</div>
		
		
		<button type="submit" class="btn btn-primary"><i class="fa-solid fa-floppy-disk"></i> Guardar</button>
		<a href="ArbolController?accion=listar"   class="btn btn-warning" data-toggle="tooltip" data-placement="top" title="Regresar a la lista de arbol"><i class="fa-solid fa-reply"></i> Regresar</a>
	</form>
	</div>
          
        
      </div>
      <!-- ----------------------------------------------------------------------------------------------- -->
    </div>
    <!-- /#page-content-wrapper -->

  </div>
  <!-- /#wrapper -->

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  
   <script src="bootstrap/js/jquery-3.3.1.min.js"></script>
   <!-- <script src="bootstrap/js/bootstrap.min.js"></script>  -->
   
   <script src="js/myScript.js"></script>

  <!-- Menu Toggle Script -->
  <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>

</body>

</html>