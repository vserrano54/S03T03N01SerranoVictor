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
import Entidad.DatosVenta;
import Entidad.DecoracionEnt;
import Entidad.DetalleVentaEnt;
import Entidad.DetalleVentaTemp;
import Entidad.FlorEnt;
import Entidad.StockEnt;
import Entidad.VentaEnt;
import Entidad.VentaTemp;
import Entidad.VentaVistaEnt;
import Entidad.VistaDetalleVentaEnt;
import Negocio.ArbolNeg;
import Negocio.DecoracionNeg;
import Negocio.DetalleVentaNeg;
import Negocio.FlorNeg;
import Negocio.StockNeg;
import Negocio.VentaNeg;

/**
 * Servlet implementation class ventaController
 */
@WebServlet("/VentaController")
public class VentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    VentaNeg ventaneg;
	DetalleVentaNeg detalleventaneg = new DetalleVentaNeg();
	StockNeg stockneg;
	VentaTemp ventatemp;
	List<VentaEnt> listaVenta;
	List<DetalleVentaEnt> listadetalle;
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
    public VentaController() {
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
		
			ventaneg = new VentaNeg();
			listaVenta= ventaneg.listar();
			
			request.setAttribute("lista", listaVenta);
			rd = request.getRequestDispatcher("vistas/venta/listaventas.jsp");
			rd.forward(request, response);
			
			break;
		
		case "registrar":
			
			detalleventaneg = new DetalleVentaNeg();
			stockneg = new StockNeg();
			
			
			listadetalle = detalleventaneg.listar();
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
			rd = request.getRequestDispatcher("vistas/venta/venta.jsp");
			rd.forward(request, response);
			
		
			break;
			
		case "visualizar":
			
			int cod = Integer.parseInt(request.getParameter("codigo"));
			
			
			int v = ventaneg.buscaCodigol(cod);
			VentaEnt venta = ventaneg.obtenerRegistro(v);
			
			
			
			
			List<DetalleVentaEnt> detalle;
			String fac = venta.getFactura();
			detalle = detalleventaneg.listar(fac);
			
			List<VistaDetalleVentaEnt> detallesVista =  new ArrayList<>();
			detallesVista = vistaDetalleVenta(detalle);
			
			VentaVistaEnt ventav = vistaVenta(venta);
			
			request.setAttribute("venta", ventav);
			request.setAttribute("lista", detallesVista);
			rd = request.getRequestDispatcher("vistas/venta/verventa.jsp");
			rd.forward(request, response);
			
			
			break;
		}
		
		
		
	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("Entra dopost venta");
		
		 BufferedReader reader = request.getReader();
	        StringBuilder stringBuilder = new StringBuilder();
	        String line;

	        while ((line = reader.readLine()) != null) {
	        	//System.out.println(line);
	            stringBuilder.append(line);
	        }
	        
	        String datosJson = stringBuilder.toString();
	        Gson gson = new Gson();
	        DatosVenta datosVenta = gson.fromJson(datosJson, DatosVenta.class);

	        List<DetalleVentaTemp> detalletemp = datosVenta.getTabla();
	        
	        VentaTemp ventatemp = datosVenta.getDatosFactura();
	    	
	    	VentaEnt ventaent = convertirVentaEnt(ventatemp);
	    	
	    	List<DetalleVentaEnt> detalle = convertirDetalleVeta(detalletemp, ventaent);
	        
	        if (guardarVenta(ventaent,detalle)) {
	        	System.out.println("guardado");
	        	
	        	
	        	/* String respuestaJson = "{\"mensaje\": \"Datos recibidos con éxito\"}";

	             // Configurar la respuesta y enviarla al cliente
	             response.setContentType("application/json");
	             response.setCharacterEncoding("UTF-8");
	             response.getWriter().write(respuestaJson);
	        	*/
	        }
	        
	      
	        
			response.sendRedirect(request.getContextPath() + "vistas/venta/listaventas.jsp");
	        
	}
	
	private boolean guardarVenta(VentaEnt venta, List<DetalleVentaEnt> detalle ) {
		
		boolean bandera = false;

		try {
		
			if (ventaneg.agregar(venta)) {
				if (ventaneg.guardar()) {
					for (int i=0;i<detalle.size();i++) {
						int lin = detalle.get(i).getLinea() + 1;
						detalle.get(i).setLinea(lin);
						
						int liea = detalle.get(i).getLinea();
						int tipo = detalle.get(i).getTipo();
						int idpro = detalle.get(i).getIdProducto();
						int cantidad = detalle.get(i).getCantidad();
						float precio = detalle.get(i).getPrecio();
						String fac = detalle.get(i).getFactura();
						
						DetalleVentaEnt det = new DetalleVentaEnt(0, liea, tipo, idpro, cantidad, precio, fac);
						
						if (detalleventaneg.agregar(det)) {
							
							if (detalleventaneg.guardar()) {
								
								int codigo = stockneg.buscaIdProducto(idpro, tipo);
								
								if (codigo < 0) {
									
									System.out.println("No hay Stock para la venta");
								}
								else {
									
									StockEnt stock = stockneg.obtenerRegistro(codigo);
									int restaStock = stock.getCantidad()-cantidad;
									stock.setCantidad(restaStock);
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
	
	public VentaEnt convertirVentaEnt(VentaTemp venta){
		
		String factura = venta.getFactura();
		String fecha = venta.getFecha();
		
		VentaEnt ventaent = new VentaEnt(0,factura,fecha);
		
		return ventaent;
	}
	
	public List<DetalleVentaEnt> convertirDetalleVeta(List<DetalleVentaTemp> detalleTemp,VentaEnt ventaent){
		DetalleVentaEnt detalle;
		List<DetalleVentaEnt> Listadetalle = new ArrayList<> ();
		for (int i = 0; i<detalleTemp.size(); i++) {
			int linea = detalleTemp.get(i).getLinea();
			int tipo =detalleTemp.get(i).getTipo();
			int idProducto    = detalleTemp.get(i).getCod();
			int cantidad = detalleTemp.get(i).getCantidad();
			float precio = Float.parseFloat(detalleTemp.get(i).getPrecio());
			String factura = ventaent.getFactura();
			
			detalle= new DetalleVentaEnt(0,linea,tipo,idProducto,cantidad,precio,factura);
			
			Listadetalle.add(detalle);
		}
		return Listadetalle;
		
		
	}
	
	public List<VistaDetalleVentaEnt> vistaDetalleVenta(List<DetalleVentaEnt> detalleventa){
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
		
		List<VistaDetalleVentaEnt> detalles =  new ArrayList<>();
				
		VistaDetalleVentaEnt detalle;
		
		int linea;
		String tipo=""; 
		String producto;
		int idproducto;
		int cantidad;
		float precio;
		float total;
		
		for (int i= 0; i< detalleventa.size();i++) {
			
			linea = detalleventa.get(i).getLinea();
			idproducto = detalleventa.get(i).getIdProducto();
			int cod;
			if (detalleventa.get(i).getTipo()==1) {
				tipo = "Arbo";
				cod = arbolneg.buscaCodigo(idproducto);
			
				producto = arboles.get(cod).getNombre();
				
			}
			else if (detalleventa.get(i).getTipo()==2) {
				tipo = "Flor";
			
				cod = arbolneg.buscaCodigo(idproducto);
				producto = flores.get(cod).getNombre();
			}
			else {
				tipo = "Decoracion";
		
				cod = arbolneg.buscaCodigo(idproducto);
				producto = decoraciones.get(cod).getNombre();
			}
			
			cantidad = detalleventa.get(i).getCantidad();
			precio = detalleventa.get(i).getPrecio();
			total = cantidad * precio;
			
			granTotal = granTotal + total;
			detalle = new VistaDetalleVentaEnt(linea, tipo,producto,cantidad,precio,total);
			detalles.add(detalle);
		
			
		}
		
		
		return detalles;
		
	}
	
	public VentaVistaEnt vistaVenta(VentaEnt venta) {
		
		VentaVistaEnt ventavista;
		String factura = venta.getFactura();
		String fecha =   venta.getFecha().toString();
		float grantotal = granTotal;
		
		ventavista = new VentaVistaEnt(factura, fecha, grantotal);
		
		return ventavista;
		
	}

}
