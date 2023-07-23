package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.UsuarioEnt;
import Negocio.UsuarioNeg;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UsuarioNeg usuarioneg;
	List<UsuarioEnt> usuarios;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		usuarioneg = new UsuarioNeg();
		usuarioneg.listar();
		UsuarioEnt usuario;
		
		String opcion = request.getParameter("accion");
		if (opcion.equals("validar")){
			String user = request.getParameter("txtuser");
			String pass = request.getParameter("txtpass");
			
			int posicion  = usuarioneg.validar(user, pass);
			
			if (posicion >= 0) {
				usuario = usuarioneg.obtenerRegistro(posicion);
			
				if (usuario.getUser()!=null) {
		
					request.setAttribute("usuario", usuario);
					RequestDispatcher rd = request.getRequestDispatcher("principal.jsp");
					rd.forward(request, response);
				}
				else {
					
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				
				}
			}
			else {
				
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);	
			}
		
		}
		else {
			
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);	
			
		}
	}

}
