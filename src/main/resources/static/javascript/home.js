//create the Cookie
const cookieArr = document.cookie.split("=");
const doctorId = cookieArr[1];

//create DOM Elements
const submitForm = document.getElementById("patient-form");
const patientContainer = document.getElementById("patient-container");

// Creating the selectedOption so that the drug name can be saved from the dropdown menu selection and added to the DB:
let selectedOption = "";

//Create Modal Elements - these are for the Modal. These are for editing
let patientAge = document.getElementById("patient-age-edit");
let patientFirstName = document.getElementById("patient-firstName-edit");
let patientLastName = document.getElementById("patient-lastName-edit");
let patientDiagnosis = document.getElementById("patient-diagnosis-edit");
let patientPrescriptions = document.getElementById("patient-prescriptions-edit");
let patientDoctorNotes = document.getElementById("patient-doctorNotes-edit");
let patientBillingNotes = document.getElementById("patient-billingNotes-edit");


let updatePatientBtn = document.getElementById("update-patient-button");

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/patients/"

//InputValidation: make sure password d/n have letter, uppercase, etc...(These are not necessary-these are nice to haves)
//On name, someone can type numbers instead of a name, but this is a more advanced nice to have...get it working first.

const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
    //these are for creating on the homepage
        age: document.getElementById("patient-age").value,
        firstName: document.getElementById("patient-firstName").value,
        lastName: document.getElementById("patient-lastName").value,
        prescriptions: selectedOption,
//        prescriptions: document.getElementById("patient-prescriptions").value,
        doctorNotes: document.getElementById("patient-doctorNotes").value,
        billingNotes: document.getElementById("patient-billingNotes").value,
        diagnosis: document.getElementById("patient-diagnosis").value
    }
    await addPatient(bodyObj);
    document.getElementById("patient-age").value = '';
    document.getElementById("patient-firstName").value = '';
    document.getElementById("patient-lastName").value = '';
    document.getElementById("patient-prescriptions").innerHTML = '';
    resultsDropdown.selectedIndex = 0;
    document.getElementById("patient-doctorNotes").value = '';
    document.getElementById("patient-diagnosis").value = '';
    document.getElementById("patient-billingNotes").value = '';
}

