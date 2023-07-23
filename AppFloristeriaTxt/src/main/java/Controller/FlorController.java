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

import Entidad.FlorEnt;
import Entidad.FlorEntVista;
import Negocio.FlorNeg;

/**
 * Servlet implementation class ArbolController
 */
@WebServlet("/FlorController")
public class FlorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FlorNeg florneg;
	List<FlorEnt> flores;
	List<FlorEntVista> floresvista;
	FlorEnt flor;
	RequestDispatcher rd;
	String accion="";
	int codigo;
	String nombre;
	double precio;
	int color;
	int posicion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlorController() {
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
		
			florneg = new FlorNeg();
			flores= florneg.listar();
			
			floresvista = listaVista();
			
			request.setAttribute("lista", floresvista);	
			rd = request.getRequestDispatcher("vistas/flor/listarflores.jsp");
			rd.forward(request, response);
			
			break;
		
		case "agregar":
			
			rd = request.getRequestDispatcher("vistas/flor/agregarflor.jsp");
			rd.forward(request, response);
	
			
			break;
		
		case "editar":
			
			codigo = Integer.parseInt(request.getParameter("codigo"));
			posicion = florneg.buscaCodigo(codigo);
			
			flor = florneg.obtenerRegistro(posicion);
			
			int colorf =  flor.getColor();
			String c = ""+colorf;
			request.setAttribute("color", c);
			
			request.setAttribute("flor", flor);
			rd = request.getRequestDispatcher("vistas/flor/editarflor.jsp");
			rd.forward(request, response);
			
		 break;
		 
		case "eliminar":
			
			codigo = Integer.parseInt(request.getParameter("codigo"));
			posicion = florneg.buscaCodigo(codigo);
			
			flor = florneg.obtenerRegistro(posicion);
			
			int colord =  flor.getColor();
			String c2 = ""+colord;
			request.setAttribute("color", c2);
			
			request.setAttribute("flor", flor);
			rd = request.getRequestDispatcher("vistas/flor/eliminarflor.jsp");
			rd.forward(request, response);
		
		break;
			
		default:
			throw new AssertionError();
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String accion = request.getParameter("accion");
		
		switch (accion) {
		
		case "guardar":
			
			nombre = request.getParameter("txtNombre");
			precio = Double.parseDouble(request.getParameter("txtPrecio"));
			color = Integer.parseInt(request.getParameter("txtColor"));
			
			flor = new FlorEnt(0,nombre, precio, color);
			
			if (florneg.agregar(flor))
				florneg.guardar();
			
			floresvista = listaVista();
			
			request.setAttribute("lista", floresvista);
			rd = request.getRequestDispatcher("vistas/flor/listarflores.jsp");
			rd.forward(request, response);
			
			break;
		
		case "editar":
			
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			nombre = request.getParameter("txtNombre");
			precio = Double.parseDouble(request.getParameter("txtPrecio"));
			color = Integer.parseInt(request.getParameter("txtColor"));
			
			posicion = florneg.buscaCodigo(codigo);
			
			flor = new FlorEnt(codigo,nombre, precio, color);
			
			if (florneg.modificar(posicion, flor))
				florneg.guardar();
		    
			floresvista = listaVista();
			
			request.setAttribute("lista", floresvista);
			rd = request.getRequestDispatcher("vistas/flor/listarflores.jsp");
			rd.forward(request, response);
			
			break;
			
		case "eliminar":
			
			
			codigo = Integer.parseInt(request.getParameter("codigo"));
			
			posicion = florneg.buscaCodigo(codigo);
			
			flor = florneg.obtenerRegistro(posicion);
			
			if (florneg.retirar(flor))
				florneg.guardar();
			
			floresvista = listaVista();
		    
			request.setAttribute("lista", floresvista);
			
			rd = request.getRequestDispatcher("vistas/flor/listarflores.jsp");
			rd.forward(request, response);
			
			
			break;
			
		default:
			throw new AssertionError();
		
		
		}
	}
	
	List<FlorEntVista> listaVista(){
		
		FlorEntVista florvista;
		List<FlorEntVista> lista = new ArrayList<FlorEntVista>();
		int codigo;
		String nombre;
		double precio;
		String color;
		
		for (int i=0;i<flores.size();i++) {
			codigo = flores.get(i).getCodigo();
			nombre = flores.get(i).getNombre();
			precio = flores.get(i).getPrecio();
			
			switch (flores.get(i).getColor()) {
			
			case 1:
				 color="Verde";
				break;
			case 2:
				color="Naranja";
				break;
			case 3:
				color="Rosa";
				break;
			case 4:
				color="Purpura";
				break;
			case 5:
				color="Rojo";
				break;
			case 6:
				color="Blanco";
				break;
			case 7:
				color="Amarillo";
				break;
			default:
				color="Negro";
			
			}
			florvista = new FlorEntVista(codigo, nombre, precio, color);
			lista.add(florvista);
			
		}
		
		return lista;
	}

}
