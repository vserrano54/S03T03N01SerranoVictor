package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.ArbolEnt;
import Entidad.DecoracionEnt;
import Entidad.FlorEnt;
import Entidad.StockEnt;
import Entidad.VistaStockEnt;
import Negocio.ArbolNeg;
import Negocio.DecoracionNeg;
import Negocio.FlorNeg;
import Negocio.StockNeg;

/**
 * Servlet implementation class StockController
 */
@WebServlet("/StockController")
public class StockController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	StockNeg stockneg = new StockNeg();
	RequestDispatcher rd;
	String accion="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockController() {
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
		
		List<StockEnt> stockent =  new ArrayList<>();
		stockent = stockneg.listar();
		List<VistaStockEnt> vista;
		vista = vistaStock(stockent);
	
		request.setAttribute("lista", vista);
		rd = request.getRequestDispatcher("vistas/stock/listastock.jsp");
		rd.forward(request, response);
		
		break;
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}


	public List<VistaStockEnt> vistaStock(List<StockEnt> listastock){
	
	
	ArbolNeg arbolneg = new ArbolNeg();
	List<ArbolEnt> arboles;
	FlorNeg florneg = new FlorNeg();
	List<FlorEnt> flores;
	DecoracionNeg decoracionneg = new DecoracionNeg();
	List<DecoracionEnt> decoraciones;
	
	arboles = arbolneg.listar();
	flores = florneg.listar();
	decoraciones = decoracionneg.listar();
	
	List<VistaStockEnt> vistastocks =  new ArrayList<>();
			
	VistaStockEnt vistastock;
	
	int linea;
	String tipo=""; 
	String producto;
	int idproducto;
	int cantidad;
	
	for (int i= 0; i< listastock.size();i++) {
		
		linea = listastock.get(i).getIdStock();
		idproducto = listastock.get(i).getIdProducto();
		int cod;
		if (listastock.get(i).getTipo()==1) {
			tipo = "Arbo";
			cod = arbolneg.buscaCodigo(idproducto);
			producto = arboles.get(cod).getNombre();
			
		}
		else if (listastock.get(i).getTipo()==2) {
			tipo = "Flor";
			cod = arbolneg.buscaCodigo(idproducto);
			producto = flores.get(cod).getNombre();
		}
		else {
			tipo = "Decoracion";
			cod = arbolneg.buscaCodigo(idproducto);
			producto = decoraciones.get(cod).getNombre();
		}
		
		cantidad = listastock.get(i).getCantidad();
		
		vistastock = new VistaStockEnt(linea, tipo,producto,cantidad);
		vistastocks.add(vistastock);
		
	}
	
	return vistastocks;
	}


}