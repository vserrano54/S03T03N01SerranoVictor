package Dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import AccesoDatos.Conexion;
import Entidad.StockEnt;

public class StockDao {
	
	private Conexion cn;
	private StockEnt stock;
	private List<StockEnt> stocks;
	
	public StockDao() { 
		this.cn = Conexion.getInstance();		
		stocks = new ArrayList<StockEnt>();
		
	}
	
	public List<StockEnt> listar() {
		stocks = new ArrayList<StockEnt>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(this.cn.getPathArchivoStock()))) {
			String linea;
			while ((linea = reader.readLine()) != null) {

				String[] partes = linea.split("┘");
				int idstock = Integer.parseInt(partes[0]);
				int tipo = Integer.parseInt(partes[1]);
				int idproducto = Integer.parseInt(partes[2]);
				int cantidad = Integer.parseInt(partes[3]);
				stock = new StockEnt(idstock,tipo, idproducto, cantidad);
				stocks.add(stock);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return stocks;
	}

	public boolean guardar() {

		boolean bandera = false;
		try (PrintWriter writer = new PrintWriter(new FileWriter(cn.getPathArchivoStock()))) {
			for (StockEnt stock : stocks) {
				writer.println(stock.getIdStock() + "┘" + stock.getTipo() + "┘" + stock.getIdProducto() + "┘" + stock.getCantidad());
			}
			bandera = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bandera;
	}
	
	public boolean agregar(StockEnt stock) {
		boolean bandera = false;
		try {
			int idstock = GenerarCodigo();
			stock.setIdStock(idstock);
			
			stocks.add(stock);
			bandera = true;

		} catch (Exception e) {
			System.out.println(e.getMessage());

			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean modificar(int i, StockEnt stock) {
		boolean bandera = false;
		try {
			stocks.set(i, stock);
			bandera = true;
	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean retirar(StockEnt stock) {
		boolean bandera = false;
		try {
			stocks.remove(stock);
			
			bandera = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;

	}
	
	public StockEnt obtenerRegistro(int i) {
		return stocks.get(i);
	}

	public int cantidadRegistro() {
		return this.stocks.size();
	}

	public int buscaCodigol(int codigo) {
		for (int i = 0; i < cantidadRegistro(); i++) {
			if (codigo == obtenerRegistro(i).getIdStock())
				return i;
		}
		return -1;
	}
	
	public int buscaIdProducto(int codigo, int tipo) {
		for (int i = 0; i < cantidadRegistro(); i++) {
			if (codigo == obtenerRegistro(i).getIdProducto() && (tipo == obtenerRegistro(i).getTipo()))
				return i;
		}
		return -1;
	}

	public int GenerarCodigo() {
		int codigo = 0;
		if (cantidadRegistro() <= 0)
			codigo = 1;
		else {
			int codTemp = obtenerRegistro(0).getIdStock();
			for (int i = 0; i < cantidadRegistro(); i++) {
				if (codTemp < obtenerRegistro(i).getIdStock()) {
					codTemp = obtenerRegistro(i).getIdStock();
				}
			}
			codigo = codTemp + 1;
		}
		return codigo;
	}	

}
