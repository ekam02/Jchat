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

public class invitacion
{
    public int _idUsuario;
    public ArrayList<String> _mensajes;
    String usernameInvita;
    String usernameRecibe;

    public invitacion(String usernameInvita, String usernameRecibe) {       
        this.usernameInvita = usernameInvita;
        this.usernameRecibe = usernameRecibe;
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
               // _mensajes.remove(0);
            }
        }
        return mensaje;
    }

    public void setMensajes(String _mensajes) {
        this._mensajes.add( _mensajes);
    }

    public String getUsernameInvita() {
        return usernameInvita;
    }

    public void setUsernameInvita(String usernameInvita) {
        this.usernameInvita = usernameInvita;
    }

    public String getUsernameRecibe() {
        return usernameRecibe;
    }

    public void setUsernameRecibe(String usernameRecibe) {
        this.usernameRecibe = usernameRecibe;
    }
            
 
}
