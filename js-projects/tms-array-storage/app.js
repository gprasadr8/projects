// Global Event is deprecated so when we are adding eventListener we can add event param to the function
// todo object properties:
// {id:0, task:task description, due_date: today/tomorrow, isCompleted:true}
const todos = [];
let todoId = 0;
document
  .getElementById("addTodoForm")
  .addEventListener("submit", function (event) {
    event.preventDefault();
    const errors = validateForm();
    if (errors.length) {
      let errorMsgs = "<ul>";
      errors.forEach((error) => (errorMsgs += `<li>${error}</li>`));
      errorMsgs += "</ul>";
      document.getElementById("errorMsg").innerHTML = errorMsgs;
    } else {
      addTodo();
      resetTodoForm();
    }
  });
function validateForm() {
  const errors = [];
  if (!document.getElementById("todo_desc").value) {
    errors.push("Task Name is Empty.");
  }
  const selected_due_date = document.querySelector(
    'input[name="due_date"]:checked'
  );
  if (!selected_due_date) {
    errors.push("Please select due date.");
  }
  return errors;
}
function addTodo() {
  let new_todo = getFormData();
  console.log("new_todo" + JSON.stringify(new_todo));
  if (new_todo.id) {
    console.log("todoId value is present" + new_todo.id);
    updateTodoObj(new_todo);
  } else {
    console.log("todoId value is not present: ");
    new_todo.id = todoId++;
    todos.push(new_todo);
    updateTbody(new_todo);
  }
  displayTodos(true);
}

function getFormData() {
  let due_date = getSlectedDueDate();

  let new_todo = {
    id: document.getElementById("todoId").value,
    task: document.getElementById("todo_desc").value,
    due_date: due_date,
    isCompleted: false,
  };
  return new_todo;
}

function updateTodoObj(updatedTodo) {
  let oldTodo = todos.find((todo) => todo.id == updatedTodo.id);
  oldTodo["task"] = updatedTodo.task;
  oldTodo["due_date"] = updatedTodo.due_date;
}

function getSlectedDueDate() {
  let selectedDueDate;
  document.getElementsByName("due_date").forEach((radioBtn) => {
    if (radioBtn.checked) {
      selectedDueDate = radioBtn.value;
    }
  });
  return selectedDueDate;
}

function displayTodos(reloadTbody) {
  console.log("Todos: " + JSON.stringify(todos));
  if (reloadTbody) {
    document.getElementById("todo_list_body").innerHTML = "";
  }
  todos.sort((todo1, todo2) => {
    if (!todo1.isCompleted && !todo2.isCompleted) {
      return todo1.id - todo2.id;
    }
    if (todo1.isCompleted && !todo2.isCompleted) {
      return 1;
    }
    if (!todo1.isCompleted && todo2.isCompleted) {
      return -1;
    }
    return 0;
  });
  todos.forEach((todo) => {
    updateTbody(todo);
  });
}

function updateTbody(todo) {
  if (todo != null) {
    let row = `<tr class=${todo.isCompleted ? "completedTodo" : ""}>
    <td>${todo.task}</td>
    <td>${todo.isCompleted ? "Completed" : todo["due_date"]}</td>
    <td>
    <button id='editTodoBtn' todoId=${todo.id} onclick='editTodo(${
      todo.id
    })' class=${todo.isCompleted ? "hideBtn" : "showBtn"}>edit</button>
    <button id='completeTodoBtn' todoId=${todo.id} onclick='completeTodo(${
      todo.id
    })' class=${todo.isCompleted ? "hideBtn" : "showBtn"}>completed</button>
    <button id='deleteTodoBtn' todoId=${todo.id} onclick='deleteTodo(${
      todo.id
    })' class=${todo.isCompleted ? "hideBtn" : "showBtn"}>delete</button>    
    </td>
    </tr>`;
    document.getElementById("todo_list_body").innerHTML += row;
  }
}

displayTodos(false);

function resetTodoForm() {
  document.getElementById("addTodoForm").reset();
  document.getElementById("errorMsg").innerHTML = "";
}

function editTodo(todoId) {
  populateEditForm(todoId);
}

function completeTodo(todoId) {
  todos.find((todo) => todo.id == todoId).isCompleted = true;
  displayTodos(true);
}

function deleteTodo(todoId) {
  let todoIndex;
  for (i = 0; i < todos.length; i++) {
    if (todos[i].id == todoId) {
      todos.splice(i, 1);
      break;
    }
  }
  displayTodos(true);
}

function populateEditForm(todoId) {
  let selectedTodo = todos.find((todo) => todo.id == todoId);
  console.log("selected Todo: " + JSON.stringify(selectedTodo));
  document.getElementById("todoId").value = selectedTodo.id;
  document.getElementById("todo_desc").value = selectedTodo.task;
  document.getElementsByName("due_date").forEach((radioBtn) => {
    if (radioBtn.value == selectedTodo.due_date) {
      radioBtn.checked = true;
    }
  });
}
