package jchat_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import jchat_interface.JChatInterface;

public class ServidorJChat
{
    public ServerSocket socketServidor = null;
    public Socket socketCliente = null;
    public JChatInterface int_jchat;
    public factory ayudante = null;

    public ServidorJChat(int puerto)
    {
        try 
        {
            int_jchat = new JChatImpl();
            socketServidor = new ServerSocket(puerto);
            socketServidor.getInetAddress();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void Demonio()
    {
        while(true) 
        {
            try 
            {
                
                System.out.println("Estoy esperando...");
                socketCliente = socketServidor.accept();
                System.out.println("encontre un cliente");
                ayudante = new factory(socketCliente, int_jchat);
                ayudante.start();
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String arg[])
    {
        ServidorJChat servidor = new ServidorJChat(2013);
        servidor.Demonio();
    }
}
