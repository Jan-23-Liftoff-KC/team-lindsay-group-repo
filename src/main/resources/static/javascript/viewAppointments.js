const viewAppointments = document.getElementById('view-appointments');
const cookieArr = document.cookie.split("=");
const doctorId = cookieArr[1];

const appointmentContainer = document.getElementById("appointment-container");

let patientId = '';

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/v1"

const getAllAppointments = async () =>{

    const response = await fetch(`${baseUrl}/appointments/doctor/1`)
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

window.addEventListener('load', getAllAppointments);
async function patientDetails(patientDto) {

     const response = await fetch(`${baseUrl}/patients/${patientDto.id}`)
                        .catch(err => console.error(err.message));

           if(response.status === 200){
           createAppointmentCards(patientDto);

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
          btnDetails.onclick= async function() { await patientDetails(appointments[i].patientDto) };
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
  let createAppointmentCards = (patientDto1) => {
      appointmentContainer.innerHTML = ''
      let appointmentCard = document.createElement("div")
          appointmentCard.classList.add("m-2")
          appointmentCard.innerHTML = `
              <div class="card d-flex" style="width: 25rem; height: 18rem;">
                  <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                      <p class="card-text"><span class="bolded">First name:</span> ${patientDto1.firstName}</p>
                      <p class="card-text"><span class="bolded">Last name:</span> ${patientDto1.lastName}</p>
                      <p class="card-text"><span class="bolded">Age:</span> ${patientDto1.age}</p>
                  </div>
              </div>
          `
      appointmentContainer.append(appointmentCard);

  }