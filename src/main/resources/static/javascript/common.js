function checkAuthorized()
{
 let med_app_pat_id = getCookie("med_app_pat_id");
 let med_app_pat_fname = getCookie("med_app_pat_fname");
 let med_app_pat_lname = getCookie("med_app_pat_lname");
 let med_app_pat_expires = getCookie("med_app_pat_expires");

 if(med_app_pat_id != null && med_app_pat_id != ""
    && med_app_pat_expires != null
    && med_app_pat_expires != "") {
    appExpiredDate = new Date(med_app_pat_expires);
        let today = new Date();
        if(today > appExpiredDate){
            clearCookies();
            window.location.replace("http://localhost:8080/error.html");
        }
        else {
            let divWelcomeUser = document.getElementById('divWelcomeUser');
            divWelcomeUser.innerHTML = "Welcome <b>" + med_app_pat_fname + " " + med_app_pat_lname + "</b>!";
        }
 }
 else {
    window.location.replace("http://localhost:8080/error.html");
 }
}

function clearCookies(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

function getCookie(cookieName) {
  let name = cookieName + "=";
  let cookiesList = document.cookie.split(';');
  for(var j = 0; j < cookiesList.length; j++) {
    let char = cookiesList[j];
    while (char.charAt(0) == ' ') {
      char = char.substring(1);
    }
    if (char.indexOf(name) == 0) {
      return char.substring(name.length, char.length);
    }
  }
  return "";
}

window.addEventListener('load', checkAuthorized);