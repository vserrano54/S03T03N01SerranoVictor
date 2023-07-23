package Dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import AccesoDatos.Conexion;
import Entidad.VentaEnt;

public class VentaDao {
	
	private Conexion cn;
	private VentaEnt venta;
	private List<VentaEnt> ventas;
	
	public VentaDao() { 
		this.cn = Conexion.getInstance();	
		
		
	}
	
	public List<VentaEnt> listar() {
		
		ventas = new ArrayList<VentaEnt>();
		try (BufferedReader reader = new BufferedReader(new FileReader(this.cn.getPathArchivoVenta()))) {
			String linea;
			while ((linea = reader.readLine()) != null) {

				String[] partes = linea.split("┘");
				int codigo = Integer.parseInt(partes[0]);
				String factura = partes[1];
				
				String fecha = partes[2];
				venta = new VentaEnt(codigo, factura, fecha);
				ventas.add(venta);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return ventas;
	}

	public boolean guardar() {

		boolean bandera = false;
		try (PrintWriter writer = new PrintWriter(new FileWriter(cn.getPathArchivoVenta()))) {
			for (VentaEnt venta : ventas) {
				writer.println(venta.getCodigo() + "┘" + venta.getFactura() + "┘" + venta.getFecha().trim());
			}
			bandera = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bandera;
	}
	
	public boolean agregar(VentaEnt venta) {
		boolean bandera = false;
		
		try {
		
			int codigo = GenerarCodigo();
		
			venta.setCodigo(codigo);
			ventas.add(venta);
			bandera = true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean modificar(int i, VentaEnt venta) {
		boolean bandera = false;
		try {
			ventas.set(i, venta);
			bandera = true;
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean retirar(VentaEnt venta) {
		boolean bandera = false;
		try {
			ventas.remove(venta);
	
			bandera = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;

	}
	
	public VentaEnt obtenerRegistro(int i) {
		return ventas.get(i);
	}

	public int cantidadRegistro() {
		
		return this.ventas.size();
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
