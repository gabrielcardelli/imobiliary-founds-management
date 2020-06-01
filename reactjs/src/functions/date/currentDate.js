export function getCurrentDate(format){

  var today = new Date();
  var dd = today.getDate();
  var mm = today.getMonth()+1; //January is 0!
  var yyyy = today.getFullYear();

  if(dd<10) {
      dd = '0'+dd
  }

  if(mm<10) {
      mm = '0'+mm
  }

  format = format.replace("dd",dd);
  format = format.replace("mm",mm);
  format = format.replace("yyyy", yyyy);
  
  return format;

}
