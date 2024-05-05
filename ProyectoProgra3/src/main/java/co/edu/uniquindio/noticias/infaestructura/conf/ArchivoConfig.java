package co.edu.uniquindio.noticias.infaestructura.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ArchivoConfig {

    private static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ArchivoConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("No se pudo encontrar el archivo de configuración " + CONFIG_FILE);
                System.exit(1);
            }
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Error al cargar el archivo de configuración " + CONFIG_FILE);
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }
}
