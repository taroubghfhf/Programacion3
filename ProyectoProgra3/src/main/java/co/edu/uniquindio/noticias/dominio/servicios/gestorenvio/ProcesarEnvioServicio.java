package co.edu.uniquindio.noticias.dominio.servicios.gestorenvio;

import co.edu.uniquindio.noticias.dominio.servicios.cliente.CrearClienteServicio;
import co.edu.uniquindio.noticias.infaestructura.conf.ArchivoConfig;
import co.edu.uniquindio.noticias.infaestructura.socket.ServidorSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ProcesarEnvioServicio {

    private static final String DIRECTORIO = ArchivoConfig.getProperty("ruta.base");
    private static final String IS_MAC = ArchivoConfig.getProperty("so");
    private static String NOMBRE_CARPETA = "publicadores";


    private static final Logger logger = LoggerFactory.getLogger(ProcesarEnvioServicio.class);

    public void ejecutar() {
        File directorio;
        if (IS_MAC.equals("mac")) {
            directorio = new File(DIRECTORIO  + NOMBRE_CARPETA);
        }else {
            directorio = new File(DIRECTORIO + "\\" + NOMBRE_CARPETA);
        }
        logger.info("Se procesan archivos para ser enviados");
        procesarArchivos(directorio);
        logger.info("Se envian archivos para ser enviados");
        procesarArchivosEnvio(directorio);

    }

    private static void procesarArchivos(File directorio) {
        File[] archivos = directorio.listFiles();
        for (File archivo : archivos) {
            if (archivo.isFile() && archivo.getName().endsWith(".csv") && archivo.getName().startsWith("archivo")) {
                convertCsvToXml(archivo,directorio.toString());
            } else if (archivo.isDirectory()) {
                procesarArchivos(archivo);
            }
        }
    }

    private static void convertCsvToXml(File csvFile, String directorio) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            String titulo = null;
            br.readLine();
            while ((line = br.readLine()) != null) {
                StringBuilder xmlContent = new StringBuilder();
                xmlContent.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                xmlContent.append("<nitf xmlns=\"http://iptc.org/std/NITF/2006-10-18/\"\n");
                xmlContent.append("      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
                xmlContent.append("      xsi:schemaLocation=\"http://iptc.org/std/NITF/2006-10-18/ http://iptc.org/std/NITF/2006-10-18/nitf.xsd\">\n");
                String[] values = line.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (values.length == 3) {
                    titulo = values[0].replaceAll("^\"|\"$", "").trim();
                    String fecha = values[1].replaceAll("^\"|\"$", "").trim();
                    String cuerpo = values[2].replaceAll("^\"|\"$", "").trim();

                    xmlContent.append("    <head>\n");
                    xmlContent.append("        <title>").append(titulo).append("</title>\n");
                    xmlContent.append("        <docdata>\n");
                    xmlContent.append("            <date.issue>").append(fecha).append("</date.issue>\n");
                    xmlContent.append("        </docdata>\n");
                    xmlContent.append("    </head>\n");
                    xmlContent.append("    <body>\n");
                    xmlContent.append("        <body.content>\n");
                    xmlContent.append(cuerpo).append("\n");
                    xmlContent.append("        </body.content>\n");
                    xmlContent.append("    </body>\n");
                    xmlContent.append("</nitf>");
                    if (titulo != null) {
                        String xmlFileName = "envio_"+ titulo + ".xml";
                        File xmlFile = new File(xmlFileName);
                        try (FileWriter fw = new FileWriter(directorio+"/"+xmlFile)) {
                            fw.write(xmlContent.toString());
                        }
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void procesarArchivosEnvio(File directorio) {
        File[] archivos = directorio.listFiles();
        for (File archivo : archivos) {
            if (archivo.isFile() && archivo.getName().endsWith(".xml") && archivo.getName().startsWith("envio")) {
                enviarArchivo(archivo);
            } else if (archivo.isDirectory()) {
                procesarArchivosEnvio(archivo);
            }
        }
    }

    private static void enviarArchivo(File archivo){
        ServidorSocket servidorSocket = new ServidorSocket();
        servidorSocket.enviarArchivo(archivo.getAbsolutePath());
    }
}
