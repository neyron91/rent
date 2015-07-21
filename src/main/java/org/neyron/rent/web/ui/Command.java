package org.neyron.rent.web.ui;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neyron.rent.Logging;
import org.neyron.rent.web.ui.cmd.CmdAddRecord;
import org.neyron.rent.web.ui.cmd.CmdGetRecordsCost;

/**
 * Абстрактный класс для выполнения команд над приложением
 *
 * @author nerush
 */
public abstract class Command {

   public enum TypeData {

      HTML, JSON
   }

   public static final HashMap<String, Class<? extends Command>> cmdMap = new HashMap<>();
   public static final Logger logW = LogManager.getLogger(Logging.WEB + Command.class.getName());

   static {
      cmdMap.put(CmdAddRecord.NAME, CmdAddRecord.class);
      cmdMap.put(CmdGetRecordsCost.NAME, CmdGetRecordsCost.class);
   }

   public static Command buildCommand(String name) {
      Class<? extends Command> cls = cmdMap.get(name);
      if (cls != null) {
         try {
            return cls.newInstance();
         } catch (InstantiationException | IllegalAccessException ex) {
            logW.error("Ошибка создание объекта", ex);
         }
      }
      return null;
   }

   private HTML html = null;
   private Event event = null;

   public abstract boolean doCmd(HttpServletRequest request, HttpServletResponse response);

   public abstract TypeData getTypeData();

   protected void setHtml(HTML html) {
      this.html = html;
   }

   protected void setEvent(Event event) {
      this.event = event;
   }

   public String getHtml() {
      if (html != null) {
         return html.getHTML();
      }
      return "";
   }
   
   public Event getEvent() {
      return event;
   }
}
