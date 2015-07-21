package org.neyron.rent.web.ui.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.neyron.rent.Main;
import org.neyron.rent.db.query.SQLGetRecordsCost;
import org.neyron.rent.web.ui.Command;
import org.neyron.rent.web.ui.html.HtmlGetRecordsCost;

/**
 *
 * @author dmitry
 */
public class CmdGetRecordsCost extends Command {
   
   public static final String NAME = "get_record_cost";

   @Override
   public boolean doCmd(HttpServletRequest request, HttpServletResponse response) {
      Main main = (Main) request.getSession(false).getAttribute(Main.class.getName());

      SQLGetRecordsCost sqlGetRecCost = new SQLGetRecordsCost();
      main.getManager().execute(sqlGetRecCost);
      setHtml(new HtmlGetRecordsCost(sqlGetRecCost.getRecCostList()));
      return true;
   }

   @Override
   public TypeData getTypeData() {
      return TypeData.HTML;
   }

}
