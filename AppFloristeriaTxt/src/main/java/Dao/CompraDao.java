package Dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import AccesoDatos.Conexion;
import Entidad.CompraEnt;


public class CompraDao {
	
	private Conexion cn;
	private CompraEnt compra;
	private List<CompraEnt> compras;
	
	public CompraDao() { 
		this.cn = Conexion.getInstance();	
		
		
	}
	
	public List<CompraEnt> listar() {
		compras = new ArrayList<CompraEnt>();
		try (BufferedReader reader = new BufferedReader(new FileReader(this.cn.getPathArchivoCompra()))) {
			String linea;
			while ((linea = reader.readLine()) != null) {

				String[] partes = linea.split("┘");
				int codigo = Integer.parseInt(partes[0]);
				String factura = partes[1];
				
				String fecha = partes[2];
				compra = new CompraEnt(codigo, factura, fecha);
				compras.add(compra);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return compras;
	}

	public boolean guardar() {

		boolean bandera = false;
		try (PrintWriter writer = new PrintWriter(new FileWriter(cn.getPathArchivoCompra()))) {
			for (CompraEnt compra : compras) {
				writer.println(compra.getCodigo() + "┘" + compra.getFactura() + "┘" + compra.getFecha().trim());
			}
			bandera = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bandera;
	}
	
	public boolean agregar(CompraEnt compra) {
		boolean bandera = false;
		
		try {
		
			int codigo = GenerarCodigo();
		
			compra.setCodigo(codigo);
			compras.add(compra);
			bandera = true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());

			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean modificar(int i, CompraEnt compra) {
		boolean bandera = false;
		try {
			compras.set(i, compra);
			bandera = true;
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean retirar(CompraEnt compra) {
		boolean bandera = false;
		try {
			compras.remove(compra);
	
			bandera = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;

	}
	
	public CompraEnt obtenerRegistro(int i) {
		return compras.get(i);
	}

	public int cantidadRegistro() {
		
		return this.compras.size();
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
