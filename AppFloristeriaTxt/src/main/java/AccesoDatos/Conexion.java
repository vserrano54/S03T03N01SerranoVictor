package AccesoDatos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Conexion {
	
	private static Conexion instance;
	
	private String directorio;
	private String archivoArbol;
	private String archivoFlor;
	private String archivoDecoracion;
	private String archivoCompra;
	private String archivoVenta;
	private String archivoDetalleCompra;
	private String archivoDetalleVenta;
	private String archivoStock;
	private String archivoUsuario;
	
	
	private String pathArchivoArbol;
	private String pathArchivoUsuario;
	private String pathArchivoFlor;
	private String pathArchivoDecoracion;
	private String pathArchivoCompra;
	private String pathArchivoVenta;
	private String pathArchivoDetalleCompra;
	private String pathArchivoDetalleVenta;
	private String pathArchivoStock;
	
	private Conexion() {
		this.directorio				= "c:" + System.getProperty("file.separator") + "BdFloristeriaVs";
		this.archivoArbol 			= "arbol.txt";
		this.archivoFlor 			= "flor.txt";
		this.archivoDecoracion 		= "decocarion.txt";
		this.archivoCompra      	= "compra.txt";
		this.archivoVenta  			= "venta.txt";
		this.archivoDetalleCompra 	= "detallecompra.txt";
		this.archivoDetalleVenta    = "detalleventa.txt";
		this.archivoStock			= "stock.txt";
		this.archivoUsuario			= "usuario.txt";
		
		crearBd();
		
	
    }
	
	 public static Conexion getInstance() {
	        if (instance == null) {
	            instance = new Conexion();
	        }
	        return instance;
	    }
	
	 
	public String getDirectorio() {
		return directorio;
	}

	public void setDirectorio(String directorio) {
		this.directorio = directorio;
	}

	public String getArchivoArbol() {
		return archivoArbol;
	}

	public void setArchivoArbol(String archivoArbol) {
		this.archivoArbol = archivoArbol;
	}

	public String getArchivoFlor() {
		return archivoFlor;
	}

	public void setArchivoFlor(String archivoFlor) {
		this.archivoFlor = archivoFlor;
	}

	public String getArchivoDecoracion() {
		return archivoDecoracion;
	}

	public void setArchivoDecoracion(String archivoDecoracion) {
		this.archivoDecoracion = archivoDecoracion;
	}

	public String getArchivoCompra() {
		return archivoCompra;
	}

	public void setArchivoCompra(String archivoCompra) {
		this.archivoCompra = archivoCompra;
	}

	public String getArchivoVenta() {
		return archivoVenta;
	}

	public void setArchivoVenta(String archivoVenta) {
		this.archivoVenta = archivoVenta;
	}

	public String getArchivoDetalleCompra() {
		return archivoDetalleCompra;
	}

	public void setArchivoDetalleCompra(String archivoDetalleCompra) {
		this.archivoDetalleCompra = archivoDetalleCompra;
	}

	public String getArchivoDetalleVenta() {
		return archivoDetalleVenta;
	}

	public void setArchivoDetalleVenta(String archivoDetalleVenta) {
		this.archivoDetalleVenta = archivoDetalleVenta;
	}

	public String getArchivoStock() {
		return archivoStock;
	}

	public void setArchivoStock(String archivoStock) {
		this.archivoStock = archivoStock;
	}

	public String getArchivoUsuario() {
		return archivoUsuario;
	}

	public void setArchivoUsuario(String archivoUsuario) {
		this.archivoUsuario = archivoUsuario;
	}
	
	

	public String getPathArchivoArbol() {
		return pathArchivoArbol;
	}

	public void setPathArchivoArbol(String pathArchivoArbol) {
		this.pathArchivoArbol = pathArchivoArbol;
	}

	public String getPathArchivoUsuario() {
		return pathArchivoUsuario;
	}

	public void setPathArchivoUsuario(String pathArchivoUsuario) {
		this.pathArchivoUsuario = pathArchivoUsuario;
	}

	public String getPathArchivoFlor() {
		return pathArchivoFlor;
	}

	public void setPathArchivoFlor(String pathArchivoFlor) {
		this.pathArchivoFlor = pathArchivoFlor;
	}

	public String getPathArchivoDecoracion() {
		return pathArchivoDecoracion;
	}

	public void setPathArchivoDecoracion(String pathArchivoDecoracion) {
		this.pathArchivoDecoracion = pathArchivoDecoracion;
	}

	public String getPathArchivoCompra() {
		return pathArchivoCompra;
	}

	public void setPathArchivoCompra(String pathArchivoCompra) {
		this.pathArchivoCompra = pathArchivoCompra;
	}

	public String getPathArchivoVenta() {
		return pathArchivoVenta;
	}

	public void setPathArchivoVenta(String pathArchivoVenta) {
		this.pathArchivoVenta = pathArchivoVenta;
	}

	public String getPathArchivoDetalleCompra() {
		return pathArchivoDetalleCompra;
	}

	public void setPathArchivoDetalleCompra(String pathArchivoDetalleCompra) {
		this.pathArchivoDetalleCompra = pathArchivoDetalleCompra;
	}

	public String getPathArchivoDetalleVenta() {
		return pathArchivoDetalleVenta;
	}

	public void setPathArchivoDetalleVenta(String pathArchivoDetalleVenta) {
		this.pathArchivoDetalleVenta = pathArchivoDetalleVenta;
	}

	public String getPathArchivoStock() {
		return pathArchivoStock;
	}

	public void setPathArchivoStock(String pathArchivoStock) {
		this.pathArchivoStock = pathArchivoStock;
	}

	public  void crearBd() {
		
		File crearDirectorio = new File(this.getDirectorio());
		
		File crearArchivoArbol = new File(this.getDirectorio() + System.getProperty("file.separator") + this.getArchivoArbol());
		File crearArchivoFlor = new File(this.getDirectorio() + System.getProperty("file.separator") + this.getArchivoFlor());
		File crearArchivoDecoracion = new File(this.getDirectorio() + System.getProperty("file.separator") + this.getArchivoDecoracion());
		File crearArchivoCompra = new File(this.getDirectorio() + System.getProperty("file.separator") + this.getArchivoCompra());
		File crearArchivoVenta = new File(this.getDirectorio() + System.getProperty("file.separator") + this.getArchivoVenta());
		File crearArchivoDetallaCompra = new File(this.getDirectorio() + System.getProperty("file.separator") + this.getArchivoDetalleCompra());
		File crearArchivoDetalleVenta = new File(this.getDirectorio() + System.getProperty("file.separator") + this.getArchivoDetalleVenta());
		File crearArchivoStock = new File(this.getDirectorio() + System.getProperty("file.separator") + this.getArchivoStock());
		File crearArchivoUsuario = new File(this.getDirectorio() + System.getProperty("file.separator") + this.getArchivoUsuario());
		
		if (!crearDirectorio.exists()) {
			if (crearDirectorio.mkdir())
				System.out.println("El directorio " + crearDirectorio.getAbsolutePath() + " se creo de forma correcta");
		} else
			System.out.println("El directorio " + this.getDirectorio() + "  existe");
		

		if (!crearArchivoArbol.exists()) {
			try {
				if (crearArchivoArbol.createNewFile()) {

					this.setPathArchivoArbol(crearArchivoArbol.getAbsolutePath());
					System.out.println("El archivo " + this.getPathArchivoArbol() + " se creo de forma correcta");

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.setPathArchivoArbol(crearArchivoArbol.getAbsolutePath());
			System.out.println("El archivo " + this.getPathArchivoArbol() + " existe");
		}

		if (!crearArchivoFlor.exists()) {
			try {
				if (crearArchivoFlor.createNewFile()) {
					this.setPathArchivoFlor(crearArchivoFlor.getAbsolutePath());
					System.out.println("El archivo " + this.getPathArchivoFlor() + " se creo de forma correcta");

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.setPathArchivoFlor(crearArchivoFlor.getAbsolutePath());
			System.out.println("El archivo " + this.getPathArchivoFlor() + " existe");

		}

		if (!crearArchivoDecoracion.exists()) {
			try {
				if (crearArchivoDecoracion.createNewFile()) {
					this.setPathArchivoDecoracion(crearArchivoDecoracion.getAbsolutePath());
					System.out.println("El archivo " + this.getPathArchivoDecoracion() + " se creo de forma correcta");

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.setPathArchivoDecoracion(crearArchivoDecoracion.getAbsolutePath());
			System.out.println("El archivo " + this.getPathArchivoDecoracion() + " existe");
		}

		if (!crearArchivoCompra.exists()) {
			try {
				if (crearArchivoCompra.createNewFile()) {
					this.setPathArchivoCompra(crearArchivoCompra.getAbsolutePath());
					System.out.println("El archivo " + this.getPathArchivoCompra() + " se creo de forma correcta");

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			this.setPathArchivoCompra(crearArchivoCompra.getAbsolutePath());
			System.out.println("El archivo " + this.getPathArchivoCompra() + " existe");
		}

		if (!crearArchivoVenta.exists()) {
			try {
				if (crearArchivoVenta.createNewFile()) {
					this.setPathArchivoVenta(crearArchivoVenta.getAbsolutePath());
					System.out.println("El archivo " + this.getPathArchivoVenta() + " se creo de forma correcta");

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			this.setPathArchivoVenta(crearArchivoVenta.getAbsolutePath());
			System.out.println("El archivo " + this.getPathArchivoVenta() + " existe");
		}

		if (!crearArchivoDetallaCompra.exists()) {
			try {
				if (crearArchivoDetallaCompra.createNewFile()) {
					this.setPathArchivoDetalleCompra(crearArchivoDetallaCompra.getAbsolutePath());
					System.out
							.println("El archivo " + this.getPathArchivoDetalleCompra() + " se creo de forma correcta");

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			this.setPathArchivoDetalleCompra(crearArchivoDetallaCompra.getAbsolutePath());
			System.out.println("El archivo " + this.getPathArchivoDetalleCompra() + " existe");
		}

		if (!crearArchivoDetalleVenta.exists()) {
			try {
				if (crearArchivoDetalleVenta.createNewFile()) {
					this.setPathArchivoDetalleVenta(crearArchivoDetalleVenta.getAbsolutePath());
					System.out.println("El archivo " + this.pathArchivoDetalleVenta + " se creo de forma correcta");

				}
			} catch (IOException e) {
				//
				e.printStackTrace();
			}
		} else {
			this.setPathArchivoDetalleVenta(crearArchivoDetalleVenta.getAbsolutePath());
			System.out.println("El archivo " + this.getPathArchivoDetalleVenta() + " existe");
		}

		if (!crearArchivoStock.exists()) {
			try {
				if (crearArchivoStock.createNewFile()) {
					this.setPathArchivoStock(crearArchivoStock.getAbsolutePath());
					System.out.println("El archivo " + this.getPathArchivoStock() + " se creo de forma correcta");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.setPathArchivoStock(crearArchivoStock.getAbsolutePath());
			System.out.println("El archivo " + this.getPathArchivoStock() + " existe");

		}

		if (!crearArchivoUsuario.exists()) {
			try {
				if (crearArchivoUsuario.createNewFile()) {
					this.setPathArchivoUsuario(crearArchivoUsuario.getAbsolutePath());
					System.out.println("El archivo " + this.getPathArchivoUsuario() + " se creo de forma correcta");
					if (crearUsuarioAdmin())
						System.out.println("Se creo el usuario admin para loguearse en el programa");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.setPathArchivoUsuario(crearArchivoUsuario.getAbsolutePath());
			System.out.println("El archivo " + this.getPathArchivoUsuario() + " existe");

		}
						
	}
	public boolean crearUsuarioAdmin(){
    	boolean bandera = false;
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.getPathArchivoUsuario()))) {
                  writer.println("1┘Administrador┘admin┘admin┘admin@gmail.com┘1");
        	  bandera = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bandera;
    }

}