async function addPatient(obj) {
    const response = await fetch(`${baseUrl}doctor/${doctorId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getPatients(doctorId);
    }
}

async function getPatients(doctorId) {
    await fetch(`${baseUrl}doctor/${doctorId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createPatientCards(data))
        .catch(err => console.error(err))
}

async function handleDelete(patientId){
    await fetch(baseUrl + patientId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getPatients(doctorId);
}

async function getPatientById(patientId){
    await fetch(baseUrl + patientId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handlePatientEdit(patientId){
    let bodyObj = {
        id: patientId,
        age: patientAge.value,
        firstName: patientFirstName.value,
        lastName: patientLastName.value,
        diagnosis: patientDiagnosis.value,
        prescriptions: patientPrescriptions.value,
        doctorNotes: patientDoctorNotes.value,
        billingNotes: patientBillingNotes.value
    }

    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getPatients(doctorId);
}

const createPatientCards = (array) => {
    patientContainer.innerHTML = ''
    array.forEach(obj => {
        let patientCard = document.createElement("div")
        patientCard.classList.add("m-2")
        patientCard.innerHTML = `
            <div class="card d-flex" /*overflow-auto*/ style="width: 25rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text"><span class="bolded">First name:</span> ${obj.firstName}</p>
                    <p class="card-text"><span class="bolded">Last name:</span> ${obj.lastName}</p>
                    <p class="card-text"><span class="bolded">Age:</span> ${obj.age}</p>
                    <p class="card-text"><span class="bolded">Diagnosis:</span> ${obj.diagnosis}</p>
                    <p class="card-text"><span class="bolded">Prescriptions:</span> ${obj.prescriptions}</p>
                    <p class="card-text"><span class="bolded">Notes:</span> ${obj.doctorNotes}</p>
                    <p class="card-text"><span class="bolded">Billing Notes:</span> ${obj.billingNotes}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getPatientById(${obj.id})" type="button" class="btn btn-primary"
                        data-bs-toggle="modal" data-bs-target="#patient-edit-modal">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `
        patientContainer.append(patientCard);
    })
}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const populateModal = (obj) =>{
    patientFirstName.innerText = ''
    patientLastName.innerText = ''
    patientAge.innerText = ''
    patientDiagnosis.innerText = ''
    patientPrescriptions.innerText = ''
    patientDoctorNotes.innerText = ''
    patientFirstName.innerText = obj.firstName
    patientLastName.innerText = obj.lastName
    patientAge.innerText = obj.age
    patientDiagnosis.innerText = obj.diagnosis
    patientPrescriptions.innerText = obj.prescriptions
    patientDoctorNotes.innerText = obj.doctorNotes
    patientBillingNotes.innerText = obj.billingNotes
    updatePatientBtn.setAttribute('data-patient-id', obj.id)
}
getPatients(doctorId);

submitForm.addEventListener("submit", handleSubmit)

updatePatientBtn.addEventListener("click", (e)=>{
    let patientId = e.target.getAttribute('data-patient-id')
    handlePatientEdit(patientId);
})

//work with the OpenFDA API:

const searchInput = document.querySelector('#search-input');
const searchButton = document.getElementById('search-button');
const resultsDropdown = document.querySelector('#results-dropdown');
const apiUrl = 'https://api.fda.gov/drug/label.json?search=';

async function fetchFromApi(selectedOption){
console.log("logging selectedOption in fetchFromAPI function", selectedOption)
await fetch(apiUrl + selectedOption)
  .then(response => response.json())
  .then(data => createDrugFieldData(data.results))
}


//this is for the p tag where the drug info is populated
const patientPrescriptionsDisplay = document.querySelector('#patient-prescriptions');

resultsDropdown.addEventListener('change', async () => {
console.log("Dropdown listener clicked")
selectedOption = resultsDropdown.options[resultsDropdown.selectedIndex].value;
  //call the api with the selected option.
await fetchFromApi(selectedOption)
console.log("selectedOption", selectedOption)
});

const commonDrugsList = ["Select Medication From Dropdown Menu:", "Aspirin", "Penicillin", "Insulin detemir", "Hydromorphone", "Metformin", "Methylergonovine", "Methotrexate ",
    "Gabapentin", "Nitroglycerin", "Oxytocin", "Pantoprazole", "Risperidone", "Methylprednisolone", "Budesonide", "Levothyroxine",
    "Vancomycin", "Piperacillin", "Clopidogrel", "Lithium", "Haloperidol", "Zolpidem", "Esomeprazole", "Amiodarone", "Aripiprazole",
    "Epoetin", "Risedronate", "Pregabalin", "Aspart", "Diltiazem", "Varenicline", "Furosemide", "Levofloxacin", "Atorvastatin",
    "Sildenafil", "Sertraline", "Fentanyl", "Fluticasone", "Propranolol", "Donepezil", "Lisinopril", "Rifampin", "Enoxaparin",
    "Adenosine", "Dutasteride", "Warfarin", "Phenytoin", "Metronidazole", "Gentamicin", "Digoxin", "Memantine", "Oxycodone",
    "Montelukast"
 ];

//this is populating the dropdown with the above array, commonDrugList
 for (let i = 0; i < commonDrugsList.length; i++) {
   let option = document.createElement("option");
   option.value = commonDrugsList[i];
   option.text = commonDrugsList[i];
   resultsDropdown.appendChild(option);
 }

//appending the fields from the API fetch to the p tag.
const createDrugFieldData = (array) => {
console.log("logging array in CreateDrugFieldData", array)
patientPrescriptionsDisplay.innerHTML = ''
array.forEach(obj => {
let drugFields = document.createElement("div")
drugFields.innerHTML = `
<div class="accordion d-grid gap-3" id="accordionExample">
	<div class="accordion-item">
		<h2 class="accordion-header" id="headingOne">
			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-capsule-pill" viewBox="0 0 16 16">
              <path d="M11.02 5.364a3 3 0 0 0-4.242-4.243L1.121 6.778a3 3 0 1 0 4.243 4.243l5.657-5.657Zm-6.413-.657 2.878-2.879a2 2 0 1 1 2.829 2.829L7.435 7.536 4.607 4.707ZM12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8Zm-.5 1.042a3 3 0 0 0 0 5.917V9.042Zm1 5.917a3 3 0 0 0 0-5.917v5.917Z"/>
            </svg>&ensp;Adverse Reactions
        </button>
		</h2>
		<div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
			<div class="accordion-body">
				<p>${obj.adverse_reactions}</p>
			</div>
		</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header" id="headingTwo">
			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-capsule-pill" viewBox="0 0 16 16">
              <path d="M11.02 5.364a3 3 0 0 0-4.242-4.243L1.121 6.778a3 3 0 1 0 4.243 4.243l5.657-5.657Zm-6.413-.657 2.878-2.879a2 2 0 1 1 2.829 2.829L7.435 7.536 4.607 4.707ZM12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8Zm-.5 1.042a3 3 0 0 0 0 5.917V9.042Zm1 5.917a3 3 0 0 0 0-5.917v5.917Z"/>
            </svg>&ensp;Contraindications
        </button>
		</h2>
		<div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
			<div class="accordion-body">
				<p>${obj.contraindications}</p>
			</div>
		</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header" id="headingThree">
			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-capsule-pill" viewBox="0 0 16 16">
              <path d="M11.02 5.364a3 3 0 0 0-4.242-4.243L1.121 6.778a3 3 0 1 0 4.243 4.243l5.657-5.657Zm-6.413-.657 2.878-2.879a2 2 0 1 1 2.829 2.829L7.435 7.536 4.607 4.707ZM12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8Zm-.5 1.042a3 3 0 0 0 0 5.917V9.042Zm1 5.917a3 3 0 0 0 0-5.917v5.917Z"/>
            </svg>&ensp;Drug Interactions
    </button>
		</h2>
		<div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
			<div class="accordion-body">
				<p>${obj.drug_interactions}</p>
			</div>
		</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header" id="headingFour">
			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-capsule-pill" viewBox="0 0 16 16">
              <path d="M11.02 5.364a3 3 0 0 0-4.242-4.243L1.121 6.778a3 3 0 1 0 4.243 4.243l5.657-5.657Zm-6.413-.657 2.878-2.879a2 2 0 1 1 2.829 2.829L7.435 7.536 4.607 4.707ZM12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8Zm-.5 1.042a3 3 0 0 0 0 5.917V9.042Zm1 5.917a3 3 0 0 0 0-5.917v5.917Z"/>
            </svg>&ensp;Indications and Usages
    </button>
		</h2>
		<div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour" data-bs-parent="#accordionExample">
			<div class="accordion-body">
				<p>${obj.indications_and_usage}</p>
			</div>
		</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header" id="headingFive">
			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-capsule-pill" viewBox="0 0 16 16">
              <path d="M11.02 5.364a3 3 0 0 0-4.242-4.243L1.121 6.778a3 3 0 1 0 4.243 4.243l5.657-5.657Zm-6.413-.657 2.878-2.879a2 2 0 1 1 2.829 2.829L7.435 7.536 4.607 4.707ZM12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8Zm-.5 1.042a3 3 0 0 0 0 5.917V9.042Zm1 5.917a3 3 0 0 0 0-5.917v5.917Z"/>
            </svg>&ensp;Mechanism of Action
    </button>
		</h2>
		<div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive" data-bs-parent="#accordionExample">
			<div class="accordion-body">
				<p>${obj.mechanism_of_action || "Mechanism of Action Not Listed"}</p>
			</div>
		</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header" id="headingSix">
			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-capsule-pill" viewBox="0 0 16 16">
              <path d="M11.02 5.364a3 3 0 0 0-4.242-4.243L1.121 6.778a3 3 0 1 0 4.243 4.243l5.657-5.657Zm-6.413-.657 2.878-2.879a2 2 0 1 1 2.829 2.829L7.435 7.536 4.607 4.707ZM12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8Zm-.5 1.042a3 3 0 0 0 0 5.917V9.042Zm1 5.917a3 3 0 0 0 0-5.917v5.917Z"/>
            </svg>&ensp;Dosage and Administration
    </button>
		</h2>
		<div id="collapseSix" class="accordion-collapse collapse" aria-labelledby="headingSix" data-bs-parent="#accordionExample">
			<div class="accordion-body">
				<p>${obj.dosage_and_administration}</p>
			</div>
		</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header" id="headingSeven">
			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-capsule-pill" viewBox="0 0 16 16">
              <path d="M11.02 5.364a3 3 0 0 0-4.242-4.243L1.121 6.778a3 3 0 1 0 4.243 4.243l5.657-5.657Zm-6.413-.657 2.878-2.879a2 2 0 1 1 2.829 2.829L7.435 7.536 4.607 4.707ZM12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8Zm-.5 1.042a3 3 0 0 0 0 5.917V9.042Zm1 5.917a3 3 0 0 0 0-5.917v5.917Z"/>
            </svg>&ensp;Information for Patients
    </button>
		</h2>
		<div id="collapseSeven" class="accordion-collapse collapse" aria-labelledby="headingSeven" data-bs-parent="#accordionExample">
			<div class="accordion-body">
				<p>${obj.information_for_patients}</p>
			</div>
		</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header" id="headingEight">
			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseEight" aria-expanded="false" aria-controls="collapseEight">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-capsule-pill" viewBox="0 0 16 16">
              <path d="M11.02 5.364a3 3 0 0 0-4.242-4.243L1.121 6.778a3 3 0 1 0 4.243 4.243l5.657-5.657Zm-6.413-.657 2.878-2.879a2 2 0 1 1 2.829 2.829L7.435 7.536 4.607 4.707ZM12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8Zm-.5 1.042a3 3 0 0 0 0 5.917V9.042Zm1 5.917a3 3 0 0 0 0-5.917v5.917Z"/>
            </svg>&ensp;Description
    </button>
		</h2>
		<div id="collapseEight" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
			<div class="accordion-body">
				<p>${obj.description}</p>
			</div>
		</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header" id="headingNine">
			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseNine" aria-expanded="false" aria-controls="collapseNine">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-capsule-pill" viewBox="0 0 16 16">
              <path d="M11.02 5.364a3 3 0 0 0-4.242-4.243L1.121 6.778a3 3 0 1 0 4.243 4.243l5.657-5.657Zm-6.413-.657 2.878-2.879a2 2 0 1 1 2.829 2.829L7.435 7.536 4.607 4.707ZM12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8Zm-.5 1.042a3 3 0 0 0 0 5.917V9.042Zm1 5.917a3 3 0 0 0 0-5.917v5.917Z"/>
            </svg>&ensp;Pregnancy Category and/or Teratogenic Effects
        </button>
		</h2>
		<div id="collapseNine" class="accordion-collapse collapse" aria-labelledby="headingNine" data-bs-parent="#accordionExample">
			<div class="accordion-body">
				<p>${obj.pregnancy || obj.teratogenic_effects}</p>
			</div>
		</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header" id="headingTen">
			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTen" aria-expanded="false" aria-controls="collapseTen">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-capsule-pill" viewBox="0 0 16 16">
              <path d="M11.02 5.364a3 3 0 0 0-4.242-4.243L1.121 6.778a3 3 0 1 0 4.243 4.243l5.657-5.657Zm-6.413-.657 2.878-2.879a2 2 0 1 1 2.829 2.829L7.435 7.536 4.607 4.707ZM12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8Zm-.5 1.042a3 3 0 0 0 0 5.917V9.042Zm1 5.917a3 3 0 0 0 0-5.917v5.917Z"/>
            </svg>&ensp;Warnings and Precautions
            </button>
		</h2>
		<div id="collapseTen" class="accordion-collapse collapse" aria-labelledby="headingTen" data-bs-parent="#accordionExample">
			<div class="accordion-body">
				<p>${obj.warnings_and_cautions || obj.warnings}</p>
			</div>
		</div>
	</div>
	<div class="accordion-item">
    		<h2 class="accordion-header" id="headingEleven">
    			<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseEleven" aria-expanded="false" aria-controls="collapseEleven">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-capsule-pill" viewBox="0 0 16 16">
                  <path d="M11.02 5.364a3 3 0 0 0-4.242-4.243L1.121 6.778a3 3 0 1 0 4.243 4.243l5.657-5.657Zm-6.413-.657 2.878-2.879a2 2 0 1 1 2.829 2.829L7.435 7.536 4.607 4.707ZM12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8Zm-.5 1.042a3 3 0 0 0 0 5.917V9.042Zm1 5.917a3 3 0 0 0 0-5.917v5.917Z"/>
                </svg>&ensp;Boxed Warnings
                </button>
    		</h2>
    		<div id="collapseEleven" class="accordion-collapse collapse" aria-labelledby="headingEleven" data-bs-parent="#accordionExample">
    			<div class="accordion-body">
    				<p>${obj.boxed_warning || "No Boxed Warnings"}</p>
    			</div>
    		</div>
    	</div>
</div>
`
patientPrescriptionsDisplay.append(drugFields);
})
}

