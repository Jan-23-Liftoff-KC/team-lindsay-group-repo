const registerForm = document.getElementById('register-form')
const registerDoctorName = document.getElementById('register-doctorname')
const registerPassword = document.getElementById('register-password')

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/doctors"

const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        doctorName: registerDoctorName.value,
        password: registerPassword.value
    }

    const response = await fetch(`${baseUrl}/register`, {
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
