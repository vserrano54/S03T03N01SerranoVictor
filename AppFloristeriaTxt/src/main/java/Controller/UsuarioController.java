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

import Entidad.UsuarioEnt;
import Entidad.UsuarioEntVista;
import Negocio.UsuarioNeg;


/**
 * Servlet implementation class UsuarioControlller
 */
@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UsuarioNeg usuarioneg;
	List<UsuarioEnt> usuarios;
	List<UsuarioEntVista> usuariosVista;
	UsuarioEnt usuario;
	RequestDispatcher rd;
	String accion="";
	int codigo;
	String nombre;
	String user;
	String password;
	String correo;
	int estado;
	int posicion;
	Object est;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsuarioEnt usuario;
		
		accion = request.getParameter("accion");
		
		switch (accion) {
		case "listar":
		
			usuarioneg = new UsuarioNeg();
			usuarios= usuarioneg.listar();
			
			usuariosVista = listaVista();
			
			request.setAttribute("lista", usuariosVista);
			rd = request.getRequestDispatcher("vistas/usuario/listarusuarios.jsp");
			rd.forward(request, response);
			
			break;
		
		case "agregar":
			
			rd = request.getRequestDispatcher("vistas/usuario/agregarusuario.jsp");
			rd.forward(request, response);
	
			
			break;
		
		case "editar":
			
			codigo = Integer.parseInt(request.getParameter("codigo"));
			posicion = usuarioneg.buscaCodigo(codigo);
			
			usuario = usuarioneg.obtenerRegistro(posicion);
			request.setAttribute("usuario", usuario);
			rd = request.getRequestDispatcher("vistas/usuario/editarusuario.jsp");
			rd.forward(request, response);
			
		 break;
		 
		case "eliminar":
			
			codigo = Integer.parseInt(request.getParameter("codigo"));
			posicion = usuarioneg.buscaCodigo(codigo);
			
			usuario = usuarioneg.obtenerRegistro(posicion);
			request.setAttribute("usuario", usuario);
			rd = request.getRequestDispatcher("vistas/usuario/eliminarusuario.jsp");
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
	
		UsuarioEnt usuario;
		
		String accion = request.getParameter("accion");
		
		switch (accion) {
		
		case "guardar":
			
			nombre = request.getParameter("txtNombre");
			user = request.getParameter("txtUsuario");
			password = request.getParameter("txtPassword");
			correo   = request.getParameter("txtCorreo");
			
			est = request.getParameter("chkEstado");
		
			if (est==null)
				estado = 0;
			else 
				estado = 1;
			
			
			
			usuario = new UsuarioEnt(0,nombre, user, password, correo, estado);
			
			if (usuarioneg.agregar(usuario))
				usuarioneg.guardar();
			
			usuariosVista = listaVista();
		    
			request.setAttribute("lista", usuariosVista);
			rd = request.getRequestDispatcher("vistas/usuario/listarusuarios.jsp");
			rd.forward(request, response);
			
			break;
		
		case "editar":
			
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			nombre = request.getParameter("txtNombre");
			user = request.getParameter("txtUsuario");
			password = request.getParameter("txtPassword");
			correo   = request.getParameter("txtCorreo");
			
			est = request.getParameter("chkEstado");
			
			if (est==null)
				estado = 0;
			else 
				estado = 1;
			
			posicion = usuarioneg.buscaCodigo(codigo);
			
			usuario = new UsuarioEnt(codigo,nombre, user, password, correo, estado);
			
			if (usuarioneg.modificar(posicion, usuario))
				usuarioneg.guardar();
			
			usuariosVista = listaVista();
		    
			request.setAttribute("lista", usuariosVista);
			rd = request.getRequestDispatcher("vistas/usuario/listarusuarios.jsp");
			rd.forward(request, response);
			
			break;
			
		case "eliminar":
			
			
			codigo = Integer.parseInt(request.getParameter("codigo"));
			
			posicion = usuarioneg.buscaCodigo(codigo);
			
			usuario = usuarioneg.obtenerRegistro(posicion);
			
			if (usuarioneg.retirar(usuario))
				usuarioneg.guardar();
			
			usuariosVista = listaVista();
		    
			request.setAttribute("lista", usuariosVista);
			rd = request.getRequestDispatcher("vistas/usuario/listarusuarios.jsp");
			rd.forward(request, response);
			
			
			break;
			
		default:
			throw new AssertionError();
		
		
		}
	}
	
	List<UsuarioEntVista> listaVista(){
		
		UsuarioEntVista usuariovista;
		List<UsuarioEntVista> lista = new ArrayList<UsuarioEntVista>();
		int codigo;
		String nombre;
		String usuariov;
		String correo;
		String estado;
		
		for (int i=0;i<usuarios.size();i++) {
			codigo = usuarios.get(i).getCodigo();
			nombre = usuarios.get(i).getNombre();
			usuariov = usuarios.get(i).getUser();
			correo = usuarios.get(i).getCorreo();
			if (usuarios.get(i).getEstado()==0)
				estado = "Inactivo";
			else
				estado = "Activo";
			
			usuariovista = new UsuarioEntVista(codigo, nombre, usuariov, correo, estado);
			lista.add(usuariovista);
			
		}
		
		return lista;
	}

}
