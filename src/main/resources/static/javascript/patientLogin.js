let loginForm = document.getElementById('patient-login')
let loginDoctorName = document.getElementById('login-patientName')
let loginPassword = document.getElementById('login-password')

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/patients"

const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        patientName: loginDoctorName.value,
        password: loginPassword.value
    }

    const response = await fetch(`${baseUrl}/patientLogin`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

       const responseArr = await response.json()

       if(response.status === 200){
        document.cookie = `patientId=${responseArr[1]}`
        window.location.replace(responseArr[0])
       }
    }

loginForm.addEventListener("submit", handleSubmit)