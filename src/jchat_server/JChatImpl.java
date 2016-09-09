package jchat_server;

import java.rmi.RemoteException;
import java.util.*;
import jchat_interface.JChatInterface;

public class JChatImpl implements JChatInterface
{
    public ArrayList<Usuario> _usuarios;
    public ArrayList<invitacion> _invitacion;
    public ArrayList<amigos> _amigo;
    public int _identificadorUsuarios;        
    public ArrayList<String> _mensageServer;
    public String _estado;

    public JChatImpl()
    {
        _usuarios = new ArrayList<Usuario>();
        _invitacion= new ArrayList<invitacion>();
        _amigo= new ArrayList<amigos>();
        _identificadorUsuarios = 0;               
        _mensageServer = new ArrayList<String>();
        _estado = "NE";
    }
    
    @Override
    public String IniciarSesion(String username, String avatar)
    {
        String encontrado = "false";
        String agregado = "false";
            if(_usuarios.isEmpty())//si el array de usuarios se encuentra vacio
            {
                //se coloca al jugador en una posicion aleatoria de la pantalla                
                _identificadorUsuarios++;
                Usuario usuario = new Usuario(username, avatar);
                usuario._idUsuario = _identificadorUsuarios;
                _usuarios.add(usuario);               
                agregado = "true";
            }
            else
            {
                for (int i = 0; i < _usuarios.size(); i++) 
                {
                    if(_usuarios.get(i)._nombre.equals(username))
                    {
                        encontrado = "true";
                        break;
                    }
                }
                if(encontrado.equals("true"))//si se encontro el usuario
                    agregado = "false";
                else //si no existe se agrega al array de usuarios
                {                  
                    _identificadorUsuarios++;
                    Usuario usuario = new Usuario(username, avatar);
                    usuario._idUsuario = _identificadorUsuarios;
                    _usuarios.add(usuario);
                    _mensageServer.add("\nSistema: inicio sesion " + username);                   
                    agregado = "true";
                }
            }
        return agregado;
    }

    @Override
    public String CerrarSesion(String username)
    {
        String encontrado = "false";
        String estadosesion = "false";
            for (int i = 0; i < _usuarios.size(); i++) 
            {
                if(_usuarios.get(i)._nombre.equals(username) && _usuarios.size() == 1)
                {                                                               
                   _usuarios.remove(i);
                    estadosesion = "true";
                    break;
                }
                else if(_usuarios.get(i)._nombre.equals(username))
                {
                    _usuarios.remove(i);
                    estadosesion = "true";
                    break;
                }
                else{}
            }
        return estadosesion;
    }

    @Override
    public String EnviarMensaje(String mensaje)
    {
        return "";
    }


    @Override
    public String VerConectados()
    {
        String Conectados = "";
        
            for (int i = 0; i < _usuarios.size(); i++) 
            {
                Conectados = Conectados + _usuarios.get(i)._nombre + ",";
            }
            
        return Conectados;
    }

    @Override
    public String VerUsername(int identificador)
    {
        for (int i = 0; i < _usuarios.size(); i++) 
        {
            if(_usuarios.get(i)._idUsuario == identificador)
                return _usuarios.get(i)._nombre;
        }
        return  "false";
    }

  

    @Override
    public String LeerMensajeSistema() 
    {
        String ms = "";
            for (int i = 0; i < _mensageServer.size(); i++) 
            {
                ms += _mensageServer.get(i) + "$";
            }
        return ms;
    }

    @Override
    public String getAvatar(int identificador) 
    {
        for (int i = 0; i < _usuarios.size(); i++) 
        {
            if(_usuarios.get(i)._idUsuario == identificador)
                return _usuarios.get(i)._avatar;
        }
        return "";
    }

    @Override
    public String EnviarMensaje(String username, String msj) 
    {
        String encontrado = "false";
        
            for(int i = 0; i < _usuarios.size(); i++)
            {
                if(_usuarios.get(i)._nombre.equals(username))
                {
                    encontrado = "true";
                    break;
                }
              
            }
            if(encontrado.equals("true"))
            {
                for(int i = 0; i < _usuarios.size();i++)
                {
                    _usuarios.get(i).setMensaje(username.toUpperCase() + " Dice:\n" + msj);
                }
            }
        
        return encontrado;
    }

    @Override
    public String VerMensaje(String username) 
    {
        byte indice = -1;
        String mensaje = "";
            for(int i = 0; i < _usuarios.size(); i++)
            {
                if(_usuarios.get(i)._nombre.equals(username))
                {
                    indice = (byte)i;
                    break;
                }
            }
            if(indice > -1)
            {
                mensaje = _usuarios.get(indice).getMensaje();
            }
        return mensaje;
    }

