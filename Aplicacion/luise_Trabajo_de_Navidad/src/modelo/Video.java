/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.Date;
public class Video {
    
    int id;
    int cliente;
    
    String nombre;
    String descripcion;
    String nombre_usuario;
    
    Date fecha;

    public Video() {}

    public Video(int id, String nombre, int cliente,  String descripcion, Date fecha, String nombre_usuario) 
    {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.fecha=fecha;
        this.nombre_usuario=nombre_usuario;
    }


    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getCliente() { return cliente; }
    public void setCliente(int cliente) { this.cliente = cliente; }
    
    public String getNombre_usuario() { return nombre_usuario; }
    public void setNombre_usuario(String nombre_usuario) {  this.nombre_usuario = nombre_usuario; }

}
