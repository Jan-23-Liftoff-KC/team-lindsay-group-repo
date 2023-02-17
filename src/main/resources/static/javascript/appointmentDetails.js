const appointmentList = document.getElementById('appointment-list');
const anchorNewAppt = document.getElementById('ancNewAppt');

let patientId = '';

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/v1"

const getAllAppointments = async () =>{

    let params = new URLSearchParams(window.location.search);
    patientId = params.get('patId');
    doctorId = params.get('docId');
    anchorNewAppt.setAttribute('href',"addAppointment.html?docId="+doctorId+"&patId="+patientId);
    const response = await fetch(`${baseUrl}/appointments/patient/${patientId}`)
                    .catch(err => console.error(err.message));

    if(response.status === 200){
        let result = await response.json();
        if(result.length === 0)
        {
        const divShowData = document.getElementById('showData');
            divShowData.innerHTML = "No data found";
        }
        else
        {
        populateAppointmentTable(result);
        }

    }

}

window.addEventListener('load', getAllAppointments);

//function cancelAppointment(appId) {
const cancelAppointment = async (appId) =>{
        let bodyObj = {
            id:appId,
            status: 0
        }

    const response = await fetch(`${baseUrl}/appointments/${appId}`, {
        method: "DELETE",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
    .catch(err => console.error(err.message))

    if(response.status === 200){
        alert("Appointment " + appId + " cancelled successfully!");
        getAllAppointments();
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
          var btnCancel = document.createElement('button');
          btnCancel.type = 'button';
          btnCancel.innerHTML = 'Cancel';
          btnCancel.className = 'btn-styled';
          //btnCancel.setAttribute("onclick",function cancelAppointment(appointments[i][col[0]]));
          btnCancel.onclick= async function() { await cancelAppointment(appointments[i][col[0]]) };
          tabCell.appendChild(btnCancel);
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