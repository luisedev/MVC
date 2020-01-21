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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Consultas_Videos extends Conexion {
    
    Connection con=conectar();
    
    public ArrayList<Video> todos_los_videos(){
       
        ArrayList<Video> videos= new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        
        String sql="SELECT v.ID, v.NOMBRE, v.CLIENTE, v.DESCRIPCION, cv.FECHA_SUBIDA,c.NOMBRE FROM videos v JOIN clientes_videos cv ON(v.ID=cv.VIDEO) JOIN clientes c ON(cv.CLIENTE=c.IP_CLIENTE)";
        
        try {
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){   videos.add( new Video( rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDate(5) , rs.getString(6)    ));  }
            
            } 
        catch (SQLException ex) { Logger.getLogger(Consultas_Videos.class.getName()).log(Level.SEVERE, null, ex); }
        
        return videos;
    }
    
    public String subirVideo( String nombre, int cliente,  String descripcion){
    
        String mensaje="";
     
     try{
         String sql="INSERT INTO `videos`(`ID`, `NOMBRE`, `CLIENTE`, `DESCRIPCION`) VALUES (null,?,?,?)";
         PreparedStatement ps = con.prepareStatement(sql);
         
         ps.setString(1, nombre);
         ps.setInt(2, cliente);
         ps.setString(3, descripcion);
         
         int verificar=ps.executeUpdate();
         if(verificar!=0){   mensaje="El vídeo se subió de forma correcta";}
         else{  mensaje="El vídeo no se pudo subir";}
         
     } catch (Exception ex) {mensaje+="ERROR: No existe el autor introducido"; }
     
     return mensaje;
    }
  
    
    public String modificarVideo (int id, String nombreModif, String DescripModif){
        String mensaje="";
        
        try{
            String sql="UPDATE `videos` SET `NOMBRE`=?,`DESCRIPCION`=? WHERE ID=?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreModif);
            ps.setString(2, DescripModif);
            ps.setInt(3,id);
            
            int verificar= ps.executeUpdate();
            if(verificar!=0){   mensaje="El vídeo se modificó de forma correcta";}
            else{  mensaje="El vídeo no se pudo modificar";}
            
        }catch (SQLException ex) {
            
            mensaje+=ex.getMessage();
        }
        
        return mensaje;
        
    }
    
    public String eliminarVideo(int id){
        String mensaje="";
        
        try{
            String sql="DELETE FROM VIDEOS WHERE ID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
           
            int verificar = ps.executeUpdate();
            if(verificar!=0){   mensaje =   "El vídeo se borró de forma correcta";}
            else            {   mensaje =   "El vídeo no se pudo borrar";}
            
        }catch (SQLException ex) {
            
            mensaje+=ex.getMessage();
        }
        
        return mensaje;
    }
}
