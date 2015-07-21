/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neyron.rent;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Manifest;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author dmitry
 */
@WebListener
public class ServerInitializer implements ServletContextListener {

   Manifest manifest;

   @Override
   public void contextInitialized(ServletContextEvent sce) {
      ServletContext context = sce.getServletContext();
      try {
         InputStream stream = context.getResourceAsStream("/META-INF/MANIFEST.MF");
         if (stream != null) {
            manifest = new Manifest(stream);
            stream.close();
         }
      } catch (IOException ex) {
      }
      Main.VERSION = readAttributesManifest("version");
      Main.PATH_APPLICATION = context.getRealPath("");

      context.setAttribute(Main.class.getName(), new Main());
   }

   @Override
   public void contextDestroyed(ServletContextEvent sce) {
      Main main = (Main) sce.getServletContext().getAttribute(Main.class.getName());
      if (main != null) {
         main.exit();
      }
   }

   private String readAttributesManifest(String key) {
      if (manifest != null) {
         return manifest.getMainAttributes().getValue(key);
      }
      return "-";
   }
}
