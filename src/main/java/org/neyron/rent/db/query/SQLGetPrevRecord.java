package org.neyron.rent.db.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.neyron.rent.db.ManagerDB;
import org.neyron.rent.db.SQLQuery;

/**
 *
 * @author dmitry
 */
public class SQLGetPrevRecord implements SQLQuery {

   private int oldBathroomCold;
   private int oldBathroomHot;
   private int oldKitchenCold;
   private int oldKitchenHot;
   private int oldLight;
   private final LocalDate date;

   public SQLGetPrevRecord(LocalDate date) {
      this.date = date;
   }

   @Override
   public String getSQL() {
      return new StringBuilder()
              .append("SELECT bathroom_cold, bathroom_hot, kitchen_cold, kitchen_hot, light FROM \"Measurement\"")
              .append("WHERE Extract(YEAR from month_date) = ").append(date.getYear())
              .append("AND Extract(MONTH from month_date) = ").append(date.getMonthValue()).append(";").toString();
   }

   @Override
   public ManagerDB.Query getTypeQuery() {
      return ManagerDB.Query.SELECT;
   }

   @Override
   public void parseResultQuery(ResultSet rs) throws SQLException {
      while (rs.next()) {
         oldBathroomCold = rs.getInt("bathroom_cold");
         oldBathroomHot = rs.getInt("bathroom_hot");
         oldKitchenCold = rs.getInt("kitchen_cold");
         oldKitchenHot = rs.getInt("kitchen_hot");
         oldLight = rs.getInt("light");
      }
   }

   @Override
   public void onUpdate(int countRowUpdate) {
   }

   public int getOldBathroomCold() {
      return oldBathroomCold;
   }

   public int getOldBathroomHot() {
      return oldBathroomHot;
   }

   public int getOldKitchenCold() {
      return oldKitchenCold;
   }

   public int getOldKitchenHot() {
      return oldKitchenHot;
   }

   public int getOldLight() {
      return oldLight;
   }
}
