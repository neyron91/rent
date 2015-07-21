package org.neyron.rent.web.ui.cmd;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.neyron.rent.LibCommon;
import org.neyron.rent.Main;
import org.neyron.rent.db.query.SQLAddRecordCost;
import org.neyron.rent.db.query.SQLAddRecordMeasure;
import org.neyron.rent.db.query.SQLGetMaxIdMeasurement;
import org.neyron.rent.db.query.SQLGetPrevRecord;
import org.neyron.rent.db.query.SQLGetPrice;
import org.neyron.rent.web.ui.Command;
import org.neyron.rent.web.ui.html.HtmlAddRecord;

/**
 *
 * @author dmitry
 */
public class CmdAddRecord extends Command {

   public static final String NAME = "add_record";

   @Override
   public boolean doCmd(HttpServletRequest request, HttpServletResponse response) {
      Main main = (Main) request.getSession(false).getAttribute(Main.class.getName());

      int newBathroomCold = LibCommon.readParamInt(request.getParameter("bathroomCold"));
      int newBathroomHot = LibCommon.readParamInt(request.getParameter("bathroomHot"));
      int newKitchenCold = LibCommon.readParamInt(request.getParameter("kitchenCold"));
      int newKitchenHot = LibCommon.readParamInt(request.getParameter("kitchenHot"));
      int newLight = LibCommon.readParamInt(request.getParameter("light"));
      int year = LibCommon.readParamInt(request.getParameter("year"));
      int month = LibCommon.readParamInt(request.getParameter("month"));

      LocalDate dateCurrent = LocalDate.of(year, month, 1);

      SQLAddRecordMeasure sqlAddRec = new SQLAddRecordMeasure(newBathroomCold, newBathroomHot, newKitchenCold, newKitchenHot, newLight, dateCurrent);
      main.getManager().execute(sqlAddRec); // Добавили показания
      SQLGetMaxIdMeasurement sqlId = new SQLGetMaxIdMeasurement();
      main.getManager().execute(sqlId);
      int id = sqlId.getId(); // ИД

      LocalDate datePrev = dateCurrent.minusMonths(1);
      SQLGetPrevRecord sqlPrevRecord = new SQLGetPrevRecord(datePrev);
      main.getManager().execute(sqlPrevRecord);
      int oldBathroomCold = sqlPrevRecord.getOldBathroomCold();
      int oldBathroomHot = sqlPrevRecord.getOldBathroomHot();
      int oldKitchenCold = sqlPrevRecord.getOldKitchenCold();
      int oldKitchenHot = sqlPrevRecord.getOldKitchenHot();
      int oldLight = sqlPrevRecord.getOldLight();

      SQLGetPrice sqlPrice = new SQLGetPrice();
      main.getManager().execute(sqlPrice);

      BigDecimal priceCold = sqlPrice.getCold();
      BigDecimal priceHot = sqlPrice.getHot();
      BigDecimal priceLight = sqlPrice.getLight();
      BigDecimal priceDoor = sqlPrice.getDoor();
      BigDecimal priceSanitation = sqlPrice.getSanitation();

      int cold = (newBathroomCold - oldBathroomCold) + (newKitchenCold - oldKitchenCold);
      int hot = (newBathroomHot - oldBathroomHot) + (newKitchenHot - oldKitchenHot);
      int light = newLight - oldLight;

      BigDecimal totalCold = priceCold.multiply(BigDecimal.valueOf(cold));
      BigDecimal totalHot = priceHot.multiply(BigDecimal.valueOf(hot));
      BigDecimal totalLight = priceLight.multiply(BigDecimal.valueOf(light));
      BigDecimal totalDoor = priceDoor;
      BigDecimal totalSanitation = priceSanitation.multiply(BigDecimal.valueOf(cold + hot));
      BigDecimal totalSum = BigDecimal.ZERO;
      totalSum = totalSum.add(totalCold);
      totalSum = totalSum.add(totalHot);
      totalSum = totalSum.add(totalLight);
      totalSum = totalSum.add(totalDoor);
      totalSum = totalSum.add(totalSanitation);
      totalCold = totalCold.setScale(2, RoundingMode.HALF_UP);
      totalHot = totalHot.setScale(2, RoundingMode.HALF_UP);
      totalLight = totalLight.setScale(2, RoundingMode.HALF_UP);
      totalDoor = totalDoor.setScale(2, RoundingMode.HALF_UP);
      totalSanitation = totalSanitation.setScale(2, RoundingMode.HALF_UP);
      totalSum.setScale(2, RoundingMode.HALF_UP);

      SQLAddRecordCost sqlCost = new SQLAddRecordCost(id, totalCold, totalHot, totalLight, totalDoor, totalSanitation, totalSum);
      main.getManager().execute(sqlCost);

      setHtml(new HtmlAddRecord(sqlCost.getResult()));
      return true;
   }

   @Override
   public TypeData getTypeData() {
      return TypeData.HTML;
   }

}
