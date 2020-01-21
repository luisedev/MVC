/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexion {
    private final String BD="aplicacion_navideña";
    private final String USER="root";
    private final String PASS="";
    private final String URL="jdbc:mysql://localhost/"+BD;
    
    public Connection conectar(){
        Connection con=null;
        String acierto="Conectado con la base de datos";
        String error="No se puede conectar con la base de datos";
        try{
            con=(Connection) DriverManager.getConnection(URL,USER,PASS);
            System.out.println(acierto);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,error);
            
        }
        return con;
    }
    
    
}
