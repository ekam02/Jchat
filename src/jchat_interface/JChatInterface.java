package jchat_interface;

public interface JChatInterface
{
    public String IniciarSesion(String username, String avatar);//listo
    public String CerrarSesion(String username);//listo
    public String EnviarMensaje(String mensaje);
    public String VerConectados();
    public String VerUsername(int identificador);    
    public String LeerMensajeSistema();
    public String getAvatar(int identificador);
    public String EnviarMensaje(String username, String msj);
    public String VerMensaje(String username);
    public String enviarInvitacion(String usernameInvita, String usernameRecibe);
    public String verInvitacion(String username);
    public String aceptarInvitacion(String username, String usernameAcepto);
    public String cancelarInvitacion(String username, String usernamecancelo);
    public String miLitaAmigos(String username);

}
