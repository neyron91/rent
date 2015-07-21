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
public class SQLAddRecordCost implements SQLQuery {

   private int result;
   
   private final int id;
   private final BigDecimal cold;
   private final BigDecimal hot;
   private final BigDecimal light;
   private final BigDecimal door;
   private final BigDecimal sanitation;
   private final BigDecimal totalSum;

   public SQLAddRecordCost(int id, BigDecimal cold, BigDecimal hot, BigDecimal light, BigDecimal door, BigDecimal sanitation, BigDecimal totalSum) {
      this.id = id;
      this.cold = cold;
      this.hot = hot;
      this.light = light;
      this.door = door;
      this.sanitation = sanitation;
      this.totalSum = totalSum;
   }

   @Override
   public String getSQL() {
      return new StringBuilder()
              .append("INSERT INTO \"Cost\" (id_measurement,cold,hot,sanitation,light,door,total) VALUES")
              .append("(")
              .append(id).append(",")
              .append(cold.toString()).append(",")
              .append(hot.toString()).append(",")
              .append(sanitation.toString()).append(",")
              .append(light.toString()).append(",")
              .append(door.toString()).append(",")
              .append(totalSum.toString()).append(");").toString();
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
      result = countRowUpdate;
   }

   public int getResult() {
      return result;
   }
   
}
