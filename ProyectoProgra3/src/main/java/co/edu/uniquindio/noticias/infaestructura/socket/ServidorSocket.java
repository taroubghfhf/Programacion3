package co.edu.uniquindio.noticias.infaestructura.socket;

import co.edu.uniquindio.noticias.infaestructura.conf.ArchivoConfig;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorSocket {

    public final static int PORT = ArchivoConfig.getIntProperty("server.port");
    public final static String RUTA_BASE = ArchivoConfig.getProperty("ruta.base");
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private static Socket socket;

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
        try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
            String rutaRelativa = dis.readUTF();
            long fileSize = dis.readLong();

            rutaRelativa=rutaRelativa.replace("\\","/");

            Path archivoDestino = Paths.get(RUTA_BASE).resolve(rutaRelativa).normalize();
            Files.createDirectories(archivoDestino.getParent());

            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(archivoDestino.toFile()))) {
                byte[] buffer = new byte[4096];
                long totalBytesRead = 0;
                int bytesRead;
                while (totalBytesRead < fileSize &&
                        (bytesRead = dis.read(buffer, 0, (int) Math.min(buffer.length, fileSize - totalBytesRead))) != -1) {
                    bos.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarArchivo(String rutaArchivo){
        try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
            File file = new File(rutaArchivo);
            if (!file.exists()) {
                return;
            }

            dos.writeUTF(file.getName());
            dos.writeLong(file.length());

            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = bis.read(buffer)) != -1) {
                    dos.write(buffer, 0, bytesRead);
                }
            }
            dos.flush();
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
