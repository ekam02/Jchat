package jchat_server;

import java.util.ArrayList;

public class Usuario
{
    public String _nombre;
    public String _avatar;
    public String _estado;
    public int _idUsuario;
    public ArrayList<String> _mensajes;

    public Usuario(String _nombre, String _avatar) 
    {
        this._nombre = _nombre;
        this._avatar = _avatar;
        this._estado = "NOUNIDO";
        this._idUsuario = -1;
        _mensajes = new ArrayList<String>();
    }   
    
    public void setMensaje(String mensaje)
    {
        _mensajes.add(mensaje);
    }
    
    public String getMensaje()
    {
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
}
