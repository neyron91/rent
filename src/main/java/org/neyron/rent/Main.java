package org.neyron.rent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neyron.rent.db.ManagerDB;

/**
 *
 * @author dmitry
 */
public class Main {

   public static String VERSION = "";
   public static String PATH_APPLICATION;
   private final ManagerDB manager;
   
   public static final Logger log = LogManager.getLogger(Logging.APP + Main.class.getName());

   public Main() {
      manager = new ManagerDB("jdbc/rentPool");
   }

   public ManagerDB getManager() {
      return manager;
   }

   void exit() {

   }
}
