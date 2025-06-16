const API_URL = 'http://localhost:8080/api/auth';

document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const response = await fetch(`${API_URL}/signin`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password }),
        credentials: 'include'
    });

    if (response.ok) {
        alert("Login successful!");
        window.location.href = "/home";
    } else {
        const error = await response.text();
        alert("Login failed: " + error);
    }
});
