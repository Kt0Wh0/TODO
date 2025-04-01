const API_URL = "http://localhost:8080/person"; // Адрес бэкенда

async function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const response = await fetch(`${API_URL}/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ name: username, pass: password })
    });

    if (response.ok) {
        const responseData = await response.json();
        localStorage.setItem("userId", responseData.id); //id
        window.location.href = "projects.html";
        document.getElementById("message").textContent = "Все кул";
    } else {
        document.getElementById("message").textContent = "Ошибка входа";
    }
}

async function register() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const response = await fetch(`${API_URL}/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ name: username, pass: password })
    });

    if (response.ok) {
        const responseData = await response.json();
        localStorage.setItem("userId", responseData.id);  // id
        document.getElementById("message").textContent = "Успешная регистрация! Теперь войдите.";
        window.location.href = "projects.html";
    } else {
        document.getElementById("message").textContent = "Ошибка регистрации!";
    }
}
