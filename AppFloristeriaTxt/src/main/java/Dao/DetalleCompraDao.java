package Dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import AccesoDatos.Conexion;

import Entidad.DetalleCompraEnt;

public class DetalleCompraDao {
	
	
	private Conexion cn;
	private DetalleCompraEnt detallecompra;
	private List<DetalleCompraEnt> detallecompras;
	
	public DetalleCompraDao() { 
		this.cn = Conexion.getInstance();
		
		
	}
	
	public List<DetalleCompraEnt> listar() {

		detallecompras = new ArrayList<DetalleCompraEnt>();
		try (BufferedReader reader = new BufferedReader(new FileReader(this.cn.getPathArchivoDetalleCompra()))) {
			String linea;
			while ((linea = reader.readLine()) != null) {

				String[] partes = linea.split("┘");
				int codigo = Integer.parseInt(partes[0]);
				int line = Integer.parseInt(partes[1]);
				int tipo = Integer.parseInt(partes[2]);
				int idProducto = Integer.parseInt(partes[3]);
				int cantidad = Integer.parseInt(partes[4]);
			    float precio = Float.parseFloat(partes[5]);
			    String factura = partes[6];
				
				detallecompra = new DetalleCompraEnt(codigo,line,tipo, idProducto, cantidad, precio, factura);
				detallecompras.add(detallecompra);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return detallecompras;
	}
	
	public List<DetalleCompraEnt> listar(String fac) {

		detallecompras = new ArrayList<DetalleCompraEnt>();
		try (BufferedReader reader = new BufferedReader(new FileReader(this.cn.getPathArchivoDetalleCompra()))) {
			String linea;
			while ((linea = reader.readLine()) != null) {

				String[] partes = linea.split("┘");
				
				if (partes[6].equals(fac)) {
					int codigo = Integer.parseInt(partes[0]);
					int line = Integer.parseInt(partes[1]);
					int tipo = Integer.parseInt(partes[2]);
					int idProducto = Integer.parseInt(partes[3]);
					int cantidad = Integer.parseInt(partes[4]);
				    float precio = Float.parseFloat(partes[5]);
				    String factura = partes[6];
				    detallecompra = new DetalleCompraEnt(codigo,line,tipo, idProducto, cantidad, precio, factura);
				    detallecompras.add(detallecompra);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return detallecompras;
	}


	public boolean guardar() {		
		

		boolean bandera = false;
		try (PrintWriter writer = new PrintWriter(new FileWriter(cn.getPathArchivoDetalleCompra()))) {
			for (DetalleCompraEnt detalle : detallecompras) {
				writer.println(detalle.getCodigo() + "┘" + detalle.getLinea() + "┘" +detalle.getTipo() + "┘" +  detalle.getIdProducto() + "┘" + detalle.getCantidad()
				+ "┘" + detalle.getPrecio() + "┘" + detalle.getFactura().trim());
			}
			bandera = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bandera;
	}
	
	public boolean agregar(DetalleCompraEnt detalle) {
		boolean bandera = false;
		try {
			int codigo = GenerarCodigo();
			detalle.setCodigo(codigo);
			
			detallecompras.add(detalle);
			bandera = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());

			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean modificar(int i, DetalleCompraEnt detalle) {
		boolean bandera = false;
		try {
			detallecompras.set(i, detalle);
			bandera = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean retirar(DetalleCompraEnt detalle) {
		boolean bandera = false;
		try {
			detallecompras.remove(detalle);
			bandera = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;

	}
	
	public DetalleCompraEnt obtenerRegistro(int i) {
		return detallecompras.get(i);
	}

	public int cantidadRegistro() {
		return this.detallecompras.size();
	}

	public int buscaCodigol(int codigo) {
		for (int i = 0; i < cantidadRegistro(); i++) {
			if (codigo == obtenerRegistro(i).getCodigo())
				return i;
		}
		return -1;
	}

	public int GenerarCodigo() {
		int codigo = 0;
		if (cantidadRegistro() <= 0)
			codigo = 1;
		else {
			int codTemp = obtenerRegistro(0).getCodigo();
			for (int i = 0; i < cantidadRegistro(); i++) {
				if (codTemp < obtenerRegistro(i).getCodigo()) {
					codTemp = obtenerRegistro(i).getCodigo();
				}
			}
			codigo = codTemp + 1;
		}
		return codigo;
	}	


}
