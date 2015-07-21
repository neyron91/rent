package org.neyron.rent.db.query;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.neyron.rent.LibCommon;
import org.neyron.rent.db.ManagerDB;
import org.neyron.rent.db.SQLQuery;

/**
 *
 * @author dmitry
 */
public class SQLGetRecordsCost implements SQLQuery {

   private final ArrayList<RecordCost> recCostList;

   public SQLGetRecordsCost() {
      recCostList = new ArrayList<>();
   }

   @Override
   public String getSQL() {
      return new StringBuilder()
              .append("SELECT \"Measurement\".month_date, \"Cost\".cold, \"Cost\".hot, \"Cost\".sanitation, \"Cost\".light, \"Cost\".door, \"Cost\".total ")
              .append("FROM \"Measurement\" INNER JOIN \"Cost\" ON \"Measurement\".id = \"Cost\".id_measurement;").toString();
   }

   @Override
   public ManagerDB.Query getTypeQuery() {
      return ManagerDB.Query.SELECT;
   }

   @Override
   public void parseResultQuery(ResultSet rs) throws SQLException {
      while (rs.next()) {
         recCostList.add(new RecordCost(
                 LibCommon.getLocalDateTime(rs.getDate("month_date").getTime()).toLocalDate(),
                 rs.getBigDecimal("cold").setScale(2, BigDecimal.ROUND_HALF_UP),
                 rs.getBigDecimal("hot").setScale(2, BigDecimal.ROUND_HALF_UP),
                 rs.getBigDecimal("sanitation").setScale(2, BigDecimal.ROUND_HALF_UP),
                 rs.getBigDecimal("light").setScale(2, BigDecimal.ROUND_HALF_UP),
                 rs.getBigDecimal("door").setScale(2, BigDecimal.ROUND_HALF_UP),
                 rs.getBigDecimal("total").setScale(2, BigDecimal.ROUND_HALF_UP)));
      }
   }

   @Override
   public void onUpdate(int countRowUpdate) {
   }

   public ArrayList<RecordCost> getRecCostList() {
      return recCostList;
   }

   public class RecordCost {

      private final LocalDate date;
      private final BigDecimal cold;
      private final BigDecimal hot;
      private final BigDecimal sanitation;
      private final BigDecimal light;
      private final BigDecimal door;
      private final BigDecimal total;

      public RecordCost(LocalDate date, BigDecimal cold, BigDecimal hot, BigDecimal sanitation, BigDecimal light, BigDecimal door, BigDecimal total) {
         this.date = date;
         this.cold = cold;
         this.hot = hot;
         this.sanitation = sanitation;
         this.light = light;
         this.door = door;
         this.total = total;
      }

      public LocalDate getDate() {
         return date;
      }

      public BigDecimal getCold() {
         return cold;
      }

      public BigDecimal getHot() {
         return hot;
      }

      public BigDecimal getSanitation() {
         return sanitation;
      }

      public BigDecimal getLight() {
         return light;
      }

      public BigDecimal getDoor() {
         return door;
      }

      public BigDecimal getTotal() {
         return total;
      }

   }
}
