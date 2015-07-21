package org.neyron.rent.web.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neyron.rent.Logging;
import org.neyron.rent.json.JSONException;
import org.neyron.rent.json.JSONObject;

/**
 * Абстрактный класс для упаковки и отсылки данных клиенту
 *
 * @author nerush
 */
public class Event {

   private static final Logger logW = LogManager.getLogger(Logging.WEB + Event.class.getName());

   public static final String EVT = "evt";
   public static final String NULL = "null";
   public static final String SYSTIME = "systime";

   protected final String type;
   protected final JSONObject data;

   public Event(String type) {
      this.type = type;
      data = new JSONObject();
   }

   public String getType() {
      return type;
   }

   public JSONObject getData() {
      return data;
   }

   public String getJSON() {
      try {
         data.put(EVT, type);
         data.put(SYSTIME, String.valueOf(System.currentTimeMillis()));
      } catch (JSONException ex) {
      }

      return data.toString();
   }

   public void printJSON() {
      Logging.TRACE(logW, "JSON: " + type + ": " + data);
   }
}
