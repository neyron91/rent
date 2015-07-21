package org.neyron.rent;

import org.apache.logging.log4j.Logger;

/**
 * Класс для работы с логированием
 *
 * @author nerush
 */
public class Logging {

   public static final String WEB = "web.";
   public static final String APP = "app.";

   public static void TRACE(Logger log, String msg) {
      if (log.isTraceEnabled()) {
         log.trace(msg);
      }
   }

   public static void DEBUG(Logger log, String msg) {
      if (log.isDebugEnabled()) {
         log.debug(msg);
      }
   }
}
