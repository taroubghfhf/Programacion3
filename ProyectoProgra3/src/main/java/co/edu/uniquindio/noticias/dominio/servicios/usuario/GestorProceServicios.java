package co.edu.uniquindio.noticias.dominio.servicios.usuario;

// wrider, reader(los archivos)

import co.edu.uniquindio.noticias.dominio.model.GestorProcesamiento;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GestorProceServicios extends GestorProcesamiento {

    public static void ejecutarProceso(String comando) {
        try {
            // Crear un proceso usando ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder(comando.split(" "));
            pb.redirectErrorStream(true);
            Process proceso = pb.start();

            // Obtener el flujo de entrada del proceso
            InputStreamReader isr = new InputStreamReader(proceso.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            // Leer la salida del proceso línea por línea
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            // Esperar a que el proceso termine
            int exitCode = proceso.waitFor();
            System.out.println("El proceso ha finalizado con código de salida: " + exitCode);

            // Cerrar BufferedReader
            br.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String comando = "ls -l";

        ejecutarProceso(comando);
    }


    public static void explorarCarpetas(File directorio) {
        // Verificar si el parámetro es un directorio
        if (directorio.isDirectory()) {
            System.out.println("Explorando carpeta: " + directorio.getAbsolutePath());

            // Obtener lista de archivos y carpetas en el directorio
            File[] archivos = directorio.listFiles();

            // Iterar sobre cada archivo o carpeta
            for (File archivo : archivos) {
                // Si es una carpeta, llamar recursivamente a la función explorarCarpetas
                if (archivo.isDirectory()) {
                    explorarCarpetas(archivo);
                } else {
                    // Si es un archivo, verificar si es un archivo de publicador
                    if (esArchivoDePublicador(archivo)) {
                        System.out.println("Archivo de publicador encontrado: " + archivo.getName());
                    }
                }
            }
        } else {
            System.out.println("La ruta especificada no es un directorio válido.");
        }
    }

    public static boolean esArchivoDePublicador(File archivo) {

        return true;
    }
    public static void procesarArchivosXML(File archivoXML) {
        try {
            // Crear un constructor de documentos
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parsear el archivo XML
            Document doc = dBuilder.parse(archivoXML);
            // Obtener el elemento raíz
            Element elementoRaiz = doc.getDocumentElement();
            System.out.println("Elemento raíz: " + elementoRaiz.getNodeName());

            // Obtener la lista de nodos hijo
            NodeList listaNodos = elementoRaiz.getChildNodes();

            // Recorrer los nodos hijo
            for (int i = 0; i < listaNodos.getLength(); i++) {
                Node nodo = listaNodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;
                    // Aquí puedes realizar las operaciones de procesamiento necesarias con los elementos XML
                    System.out.println("Elemento: " + elemento.getNodeName());
                    System.out.println("Contenido: " + elemento.getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static void procesarFoto(File foto) {
        try {
            // Cargar la imagen desde el archivo
            BufferedImage imagen = ImageIO.read(foto);

            // Obtener información de la imagen
            int ancho = imagen.getWidth();
            int alto = imagen.getHeight();
            int tipo = imagen.getType();

            System.out.println("Información de la imagen:");
            System.out.println("Ancho: " + ancho);
            System.out.println("Alto: " + alto);
            System.out.println("Tipo: " + tipo);

            // Aquí puedes realizar cualquier operación de procesamiento necesaria con la imagen

            // Por ejemplo, guardar la imagen procesada en un nuevo archivo
            File nuevoArchivo = new File("ruta/a/tu/nueva_foto.jpg");
            ImageIO.write(imagen, "jpg", nuevoArchivo);

            System.out.println("La foto ha sido procesada y guardada correctamente en: " + nuevoArchivo.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void descomprimirArchivo(String archivoZip, String directorioDestino) {
        byte[] buffer = new byte[1024];

        try {
            // Crear directorio de destino si no existe
            File directorio = new File(directorioDestino);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Inicializar entrada ZIP
            ZipInputStream zis = new ZipInputStream(new FileInputStream(archivoZip));
            ZipEntry entrada;

            // Iterar sobre las entradas del archivo ZIP
            while ((entrada = zis.getNextEntry()) != null) {
                String nombreArchivo = entrada.getName();
                File nuevoArchivo = new File(directorioDestino + File.separator + nombreArchivo);

                // Crear directorio si la entrada es un directorio
                if (entrada.isDirectory()) {
                    nuevoArchivo.mkdirs();
                    continue;
                }

                // Crear los directorios padre si no existen
                File parent = nuevoArchivo.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }

                // Escribir el archivo descomprimido
                FileOutputStream fos = new FileOutputStream(nuevoArchivo);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                int leido;
                while ((leido = zis.read(buffer)) != -1) {
                    bos.write(buffer, 0, leido);
                }

                // Cerrar streams
                bos.close();
                fos.close();
            }
            zis.closeEntry();
            zis.close();
            System.out.println("¡Archivo ZIP descomprimido exitosamente!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarDatosCSV(List<String[]> datos, String rutaArchivo) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(rutaArchivo)));

            // Escribir los datos en el archivo CSV
            for (String[] fila : datos) {
                StringBuilder filaCSV = new StringBuilder();
                for (int i = 0; i < fila.length; i++) {
                    filaCSV.append(fila[i]);
                    if (i < fila.length - 1) {
                        filaCSV.append(",");
                    }
                }
                writer.write(filaCSV.toString());
                writer.newLine();
            }

            writer.close();
            System.out.println("Los datos han sido guardados en formato CSV correctamente en: " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Ruta del archivo CSV de destino
        String rutaArchivoCSV = "ruta/a/tu/archivo.csv";

        // Llama al método guardarDatosCSV para guardar los datos en el archivo CSV
        guardarDatosCSV(datos, rutaArchivoCSV);
    }
    }

