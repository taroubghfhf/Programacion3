package co.edu.uniquindio.noticias.dominio.servicios.gestorprocesamiento;


import co.edu.uniquindio.noticias.infaestructura.conf.ArchivoConfig;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ProcesarInformacionServicio {
    private static final String DIRECTORIO = ArchivoConfig.getProperty("ruta.base");
    private static final String IS_MAC = ArchivoConfig.getProperty("so");
    private static String NOMBRE_CARPETA = "publicadores";

    public void ejecutar() {
        File directorio;
        if (IS_MAC.equals("mac")) {
             directorio = new File(DIRECTORIO  + NOMBRE_CARPETA);
        }else {
            directorio = new File(DIRECTORIO + "\\" + NOMBRE_CARPETA);
        }
        procesarDirectorio(directorio);
        String fechaActual = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        File[] archivos = directorio.listFiles();
        for (File archivo : archivos) {
            if (archivo.isDirectory()) {
                try {
                    File archivoCSV;
                    File archivoCSVFoto;
                    if (IS_MAC.equals("mac")) {
                        archivoCSV = new File(archivo.getAbsoluteFile() + "/" + "archivo_"+fechaActual+".csv");
                        archivoCSVFoto = new File(archivo.getAbsoluteFile() + "/" + "fotos_"+fechaActual+".csv");
                    }else {
                        archivoCSV = new File(archivo.getAbsoluteFile() + "\\" + "archivo_"+fechaActual+".csv");
                        archivoCSVFoto = new File(archivo.getAbsoluteFile() + "\\" + "fotos_"+fechaActual+".csv");
                    }

                    FileWriter fileWriter = new FileWriter(archivoCSV);
                    FileWriter fileWriterFotos = new FileWriter(archivoCSVFoto);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    BufferedWriter bufferedWriterFotos = new BufferedWriter(fileWriterFotos);
                    bufferedWriter.write("titulo,fecha,cuerpo\n");
                    bufferedWriterFotos.write("Nombre,Fecha de Creación\n");
                    procesarArchivos(archivo.getAbsoluteFile(), bufferedWriter,bufferedWriterFotos);
                    bufferedWriter.close();
                    bufferedWriterFotos.close();
                } catch (Exception e) {

                }
            }
        }
    }

    private static void procesarArchivos(File directorio, BufferedWriter bufferedWriter,BufferedWriter bufferedWriterFotos) {
        File[] archivos = directorio.listFiles();
        for (File archivo : archivos) {
            if (archivo.isFile() && archivo.getName().endsWith(".xml")) {
                procesarArchivoXML(archivo, bufferedWriter);
            } else if (archivo.isFile() && archivo.getName().endsWith(".jpg")){
                procesarFoto(archivo,bufferedWriterFotos);
            } else if (archivo.isDirectory()) {
                procesarArchivos(archivo, bufferedWriter,bufferedWriterFotos);
            }
        }
    }

    private static void procesarDirectorio(File directorio) {
        File[] archivos = directorio.listFiles();
        for (File archivo : archivos) {
            if (archivo.isFile() && archivo.getName().endsWith(".zip")) {
                descomprimirArchivo(archivo.getName(), directorio.getAbsolutePath());
            } else if (archivo.isDirectory()) {
                procesarDirectorio(archivo);
            }
        }
    }

    public static void descomprimirArchivo(String archivoZip, String directorioDestino) {
        byte[] buffer = new byte[1024];

        try {
            File directorio = new File(directorioDestino);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            archivoZip = directorioDestino + "\\" + archivoZip;

            ZipInputStream zis = new ZipInputStream(new FileInputStream(archivoZip));
            ZipEntry entrada;

            while ((entrada = zis.getNextEntry()) != null) {
                String nombreArchivo = entrada.getName();
                File nuevoArchivo = new File(directorioDestino + File.separator + nombreArchivo);

                if (entrada.isDirectory()) {
                    nuevoArchivo.mkdirs();
                    continue;
                }

                File parent = nuevoArchivo.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }

                FileOutputStream fos = new FileOutputStream(nuevoArchivo);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                int leido;
                while ((leido = zis.read(buffer)) != -1) {
                    bos.write(buffer, 0, leido);
                }

                bos.close();
                fos.close();
            }
            zis.closeEntry();
            zis.close();
            File fileArchivo = new File(archivoZip);
            fileArchivo.delete();
            System.out.println("¡Archivo ZIP descomprimido exitosamente!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void procesarArchivoXML(File archivoXML, BufferedWriter bufferedWriter) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            Document documento = dbFactory.newDocumentBuilder().parse(archivoXML);

            Element root = documento.getDocumentElement();
            String titulo = root.getElementsByTagName("title").item(0).getTextContent().replaceAll("\\r|\\n", " ").trim();

            Element docdata = (Element) root.getElementsByTagName("docdata").item(0);
            String fecha = docdata.getElementsByTagName("date.issue").item(0).getTextContent().replaceAll("\\r|\\n", " ").trim();
            String contenido = root.getElementsByTagName("body.content").item(0).getTextContent().replaceAll("\\r|\\n", " ").trim();

            bufferedWriter.write(String.format("%s;%s;%s\n", titulo, fecha, contenido));

            archivoXML.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void procesarFoto(File archivo, BufferedWriter bufferedWriterFotos) {
        try {
            Path path = archivo.toPath();
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

            String nombre = archivo.getName();
            String fechaCreacion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(attr.creationTime().toMillis()));
            bufferedWriterFotos.write(String.format("\"%s\",\"%s\"\n", nombre, fechaCreacion));
            archivo.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
