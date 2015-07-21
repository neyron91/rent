package org.neyron.rent.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.neyron.rent.db.ManagerDB.Query;

/**
 *
 * @author dmitry
 */
public interface SQLQuery {

   public String getSQL();

   public Query getTypeQuery();

   public void parseResultQuery(ResultSet rs) throws SQLException;

   public void onUpdate(int countRowUpdate);
}
