package Dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import AccesoDatos.*;
import Entidad.*;

public class ArbolDao {
	
	private Conexion cn;
	private ArbolEnt arbol;
	private List<ArbolEnt> arboles;
	
	public ArbolDao() { 
		this.cn = Conexion.getInstance();		
		
	}
	
	public List<ArbolEnt> listar() {

		arboles = new ArrayList<ArbolEnt>();
		try (BufferedReader reader = new BufferedReader(new FileReader(this.cn.getPathArchivoArbol()))) {
			String linea;
			while ((linea = reader.readLine()) != null) {

				String[] partes = linea.split("┘");
				int codigo = Integer.parseInt(partes[0]);
				String nombre = partes[1];
				double precio = Double.parseDouble(partes[2]);
				double altura = Double.parseDouble(partes[3]);
				arbol = new ArbolEnt(codigo, nombre, precio, altura);
				arboles.add(arbol);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return arboles;
	}

	public boolean guardar() {

		boolean bandera = false;
		try (PrintWriter writer = new PrintWriter(new FileWriter(cn.getPathArchivoArbol()))) {
			for (ArbolEnt arbol : arboles) {
				writer.println(arbol.getCodigo() + "┘" + arbol.getNombre() + "┘" + arbol.getPrecio() + "┘"
						+ arbol.getAltura());
			}
			bandera = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bandera;
	}
	
	public boolean agregar(ArbolEnt arbol) {
		boolean bandera = false;
		try {
			int codigo = GenerarCodigo();
			arbol.setCodigo(codigo);
			
			arboles.add(arbol);
			bandera = true;
		
		} catch (Exception e) {
			System.out.println(e.getMessage());

			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean modificar(int i, ArbolEnt arbol) {
		boolean bandera = false;
		try {
			arboles.set(i, arbol);
			bandera = true;
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean retirar(ArbolEnt arbol) {
		boolean bandera = false;
		try {
			arboles.remove(arbol);
		
			bandera = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;

	}
	
	public ArbolEnt obtenerRegistro(int i) {
		return arboles.get(i);
	}

	public int cantidadRegistro() {
		return this.arboles.size();
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
