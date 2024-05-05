package co.edu.uniquindio.noticias.infaestructura.socket;

import co.edu.uniquindio.noticias.infaestructura.conf.ArchivoConfig;

import java.io.*;
import java.net.Socket;

public class ClienteSocket {
    public final static int PORT = ArchivoConfig.getIntProperty("server.port");
    public final static String HOST= ArchivoConfig.getProperty("server.host");
    private static String NOMBRE_CARPETA = "publicadores";
    public void cliente(String file,String identificacion){
        try (Socket socket = new Socket(HOST, PORT);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             FileInputStream fis = new FileInputStream(file)) {

            String nombreArchivo =  new File(file).getName();
            String rutaFinal = NOMBRE_CARPETA + "\\"+identificacion+"\\"+nombreArchivo;
            dos.writeUTF(rutaFinal);

            dos.writeLong(new File(file).length());

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
