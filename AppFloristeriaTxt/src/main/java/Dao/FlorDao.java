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

public class FlorDao {
	
	private Conexion cn;
	private FlorEnt flor;
	private List<FlorEnt> flores;
	
	public FlorDao() { 
		this.cn = Conexion.getInstance();		
		
	}
	
	public List<FlorEnt> listar() {

		flores = new ArrayList<FlorEnt>();
		try (BufferedReader reader = new BufferedReader(new FileReader(this.cn.getPathArchivoFlor()))) {
			String linea;
			while ((linea = reader.readLine()) != null) {

				String[] partes = linea.split("┘");
				int codigo = Integer.parseInt(partes[0]);
				String nombre = partes[1];
				double precio = Double.parseDouble(partes[2]);
				int color = Integer.parseInt(partes[3]);
				flor = new FlorEnt(codigo, nombre, precio, color);
				flores.add(flor);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return flores;
	}

	public boolean guardar() {

		boolean bandera = false;
		try (PrintWriter writer = new PrintWriter(new FileWriter(cn.getPathArchivoFlor()))) {
			for (FlorEnt flor : flores) {
				writer.println(flor.getCodigo() + "┘" + flor.getNombre() + "┘" + flor.getPrecio() + "┘"
						+ flor.getColor());
			}
			bandera = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bandera;
	}
	
	public boolean agregar(FlorEnt flor) {
		boolean bandera = false;
		try {
			flores.add(flor);
			
			int codigo = GenerarCodigo();
			flor.setCodigo(codigo);
			bandera = true;
		
		} catch (Exception e) {
			System.out.println(e.getMessage());

			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean modificar(int i, FlorEnt flor) {
		boolean bandera = false;
		try {
			flores.set(i, flor);
			bandera = true;
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean retirar(FlorEnt flor) {
		boolean bandera = false;
		try {
			flores.remove(flor);
		
			bandera = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;

	}
	
	public FlorEnt obtenerRegistro(int i) {
		return flores.get(i);
	}

	public int cantidadRegistro() {
		return this.flores.size();
	}

	public int buscaCodigo(int codigo) {
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
