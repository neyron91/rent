package org.neyron.rent.db.query;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.neyron.rent.db.ManagerDB;
import org.neyron.rent.db.SQLQuery;

/**
 *
 * @author dmitry
 */
public class SQLGetPrice implements SQLQuery {

   private BigDecimal cold;
   private BigDecimal hot;
   private BigDecimal light;
   private BigDecimal sanitation;
   private BigDecimal door;

   @Override
   public String getSQL() {
      return "select cold, hot, sanitation, light, door from \"Price\";";
   }

   @Override
   public ManagerDB.Query getTypeQuery() {
      return ManagerDB.Query.SELECT;
   }

   @Override
   public void parseResultQuery(ResultSet rs) throws SQLException {
      while (rs.next()) {
         cold = rs.getBigDecimal("cold");
         hot = rs.getBigDecimal("hot");
         light = rs.getBigDecimal("light");
         sanitation = rs.getBigDecimal("sanitation");
         door = rs.getBigDecimal("door");
      }
   }

   @Override
   public void onUpdate(int countRowUpdate) {
   }

   public BigDecimal getCold() {
      return cold;
   }

   public BigDecimal getHot() {
      return hot;
   }

   public BigDecimal getLight() {
      return light;
   }

   public BigDecimal getSanitation() {
      return sanitation;
   }

   public BigDecimal getDoor() {
      return door;
   }
}
