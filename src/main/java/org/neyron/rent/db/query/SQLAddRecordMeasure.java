package org.neyron.rent.db.query;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.neyron.rent.LibCommon;
import org.neyron.rent.db.ManagerDB;
import org.neyron.rent.db.SQLQuery;

/**
 *
 * @author dmitry
 */
public class SQLAddRecordMeasure implements SQLQuery {

   private final int newBathroomCold;
   private final int newBathroomHot;
   private final int newKitchenCold;
   private final int newKitchenHot;
   private final int newLight;
   private final LocalDate date;

   public SQLAddRecordMeasure(int newBathroomCold, int newBathroomHot, int newKitchenCold, int newKitchenHot, int newLight, LocalDate date) {
      this.newBathroomCold = newBathroomCold;
      this.newBathroomHot = newBathroomHot;
      this.newKitchenCold = newKitchenCold;
      this.newKitchenHot = newKitchenHot;
      this.newLight = newLight;
      this.date = date;
   }

   @Override
   public String getSQL() {

      return new StringBuilder()
              .append("INSERT INTO \"Measurement\" (bathroom_cold,bathroom_hot,kitchen_cold,kitchen_hot,light,month_date) VALUES")
              .append("(")
              .append(newBathroomCold).append(",")
              .append(newBathroomHot).append(",")
              .append(newKitchenCold).append(",")
              .append(newKitchenHot).append(",")
              .append(newLight).append(",")
              .append("'").append(LibCommon.getStringDate(date)).append("'").append(");").toString();
   }

   @Override
   public ManagerDB.Query getTypeQuery() {
      return ManagerDB.Query.UPDATE;
   }

   @Override
   public void parseResultQuery(ResultSet rs) throws SQLException {
   }

   @Override
   public void onUpdate(int countRowUpdate) {
      
   }
}
