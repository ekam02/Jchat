package jchat_cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import jchat_interface.JChatInterface;

public class ClienteJChat implements JChatInterface
{
    public Socket socket;
    public DataOutputStream salida;
    public DataInputStream entrada;
    public String mensaje;
    
    public ClienteJChat(String host, int puerto)
    {
        mensaje = "";
        try
        {
            this.socket = new Socket(InetAddress.getByName(host), puerto);
            this.salida = new DataOutputStream(this.socket.getOutputStream());
            this.entrada = new DataInputStream(this.socket.getInputStream());
        }
        catch(IOException e){}
    }
    
    public String ComunicarMensaje(String mensj_ftp)
    {
        System.out.println("mensaje enviado <" + mensj_ftp + ">");
        try 
        {
            salida.writeUTF(mensj_ftp);
            salida.flush();
            mensj_ftp = entrada.readUTF();
            System.out.println("respuesta <" + mensj_ftp + ">");            
        }
        catch (IOException e) 
        {
        
        }
        return mensj_ftp;
    }

    @Override
    public String IniciarSesion(String username, String avatar) 
    {
        return ComunicarMensaje("INICIARSESION&" + username + "&" + avatar);
    }

    @Override
    public String CerrarSesion(String username) 
    {
        return ComunicarMensaje("CERRARSESION&" + username);
    }

    @Override
    public String EnviarMensaje(String mensaje)
    {
        return ComunicarMensaje("");
    }


    @Override
    public String VerConectados() 
    {
        return ComunicarMensaje("VERCONECTADOS&");
    }

    @Override
    public String VerUsername(int identificador) 
    {
        return ComunicarMensaje("VERUSERNAME&" + identificador);
    }



    @Override
    public String LeerMensajeSistema() 
    {
        return ComunicarMensaje("LEERMSMSIS&");
    }

    @Override
    public String getAvatar(int identificador) 
    {
        return ComunicarMensaje("GETAVATAR&" + identificador);
    }

    @Override
    public String EnviarMensaje(String username, String msj) 
    {
        return ComunicarMensaje("SENDMSM&" + username + "&" + msj);
    }

    @Override
    public String VerMensaje(String username) 
    {
        return ComunicarMensaje("VERMSM&" + username);
    }
    @Override
    public String enviarInvitacion(String usernameInvita, String usernameRecibe) {
        return ComunicarMensaje("ENVIARINVITACION&" + usernameInvita+"&"+usernameRecibe);
    }

    @Override
    public String verInvitacion(String username) {
        return ComunicarMensaje("VERINVITACION&" + username);
    }

    @Override
    public String aceptarInvitacion(String username, String usernameAcepto) {
        return ComunicarMensaje("ACEPTARINVITACION&" + username+"&"+usernameAcepto);
    }

    @Override
    public String cancelarInvitacion(String username, String usernamecancelo) {
        return ComunicarMensaje("CANCELARINVITACION&" + username+"&"+usernamecancelo);
    }

    @Override
    public String miLitaAmigos(String username) {
        return ComunicarMensaje("MILISTAAMIGOS&" + username);
    }

 

    
}