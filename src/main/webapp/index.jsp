<%@page import="org.neyron.rent.Main"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Учет квартплаты || Версия <%=Main.VERSION%></title>
      <script>
         var PATH_CONTEXT = "<%=request.getContextPath()%>";
      </script>

      <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/general.css"/>
      <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jq-ui/jquery-ui.min.css"/>

      <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-2.1.1.min.js"></script>
      <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-migrate-1.2.1.min.js"></script>
      <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-ui.min.js"></script>
      <script type="text/javascript" src="<%=request.getContextPath()%>/js/general.js"></script>
   </head>
   <body>
      <div class="wrapper">
         <div id="add-record" class="left">
            <table>
               <tr>
                  <td>Дата: </td>
                  <td>
                     <select id="year">
                        <option value="2013">2013</option>
                        <option selected value="2014">2014</option>
                        <option value="2015">2015</option>
                        <option value="2016">2016</option>
                     </select>
                  </td>
                  <td>-</td>
                  <td>
                     <select id="month">
                        <option selected value="1">Январь</option>
                        <option value="2">Февраль</option>
                        <option value="3">Март</option>
                        <option value="4">Апрель</option>
                        <option value="5">Май</option>
                        <option value="6">Июнь</option>
                        <option value="7">Июль</option>
                        <option value="8">Август</option>
                        <option value="9">Сентябрь</option>
                        <option value="10">Октябрь</option>
                        <option value="11">Ноябрь</option>
                        <option value="12">Декабрь</option>
                     </select>
                  </td>
               </tr>
            </table>
            <br>
            <table>
               <thead>
                  <tr>
                     <th colspan="3">Показатели</th>
                  </tr>
               </thead>
               <tbody>
                  <tr>
                     <td></td>
                     <td>Кухня</td>
                     <td>Ванная</td>
                  </tr>
                  <tr>
                     <td>Горячая</td>
                     <td>
                        <input id="field-kitchen-hot" type="number" size="6">
                     </td>
                     <td>
                        <input id="field-bathroom-hot" type="number" size="6">
                     </td>
                  </tr>
                  <tr>
                     <td>Холодная</td>
                     <td>
                        <input id="field-kitchen-cold" type="number" size="6">
                     </td>
                     <td>
                        <input id="field-bathroom-cold" type="number" size="6">
                     </td>
                  </tr>
                  <tr>
                     <td>Свет</td>
                     <td colspan="2">
                        <input id="field-light" type="number" size="6">
                     </td>
                  </tr>
                  <tr>
                     <td>
                        <button id="button-add">Добавить</button>
                     </td>
                     <td colspan="2" id="status"></td>
                  </tr>
               </tbody>
            </table> 
         </div>
         <div id="preview" class="right" >
         </div>
      </div>
   </body>

</html>