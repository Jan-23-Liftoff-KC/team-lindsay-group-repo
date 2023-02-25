const appointmentsForm = document.getElementById('appointments-form')
const appointmentsDoctorName = document.getElementById('appointments-doctorname')
const appointmentsPassword = document.getElementById('appointments-password')

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/appointments"

const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        doctorName: appointmentsDoctorName.value,
        password: appointmentsPassword.value
    }
    
        const response = await fetch(`${baseUrl}/appointments`, {
            method: "POST",
            body: JSON.stringify(bodyObj),
            headers: headers
        })
        .catch(err => console.error(err.message))
    
        const responseArr = await response.json()
    
        if(response.status === 200){
            window.location.replace(responseArr[0])
        }
    }
    
    registerForm.addEventListener("submit", handleSubmit)