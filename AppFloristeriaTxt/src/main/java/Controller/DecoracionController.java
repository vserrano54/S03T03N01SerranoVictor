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

import Entidad.DecoracionEnt;
import Entidad.DecoracionEntVista;
import Negocio.DecoracionNeg;

/**
 * Servlet implementation class ArbolController
 */
@WebServlet("/DecoracionController")
public class DecoracionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DecoracionNeg decoracionneg;
	List<DecoracionEnt> decoraciones;
	List<DecoracionEntVista> decoracionesvista;
	DecoracionEnt decoracion;
	RequestDispatcher rd;
	String accion="";
	int codigo;
	String nombre;
	double precio;
	int tipomaterial;
	int posicion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DecoracionController() {
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
		
			decoracionneg = new DecoracionNeg();
			decoraciones = decoracionneg.listar();
			
			decoracionesvista = listaVista();
			
			request.setAttribute("lista", decoracionesvista);
			rd = request.getRequestDispatcher("vistas/decoracion/listardecoraciones.jsp");
			rd.forward(request, response);
			
			break;
		
		case "agregar":
			
			rd = request.getRequestDispatcher("vistas/decoracion/agregardecoracion.jsp");
			rd.forward(request, response);
	
			
			break;
		
		case "editar":
			
			codigo = Integer.parseInt(request.getParameter("codigo"));
			posicion = decoracionneg.buscaCodigo(codigo);
			
			decoracion = decoracionneg.obtenerRegistro(posicion);
			
			int mateteial =  decoracion.getTipoMaterial();
			String tipo = ""+mateteial;
			
			request.setAttribute("tipomaterial", tipo);
			
			request.setAttribute("decoracion", decoracion);
			rd = request.getRequestDispatcher("vistas/decoracion/editardecoracion.jsp");
			rd.forward(request, response);
			
		 break;
		 
		case "eliminar":
			
			codigo = Integer.parseInt(request.getParameter("codigo"));
			posicion = decoracionneg.buscaCodigo(codigo);
			
			decoracion = decoracionneg.obtenerRegistro(posicion);
			mateteial =  decoracion.getTipoMaterial();
			tipo = ""+mateteial;
			
			request.setAttribute("tipomaterial", tipo);
			
			decoracion = decoracionneg.obtenerRegistro(posicion);
			request.setAttribute("decoracion", decoracion);
			rd = request.getRequestDispatcher("vistas/decoracion/eliminardecoracion.jsp");
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
			tipomaterial = Integer.parseInt(request.getParameter("txtTipoMaterial"));
			
			decoracion = new DecoracionEnt(0,nombre, precio, tipomaterial);
			
			if (decoracionneg.agregar(decoracion))
				decoracionneg.guardar();
			
			decoracionesvista = listaVista();
			
			request.setAttribute("lista", decoracionesvista);
			rd = request.getRequestDispatcher("vistas/decoracion/listardecoraciones.jsp");
			rd.forward(request, response);
			
			break;
		
		case "editar":
			
			Integer.parseInt(request.getParameter("codigo"));
			nombre = request.getParameter("txtNombre");
			precio = Double.parseDouble(request.getParameter("txtPrecio"));
			tipomaterial =Integer.parseInt(request.getParameter("txtTipoMaterial"));
			
			posicion = decoracionneg.buscaCodigo(codigo);
			
			decoracion = new DecoracionEnt(codigo,nombre, precio, tipomaterial);
			
			if (decoracionneg.modificar(posicion, decoracion))
				decoracionneg.guardar();
			
			decoracionesvista = listaVista();
		    
			request.setAttribute("lista", decoracionesvista);
			rd = request.getRequestDispatcher("vistas/decoracion/listardecoraciones.jsp");
			rd.forward(request, response);
			
			break;
			
		case "eliminar":
			
			codigo = Integer.parseInt(request.getParameter("codigo"));
		
			
			posicion = decoracionneg.buscaCodigo(codigo);
			
			decoracion = decoracionneg.obtenerRegistro(posicion);
			
			if (decoracionneg.retirar(decoracion))
				decoracionneg.guardar();
			
			decoracionesvista = listaVista();
		    
			request.setAttribute("lista", decoracionesvista);
			rd = request.getRequestDispatcher("vistas/decoracion/listardecoraciones.jsp");
			rd.forward(request, response);
			
			
			break;
			
		default:
			throw new AssertionError();
		
		
		}
	}
	
List<DecoracionEntVista> listaVista(){
		
		DecoracionEntVista decoracionvista;
		List<DecoracionEntVista> lista = new ArrayList<DecoracionEntVista>();
		int codigo;
		String nombre;
		double precio;
		String tipomaterial;
		
		for (int i=0;i<decoraciones.size();i++) {
			codigo = decoraciones.get(i).getCodigo();
			nombre = decoraciones.get(i).getNombre();
			precio = decoraciones.get(i).getPrecio();
			
			switch (decoraciones.get(i).getTipoMaterial()) {
			
			case 1:
				 tipomaterial="Pampa grass";
				break;
			case 2:
				tipomaterial="Monsteras";
				break;
			case 3:
				tipomaterial="Lanza de palma";
				break;
			case 4:
				tipomaterial="Aspidistras";
				break;
			case 5:
				tipomaterial="Hojas de eucalipto baby";
				break;
			case 6:
				tipomaterial="Hiedras";
				break;
			case 7:
				tipomaterial="Hojas de magnolia";
				break;
			case 8:
				tipomaterial="Liriope";
				break;
			case 9:
				tipomaterial="Mirto";
				break;
			case 10:
				tipomaterial="Cineraria marítima";
				break;
			case 11:
				tipomaterial="Sorgo";
				break;
			default:
				tipomaterial="Tepozán";
			
			}
			decoracionvista = new DecoracionEntVista(codigo, nombre, precio, tipomaterial);
			lista.add(decoracionvista);
			
		}
		
		return lista;
	}

}
