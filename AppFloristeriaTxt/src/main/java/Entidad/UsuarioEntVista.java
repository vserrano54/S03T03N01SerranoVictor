package Entidad;

public class UsuarioEntVista {
	int codigo;
	String nombre;
	String user;
	String correo;
	String estado;
	public UsuarioEntVista(int codigo, String nomobre, String user,String correo, String estado) {
		this.codigo = codigo;
		this.nombre = nomobre;
		this.user = user;
		this.correo = correo;
		this.estado = estado;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNomobre(String nomobre) {
		this.nombre = nomobre;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nombre=" + nombre + ", user=" + user + "]";
	}
		
}
