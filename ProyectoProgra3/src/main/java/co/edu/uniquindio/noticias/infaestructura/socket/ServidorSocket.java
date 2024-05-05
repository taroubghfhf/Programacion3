package co.edu.uniquindio.noticias.infaestructura.socket;

import co.edu.uniquindio.noticias.infaestructura.conf.ArchivoConfig;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorSocket {

    public final static int PORT = ArchivoConfig.getIntProperty("server.port");
    public final static String RUTA_BASE = ArchivoConfig.getProperty("ruta.base");
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private Socket socket;

    public void iniciarSevidor() {
        try {
            serverSocket = new ServerSocket(PORT);
            executorService = Executors.newCachedThreadPool();
            while(!serverSocket.isClosed()){
                socket = serverSocket.accept();
                executorService.submit(() -> recibirArchivo(socket));
            }
        } catch (IOException e) {}
    }


    public void recibirArchivo(Socket socket) {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            String nombreArchivo = dis.readUTF();

            long fileSize = dis.readLong();

            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(RUTA_BASE + nombreArchivo))) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                long totalBytesRead = 0;
                while (totalBytesRead < fileSize && (bytesRead = dis.read(buffer, 0, (int) Math.min(buffer.length, fileSize - totalBytesRead))) != -1) {
                    bos.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexiones() {
        try {
            serverSocket.close();
            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
