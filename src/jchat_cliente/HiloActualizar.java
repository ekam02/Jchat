package jchat_cliente;

import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import jchat_interface.JChatInterface;
import jchat_server.Usuario;

public class HiloActualizar extends Thread implements Runnable
{
    public Presentacion _ventanaPresent;   
    public boolean _act;    
    public JChatInterface _cliente;
    public Usuario _usuario;
    public int[][] _salon;
    public int _dimension, _tamanio;
    private Image imagen;
    public int _tecla;
    public String _mens, _mensjUsuario;

    public HiloActualizar(Presentacion _ventanaPresent)
    {
        this._ventanaPresent = _ventanaPresent;        
        _act = true;
        
        _cliente = _ventanaPresent.getCliente();
        _usuario = _ventanaPresent.getUsuario();
        _tamanio = 15;
        _dimension = 30;
        _salon = new int[_tamanio][_tamanio];
        _tecla = 0;
        _mens = "";
        _mensjUsuario = "";
    }
    
  
 
    public void MostrarMenSis()
    {
        _mensjUsuario += _cliente.VerMensaje(_usuario._nombre);
        _mens = _mensjUsuario + "-";
        _ventanaPresent.getjTextArea1().setText("\n" +_mens.replaceAll("-", "\n"));
        _mens = "";
        
    }
    
    public void run()
    {
        while (_act) 
        {            
            try 
            {                             
                MostrarMenSis();
                _ventanaPresent.getjTextArea2().setText(_cliente.VerConectados().replaceAll(",", "\n"));
                _ventanaPresent.getjTextArea3().setText(_cliente.verInvitacion(_usuario._nombre).replaceAll(",", "\n"));
                _ventanaPresent.getjMenu3().setText(""+_cliente.verInvitacion(_usuario._nombre).replaceAll(",", "\n"));
                _ventanaPresent.getjMenu1().add(_ventanaPresent.getjMenu3());
                _ventanaPresent.getjMenuItem1().setText("Cancelar");
                _ventanaPresent.getjMenuItem2().setText("Aceptar");
                _ventanaPresent.getjMenu3().add(_ventanaPresent.getjMenuItem1());
                _ventanaPresent.getjMenu3().add(_ventanaPresent.getjMenuItem2());
                _ventanaPresent.getjTextArea4().setText(_cliente.miLitaAmigos(_usuario._nombre).replaceAll(",", "\n"));                
                Thread.sleep(100);           
                
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(HiloActualizar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void Detener()
    {
        _act = false;
    }
}
