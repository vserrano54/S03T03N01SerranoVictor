package Entidad;

public class FlorEntVista extends ProductoEnt {
	
	private String color;

	public FlorEntVista(int codigo, String nombre, double precio, String color) {
		super(codigo, nombre, precio);
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return super.toString() + "Flor [color=" + color + "]";
	}

	
	
	

}
