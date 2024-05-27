package co.edu.uniquindio.noticias.infaestructura.socket;

import co.edu.uniquindio.noticias.infaestructura.conf.ArchivoConfig;

import java.io.*;
import java.net.Socket;

public class ClienteSocket {
    public final static int PORT = ArchivoConfig.getIntProperty("server.port");
    public final static String HOST = ArchivoConfig.getProperty("server.host");
    private static String NOMBRE_CARPETA = "publicadores";
    private static String NOMBRE_CARPETA_CLIENTE = "clientes";
    private final static String RUTA_BASE = ArchivoConfig.getProperty("ruta.base");


    public void iniciarEscuchar(String identification) {
        Thread escuchaThread = new Thread(() -> escucharArchivos(identification));
        escuchaThread.start();
    }

    public void cliente(String file, String identificacion) {
        try (Socket socket = new Socket(HOST, PORT);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             FileInputStream fis = new FileInputStream(file)) {

            String nombreArchivo = new File(file).getName();
            String rutaFinal = NOMBRE_CARPETA + "\\" + identificacion + "\\" + nombreArchivo;
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

    public void escucharArchivos(String identificacion) {
        try (Socket socket = new Socket(HOST, PORT);
             DataInputStream dis = new DataInputStream(socket.getInputStream())) {

            while (true) {
                String rutaArchivo = dis.readUTF();
                long fileSize = dis.readLong();
                String nombre[] = rutaArchivo.split("/");
                String rutaDestino = RUTA_BASE + "/" + NOMBRE_CARPETA_CLIENTE +"/"+ identificacion + "/" + nombre[nombre.length-1];

                File destino = new File(rutaDestino);
                destino.getParentFile().mkdirs();

                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destino))) {
                    byte[] buffer = new byte[4096];
                    long totalBytesRead = 0;
                    int bytesRead;
                    while (totalBytesRead < fileSize && (bytesRead = dis.read(buffer, 0, (int) Math.min(buffer.length, fileSize - totalBytesRead))) != -1) {
                        bos.write(buffer, 0, bytesRead);
                        totalBytesRead += bytesRead;
                    }
                    bos.flush();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
