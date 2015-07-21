package org.neyron.rent.db.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.neyron.rent.db.ManagerDB;
import org.neyron.rent.db.SQLQuery;

/**
 *
 * @author dmitry
 */
public class SQLGetMaxIdMeasurement implements SQLQuery {

   private int id;

   @Override
   public String getSQL() {
      return "select max(id) from \"Measurement\";";
   }

   @Override
   public ManagerDB.Query getTypeQuery() {
      return ManagerDB.Query.SELECT;
   }

   @Override
   public void parseResultQuery(ResultSet rs) throws SQLException {
      while (rs.next()) {
         id = rs.getInt("max");
      }
   }

   @Override
   public void onUpdate(int countRowUpdate) {
   }

   public int getId() {
      return id;
   }

}
