// based on the type of the user, show the appropriate UI
$.ajax({
  url: 'http://localhost:8444/e-diary/users/type',
  type: 'GET',
  crossDomain: true,
  headers: {
    'User-ID': 1,
  },
  success: function(data) {
    console.log('Success!');
    console.log(data);
    if (data === 'ADMIN') {
        console.log('admin-ui')
        $('#student-ui').hide();
        $('#parent-ui').hide();
        $('#instructor-ui').hide();
        $('#admin-ui').show();
    } else if (data === 'STUDENT') {
        console.log('student-ui')
        $('#parent-ui').hide();
        $('#instructor-ui').hide();
//        $('#admin-ui').hide();
        $('#student-ui').show();
    } else if (data === 'PARENT') {
        console.log('parent-ui')
        $('#instructor-ui').hide();
        $('#admin-ui').hide();
        $('#student-ui').hide();
        $('#parent-ui').show();
    } else if (data === 'INSTRUCTOR') {
        console.log('instructor-ui')
        $('#admin-ui').hide();
        $('#student-ui').hide();
        $('#parent-ui').hide();
        $('#instructor-ui').show();
    }
  },
  error: function(error) {
    console.log('Error!');
    console.log(error);
  }
});


async function fetchRequest(endpoint) {
  return $.ajax({
    url: `http://localhost:8444/e-diary${endpoint}`,
    type: 'GET',
    crossDomain: true,
    headers: {
        "USER-ID": 1,
    },
    success: function(data) {
        return data
    },
    error: function(error) {
      return error
    }
  });
}

// register a user
function registerUser() {
    $('#registerUserBtn').hide();
    $('#registerSubjectBtn').hide();
    $('#registerCourseBtn').hide();
    $('#registerFormClassBtn').hide();
    $('#enrollStudentBtn').hide();
    $('#registerUser').show();
}

function submitRegisterUser() {
    let username = $('#username').val();
    let name = $('#name').val();
    let surname = $('#surname').val();
    let address = $('#address').val();
    let phoneNumber = $('#phoneNumber').val();
    let birthDate = $('#birthDate').val();
    let type = $('#type').val();

    $.ajax({
      url: 'http://localhost:8444/e-diary/users',
      type: 'POST',
      crossDomain: true,
      headers: {
        'User-ID': 1,
      },
      contentType: 'application/json',
      data: JSON.stringify({
        username, name, surname, address, phoneNumber, birthDate, type
      }),
      success: function(data) {
        console.log('Success!');
        console.log(data);
      },
      error: function(error) {
        console.log('Error!');
        console.log(error);
      }
    });
}

// register a subject
function registerSubject() {
    $('#registerSubjectBtn').hide();
    $('#registerUserBtn').hide();
    $('#registerCourseBtn').hide();
    $('#registerFormClassBtn').hide();
    $('#enrollStudentBtn').hide();
    $('#registerSubject').show();
}

function submitRegisterSubject() {
    let subjectName = $('#subjectName').val();

    $.ajax({
      url: 'http://localhost:8444/e-diary/subjects',
      type: 'POST',
      crossDomain: true,
      contentType: 'application/json',
      data: JSON.stringify({
        subjectName
      }),
      success: function(data) {
        console.log('Success!');
        console.log(data);
      },
      error: function(error) {
        console.log('Error!');
        console.log(error);
      }
    });
}

// register a course
function registerCourse() {
    $('#registerSubjectBtn').hide();
    $('#registerUserBtn').hide();
    $('#registerCourseBtn').hide();
    $('#registerFormClassBtn').hide();
    $('#enrollStudentBtn').hide();
    $('#registerCourse').show();
}

function populateSubjects() {
    let res = fetchRequest('/subjects')
        .then((data) => {
            populateSelect(
            "#subjectId",
             "Select a subject",
             "name",
              data
            )
        })
        .catch((e) => {
        });

}

function submitRegisterCourse() {
    let crn = $('#crn').val();
    let weekday = $('#weekday').val();
    let startTime = $('#startTime').val();
    let endTime = $('#endTime').val();
    let roomNumber = $('#roomNumber').val();
    let subjectId = $('#subjectId').val();

    $.ajax({
      url: 'http://localhost:8444/e-diary/courses',
      type: 'POST',
      crossDomain: true,
      headers: {
        'USER-ID': 1,
      }
      contentType: 'application/json',
      data: JSON.stringify({
        crn, weekday, startTime, endTime, roomNumber, subjectId
      }),
      success: function(data) {
        console.log('Success!');
        console.log(data);
      },
      error: function(error) {
        console.log('Error!');
        console.log(error);
      }
    });
}

// register a form class
function registerFormClass() {
    $('#registerSubjectBtn').hide();
    $('#registerUserBtn').hide();
    $('#registerCourseBtn').hide();
    $('#registerFormClassBtn').hide();
    $('#enrollStudentBtn').hide();
    $('#registerFormClass').show();
}

function populateInstructors() {
    let res = fetchRequest('/users/instructors')
        .then((data) => {
            populateSelect(
            "#formTutor",
             "Select an instructor",
             "id",
              data
            )
        })
        .catch((e) => {
        });

}

$(document).ready(function() {
  populateInstructors();
  populateSubjects();
  populateFormClasses();
  populateStudents();
});

function submitRegisterFormClass() {
    let year = $('#year').val();
    let identifier = $('#identifier').val();
    let formTutor = $('#formTutor').val();
    let roomNumber_FormClass = $('#roomNumber_FormClass').val();

    $.ajax({
      url: 'http://localhost:8444/e-diary/classes',
      type: 'POST',
      crossDomain: true,
      headers: {
        'User-ID': 1,
      },
      contentType: 'application/json',
      data: JSON.stringify({
        year, identifier, formTutor, roomNumber_FormClass
      }),
      success: function(data) {
        console.log('Success!');
        console.log(data);
      },
      error: function(error) {
        console.log('Error!');
        console.log(error);
      }
    });
}

// enroll a student to a class
function enrollStudent() {
    $('#registerSubjectBtn').hide();
    $('#registerUserBtn').hide();
    $('#registerCourseBtn').hide();
    $('#registerFormClassBtn').hide();
    $('#enrollStudentBtn').hide();
    $('#enrollStudent').show();
}


function populateFormClasses() {
    let res = fetchRequest('/classes')
        .then((data) => {
            populateSelect(
            ".formClassList",
             "Select a class",
             "identifier",
              data
            )
        })
        .catch((e) => {
        });

}

function populateStudents() {
    let res = fetchRequest('/users/students')
        .then((data) => {
            populateSelect(
            "#studentsList",
             "Choose a student",
             "username",
              data
            )
        })
        .catch((e) => {
        });

}

function populateSelect(id, label, key, items) {
      $(id).empty();
      $(id).append(`<option value="">${label}</option>`);
      $(id).append('<option value="-1">null</option>');
      $.each(items, function(index, item) {
        $(id).append('<option value="' + item.id + '">' + item[key] + '</option>');
      });

}

function submitEnrolledStudent() {
    let formClass = $('.formClassList').val();
    let student = $('#studentsList').val();

    $.ajax({
      url: `http://localhost:8444/e-diary/classes/${formClass}/student/${student}`,
      type: 'PUT',
      crossDomain: true,
      headers: {
        'User-ID': 1,
      },
      contentType: 'application/json',
      data: JSON.stringify({
        formClass, student
      }),
      success: function(data) {
        console.log('Success!');
        console.log(data);
      },
      error: function(error) {
        console.log('Error!');
        console.log(error);
      }
    });
}

