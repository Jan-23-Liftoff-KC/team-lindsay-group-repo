const viewAppointments = document.getElementById('view-appointments');
const cookieArr = document.cookie.split("=");
const doctorId = cookieArr[1];

const updatePatientDetailsBtn = document.getElementById("update-patient-details-button");
const successMessage = document.getElementById("success-message");

let patientDiagUpdate = document.getElementById("patient-diagnosis-update");
let patientPresUpdate = document.getElementById("patient-prescriptions-update");
let patientDocNotesUpdate = document.getElementById("patient-doctorNotes-update");

let patientId = '';

var completeBtn = document.createElement('button');
completeBtn.type = 'button';
completeBtn.innerHTML = 'Mark as Complete';
completeBtn.className = 'btn-styled';

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/v1"

const getAllAppointments = async () =>{

    const response = await fetch(`${baseUrl}/appointments/doctor/${doctorId}`)
                    .catch(err => console.error(err.message));

    if(response.status === 200){
        let result = await response.json();
        if(result.length === 0)
        {
        const divShowData = document.getElementById('showData');
            divShowData.innerHTML = "You don't have any appointments yet!!";
        }
        else
        {
        populateAppointmentTable(result);
        }

    }

}

var modal = document.getElementById("myModal");
var span = document.getElementsByClassName("close")[0];

span.onclick = function() {
  modal.style.display = "none";
}

modal.style.display = "none";

window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

window.addEventListener('load', getAllAppointments);

async function patientDetails(patientDto, apptId) {

    completeBtn.setAttribute('appointment-id', apptId);
    modal.style.display = "block";
    const result = await fetch(`${baseUrl}/patients/${patientDto.id}`)
                        .catch(err => console.error(err.message));
           if(result.status === 200){
           displayPatientDetails(patientDto);
           }
}

updatePatientDetailsBtn.addEventListener("click", (e)=>{
    let patientId = e.target.getAttribute('patient-id')
    handlePatientUpdate(patientId);
})

completeBtn.addEventListener("click", (e)=>{
    let id = e.target.getAttribute('appointment-id')
    handleApptStatusUpdate(id);
})


async function handleApptStatusUpdate(appId){
    let bodyObj = {
        id: appId,
        status: 2
    }

   const res = await fetch(`${baseUrl}/appointments/${appId}`, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))
        if (res.status == 200) {
            document.getElementById("success-message").innerHTML = "Marked as Completed Successfully!!";
            getAllAppointments();
        }
        else {
            document.getElementById("success-message").innerHTML = "Something Went Wrong! Try Again later!";
        }
}


async function handlePatientUpdate(patientId){
    let bodyObj = {
        id: patientId,
        diagnosis: patientDiagUpdate.value,
        prescriptions: patientPresUpdate.value,
        doctorNotes: patientDocNotesUpdate.value
    }

   const res = await fetch(`${baseUrl}/patients/${patientId}/update/diagnosis/`, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))
        if (res.status == 200) {
            document.getElementById("success-message").innerHTML = "Diagnosis details have been updated Successfully!,    "
            successMessage.appendChild(completeBtn);

        }
        else {
            document.getElementById("success-message").innerHTML = "Something Went Wrong! Try Again later!";
        }
}


let populateAppointmentTable = (appointments) => {

    let col = [];
    let colTitle = ["Appointment Id","Appointment Date & Time",""];
    for (let i = 0; i < appointments.length; i++) {
      for (let key in appointments[i]) {
        if (col.indexOf(key) === -1 && (key == "id" || key =="appointmentDate")) {
          col.push(key);
        }
      }
    }
    col.push("");

    // Create table.
    const table = document.createElement("table");
    table.setAttribute('class', 'table table-striped');

    // Create table header row using the extracted headers above.
    let tr = table.insertRow(-1);                   // table row.

    for (let i = 0; i < colTitle.length; i++) {
      let th = document.createElement("th");      // table header.
        th.innerHTML = colTitle[i];
        tr.appendChild(th);
    }

    // add json data to the table as rows.
    for (let i = 0; i < appointments.length; i++) {

      tr = table.insertRow(-1);

      for (let j = 0; j < col.length; j++) {
      let tabCell = tr.insertCell(-1);
      if(j===1)
        {
        tabCell.innerHTML = (new Date(appointments[i][col[j]])).toLocaleString();
        }
      else if(j===2)
      {
          var btnDetails = document.createElement('button');
          btnDetails.type = 'button';
          btnDetails.innerHTML = 'View Details';
          btnDetails.className = 'btn-styled';
          //btnDetails.setAttribute("onclick",function cancelAppointment(appointments[i][col[0]]));
          btnDetails.onclick= async function() { await patientDetails(appointments[i].patientDto, appointments[0][col[0]]) };
          tabCell.appendChild(btnDetails);
      }
      else
      {
      tabCell.innerHTML = appointments[i][col[j]];
      }
      }
    }

    // Now, add the newly created table with json data, to a container.
    const divShowData = document.getElementById('showData');
    divShowData.innerHTML = "";
    divShowData.appendChild(table);
  }

  let displayPatientDetails = (obj) => {
    document.getElementById("patient-firstName-label").innerHTML = obj.firstName;
    document.getElementById("patient-lastName-label").innerHTML = obj.lastName;
    document.getElementById("patient-age-label").innerHTML = obj.age;
    updatePatientDetailsBtn.setAttribute('patient-id', obj.id);
  }