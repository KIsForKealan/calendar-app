function doPostName() {

    const inputString = document.getElementById("input-demo").value;
    const element = JSON.stringify({ name: inputString });
    console.log("element : "+element)

    const requestOptions = {
        method: "GET",
        headers: {
            "Access-Control-Allow-Headers": "Origin, Content-Type",
            "Access-Control-Allow-Origin": "http://localhost:8080",
            "Access-Control-Allow-Methods": "HEAD, GET, POST, PUT, PATCH, DELETE"
        }
    };

    fetch("http://localhost:8080/api/hello?name="+inputString, requestOptions)
        .then(response => response.json())
        .then(function (data) {

            console.log(JSON.stringify(data));
            document.getElementById("results").innerHTML = "";

            Object.entries(data).forEach((entry) => {
                const [key, value] = entry;
                console.log(`${key}: ${value}`);

                var _p = document.createElement("p");
                _p.innerHTML = `${key}: ${value}`;
                document.getElementById("results").append(_p);
            });

        })
        .catch(err => console.log(err));
}