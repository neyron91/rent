package org.neyron.rent.web.ui;

/**
 *
 * @author nerush
 */
public class HTML {

   private String html;

   public String getHTML() {
      return html;
   }

   protected void setHTML(StringBuilder str) {
      html = str.toString();
   }
   
   protected void setHTML(String str) {
      html = str;
   }
}
