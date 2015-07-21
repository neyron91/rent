package org.neyron.rent.web;

import java.io.PrintWriter;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neyron.rent.Logging;
import org.neyron.rent.web.ui.Command;
import org.neyron.rent.web.ui.Event;

/**
 *
 * @author User
 */
public class AsyncCommandRun implements Runnable {

   public static final Logger log = LogManager.getLogger(Logging.APP + AsyncCommandRun.class.getName());

   private final AsyncContext async;
   private final Command cmd;
   private final HttpServletRequest request;
   private final HttpServletResponse response;

   public AsyncCommandRun(AsyncContext async, Command cmd, HttpServletRequest request, HttpServletResponse response) {
      this.async = async;
      this.cmd = cmd;
      this.request = request;
      this.response = response;
   }

   @Override
   public void run() {
      PrintWriter out;
      try {
         out = async.getResponse().getWriter();
         boolean result = cmd.doCmd(request, response);
         switch (cmd.getTypeData()) {
            case HTML: {
//               if (result) {
               out.println(cmd.getHtml());
//               }
               break;
            }
            case JSON: {
               if (result) {
                  Event evt = cmd.getEvent();
                  evt.printJSON();
                  out.println(evt.getJSON());
               } else {
//                  logS.warn("ID SESSION: " + session.getId() + " | " + session.getUser().getLogin() + " Неудачное выполнение команды cmd: " + cmd);
//                  out.println(new EvtMsg("Во время выполнения запроса произошла ошибка.").getJson());
               }
               break;
            }
         }
      } catch (Exception ex) {
         log.error("...",ex);
//         logS.warn("", ex);
      }
      async.complete();
   }

}
