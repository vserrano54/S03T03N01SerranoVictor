package Entidad;

public class UsuarioEnt {
	int codigo;
	String nombre;
	String user;
	String password;
	String correo;
	int estado;
	public UsuarioEnt(int codigo, String nomobre, String user, String password,String correo, int estado) {
		this.codigo = codigo;
		this.nombre = nomobre;
		this.user = user;
		this.password = password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nombre=" + nombre + ", user=" + user + "]";
	}
		
}

