package Entidad;

public class ArbolEnt extends ProductoEnt {
	
	private double altura;

	public ArbolEnt(int codigo, String nombre, double precio, double altura) {
		super(codigo, nombre, precio);
		this.altura = altura;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	@Override
	public String toString() {
		return super.toString() + "Arbol [altura=" + altura + "]";
	}
	
	
}
