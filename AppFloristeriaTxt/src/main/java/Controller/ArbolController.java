package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.ArbolEnt;
import Negocio.ArbolNeg;

/**
 * Servlet implementation class ArbolController
 */
@WebServlet("/ArbolController")
public class ArbolController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ArbolNeg arbolneg;
	List<ArbolEnt> arboles;
	ArbolEnt arbol;
	RequestDispatcher rd;
	String accion="";
	int codigo;
	String nombre;
	double precio;
	double altura;
	int posicion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArbolController() {
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
		
			arbolneg = new ArbolNeg();
			arboles= arbolneg.listar();
			
			request.setAttribute("lista", arboles);
			rd = request.getRequestDispatcher("vistas/arbol/listararboles.jsp");
			rd.forward(request, response);
			
			break;
		
		case "agregar":
			
			rd = request.getRequestDispatcher("vistas/arbol/agregararbol.jsp");
			rd.forward(request, response);
	
			
			break;
		
		case "editar":
			
			codigo = Integer.parseInt(request.getParameter("codigo"));
			posicion = arbolneg.buscaCodigo(codigo);
			
			arbol = arbolneg.obtenerRegistro(posicion);
			request.setAttribute("arbol", arbol);
			rd = request.getRequestDispatcher("vistas/arbol/editararbol.jsp");
			rd.forward(request, response);
			
		 break;
		 
		case "eliminar":
			
			codigo = Integer.parseInt(request.getParameter("codigo"));
			posicion = arbolneg.buscaCodigo(codigo);
			
			arbol = arbolneg.obtenerRegistro(posicion);
			request.setAttribute("arbol", arbol);
			rd = request.getRequestDispatcher("vistas/arbol/eliminararbol.jsp");
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
			altura = Double.parseDouble(request.getParameter("txtAltura"));
			
			arbol = new ArbolEnt(0,nombre, precio, altura);
			
			if (arbolneg.agregar(arbol))
				arbolneg.guardar();
			
			request.setAttribute("lista", arboles);
			rd = request.getRequestDispatcher("vistas/arbol/listararboles.jsp");
			rd.forward(request, response);
			
			break;
		
		case "editar":
			
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			nombre = request.getParameter("txtNombre");
			precio = Double.parseDouble(request.getParameter("txtPrecio"));
			altura = Double.parseDouble(request.getParameter("txtAltura"));
			
			posicion = arbolneg.buscaCodigo(codigo);
			
			arbol = new ArbolEnt(codigo,nombre, precio, altura);
			
			if (arbolneg.modificar(posicion, arbol))
				arbolneg.guardar();
		    
			request.setAttribute("lista", arboles);
			rd = request.getRequestDispatcher("vistas/arbol/listararboles.jsp");
			rd.forward(request, response);
			
			break;
			
		case "eliminar":
			
			
			codigo = Integer.parseInt(request.getParameter("codigo"));
			
			posicion = arbolneg.buscaCodigo(codigo);
			
			arbol = arbolneg.obtenerRegistro(posicion);
			
			if (arbolneg.retirar(arbol))
				arbolneg.guardar();
		    
			request.setAttribute("lista", arboles);
			rd = request.getRequestDispatcher("vistas/arbol/listararboles.jsp");
			rd.forward(request, response);
			
			
			break;
			
		default:
			throw new AssertionError();
		
		
		}
	}

}
