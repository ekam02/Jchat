/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jchat_server;

/**
 *
 * @author Ultimate
 */
import java.util.ArrayList;

public class amigos
{
    public int _idUsuario;
    public ArrayList<String> _mensajes;
    String yo;
    String usernameamigo;

    public amigos(String yo, String usernameamigo) {       
       this.yo=yo;
       this.usernameamigo=usernameamigo;
        _mensajes = new ArrayList<String>();
    }

    public int getIdUsuario() {
        return _idUsuario;
    }

    public void setIdUsuario(int _idUsuario) {
        this._idUsuario = _idUsuario;
    }

    public String getMensajes() {
         String mensaje = "";
        if(mensaje != null)
        {
            if(_mensajes.size() > 0)
            {
                mensaje = _mensajes.get(0);
                _mensajes.remove(0);
            }
        }
        return mensaje;
    }

    public void setMensajes(String _mensajes) {
        this._mensajes.add( _mensajes);
    }

    public String getYo() {
        return yo;
    }

    public void setYo(String yo) {
        this.yo = yo;
    }

    public String getUsernameamigo() {
        return usernameamigo;
    }

    public void setUsernameamigo(String usernameamigo) {
        this.usernameamigo = usernameamigo;
    }

    
}
