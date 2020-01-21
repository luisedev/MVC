/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis
 */
public class Consulta_Usuarios extends Conexion {
    Connection con=conectar();
    
    public boolean consultarUsuario(String nombre, String pass){
        boolean log;
        String mensaje="";
        PreparedStatement ps;
        ResultSet rs;
        String sql="SELECT `Nombre`, `Password` FROM `usuarios` WHERE nombre=? AND Password=?";
        
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, pass);
            rs=ps.executeQuery();
            
            if(rs.next()){
             mensaje="Usuario y contraseña correctos. Bienvenido/a";
             log=true;
                
            }else{
                log=false;
                mensaje="El usuario y contraseña no coincide con nuestros registros.";
            }
        }catch(SQLException e){
            log=false;
            mensaje="El usuario y contraseña no coincide con nuestros registros.";
        }
        JOptionPane.showMessageDialog(null, mensaje);
        return log;
    }
}
