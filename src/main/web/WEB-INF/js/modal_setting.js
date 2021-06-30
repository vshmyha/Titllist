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

function refreshUsersAndRoles() {
    tableUsers.html("");
    $.getJSON('controller?command=getUsersAndRoles', function (users) {
        status = users.status;
        if (status != null) {
            if (status === 'OK') {
                let availableRoles = [];
                $.getJSON('controller?command=getAvailableRoles', function (roles) {
                    let resultStatus = roles.status;
                    if (resultStatus != null) {
                        if (resultStatus === 'OK') {
                            $.each(roles.value, function (i) {
                                let role = roles.value[i];
                                availableRoles.push(role);
                            })
                        }
                        tableUsers.append("<tr><td>User</td>" +
                            "<td>Role</td></tr>" +
                            "<tr id='supperAdmin'><td>Lerkin</td>" +
                            "<td>SUPER ADMIN</td></tr>");
                        $.each(users.value, function (i, field) {
                            let id = field.id;
                            let role = field.role;
                            let userName = field.userName;
                            let roleContainerId = "admin_table_role_" + role + i;
                            let buttonId = "changeRoleButton" + userName;
                            let trId = 'tr' + userName;
                            tableUsers.append("<tr id='" + trId + "'> <td id='admin_table_username_" + userName + "'>" + userName + "</td> " +
                                "<td id='" + roleContainerId + "'>" + role + "</td></tr>");
                            $.each(availableRoles, function (index) {
                                console.log(availableRoles)
                                let availableRole = availableRoles[index];
                                if (availableRole === role) {
                                    $('#userTable tr:last').append("<td><button class='changeRoleButton' id='" + buttonId + "' onclick='showRoleSelectionForUser(" + roleContainerId + ", " + id + "," + buttonId + ")' " +
                                        ">Change Role</button></td>")
                                }
                            });
                        });
                    }
                });
            } else if (status === 'NEW_PAGE') {
                alert("Your rights were changed by the administrator, now you will be redirected to the login page.");
            } else if (status === 'ERROR') {
                $('#errorMessagePlace').html(users.value);
            } else {
                alert("Unknown response");
            }
        }
    });
};


function showRoleSelectionForUser(roleContainerStr, userId, buttonId) {
    let roleContainer = $(roleContainerStr);
    roleContainer.html('');
    $.getJSON('controller?command=getRolesCommand', function (result) {
        if (status != null) {
            if (status === 'OK') {
                let changeRoleButton = buttonId.id;
                let butt = document.getElementById(changeRoleButton);
                butt.style.display = "none";
                roleContainer.append("<form id='changeRole' action='controller' method='post'>" +
                    "<select name='role' form='changeRole' style='color: black'  id='selection" + userId + "'></select>" +
                    "<input type='hidden' name='command' value='changeRoleCommand'>" +
                    "<input type='hidden' name='userId' value='" + userId + "'>" +
                    "<input form='changeRole' type='submit'>Save</input></form>")
                let selectionId = "selection" + userId;
                let selection = $('#' + selectionId);
                console.log(selection);
                $.each(result.value, function (i) {
                    let role = result.value[i];
                    selection.append("<option class='option' value='" + role + "'>" + role + "</option>")
                })
                addChangeRoleHandler();
            } else if (status === 'ERROR') {
                $('#errorMessagePlace').html(result.value);
            }
        }
    });
};

function addChangeRoleHandler() {
    let changeRoleForm = $('#changeRole');
    changeRoleForm.submit(function (event) {
        event.preventDefault();
        $.ajax({
            url: changeRoleForm.attr('action'),
            type: 'post',
            dataType: 'json',
            data: changeRoleForm.serialize(),
            success: function (data) {
                refreshUsersAndRoles();
                let status = data.status;
                if (status != null) {
                    if (status === 'OK') {

                    } else if (status === 'ERROR') {
                        $('#errorMessage').html(data.value);
                    }
                } else {
                    alert('Unknown response');
                }
            }
        });
    });
}

let refreshUserAndRoleButton = document.getElementById("refreshUserAndRole");

refreshUserAndRoleButton.onclick = function () {
    refreshUsersAndRoles();
}

function changeDivStyle(div) {
    if (getComputedStyle(div, null).display === "none") {
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
    refreshUsersAndRoles();
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
