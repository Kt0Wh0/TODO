document.addEventListener("DOMContentLoaded", () => {
    const userId = localStorage.getItem("userId"); // ид пользователя
    const projectsList = document.getElementById("projectList");

    fetch(`http://localhost:8080/project/${userId}`)
        .then(response => response.json())
        .then(data => {
            data.forEach(project => {
                const projectElement = document.createElement("div");
                projectElement.classList.add("project");

                // удаления
                const deleteBtn = document.createElement("span");
                deleteBtn.innerHTML = "&#10060;";
                deleteBtn.classList.add("delete-btn");
                deleteBtn.onclick = () => deleteProject(project.id, projectElement);

                // обнова
                const updBtn = document.createElement("span");
                updBtn.innerHTML = "🔄";
                updBtn.classList.add("upd-btn");
                updBtn.onclick = () => openProjectUpdForm(project.id) ;

                // добавить задачу
                const addTask = document.createElement("span");
                addTask.innerHTML = "➕";
                addTask.classList.add("add-task");
                addTask.onclick = () => openTaskAdd(project.id) ;

                projectElement.ondblclick = () => loadProjectTasks(project.id, projectElement);

                projectElement.innerHTML = `
                    <h2>${project.name}</h2>
                    <p><strong>Описание:</strong> ${project.description || "Нет описания"}</p>
                    <p><strong>Дата начала:</strong> ${project.startDate || "Не указано"}</p>
                    <p><strong>Дата окончания:</strong> ${project.endDate || "Не указано"}</p>
                `;
                projectElement.appendChild(deleteBtn);
                projectElement.appendChild(updBtn);
                projectElement.appendChild(addTask);
                projectsList.appendChild(projectElement);
            });
        })
        .catch(error => console.error("Ошибка загрузки проектов:", error));
});


function deleteProject(projectId, projectElement) {
    fetch(`http://localhost:8080/project/${projectId}`, {
        method: "DELETE",
    })
        .then(response => {
            if (response.ok) {
                projectElement.remove(); // Удаляем блок из DOM
            } else {
                console.error("Ошибка удаления проекта");
            }
        })
        .catch(error => console.error("Ошибка при удалении:", error));
}

function openProjectForm() {
    document.getElementById("modal").style.display = "block";
}

function closeProjectForm() {
    document.getElementById("modal").style.display = "none";
}

function openProjectUpdForm(projectId) {
    document.getElementById("updateProjectId").value = projectId; // Сохраняем ID
    document.getElementById("modalUpd").style.display = "block";
}

function closeProjectUpdForm() {
    document.getElementById("modalUpd").style.display = "none";
}

function openTaskAdd(projectId) {
    console.log("1 " + projectId)
    document.getElementById("ProjectId").value = projectId;
    document.getElementById("addTask").style.display = "block";
}

function closeTaskAdd() {
    document.getElementById("addTask").style.display = "none";
}



function saveProject() {
    const userId = localStorage.getItem("userId");
    let name = document.getElementById("name").value;
    let description = document.getElementById("description").value;
    let startDate = document.getElementById("startDate").value;
    let endDate = document.getElementById("endDate").value;

    let project = {
        name: name,
        description: description,
        startDate: startDate,
        endDate: endDate
    };

    console.log("Добавлен проект:", project); // Проверка в консоли

    fetch(`http://localhost:8080/project/${userId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(project)
    }).then(response => response.json()).then(data => console.log("Проект сохранен", data));

    closeProjectForm(); // Закрываем окно
}

function updateProject() {
    const id = document.getElementById("updateProjectId").value;
    let name = document.getElementById("nameUpd").value;
    let description = document.getElementById("descriptionUpd").value;
    let startDate = document.getElementById("startDateUpd").value;
    let endDate = document.getElementById("endDateUpd").value;

    let project = {
        name: name,
        description: description,
        startDate: startDate,
        endDate: endDate
    };

    fetch(`http://localhost:8080/project/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(project)
    }).then(response => response.json()).then(data => console.log("Проект обновлен", data));

    closeProjectUpdForm();

}

function loadProjectTasks(projectId, projectElement) {
    fetch(`http://localhost:8080/task/${projectId}`)
        .then(response => response.json())
        .then(tasks => {
            let tasksContainer = projectElement.querySelector(".tasks");

            // Если контейнер уже есть — просто показываем/скрываем
            if (tasksContainer) {
                tasksContainer.style.display = tasksContainer.style.display === "none" ? "block" : "none";
                return;
            }

            // контейнер
            tasksContainer = document.createElement("div");
            tasksContainer.classList.add("tasks");

            if (tasks.length === 0) {
                tasksContainer.innerHTML = "<p>Нет задач</p>";
            } else {
                tasks.forEach(task => {
                    const taskElement = document.createElement("div");
                    taskElement.classList.add("task");

                    // контейнер задачи
                    const taskContent = document.createElement("div");
                    taskContent.classList.add("task-content");
                    taskContent.innerHTML = `
                        <h3>${task.name}</h3>
                        <p><strong>Описание:</strong> ${task.description || "Нет описания"}</p>
                        <p><strong>Дата завершения:</strong> ${task.endData || "Не указано"}</p>
                    `;

                    //  удаления
                    const deleteTaskBtn = document.createElement("span");
                    deleteTaskBtn.innerHTML = "&#10060;";
                    deleteTaskBtn.classList.add("deleteTask-btn");
                    deleteTaskBtn.onclick = () => deleteTask(task.id, taskElement);

                    taskElement.appendChild(taskContent);
                    taskElement.appendChild(deleteTaskBtn);

                    tasksContainer.appendChild(taskElement);
                });

            }

            projectElement.appendChild(tasksContainer);
        })
        .catch(error => console.error("Ошибка при загрузке задач:", error));
}

function addTask() {
    const projectId = document.getElementById("ProjectId").value;
    let name = document.getElementById("nameTask").value;
    let description = document.getElementById("descriptionTask").value;
    let endDate = document.getElementById("endDateTask").value;
    console.log("2" + projectId)
    let task = {
        name: name,
        description: description,
        endData: endDate
    };

    console.log("Добавлена задача:", task);

    fetch(`http://localhost:8080/task/${projectId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(task)
    }).then(response => response.json()).then(data => console.log("Задача сохранена", data));

    closeTaskAdd(); // Закрываем окно
}

function deleteTask(projectId, taskElement) {
    fetch(`http://localhost:8080/task/${projectId}`, {
        method: "DELETE",
    })
        .then(response => {
            if (response.ok) {
                taskElement.remove();
            } else {
                console.error("Ошибка удаления задачт");
            }
        })
        .catch(error => console.error("Ошибка при удалении:", error));
}

