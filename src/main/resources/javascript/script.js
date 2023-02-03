window.addEventListener("load", function () {
    let url = "https://api.fda.gov/drug/label.json?search="
    let fetchPromise = fetch(url);

let astroFirstName;

    fetchPromise.then(function (response) {
        response.json().then(function(data){
            console.log(data);

            const container = document.getElementById("container");

            for (i=0; i < data.length; i++){
                container.innerHTML= '${data[i]}'
                console.log(data[i])
            }

        });
    })


})