package Dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import AccesoDatos.Conexion;
import Entidad.DetalleVentaEnt;

public class DetalleVentaDao {
	

	private Conexion cn;
	private DetalleVentaEnt detalleventa;
	private List<DetalleVentaEnt> detalleventas;
	
	public DetalleVentaDao() { 
		this.cn = Conexion.getInstance();
		
		
	}
	
	public List<DetalleVentaEnt> listar() {

		detalleventas = new ArrayList<DetalleVentaEnt>();
		try (BufferedReader reader = new BufferedReader(new FileReader(this.cn.getPathArchivoDetalleVenta()))) {
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
				
				detalleventa = new DetalleVentaEnt(codigo,line,tipo, idProducto, cantidad, precio, factura);
				detalleventas.add(detalleventa);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return detalleventas;
	}
	
	public List<DetalleVentaEnt> listar(String fac) {

		detalleventas = new ArrayList<DetalleVentaEnt>();
		try (BufferedReader reader = new BufferedReader(new FileReader(this.cn.getPathArchivoDetalleVenta()))) {
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
				    detalleventa = new DetalleVentaEnt(codigo,line,tipo, idProducto, cantidad, precio, factura);
				    detalleventas.add(detalleventa);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return detalleventas;
	}


	public boolean guardar() {		
		

		boolean bandera = false;
		try (PrintWriter writer = new PrintWriter(new FileWriter(cn.getPathArchivoDetalleVenta()))) {
			for (DetalleVentaEnt detalle : detalleventas) {
				writer.println(detalle.getCodigo() + "┘" + detalle.getLinea() + "┘" +detalle.getTipo() + "┘" +  detalle.getIdProducto() + "┘" + detalle.getCantidad()
				+ "┘" + detalle.getPrecio() + "┘" + detalle.getFactura().trim());
			}
			bandera = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bandera;
	}
	
	public boolean agregar(DetalleVentaEnt detalle) {
		boolean bandera = false;
		try {
			int codigo = GenerarCodigo();
			detalle.setCodigo(codigo);
			
			detalleventas.add(detalle);
			bandera = true;
		
		} catch (Exception e) {
			System.out.println(e.getMessage());

			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean modificar(int i, DetalleVentaEnt detalle) {
		boolean bandera = false;
		try {
			detalleventas.set(i, detalle);
			bandera = true;
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean retirar(DetalleVentaEnt detalle) {
		boolean bandera = false;
		try {
			detalleventas.remove(detalle);
			
			bandera = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;

	}
	
	public DetalleVentaEnt obtenerRegistro(int i) {
		return detalleventas.get(i);
	}

	public int cantidadRegistro() {
		return this.detalleventas.size();
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
