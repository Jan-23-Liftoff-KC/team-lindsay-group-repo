let loginForm = document.getElementById('patient-login')
let loginUserName = document.getElementById('login-userName')
let loginPassword = document.getElementById('login-password')

if(document.referrer != '' && (document.referrer.indexOf('appointmentDetails.html') >= 0 || document.referrer.indexOf('addAppointment.html') >= 0))
{
    clearCookies();
    document.getElementById('messageDiv').innerHTML = "You have been successfully logged out!!";
}

function clearCookies(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/"

const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        email: loginUserName.value,
        password: loginPassword.value
    }

    const response = await fetch(`${baseUrl}patients/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

       const responseArr = await response.json();

       if(response.status === 200){

        if (responseArr[1]==="username or password incorrect"){
              document.getElementById('errorMessage').innerHTML = "username or password incorrect, Please try again!!";
        }
        else{
            let patientId = responseArr[1];
            let doctorId = responseArr[2];
            let patFirstName = responseArr[3];
            let patLastName = responseArr[4];
            document.cookie = `med_app_pat_id=${patientId}`;
            document.cookie = `med_app_pat_fname=${patFirstName}`;
            document.cookie = `med_app_pat_lname=${patLastName}`;
            setPatientLoginExpireCookie();
            window.location.replace(responseArr[0]+"?docId="+doctorId+"&patId="+patientId);
       }
    }
}

function setPatientLoginExpireCookie() {
  var now = new Date();
  var time = now.getTime();
  var expireTime = time + 1000*300;
  now.setTime(expireTime);
  document.cookie = 'med_app_pat_expires='+now.toString();
  console.log(document.cookie);  // 'Wed, 31 Oct 2012 08:50:17 UTC'
}

loginForm.addEventListener("submit", handleSubmit);
