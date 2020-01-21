/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controladores.Controlador_Usuarios;
import controladores.Controlador_Videos;
import modelo.Consulta_Usuarios;
import modelo.Consultas_Videos;
import modelo.Video;
import vista.Login;
import vista.Vista;

/**
 *
 * @author GreenThunder
 */
public class Luise_Trabajo_de_Navidad {

    
    public static void main(String[] args) {
       
        Controlador_Videos controlador=new Controlador_Videos( new Video(), new Consultas_Videos(), new Vista());
         Controlador_Usuarios controlador_Usuarios= new Controlador_Usuarios(new Login(), new Consulta_Usuarios(),controlador );
        controlador_Usuarios.inicio();
        
    }
    
}
