/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validacion;

import EntidadesInmobiliaria.Inmobiliaria;
import Persona.Usuario;
import static Validacion.Validaciones.password;
import static Validacion.Validaciones.sc;
import static Validacion.Validaciones.subject;
import static Validacion.Validaciones.username;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author bllv1
 */
public class ValidacionesPersona {
    public static List<Usuario> validarNombreUsuario(String nombreUsuario) {
        nombreUsuario = nombreUsuario.toUpperCase();
        List<Usuario> lu = new ArrayList<>();
        boolean opcion = false;
        do {
            nombreUsuario = nombreUsuario.toUpperCase();
            for (Usuario us : Inmobiliaria.usuarios) {
                if (us.getNombre().equals(nombreUsuario)) {
                    lu.add(us);
                }
            }
            if (lu.isEmpty()) {
                System.out.println("Usted mo esta registrado en el sistenma");
                System.out.println("Ingrese un usuario valido");
                nombreUsuario = sc.nextLine();
            } else {
                opcion = true;
                return lu;
            }

        } while (opcion == false);

        return null;
    }
    public static Usuario validarContrasenaUsuario(List<Usuario> lu, String contrasena) {
        boolean opcion = false;
        do {
            for (Usuario u : lu) {
                if (u.getContrasena().equals(contrasena)) {
                    opcion = true;
                    return u;
                } else {
                    System.out.println("Contrasena incorrecta!\nIngrese otra vez su contraseña: ");
                    contrasena = sc.nextLine();
                }
            }
        } while (opcion == false);

        return null;
    }
    
    public static String enviarCorreo(String correoReceptor, String mensaje) {

        boolean opcion = false;
        do {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(correoReceptor));
                message.setSubject(subject);
                message.setText(mensaje);

                Transport.send(message);
                opcion = true;
                return correoReceptor;

                //JOptionPane.showMessageDialog(this , "Su mensaje ha sido enviado");
            } catch (MessagingException e) {
                //throw new RuntimeException(e);
                System.out.println("Usted no esta conectado al internet para enviar el mensaje.");
                String op = sc.nextLine();
                //System.out.println("El correo electronico no existe!\nIngrese uno válido: ");
                //correoReceptor= sc.nextLine();
            }
        } while (opcion == false);

        return null;
    }
    
   
    
     public static Usuario pedirleInformacionUsuario() {
        System.out.println("Ingrese su nombre: ");
        String nombreUsuario = sc.nextLine();
        List<Usuario> lu = ValidacionesPersona.validarNombreUsuario(nombreUsuario);
        System.out.println("Ingrese su contrasena: ");
        String contrasena = sc.nextLine();
        Usuario u = ValidacionesPersona.validarContrasenaUsuario(lu, contrasena);
        return u;
    }
}
