$(document).ready(function () {
   $('#button-add').button().click(function (event) {
      var year = $('#year').val();
      var month = $('#month').val();
      var bathroomCold = $('#field-bathroom-cold').val();
      var bathroomHot = $('#field-bathroom-hot').val();
      var kitchenCold = $('#field-kitchen-cold').val();
      var kitchenHot = $('#field-kitchen-hot').val();
      var light = $('#field-light').val();
      if (bathroomCold == "" ||
              bathroomHot == "" ||
              kitchenCold == "" ||
              kitchenHot == "" ||
              light == "") {
         $('#status').html("Заполните все поля");
         return true;
      }
      sendCmd("add_record", {
         year: year,
         month: month,
         bathroomCold: bathroomCold,
         bathroomHot: bathroomHot,
         kitchenCold: kitchenCold,
         kitchenHot: kitchenHot,
         light: light
      }, function (param) {
         $('#status').html(param);

         sendCmd("get_record_cost", {}, function (param) {
            $("#preview").html(param);
         });
      });
      return true;
   });

   sendCmd("get_record_cost", {}, function (param) {
      $("#preview").html(param);
   });

});

var sendCmd = function (cmd, data, handler) {
   return (function () {
      var path = PATH_CONTEXT + "/cmd";

      if (cmd != null)
         data["cmd"] = cmd;

      $.post(path, data, function (param) {
         handler(param);
      }, "html");
   })();
};

function validCostFromInput(val) {
   var reg = [/^\D+/, /[^.,\d]+/g, /[\.,]+/, /(\d+\.\d{2}).*$/],
           ch = val.replace(reg[0], '').replace(reg[1], '').replace(reg[2], '.').replace(reg[3], '$1');
   return ch;
}
