package Entidad;

public class FlorEnt extends ProductoEnt {
	
	private int color;

	public FlorEnt(int codigo, String nombre, double precio, int color) {
		super(codigo, nombre, precio);
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return super.toString() + "Flor [color=" + color + "]";
	}

	
	
	

}
