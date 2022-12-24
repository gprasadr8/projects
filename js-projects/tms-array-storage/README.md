# Todo Management System (TMS) or Todo App

This repository contains the plain javascript code for todo app crud operations.

## Workflow:

- Simple Todo form is displayed with two properties:
     1. task name
     2. due date (today or tomorrow)

- once user submits the Add todo form then new todo object is created on javascript with below properties:

```javascript
let new_todo = {
      id: sequence_number,
      task: task_name,
      due_date: today or tomorrow,
      isCompleted: true or false      
}

```

- We maintain global array to manage submitted todos.
- we add newly added todo to the global array and display all the todos on the table.
- On each row three action buttons(edit, completed, delete) will be displayed.

#### Edit Action:

- When user clicks on edit button then selected todo's data will be populated on Add Todo Form.
- Once user modify and submit the form then respective todo will be updated and todo list table will be reloaded

#### Complete Action:

- When user clicks on completed button then `isCompleted` property on selected todo object is marked as `true` and reload the todo list table 
- All the completed tasks will be displayed on the botton of the table with highlighted color.


#### Delete Action:

- When user clicks on delete button then will find the respective todo from global array and remove it.
- After that reload todo list table with existing todos.

      
