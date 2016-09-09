package jchat_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import jchat_interface.JChatInterface;

public class factory extends Thread implements Runnable
{
    public JChatInterface int_jchat;
    public DataInputStream entrada;
    public DataOutputStream salida;
    public Socket socketCliente;
    public String vector[];
    public String retorno, mensaje;

    public factory(Socket SocketCliente, JChatInterface int_jchat)
    {
        mensaje = "";
        this.socketCliente = SocketCliente;
        this.int_jchat = int_jchat;
        try
        {
            this.entrada = new DataInputStream(this.socketCliente.getInputStream());
            this.salida = new DataOutputStream(this.socketCliente.getOutputStream());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void run() 
    {
        mensaje = "NEW";
        try 
        {
            for(; !mensaje.equals("CERRARSESION"); salida.flush())
            {
                mensaje = entrada.readUTF(); 
                salida.writeUTF(Implementador(mensaje));
            }
            //socketCliente.close();
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
        }
    }

    private String Implementador(String mensaje)
    {
        vector = mensaje.split("&");    //separador '&'
        retorno = "OK";
        try
        {
            if(vector[0].equals("INICIARSESION"))//listo
            {
                retorno = "" + int_jchat.IniciarSesion(vector[1], vector[2]);
            }
           
            else if(vector[0].equals("CERRARSESION"))//listo
            {
                retorno = "" + int_jchat.CerrarSesion(vector[1]);//listo
            }
           
            else if(vector[0].equals("VERCONECTADOS"))
            {
                retorno = "" + int_jchat.VerConectados();
            }
            else if(vector[0].equals("VERUSERNAME"))
            {
                retorno = "" + int_jchat.VerUsername(Integer.parseInt(vector[1]));
            }
           
            else if(vector[0].equals("LEERMSMSIS"))
            {
                retorno = "" + int_jchat.LeerMensajeSistema();
            }
            else if(vector[0].equals("GETAVATAR"))
            {
                retorno = "" + int_jchat.getAvatar(Integer.parseInt(vector[1]));
            }
            else if(vector[0].equals("SENDMSM"))
            {
                retorno = "" + int_jchat.EnviarMensaje(vector[1], vector[2]);
            }
            else if(vector[0].equals("VERMSM"))
            {
                retorno = "" + int_jchat.VerMensaje(vector[1]);
            }
           
             else if(vector[0].equals("ENVIARINVITACION"))
            {
                retorno = "" + int_jchat.enviarInvitacion(vector[1], vector[2]);
            }
             else if(vector[0].equals("VERINVITACION"))
            {
                retorno = "" + int_jchat.verInvitacion(vector[1]);
            }
               else if(vector[0].equals("ACEPTARINVITACION"))
            {
                retorno = "" + int_jchat.aceptarInvitacion(vector[1], vector[2]);
            }
                 else if(vector[0].equals("CANCELARINVITACION"))
            {
                retorno = "" + int_jchat.cancelarInvitacion(vector[1], vector[2]);
            }
                   else if(vector[0].equals("MILISTAAMIGOS"))
            {
                retorno = "" + int_jchat.miLitaAmigos(vector[1]);
            }
            else
                retorno = "";
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return retorno;
    }    
}
