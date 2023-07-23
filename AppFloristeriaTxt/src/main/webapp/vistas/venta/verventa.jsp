<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	//String htmlArbol = (String) request.getAttribute("htmlArbol");
	//System.out.println(htmlArbol);
	//String htmlFlor = (String) request.getAttribute("htmlFlor");
	//String htmlDecoracion = (String) request.getAttribute("htmlDecoracion");
%>
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
  
   <link href="css/venta.css"  rel="stylesheet"> 
   

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

	 <div class="container-fluid grid-block" >  
	 
	 <h3 class="mb-3 mt-3 ml-5">Registro de Factura de Venta</h3>
	 
	 <form name="frmVenta" method="post" action="" >
	 
      <div class="row">
      	 <div class="form-group form-group col-md-4">
			<label for="factura">No. Factura</label> <input
				type="text" class="form-control" id="factura" name="txtfactura" disabled 
				value="<C:out value="${venta.getFactura()}"></C:out>">
		</div>
		
		<div class="form-group form-group col-md-4">
			<label for="fecha">Fecha</label>
			<input class="form-control" name="fecha" id="fecha" type="text" disabled
			value="<C:out value="${venta.getFecha()}"></C:out>">
		</div>
		
		<div class="form-group form-group col-md-2">
			<label for="grantotal">Gran Total</label> <input
				type="text" class="form-control" id="grantotal" name="txtgrantotal" disabled 
				value="<C:out value="${venta.getGrantotal()}"></C:out>">
		</div>
      
      </div>
      

		<a href="VentaController?accion=listar"  class="btn btn-warning" data-toggle="tooltip" data-placement="top" title="Regresar a la lista de Compras"><i class="fa-solid fa-reply"></i> Regresar</a>
	</form>
   </div>
      	
<!-- -*************************************************************************** -->

<!-- -*************************************************************************** -->

		
		
		 <div class="col-sm-12 mt-3" id="productos">
			<table id="lista" class="table table-striped" >
				<thead>
					<tr>
					    <th>Linea</th>
					    <th>Tipo</th>
						<th>Nombre</th>
						<th>Cantidad</th>
						<th>Precio</th>
						<th>Total</th>
						
					</tr>
				</thead>
				<tbody>
					
					<C:forEach var="item" items="${lista}">
					
						<tr>
						 	<td> <C:out value="${item.getLinea()}"></C:out> </td>
							<td> <C:out value="${item.getTipo()}"></C:out> </td>
							<td> <C:out value="${item.getIdProducto()}"></C:out> </td>
							<td> <C:out value="${item.getCantidad()}"></C:out> </td>
							<td> <C:out value="${item.getPrecio()}"></C:out> </td>
							<td> <C:out value="${item.getTotal()}"></C:out> </td>
						</tr>
					</C:forEach>
				</tbody>
			</table>
		</div>
		<div class="col-10 text-right" id="total"></div>
      
        
        
        
      
    </div>
    <!-- /#page-content-wrapper -->

  </div>
  <!-- /#wrapper -->

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  
   <script src="bootstrap/js/jquery-3.3.1.min.js"></script>
   <!-- <script src="bootstrap/js/bootstrap.min.js"></script>  -->
   <script src="js/venta.js"></script>
    
  <!-- Menu Toggle Script -->
  <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>

</body>

</html>
