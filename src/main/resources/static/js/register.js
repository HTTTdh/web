const API_URL = 'http://localhost:8080/api/auth';

document.getElementById('registerForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const response = await fetch(`${API_URL}/signup`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, email, password }),
        credentials: 'include'
    });

    if (response.ok) {
        alert("Successful!");
        window.location.href = "/login";
    } else {
        const error = await response.text();
        alert("Failed: " + error);
    }
});
