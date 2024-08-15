package com.github.ricardojlrufino.tray2.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class IconUtils {

    public static final Logger logger = Logger.getLogger(IconUtils.class.getName());

    public static String getIconPath(String resource) throws IOException {

        Path tmpIcon = Paths.get(System.getProperty("java.io.tmpdir"), "tray_res", resource);

        if(tmpIcon.toFile().exists()){

            return tmpIcon.toString();

        }else{

            // File in same directory of jar
            try {
                Path jarPaths = new File(IconUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI()).toPath();
                File jarIcon = jarPaths.resolve(resource).toFile();
                if(jarIcon.exists()){
                    return jarIcon.toString();
                }
            } catch (URISyntaxException e) {
                throw new IOException(e);
            }

            // try extract to tmp
            InputStream link = (IconUtils.class.getResourceAsStream(resource));
            if(link != null) {
                logger.fine("Extracting icon to " + tmpIcon);
                tmpIcon.toFile().getParentFile().mkdirs();
                Files.copy(link, tmpIcon); // extract from jar//
                return tmpIcon.toString();
            }

            throw new IOException("Image not found: " + resource);
        }

    };

}
