package Entidad;

public class DecoracionEnt extends ProductoEnt{
	
	private int tipoMaterial;

	public DecoracionEnt(int codigo, String nombre, double precio, int tipoMaterial) {
		super(codigo, nombre, precio);
		this.tipoMaterial = tipoMaterial;
	}

	public int getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(int tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	@Override
	public String toString() {
		return super.toString() + "Decoracion [tipoMaterial=" + tipoMaterial + "]";
	}
	
	

}
