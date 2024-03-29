package modelo;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import controlador.Conexion;

	public class ClienteDAO {

		Conexion cnn= new Conexion();
		Connection conec=cnn.Conecta();
		PreparedStatement ps=null;
		ResultSet res=null;
		
		public boolean Inserta_Clientes(ClientesDTO lib) {
			boolean resul=false;
			try {
			String sql="insert into clientes values(?,?,?,?,?)";
			ps = conec.prepareStatement(sql);
			ps.setInt(1, lib.getCedula());
			ps.setString(2, lib.getDireccion());
			ps.setString(3, lib.getEmail());
			ps.setString(4, lib.getNombre());
			ps.setString(5, lib.getTelefono());
			resul=ps.executeUpdate()>0;
			}catch(SQLException ex) {
			    //JOptionPane.showMessageDialog(null, "Error al insertar el cliente"+ex);
		    }
		    return resul;   
	     }
		
	    public ClientesDTO Buscar_Cliente(int cedula) {
	    	
	    	ClientesDTO lib=null;
	    	try {
	    	String sql="select * from clientes where cedula_cliente=?";
	    	ps=conec.prepareStatement(sql);
	    	ps.setInt(1, cedula);
	    	res=ps.executeQuery();
	    	while(res.next()) {
	    		lib= new ClientesDTO(res.getInt(1),res.getString(2),res.getString(3),res.getString(4), res.getString(5));
	    	}
	    	}catch(SQLException ex) {
	    		//JOptionPane.showMessageDialog(null, "Error al Consultar"+ ex);
	    	}
	    	return lib;
	    }
	    
	    public boolean Actualiza_Clientes(ClientesDTO lib) {
			boolean resul=false;
			try {
			String sql="update clientes set direccion_ciente=?, email_cliente=?, nombre_cliente=?, telefono_cliente=? where cedula_cliente=?";
			ps = conec.prepareStatement(sql);
			ps.setString(1, lib.getDireccion());
			ps.setString(2, lib.getEmail());
			ps.setString(3, lib.getNombre());
			ps.setString(4, lib.getTelefono());
			ps.setInt(5, lib.getCedula());
			resul=ps.executeUpdate()>0;
			}catch(SQLException ex) {
				//JOptionPane.showMessageDialog(null,"Error al Actualizar el cliente"+ex);
			}
			return resul;
		}
	    public boolean Eliminar_Cliente(int cedula) {
			boolean resul = false;
			try {
				String sql ="delete from clientes where cedula_cliente=?";
				ps=conec.prepareStatement(sql);
				ps.setInt(1, cedula);
				resul=ps.executeUpdate()>0;
				
			}catch(SQLException Q) {
				//JOptionPane.showMessageDialog(null, "Error de eliminacion"+Q);
			}
			return resul;
				
			}
	    
	    
	    public ArrayList<ClientesDTO> cargarClientes(){
			
			ClientesDTO lib=null;
			ArrayList<ClientesDTO> lista= new ArrayList<>();
			try {
			String sql="select * from clientes";	
			ps=conec.prepareStatement(sql);
			res=ps.executeQuery();
			while(res.next()) {
				lib= new ClientesDTO(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));
				lista.add(lib);
			}
			}catch(SQLException ex) {
				//JOptionPane.showMessageDialog(null,"Error al consultar El Cliente" +ex);
			}
			 return lista;
		}  
	    
}


