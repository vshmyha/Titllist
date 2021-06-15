function configPropertyOption(command, selectionContainer, parameter) {
    $.getJSON('controller?command=' + command, function (result) {
        status = result.status;
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    let id = field.id;
                    let property = parameter(field);
                    selectionContainer.append("<option class='option' data_id='" + id + "' value='" + id + "'>" + property + "</option>");
                });
            }
        }
    })
};

configPropertyOption(
    'getAllTypesCommand',
    $('#typeSelection'),
    function (field) {
        return field.typeName;
    }
);
configPropertyOption(
    'getAllGenresCommand',
    $('#genreSelection'),
    function (field) {
        return field.genreName;
    }
);


function showModalWindow(div) {
    div.style.display = "block";
};

function closeModalWindow(div) {
    div.style.display = "none";
};

let adminModal = document.getElementById("adminModal");
let adminSettingBtn = document.getElementById("adminSetting");
let adminSpan = document.getElementsByClassName("closeWindow")[0];

adminSettingBtn.onclick = function () {
    showModalWindow(adminModal);
};

adminSpan.onclick = function () {
    closeModalWindow(adminModal);

};

let addAnime = document.getElementById("addAnime");
let addAnimeBody = document.getElementById("addAnimeBody");
let users = document.getElementById("users");
let usersBody = document.getElementById("usersBody");
let tableUsers = $('#userTable');

$.getJSON('controller?command=getUsersAndRoles', function (result) {
    status = result.status;
    if (status != null) {
        if (status === 'OK') {
            $.each(result.value, function (i, field) {
                let id = field.id;
                let role = field.role;
                let userName = field.userName;
                tableUsers.append("<tr> <td id='" + id + "'>" + userName + "</td> <td>" + role + "</td> <td><button class='roleChanger' id='" + id + "'>Change Role</button></td></tr> ");
            });
        } else if (status === 'NEW_PAGE') {
            alert("Your rights were changed by the administrator, now you will be redirected to the login page.");
        } else if (status === 'ERROR') {
            $('#errorMessagePlace').html(result.value);
        } else {
            alert("Unknown response");
        }
    }
});




function changeDivStyle(div) {
    if (div.style.display === "none") {
        div.style.display = "block";
    } else {
        div.style.display = "none";
    }
};

addAnime.onclick = function () {
    if (usersBody.style.display === "block") {
        changeDivStyle(usersBody);
    }
    changeDivStyle(addAnimeBody);
};

users.onclick = function () {
    if (addAnimeBody.style.display === "block") {
        changeDivStyle(addAnimeBody);
    }
    changeDivStyle(usersBody);
};


let modal = document.getElementById("modal");
let settingBtn = document.getElementById("setting");
let span = document.getElementsByClassName("close")[0];

settingBtn.onclick = function () {
    showModalWindow(modal);
};

span.onclick = function () {
    closeModalWindow(modal);
};

window.onclick = function (event) {
    if (event.target === modal) {
        closeModalWindow(modal);
    } else if (event.target === adminModal) {
        closeModalWindow(adminModal);
    }
};

let changePasswordForm = $('#changePasswordForm');
let isValid = false;
changePasswordForm.submit(function () {
    let currentPasswordValue = document.getElementById("currentPasswordField").value;
    let newPasswordValue = document.getElementById("newPasswordField").value;
    let repeatNewPasswordValue = document.getElementById("repeatNewPasswordField").value;
    let success = true;
    let anyEmpty = currentPasswordValue === '' || newPasswordValue === '' || repeatNewPasswordValue === '';
    if (anyEmpty) {
        success = !anyEmpty;
        alert("All fields must be filled in");
    } else if (newPasswordValue !== repeatNewPasswordValue) {
        success = false;
        alert("New passwords don't match")
    }
    return isValid = success;
});


changePasswordForm.submit(function (event) {
    event.preventDefault();
    if (isValid) {
        $.ajax({
            url: changePasswordForm.attr('action'),
            type: 'post',
            dataType: 'json',
            data: changePasswordForm.serialize(),
            success: function (data) {
                let status = data.status;
                if (status != null) {
                    if (status === 'ERROR') {
                        $('#errorMessage').html(data.value);
                    } else if (status === 'NEW_PAGE') {
                        alert("Password changed successfully");
                    }
                } else {
                    alert('Unknown response');
                }
            }
        });
    }
});
