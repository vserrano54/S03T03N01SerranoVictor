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

public class UsuarioDao {
	
	private Conexion cn;
	private UsuarioEnt usuario;
	private List<UsuarioEnt> usuarios;
	
	public UsuarioDao() { 
		this.cn = Conexion.getInstance();		
	}
	
	public List<UsuarioEnt> listar() {
		
    	usuarios = new ArrayList<UsuarioEnt>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.cn.getPathArchivoUsuario()))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("┘");
               
                int codigo = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                String user = partes[2];
                String password = partes[3];
                String correo = partes[4];
                int estado = Integer.parseInt(partes[5]);
                usuario = new UsuarioEnt(codigo,nombre,user,password,correo,estado);
                usuarios.add(usuario);          
            }   
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return usuarios;
    }
	

	public boolean guardar() {

		boolean bandera = false;
		try (PrintWriter writer = new PrintWriter(new FileWriter(cn.getPathArchivoUsuario()))) {
			for (UsuarioEnt usuario : usuarios) {
				writer.println(usuario.getCodigo() + "┘" + usuario.getNombre() + "┘" + usuario.getUser() + "┘"
						+ usuario.getPassword()+ "┘" + usuario.getCorreo()  + "┘" + usuario.getEstado());
			}
			bandera = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bandera;
	}
	
	public boolean agregar(UsuarioEnt usuario) {
		boolean bandera = false;
		try {
			int codigo =generarCodigo();
			usuario.setCodigo(codigo);
			usuarios.add(usuario);
			bandera = true;
		
		} catch (Exception e) {
			System.out.println(e.getMessage());

			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean modificar(int i, UsuarioEnt usuario) {
		boolean bandera = false;
		try {
			usuarios.set(i, usuario);
			bandera = true;
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;
	}
	
	public boolean retirar(UsuarioEnt usuario) {
		boolean bandera = false;
		try {
			usuarios.remove(usuario);
			
			bandera = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bandera;

	}
	
	public UsuarioEnt obtenerRegistro(int i){
		
		return usuarios.get(i);
		
	}

	public int cantidadRegistro(){
		
        return this.usuarios.size();
        
	}

	public int buscaCodigo(int codigo) {
		for (int i = 0; i < cantidadRegistro(); i++) {
			if (codigo == obtenerRegistro(i).getCodigo())
				return i;
		}
		return -1;
	}
	
	public int validar(String user, String pass){
		
		for(int i = 0; i < cantidadRegistro(); i++){
			
			if(user.equalsIgnoreCase(obtenerRegistro(i).getUser())
					&& (pass.equals(obtenerRegistro(i).getPassword())))
				return i;
	     }
	     return -1;
	     
	}

	public int generarCodigo() {
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
