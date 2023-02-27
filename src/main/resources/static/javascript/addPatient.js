const addPatientForm = document.getElementById('addPatient-form')
const addPatientFirstName = document.getElementById('addPatient-firstName')
const addPatientLastName = document.getElementById('addPatient-lastName')
const addPatientAge = document.getElementById('addPatient-age')
const addPatientEmail = document.getElementById('addPatient-email')
const addPatientPassword = document.getElementById('addPatient-password')
const addPatientPhoneNo = document.getElementById('addPatient-phoneNo')
const addPatientDoctorId = document.getElementById('addPatient-doctorId')

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/v1"

const handleSubmit = async (e) =>{
    e.preventDefault();

    const doctorId = parseInt(addPatientDoctorId.options[addPatientDoctorId.selectedIndex].value);

    let bodyObj = {
        firstName: addPatientFirstName.value,
        lastName: addPatientLastName.value,
        age: addPatientAge.value,
        email: addPatientEmail.value,
        phoneNo: addPatientPhoneNo.value,
        password: addPatientPassword.value
    }

    const response = await fetch(`${baseUrl}/patients/addpatient/doctor/${doctorId}`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
    .catch(err => console.error(err.message))

    if(response.status === 200){
        const result = await response.text();
        if (result === "SUCCESS"){
        window.location.replace("http://localhost:8080/patientLogin.html");
        }
        else{
        document.getElementById("uniqueCons").innerHTML = result;
        }

    }
}

const loadDoctors = async () =>{

    const response = await fetch(`${baseUrl}/doctors`)
                    .catch(err => console.error(err.message));

    if(response.status === 200){
        const responseArr = await response.json()
        //const responseArr = [{"id":1,"doctorName":"Andy Mark"},{"id":2,"doctorName":"Johnson Dan"}];
        for (let i = 0; i < responseArr.length; i++) {
            // POPULATE SELECT ELEMENT WITH JSON.
            addPatientDoctorId.innerHTML = addPatientDoctorId.innerHTML +
                '<option value="' + responseArr[i]['id'] + '">' + responseArr[i]['doctorName'] + '</option>';
        }
    }
}

window.addEventListener('load', loadDoctors);

addPatientForm.addEventListener("submit", handleSubmit);

