let loginForm = document.getElementById('login-form')
let loginDoctorName = document.getElementById('login-doctorname')
let loginPassword = document.getElementById('login-password')

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/doctors"

const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        doctorName: loginDoctorName.value,
        password: loginPassword.value
    }

    const response = await fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

       const responseArr = await response.json()

       if(response.status === 200){
        document.cookie = `doctorId=${responseArr[1]}`
        window.location.replace(responseArr[0])
       }
    }

loginForm.addEventListener("submit", handleSubmit)