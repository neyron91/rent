package org.neyron.rent.web.servlet;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.neyron.rent.Main;

/**
 * Фильтр запросов
 *
 * @author nerush
 */
@WebFilter(urlPatterns = "/*", dispatcherTypes = {DispatcherType.REQUEST})
public class Filter implements javax.servlet.Filter {

   ServletContext context;

   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
      context = filterConfig.getServletContext();
   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");

      Main main = (Main) context.getAttribute(Main.class.getName());
      if (main != null) {
         HttpSession httpSess = ((HttpServletRequest) request).getSession(true);
         Main mainBuf = (Main) httpSess.getAttribute(Main.class.getName());
         if (mainBuf == null) {
            httpSess.setAttribute(Main.class.getName(), main);
         }
      }
      chain.doFilter(request, response);
   }

   @Override
   public void destroy() {
   }

}
