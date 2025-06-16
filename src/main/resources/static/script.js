const API_URL = "http://localhost:9090/employees";

document.addEventListener("DOMContentLoaded", () => {
    checkLoginStatus();
});

function checkLoginStatus() {
    fetch(API_URL, { credentials: 'include' })
        .then(res => {
            if (res.status === 200) {
                showApp();
                fetchEmployees();
            } else {
                showLogin();
            }
        });
}

function login() {
    const formData = new URLSearchParams();
    formData.append('username', document.getElementById("username").value);
    formData.append('password', document.getElementById("password").value);

    fetch("http://localhost:9090/login", {
        method: "POST",
        body: formData,
        credentials: "include"
    }).then(res => {
        if (res.ok) {
            showApp();
            fetchEmployees();
        } else {
            document.getElementById("login-error").textContent = "Invalid credentials.";
        }
    });
}

function logout() {
    fetch("http://localhost:9090/logout", {
        method: "POST",
        credentials: "include"
    }).then(() => {
        showLogin();
    });
}

function showApp() {
    document.getElementById("login-section").style.display = "none";
    document.getElementById("app-section").style.display = "block";
}

function showLogin() {
    document.getElementById("login-section").style.display = "block";
    document.getElementById("app-section").style.display = "none";
}

function fetchEmployees() {
    fetch(API_URL, { credentials: "include" })
        .then(res => res.json())
        .then(data => {
            const list = document.getElementById("employee-list");
            list.innerHTML = "";
            data.forEach(emp => {
                const div = document.createElement("div");
                div.className = "employee";
                div.innerHTML = `
                    <strong>ID: ${emp.id} - ${emp.name}</strong><br>
                    Email: ${emp.email}<br>
                    Department: ${emp.department}<br>
                    Salary: $${emp.salary}<br>
                    <button onclick="editEmployee(${emp.id})">Edit</button>
                    <button onclick="deleteEmployee(${emp.id})">Delete</button>
                `;
                list.appendChild(div);
            });
        });
}

function submitEmployee() {
    const id = document.getElementById("employee-id").value;
    const employee = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        department: document.getElementById("department").value,
        salary: parseFloat(document.getElementById("salary").value)
    };

    const method = id ? "PUT" : "POST";
    const url = id ? `${API_URL}/${id}` : API_URL;

    fetch(url, {
        method: method,
        headers: { "Content-Type": "application/json" },
        credentials: "include",
        body: JSON.stringify(employee)
    }).then(() => {
        clearForm();
        fetchEmployees();
    });
}

function editEmployee(id) {
    fetch(`${API_URL}/${id}`, { credentials: "include" })
        .then(res => res.json())
        .then(emp => {
            document.getElementById("employee-id").value = emp.id;
            document.getElementById("name").value = emp.name;
            document.getElementById("email").value = emp.email;
            document.getElementById("department").value = emp.department;
            document.getElementById("salary").value = emp.salary;
        });
}

function deleteEmployee(id) {
    fetch(`${API_URL}/${id}`, {
        method: "DELETE",
        credentials: "include"
    }).then(() => fetchEmployees());
}

function clearForm() {
    document.getElementById("employee-id").value = "";
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("department").value = "";
    document.getElementById("salary").value = "";
}
