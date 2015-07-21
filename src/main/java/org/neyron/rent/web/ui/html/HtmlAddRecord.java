package org.neyron.rent.web.ui.html;

import org.neyron.rent.web.ui.HTML;

/**
 *
 * @author dmitry
 */
public class HtmlAddRecord extends HTML {

   public HtmlAddRecord(int result) {
      StringBuilder html = new StringBuilder();
      if (result != 0) {
         html.append("<p status=\"1\">Запись добавлена успешно!</p>");
      } else {
         html.append("<p status=\"0\">При добавлении записи возникла ошибка</p>");
      }
      setHTML(html);
   }

}
