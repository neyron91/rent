package org.neyron.rent;

import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author nerush
 */
public class LibCommon {

   public static Charset WIN1251 = Charset.forName("windows-1251");
   public static Charset UTF_8 = Charset.forName("UTF-8");
   private static final DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

   public static final Logger log = LogManager.getLogger(Logging.APP + LibCommon.class.getName());

   public static long getTimeOfLocalMillis(long time) {
      TimeZone t = TimeZone.getDefault();
      long dd = t.getRawOffset();
      return time + dd;
   }

   public static String getNameMonth(int numberMonth) {
      switch (numberMonth) {
         case 1:
            return "Январь";
         case 2:
            return "Февраль";
         case 3:
            return "Март";
         case 4:
            return "Апрель";
         case 5:
            return "Март";
         case 6:
            return "Июнь";
         case 7:
            return "Июль";
         case 8:
            return "Август";
         case 9:
            return "Сентябрь";
         case 10:
            return "Октябрь";
         case 11:
            return "Ноябрь";
         case 12:
            return "Декабрь";
         default:
            return "-";
      }
   }

   public static LocalDateTime getLocalDateTime(long millis) {
      return getLocalDateTime(millis, ZoneOffset.systemDefault());
   }

   public static LocalDateTime getLocalDateTime(long millis, ZoneId zone) {
      return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), zone);
   }

   public static LocalDateTime getLocalDateTime(long millis, String offSet) {
      ZoneId zone;
      try {
         zone = ZoneOffset.ofTotalSeconds(Integer.parseInt(offSet));
      } catch (Exception e) {
         zone = ZoneOffset.UTC;
      }
      return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), zone);
   }

   public static String getStringDate(LocalDate date) {
      return date.format(formatDate);
   }
   
//   public static LocalDate getDate(String date) {
//      return date.format(formatDate);
//   }

   public static int readParamInt(String param) {
      int res = -1;
      if (param != null) {
         try {
            res = Integer.parseInt(param);
         } catch (NumberFormatException ex) {
            log.warn("Ошибка при парсе строки \"" + param + "\"");
         }
      }
      return res;
   }

   public static double readParamDouble(String param) {
      double res = -1;
      if (param != null) {
         try {
            res = Double.parseDouble(param);
         } catch (NumberFormatException ex) {
            log.warn("Ошибка при парсе строки \"" + param + "\"");
         }
      }
      return res;
   }

   public static long readParamLong(String param) {
      long res = 0;
      if (param != null) {
         try {
            res = Long.parseLong(param);
         } catch (NumberFormatException ex) {
            log.warn("Ошибка при парсе строки \"" + param + "\"");
         }
      }
      return res;
   }
}
