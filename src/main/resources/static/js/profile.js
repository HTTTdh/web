function saveProfile(){
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    console.log(username + "  " + email);
    const response =  fetch(`http://localhost:8080/profile/update`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({username: username, email: email, password: password}),
        credentials: 'include'
    })
        .then(response => {
            if (response.ok) {
                alert("Update successful!");
                window.location.href = `/profile`;
            } else {
                return response.text().then(text => {
                    alert("Failed: " + text);
                });
            }
        })
        .catch(error => {
            alert("Request error: " + error.message);
        });
}