    @Override
    public String enviarInvitacion(String usernameInvita, String usernameRecibe) {
     
     String agregado = "false";   
            for(int i = 0; i < _usuarios.size(); i++)
            {
                if(_usuarios.get(i)._nombre.equals(usernameRecibe))
                {
                     if(_invitacion.isEmpty())//si el array de usuarios se encuentra vacio
                        {
                        //se coloca al jugador en una posicion aleatoria de la pantalla                
                        _identificadorUsuarios++;
                        invitacion invitacion = new invitacion(usernameInvita, usernameRecibe);
                        invitacion._idUsuario = i;
                        invitacion.usernameInvita=usernameInvita;
                        invitacion.usernameRecibe=usernameRecibe;
                        _invitacion.add(invitacion);               
                        agregado = "true";
                        }else
                     {
                      for(int ii = 0; ii < _invitacion.size(); ii++)
                      {
                       if(_invitacion.get(i).usernameInvita.equals(usernameInvita)&&_invitacion.get(i).usernameRecibe.equals(usernameRecibe))
                       {
                       agregado="Invitacion ya fue enviada";
                       }
                       else
                       {
                        //se coloca al jugador en una posicion aleatoria de la pantalla                
                        _identificadorUsuarios++;
                        invitacion invitacion = new invitacion(usernameInvita, usernameRecibe);
                        invitacion._idUsuario = i;
                        invitacion.usernameInvita=usernameInvita;
                        invitacion.usernameRecibe=usernameRecibe;
                        _invitacion.add(invitacion);               
                        agregado = "true";
                       }
                      }
                     }                                         
                    break;
                }
            }
   
        
        return agregado;
    }

    @Override
    public String verInvitacion(String username) {
        byte indice = -1;
        String mensaje = "";
            for(int i = 0; i < _invitacion.size(); i++)
            {
                if(_invitacion.get(i).usernameRecibe.equals(username))
                {
                   mensaje= mensaje+_invitacion.get(i).usernameInvita +",";
                }
            }
          
        return mensaje;
    }

    @Override
    public String aceptarInvitacion(String username, String usernameAcepto) {
        
     String agregado = "false";   
            for(int i = 0; i < _invitacion.size(); i++)
            {
                if(_invitacion.get(i).usernameRecibe.equals(username))
                {                    
                        //se coloca al jugador en una posicion aleatoria de la pantalla                
                        _identificadorUsuarios++;
                        amigos amigo=new amigos(username, usernameAcepto);
                        amigo._idUsuario = i;
                        amigo.yo=username;
                        amigo.usernameamigo=usernameAcepto;
                        _amigo.add(amigo); 
                        _invitacion.remove(i);
                        agregado = "true";
                                                  
                    break;
                }
            }
     
        
        return agregado;
    }

    @Override
    public String cancelarInvitacion(String usernameInvita, String usernameRecibe) {       
         String encontrado = "false";
     String agregado = "false";   
            for(int i = 0; i < _usuarios.size(); i++)
            {
                if(_usuarios.get(i)._nombre.equals(usernameInvita))
                {
                     if(_invitacion.isEmpty())//si el array de usuarios se encuentra vacio
                        {
                        //se coloca al jugador en una posicion aleatoria de la pantalla                
                        _identificadorUsuarios++;
                        invitacion invitacion = new invitacion(usernameInvita, usernameRecibe);
                        invitacion._idUsuario = i;
                        invitacion.usernameInvita=usernameInvita;
                        invitacion.usernameRecibe=usernameRecibe;
                        _invitacion.remove(invitacion);               
                        agregado = "true";
                        }else
                     {
                      for(int ii = 0; ii < _invitacion.size(); ii++)
                      {
                       if(_invitacion.get(i).usernameInvita.equals(usernameInvita)&&_invitacion.get(i).usernameRecibe.equals(usernameRecibe))
                       {
                       agregado="Invitacion ya fue enviada";
                       }
                       else
                       {
                        //se coloca al jugador en una posicion aleatoria de la pantalla                
                        _identificadorUsuarios++;
                        invitacion invitacion = new invitacion(usernameInvita, usernameRecibe);
                        invitacion._idUsuario = i;
                        invitacion.usernameInvita=usernameInvita;
                        invitacion.usernameRecibe=usernameRecibe;
                        _invitacion.remove(invitacion);               
                        agregado = "true";
                       }
                      }
                     }                                         
                    break;
                }
            }
   
        
        return encontrado;
    }

    @Override
    public String miLitaAmigos(String username) {
         byte indice = -1;
        String mensaje = "";
            for(int i = 0; i < _amigo.size(); i++)
            {
                if(_amigo.get(i).yo.equals(username))
                {
                   mensaje = mensaje+_amigo.get(i).getUsernameamigo()+","                
                ;}
                 if(_amigo.get(i).usernameamigo.equals(username))
                {
                   mensaje = mensaje+_amigo.get(i).getYo()+","                
                ;}
            }  
        return mensaje;
    }

   
}
