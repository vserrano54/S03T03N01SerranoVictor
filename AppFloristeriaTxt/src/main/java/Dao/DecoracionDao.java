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

public class DecoracionDao {
	
	private Conexion cn;
	private DecoracionEnt decoracion;
	private List<DecoracionEnt> decoraciones;
	
	public DecoracionDao() { 
		this.cn = Conexion.getInstance();		
		
	}
	
	public List<DecoracionEnt> listar() {

		decoraciones = new ArrayList<DecoracionEnt>();
		try (BufferedReader reader = new BufferedReader(new FileReader(this.cn.getPathArchivoDecoracion()))) {
			String linea;
			while ((linea = reader.readLine()) != null) {

				String[] partes = linea.split("┘");
				int codigo = Integer.parseInt(partes[0]);
				String nombre = partes[1];
				double precio = Double.parseDouble(partes[2]);
				int tipomaterial =Integer.parseInt(partes[3]);
				decoracion = new DecoracionEnt(codigo, nombre, precio, tipomaterial);
				decoraciones.add(decoracion);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return decoraciones;
	}

	public boolean guardar() {

		boolean bandera = false;
		try (PrintWriter writer = new PrintWriter(new FileWriter(cn.getPathArchivoDecoracion()))) {
			for (DecoracionEnt decoracion : decoraciones) {
				writer.println(decoracion.getCodigo() + "┘" + decoracion.getNombre() + "┘" + decoracion.getPrecio() + "┘"
						+ decoracion.getTipoMaterial());
			}
			bandera = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bandera;
	}
	
	public boolean agregar(DecoracionEnt decoracion) {
		boolean bandera = false;
		try {
			int codigo = GenerarCodigo();
			decoracion.setCodigo(codigo);
			
			decoraciones.add(decoracion);
			bandera = true;
		
		} catch (Exception e) {
			System.out.println(e.getMessage());

			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean modificar(int i, DecoracionEnt decoracion) {
		boolean bandera = false;
		try {
			decoraciones.set(i, decoracion);
			bandera = true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean retirar(DecoracionEnt decoracion) {
		boolean bandera = false;
		try {
			decoraciones.remove(decoracion);
		
			bandera = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;

	}
	
	public DecoracionEnt obtenerRegistro(int i) {
		return decoraciones.get(i);
	}

	public int cantidadRegistro() {
		return this.decoraciones.size();
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