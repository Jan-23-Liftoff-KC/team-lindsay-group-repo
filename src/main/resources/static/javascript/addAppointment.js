const addAppt = document.getElementById('addAppointment');
const addProviderName = document.getElementById('addAppointment-providerName');
const apptDateTime = document.getElementById('addAppointment-appDateTime');
const apptDetails = document.getElementById('appDetails');

let doctorId = '';
let patientId = '';

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/v1"

const handleSubmit = async (e) =>{
    e.preventDefault();

    let bodyObj = {
        appointmentDate: new Date(apptDateTime.value)
    }

    const response = await fetch(`${baseUrl}/appointments/doctor/${doctorId}/patient/${patientId}`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
    .catch(err => console.error(err.message))

    if(response.status === 200){
        window.location.replace("http://localhost:8080/appointmentDetails.html?docId="+doctorId+"&patId="+patientId);
    }
}

const getDoctorAndPatient = async () =>{

    let params = new URLSearchParams(window.location.search);
    doctorId = params.get('docId');
    patientId = params.get('patId');
    apptDetails.setAttribute('href',"appointmentDetails.html?docId="+doctorId+"&patId="+patientId);
    const response = await fetch(`${baseUrl}/doctors/${doctorId}`)
                    .catch(err => console.error(err.message));

    if(response.status === 200){
        let result = await response.json()
        addProviderName.innerHTML = "Provider Name: <b>"+result['doctorName']+"</b>";

    }

}

window.addEventListener('load', getDoctorAndPatient);

addAppt.addEventListener("submit", handleSubmit);

