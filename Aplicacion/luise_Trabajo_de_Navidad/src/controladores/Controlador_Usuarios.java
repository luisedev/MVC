/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.sun.glass.ui.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Consulta_Usuarios;
import vista.Login;
import vista.Vista;

/**
 *
 * @author Luis
 */
public class Controlador_Usuarios implements ActionListener {
    Login login;
    Consulta_Usuarios consulta;
    Controlador_Videos cv;

    public Controlador_Usuarios(Login login, Consulta_Usuarios consulta, Controlador_Videos cv) {
        this.login = login;
        this.consulta = consulta;
        this.cv = cv;
        
          login.jbtnLogin.addActionListener(this);
    }

   

   
    
    public void inicio(){
        
        login.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==login.jbtnLogin){
           boolean log=false;
           log=consulta.consultarUsuario(login.jtnombre.getText(), String.valueOf(login.jpass.getPassword()));
           if(log){
              cv.inicio();
              login.setVisible(false);
           }
       }
    }
    
    
    
}
