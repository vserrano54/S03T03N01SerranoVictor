package Controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Entidad.ArbolEnt;
import Entidad.CompraEnt;
import Entidad.DetalleCompraTemp;
import Entidad.DatosCompra;
import Entidad.CompraTemp;
import Entidad.CompraVistaEnt;
import Entidad.DecoracionEnt;
import Entidad.DetalleCompraEnt;
import Entidad.FlorEnt;
import Entidad.StockEnt;
import Entidad.VistaDetalleCompraEnt;
import Negocio.ArbolNeg;
import Negocio.CompraNeg;
import Negocio.DecoracionNeg;
import Negocio.DetalleCompraNeg;
import Negocio.FlorNeg;
import Negocio.StockNeg;

/**
 * Servlet implementation class CompraController
 */
@WebServlet("/CompraController")
public class CompraController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	CompraNeg compraneg;
	DetalleCompraNeg detallecompraneg = new DetalleCompraNeg();
	StockNeg stockneg;
	CompraTemp compratemp;
	List<CompraEnt> listaCompra;
	List<DetalleCompraEnt> listadetalle;
	List<StockEnt> listaStock;
	
	String arbolJson;
	String florJson;
	String decoracionJson;
	
	RequestDispatcher rd;
	String accion="";
	float granTotal=0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompraController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		accion = request.getParameter("accion");
			
		switch (accion) {
		case "listar":
		
			compraneg = new CompraNeg();
			listaCompra= compraneg.listar();
			
			request.setAttribute("lista", listaCompra);
			rd = request.getRequestDispatcher("vistas/compra/listacompras.jsp");
			rd.forward(request, response);
			
			break;
		
		case "registrar":
			
			detallecompraneg = new DetalleCompraNeg();
			stockneg = new StockNeg();
			
			
			listadetalle = detallecompraneg.listar();
			listaStock = stockneg.listar();
			
			ArbolNeg arbolneg = new ArbolNeg();
			List<ArbolEnt> arboles;
			FlorNeg florneg = new FlorNeg();
			List<FlorEnt> flores;
			DecoracionNeg decoracionneg = new DecoracionNeg();
			List<DecoracionEnt> decoraciones;
			
			arboles = arbolneg.listar();
			flores = florneg.listar();
			decoraciones = decoracionneg.listar();
			
			arbolJson = generarJsonArbol(arboles); 
			florJson = generarJsonFlor(flores);
			decoracionJson = generarJsonDecoracion(decoraciones);
			
			
			request.setAttribute("arbolJson", arbolJson);
			request.setAttribute("florJson", florJson);
			request.setAttribute("decoracionJson", decoracionJson);
			rd = request.getRequestDispatcher("vistas/compra/compra.jsp");
			rd.forward(request, response);
			
		
			break;
			
		case "visualizar":
			
			int cod = Integer.parseInt(request.getParameter("codigo"));	
			int c = compraneg.buscaCodigol(cod);
			CompraEnt compra = compraneg.obtenerRegistro(c);
			
			List<DetalleCompraEnt> detalle;
			String fac = compra.getFactura();
			detalle = detallecompraneg.listar(fac);
			
			List<VistaDetalleCompraEnt> detallesVista =  new ArrayList<>();
			detallesVista = vistaDetalleCompra(detalle);
			
			CompraVistaEnt comprav = vistaCompra(compra);
			
			request.setAttribute("compra", comprav);
			request.setAttribute("lista", detallesVista);
			rd = request.getRequestDispatcher("vistas/compra/vercompra.jsp");
			rd.forward(request, response);
			
			
			break;
		}
		
		
		
	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		 BufferedReader reader = request.getReader();
	        StringBuilder stringBuilder = new StringBuilder();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	        }
	        
	        String datosJson = stringBuilder.toString();
	        Gson gson = new Gson();
	        DatosCompra datosCompra = gson.fromJson(datosJson, DatosCompra.class);

	        List<DetalleCompraTemp> detalletemp = datosCompra.getTabla();
	        
	        CompraTemp compratemp = datosCompra.getDatosFactura();
	    	
	    	CompraEnt compraent = convertirCompraEnt(compratemp);
	    	
	    	List<DetalleCompraEnt> detalle = convertirDetalleComprat(detalletemp, compraent);
	        
	        if (guardarCompra(compraent,detalle)) {
	        	System.out.println("guardado");
	        	
	        }
	        

			response.sendRedirect(request.getContextPath() + "vistas/compra/listacompras.jsp");
	        
	}
	
	private boolean guardarCompra(CompraEnt compra, List<DetalleCompraEnt> detalle ) {
		boolean bandera = false;

		try {
		
			if (compraneg.agregar(compra)) {
				if (compraneg.guardar()) {
					for (int i=0;i<detalle.size();i++) {
						int lin = detalle.get(i).getLinea() + 1;
						detalle.get(i).setLinea(lin);
						
						int liea = detalle.get(i).getLinea();
						int tipo = detalle.get(i).getTipo();
						int idpro = detalle.get(i).getIdProducto();
						int cantidad = detalle.get(i).getCantidad();
						float precio = detalle.get(i).getPrecio();
						String fac = detalle.get(i).getFactura();
						
						DetalleCompraEnt det = new DetalleCompraEnt(0, liea, tipo, idpro, cantidad, precio, fac);
						
						if (detallecompraneg.agregar(det)) {
							
							if (detallecompraneg.guardar()) {
								
								int codigo = stockneg.buscaIdProducto(idpro, tipo);
								
								if (codigo < 0) {
									
									StockEnt estock = new StockEnt(0,tipo, idpro,cantidad);
									if (stockneg.agregar(estock))
										stockneg.guardar();
								}
								else {
									
									StockEnt stock = stockneg.obtenerRegistro(codigo);
									int sumaStock = stock.getCantidad()+cantidad;
									stock.setCantidad(sumaStock);
									if (stockneg.modificar(codigo, stock));
										stockneg.guardar();
										
								}
							}
						}
					}	
				}
				bandera = true;	
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return bandera;
		
	}
	
	
	public String generarJsonArbol(List<ArbolEnt> lista) {
		System.out.println("entrod a capa dato json");
	     Gson gson = new Gson();
	     String listaJson = gson.toJson(lista);
	     return listaJson;

	}
	
	public String generarJsonFlor(List<FlorEnt> lista) {
		
	     Gson gson = new Gson();
	     String listaJson = gson.toJson(lista);
	     return listaJson;
	     
	}

	public String generarJsonDecoracion(List<DecoracionEnt> lista) {
		
		 Gson gson = new Gson();
	     String listaJson = gson.toJson(lista);
	     return listaJson;

	}
	
	public CompraEnt convertirCompraEnt(CompraTemp compra){
		
		String factura = compra.getFactura();
		String fecha = compra.getFecha();
		
		CompraEnt compraent = new CompraEnt(0,factura,fecha);
		
		return compraent;
	}
	
	public List<DetalleCompraEnt> convertirDetalleComprat(List<DetalleCompraTemp> detalleTemp,CompraEnt compraent){
		DetalleCompraEnt detalle;
		List<DetalleCompraEnt> Listadetalle = new ArrayList<> ();
		for (int i = 0; i<detalleTemp.size(); i++) {
			int linea = detalleTemp.get(i).getLinea();
			int tipo =detalleTemp.get(i).getTipo();
			int idProducto    = detalleTemp.get(i).getCod();
			int cantidad = detalleTemp.get(i).getCantidad();
			float precio = Float.parseFloat(detalleTemp.get(i).getPrecio());
			String factura = compraent.getFactura();
			
			detalle= new DetalleCompraEnt(0,linea,tipo,idProducto,cantidad,precio,factura);
			
			Listadetalle.add(detalle);
		}
		return Listadetalle;
		
		
	}
	
	public List<VistaDetalleCompraEnt> vistaDetalleCompra(List<DetalleCompraEnt> detallecompra){
		granTotal=0;
		
		ArbolNeg arbolneg = new ArbolNeg();
		List<ArbolEnt> arboles;
		FlorNeg florneg = new FlorNeg();
		List<FlorEnt> flores;
		DecoracionNeg decoracionneg = new DecoracionNeg();
		List<DecoracionEnt> decoraciones;
		
		arboles = arbolneg.listar();
		flores = florneg.listar();
		decoraciones = decoracionneg.listar();
		
		List<VistaDetalleCompraEnt> detalles =  new ArrayList<>();
				
		VistaDetalleCompraEnt detalle;
		
		int linea;
		String tipo=""; 
		String producto;
		int idproducto;
		int cantidad;
		float precio;
		float total;
		
		for (int i= 0; i< detallecompra.size();i++) {
			
			linea = detallecompra.get(i).getLinea();
			idproducto = detallecompra.get(i).getIdProducto();
			int cod;
			if (detallecompra.get(i).getTipo()==1) {
				tipo = "Arbo";
				cod = arbolneg.buscaCodigo(idproducto);
			
				producto = arboles.get(cod).getNombre();
				
			}
			else if (detallecompra.get(i).getTipo()==2) {
				tipo = "Flor";
				
				cod = arbolneg.buscaCodigo(idproducto);
				producto = flores.get(cod).getNombre();
			}
			else {
				tipo = "Decoracion";
				
				cod = arbolneg.buscaCodigo(idproducto);
				producto = decoraciones.get(cod).getNombre();
			}
			
			cantidad = detallecompra.get(i).getCantidad();
			precio = detallecompra.get(i).getPrecio();
			total = cantidad * precio;
			
			granTotal = granTotal + total;
			detalle = new VistaDetalleCompraEnt(linea, tipo,producto,cantidad,precio,total);
			detalles.add(detalle);
			
		}
		return detalles;
		
	}
	
	public CompraVistaEnt vistaCompra(CompraEnt compra) {
		
		CompraVistaEnt compravista;
		String factura = compra.getFactura();
		String fecha =   compra.getFecha().toString();
		float grantotal = granTotal;
		
		compravista = new CompraVistaEnt(factura, fecha, grantotal);
		
		return compravista;
		
	}
	

}
