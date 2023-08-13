const subjects = ["telugu", "hindi", "english", "maths", "science", "social"];
const students = [
  {
    id: 102,
    firstName: "John",
    lastName: "Doe",
    class: "IX",
    marks: [
      {
        subject: "Telugu",
        marks: 56,
      },
      {
        subject: "Hindi",
        marks: 65,
      },
      {
        subject: "English",
        marks: 56,
      },
      {
        subject: "Maths",
        marks: 76,
      },
      {
        subject: "Science",
        marks: 56,
      },
      {
        subject: "Social",
        marks: 56,
      },
    ],
  },
  {
    id: 125,
    firstName: "Ram",
    lastName: "G",
    class: "X",
    marks: [
      {
        subject: "Telugu",
        marks: 65,
      },
      {
        subject: "Hindi",
        marks: 78,
      },
      {
        subject: "English",
        marks: 69,
      },
      {
        subject: "Maths",
        marks: 76,
      },
      {
        subject: "Science",
        marks: 56,
      },
      {
        subject: "Social",
        marks: 56,
      },
    ],
  },
];

function toggleRegForm(containerId) {
  console.log("toggleRegForm called...");
  let regFormContainer = document.getElementById(containerId);
  regFormContainer.style.display =
    regFormContainer.style.display == "none" ? "block" : "none";
}
const stdRegForm = document.querySelector("#stdRegForm");

stdRegForm.addEventListener("submit", (event) => {
  event.preventDefault();

  const formData = new FormData(event.target);
  const newStudent = createStudent(formData);

  students.push(newStudent);
  udpateStudentTbl(newStudent);
  console.log(newStudent);
  stdRegForm.reset();
});

function createStudent(formData) {
  const student = {};
  const marks = [];
  for (const pair of formData.entries()) {
    const key = `${pair[0]}`;
    const value = `${pair[1]}`;
    if (subjects.includes(key)) {
      marks.push({
        subject: key,
        marks: value,
      });
    } else {
      if (key === "rollNum") {
        student["id"] = value;
      } else {
        student[key] = value;
      }
    }
  }
  student.marks = marks;
  console.log(`new student details from form-data: ${JSON.stringify(student)}`);
  return student;
}

function getSubjectMarks(student, subj) {
  const subjDetails = student.marks.find(
    (subjMarks) => subjMarks.subject.toLowerCase() === subj.toLowerCase()
  );
  if (subjDetails) {
    return subjDetails.marks;
  }
  console.log(
    `${subj} Marks not found on student obj:${JSON.stringify(student)}`
  );
  return -1;
}

function udpateStudentTbl(newStudent) {
  document.getElementById("stdRows").innerHTML =
    document.getElementById("stdRows").innerHTML + createStdRow(newStudent);
}
function createStdRow(student) {
  return `<tr><td>${student.id}</td>
      <td>${student.firstName + " " + student.lastName}</td>
      <td>${student.class}</td>
      <td>${getSubjectMarks(student, "telugu")}</td>
      <td>${getSubjectMarks(student, "hindi")}</td>
      <td>${getSubjectMarks(student, "english")}</td>
      <td>${getSubjectMarks(student, "maths")}</td>
      <td>${getSubjectMarks(student, "science")}</td>
      <td>${getSubjectMarks(student, "social")}</td>
      <td>${calculateTotalMarks(student)}</td>
      <td>${calculateStudentGrade(student)}</td>
      <td><img src='./images/edit_icon.png' class='actionImage editIcon' data-arg1='${
        student.id
      }'/>
      <img src='./images/delete_icon.png' class='actionImage deleteIcon' onClick="deleteStudent(${
        student.id
      })"/></td>
      </tr>`;
}
function deleteStudent(rollNum) {
  confirm(`are you sure to delete Student with Roll No:${rollNum}?`);
}
function calculateTotalMarks(student) {
  return student.marks.reduce(
    (accumulator, subjDetails) =>
      Number(accumulator) + Number(subjDetails.marks),
    0
  );
}
function calculateStudentGrade(student) {
  const isStdFailed = student.marks.find(
    (subjDetails) => subjDetails.marks < 35
  );
  if (isStdFailed) {
    return "Fail";
  } else {
    const totalMarksPercent = calculateTotalMarks(student) / subjects.length;
    if (totalMarksPercent >= 75) {
      return "Distinction";
    } else if (totalMarksPercent >= 60) {
      return "First Class";
    } else {
      return "Second Class";
    }
  }
}

function dispalyStudentsWithString() {
  let stdRowsData = "";
  if (students.length > 0) {
    students.forEach((student) => {
      stdRowsData += createStdRow(student);
    });
    document.getElementById("stdRows").innerHTML = stdRowsData;
  }
}

document.addEventListener("DOMContentLoaded", function () {
  dispalyStudentsWithString();

  document.querySelectorAll(".editIcon").forEach((editIcon) =>
    editIcon.addEventListener("click", (event) => {
      console.log(`edit Icon is clicked...`);
      alert(
        `Edit Icon is clicked for Roll Num:
      ${event.target.getAttribute("data-arg1")}`
      );
    })
  );
});
