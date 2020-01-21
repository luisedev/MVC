/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import modelo.Consultas_Videos;
import modelo.Video;
import vista.Vista;

public class Controlador_Videos implements ActionListener{
    Video video;
    Consultas_Videos consultar;
    Vista vista;
    DefaultTableModel modeloTabla;
    ArrayList<Video> listaVideos=new ArrayList<>();
    int seleccion;
    

    public Controlador_Videos(Video video, Consultas_Videos consultar, Vista vista) {
        this.video = video;
        this.consultar = consultar;
        this.vista = vista;
        
        //agregación de listeners
        accion_en_tabla();
        vista.jButtonInsert.addActionListener(this);
        vista.jButtonModif.addActionListener(this);
        vista.btnDelete.addActionListener(this);
    }
    
    
    public void inicio()
    {
        vista.setVisible(true);
        modeloTabla=new DefaultTableModel();
        vista.jTableDatos.setModel(modeloTabla);
        cargarEncabezadosVideo(modeloTabla);
        cargarVideos(); 
    }

    private void cargarEncabezadosVideo(DefaultTableModel modeloTabla) 
    {
      modeloTabla.addColumn("Id");
      modeloTabla.addColumn("Título");
      modeloTabla.addColumn("Usuario");
      modeloTabla.addColumn("Descripción");
    }
    
    public void cargarVideos(){
        modeloTabla.setRowCount(0);
        listaVideos=new ArrayList<>();
        listaVideos=consultar.todos_los_videos();
        
        
        for (Video v: listaVideos) {
            String []aux = new String []
                    {
                        String.valueOf(v.getId()),
                        v.getNombre(),
                        String.valueOf(v.getCliente()),
                        v.getDescripcion()
                    };
            modeloTabla.addRow(aux);  
        }//fín foreach
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
        accion_boton_insertar(e);
        accion_boton_eliminar(e);
        accion_boton_modificar(e);
       
        
    }

    private void accion_en_tabla() {
        
        vista.jTableDatos.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            seleccion=vista.jTableDatos.getSelectedRow();
            
            Video v=listaVideos.get(seleccion);
            
            vista.jlabelAutor.setText(v.getNombre_usuario());
            vista.jlabelTitulo.setText(v.getNombre());
            vista.jlabeldescripcion.setText(v.getDescripcion());
            vista.jlabelFecha.setText(v.getFecha().toString());
            
            ImageIcon imagen = new ImageIcon("src/imagenes/"+String.valueOf(v.getId())+".jpg");
            Image nimagen = imagen.getImage();
            Image escalada = nimagen.getScaledInstance(vista.jlabelImagen.getWidth(),vista.jlabelImagen.getHeight(),BufferedImage.SCALE_SMOOTH);
            ImageIcon img=new ImageIcon(escalada);
            vista.jlabelImagen.setIcon(img);
            
            vista.jtAutorModificar.setText(String.valueOf(v.getCliente()));
            vista.jtTituloModificar.setText(v.getNombre());
            vista.jtaDescModificar.setText(v.getDescripcion());
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void accion_boton_insertar(ActionEvent e) {
       if(e.getSource()==vista.jButtonInsert)
       {
           try{
               int autor=Integer.parseInt(vista.jtAutor.getText());
           }catch(NumberFormatException nfe){
               JOptionPane.showMessageDialog(vista, "Autor no válido");
           }
           String mensaje= consultar.subirVideo(vista.jtTitulo.getText(),Integer.parseInt(vista.jtAutor.getText()), vista.jtaDescripcion.getText());
           JOptionPane.showMessageDialog(vista, mensaje);
           cargarVideos();
       }
    }

    private void accion_boton_eliminar(ActionEvent e) {
        if(e.getSource()==vista.btnDelete){
           int ide=listaVideos.get(seleccion).getId();
           String mensaje=consultar.eliminarVideo(ide);
           JOptionPane.showMessageDialog(vista, mensaje);
           cargarVideos();
       }
    }

    private void accion_boton_modificar(ActionEvent e) {
        if(e.getSource()==vista.jButtonModif)
       {        
          int idm=listaVideos.get(seleccion).getId();
           System.out.println(idm);
                   
           String mensaje=consultar.modificarVideo(idm, vista.jtTituloModificar.getText(), vista.jtaDescModificar.getText());
           JOptionPane.showMessageDialog(vista, mensaje);
           cargarVideos();
     
       }
    }
      
}
