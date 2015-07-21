package org.neyron.rent.web.servlet;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.neyron.rent.web.AsyncCommandRun;
import org.neyron.rent.web.ui.Command;

/**
 * Сервлет для обработки команд, идущих от пользователя
 *
 * @author nerush
 */
@WebServlet(name = "CommandServlet", urlPatterns = {"/cmd"}, asyncSupported = true)
public class CommandServlet extends HttpServlet {

   public static final String CMD = "cmd";
   public static final String JSON_ESYS_CMD = "ERR";
   public static final int ESYS_DELAY = 500;

   private ExecutorService executor;

   @Override
   public void init() throws ServletException {
      executor = Executors.newCachedThreadPool();
   }

   @Override
   public void destroy() {
      executor.shutdown();
   }

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/plain; charset=utf-8");

      action(request, response);
   }

   private void action(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String cmd = request.getParameter(CMD);//считываем имя комманды
//      Session session = (Session) request.getSession(false).getAttribute(Session.class.getName());
//      if (session == null) {
//         logS.warn("Отсутствует объект session: Парамерт cmd = " + cmd);
//         response.sendError(HttpServletResponse.SC_CONFLICT);
//         return;
//      }
//      String user = "Пользователь: ";
//      if (session.getUser() != null) {
//         user += session.getUser().getLogin();
//      } else {
//         user += "нет авторизации";
//      }

      //если пользователь не авторизован то делаем логаут
//      if (!session.isAuth() && (!cmd.equals(CmdKernelStatus.NAME) && !cmd.equals(CmdMemory.NAME) && !cmd.equals(CmdKernelControl.NAME) && !cmd.equals(CmdAdminLogout.NAME) && !cmd.equals(CmdAdminServerReboot.NAME))) { // FIXME переделать этот кусок
//         Event evt = new EvtLogout(Value.NO_AUTH, Value.DELAY_LOGOUT);
//         PrintWriter out = response.getWriter();
//         out.println(evt.getJson());
//         return;
//      }

//      if (!cmd.equals("stat")) {
//         Logging.DEBUG(logS, "ID SESSION: " + session.getId() + " | " + user + ". cmd: " + cmd);
//      }

      Command ch = Command.buildCommand(cmd);
      if (ch == null) {
//         logS.warn("ID SESSION: " + session.getId() + ". Неизвестный параметр cmd: " + cmd + " | " + user);
//         PrintWriter out = response.getWriter();
//         out.println(new EvtMsg("Неизвестный запрос").getJson());
         return;
      }

      request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
      AsyncContext asyncCtx = request.startAsync();
      executor.execute(new AsyncCommandRun(asyncCtx, ch, request, response));
   }

}
