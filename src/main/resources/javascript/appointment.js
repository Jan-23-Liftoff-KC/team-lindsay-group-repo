const appointmentForm = document.getElementById('appointment-form')
const appointmentDoctorName = document.getElementById('appointment-doctorname')
const appointmentPassword = document.getElementById('appointment-password')

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/appointment"

const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        doctorName: appointmentDoctorName.value,
        password: appointmentPassword.value
    }

        const response = await fetch(`${baseUrl}/appointment`, {
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