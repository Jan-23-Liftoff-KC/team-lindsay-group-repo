const appointmentList = document.getElementById('doc-appointment-list');

const cookieArr = document.cookie.split("=");
const doctorId = 2;

let patientId = '';

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

const viewAppointment = async (patientId) =>{

    const resp = await fetch(`${baseUrl}/appointments/patient/${patientId}`)
                    .catch(err => console.error(err.message));
    if(resp.status === 200){
        alert("Patient ID " + patientId + " appointment!");
    }
}

window.addEventListener('load', getAllAppointments);
//.appointmentList.addEventListener("submit", handleSubmit);

let populateAppointmentTable = (appointments) => {

    let col = [];
    let colTitle = ["Appointment Id","Appointment Date & Time","Patient First Name","Patient Last Name", ""];
    for (let i = 0; i < appointments.length; i++) {
      for (let key in appointments[i]) {
        if (col.indexOf(key) === -1 && (key == "id" || key =="appointmentDate" || key =="patientDto")) {
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

      for (let j = 0; j < col.length+1; j++) {
      let tabCell = tr.insertCell(-1);
        if(j===1) {
            tabCell.innerHTML = (new Date(appointments[i][col[j]])).toLocaleString();
        }
        else if(j===2) {
            tabCell.innerHTML = appointments[i][col[2]].firstName;
        }
        else if(j===3){
            tabCell.innerHTML = appointments[i][col[2]].lastName;
        }
        else if(j===4)
        {
          var btnView = document.createElement('button');
          btnView.type = 'button';
          btnView.innerHTML = 'View Details';
          btnView.className = 'btn-styled';
          //btnCancel.setAttribute("onclick",function cancelAppointment(appointments[i][col[0]]));
          btnView.onclick= async function() { await viewAppointment(appointments[i][col[2]].id) };
          tabCell.appendChild(btnView);
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