package org.neyron.rent.web.ui.html;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.neyron.rent.LibCommon;
import org.neyron.rent.db.query.SQLGetRecordsCost;
import org.neyron.rent.web.ui.HTML;

/**
 *
 * @author dmitry
 */
public class HtmlGetRecordsCost extends HTML {
   
   public HtmlGetRecordsCost(ArrayList<SQLGetRecordsCost.RecordCost> recCostList) {
      StringBuilder html = new StringBuilder();
      html.append("<table>")
              .append("<tr>")
              .append("<th>Дата</th>")
              .append("<th>Холодная</th>")
              .append("<th>Горячая</th>")
              .append("<th>Водоотвед.</th>")
              .append("<th>Свет</th>")
              .append("<th>Подъезд</th>")
              .append("<th>Всего</th>")
              .append("</tr>");
      recCostList.stream().forEach(rec -> {
         DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(LibCommon.getNameMonth(rec.getDate().getMonthValue()) + ", yyyyг.");
         html.append("<tr>")
                 .append("<td>").append(rec.getDate().format(formatDate)).append("</td>")
                 .append("<td>").append(rec.getCold().toString()).append("</td>")
                 .append("<td>").append(rec.getHot().toString()).append("</td>")
                 .append("<td>").append(rec.getSanitation().toString()).append("</td>")
                 .append("<td>").append(rec.getLight().toString()).append("</td>")
                 .append("<td>").append(rec.getDoor().toString()).append("</td>")
                 .append("<td>").append(rec.getTotal().toString()).append("</td>")
                 .append("</tr>");
      });
      html.append("</table>");
      setHTML(html);
   }
}
