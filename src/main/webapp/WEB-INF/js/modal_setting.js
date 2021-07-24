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
                        tableUsers.append("<tr><td class='tdUser'>User</td>" +
                            "<td class='tdRole'>Role</td></tr>" +
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
                                    $('#userTable tr:last').append("<td><button class='changeRoleButton' id='" + buttonId + "'onclick='showRoleSelectionForUser(" + roleContainerId + ", " + id + "," + buttonId + ")'" +
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
    $.getJSON('/role', function (result) {
        let changeRoleButton = buttonId.id;
        let butt = document.getElementById(changeRoleButton);
        butt.style.display = "none";
        roleContainer.append("<form id='changeRole' action='controller' method='post'>" +
            "<select name='role' form='changeRole' style='color: black'  id='selection" + userId + "'></select>" +
            "<input type='hidden' name='command' value='changeRoleCommand'>" +
            "<input type='hidden' name='userId' value='" + userId + "'>" +
            "<input form='changeRole' type='submit' value='Save'></form>")
        let selectionId = "selection" + userId;
        let selection = $('#' + selectionId);
        console.log(selection);
        $.each(result, function (i) {
            let role = result[i];
            selection.append("<option class='option' value='" + role + "'>" + role + "</option>")
        })
        addChangeRoleHandler();
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
                        $('#errorMessagePlace').html(data.value);
                    }
                } else {
                    alert('Unknown response');
                }
            }
        });
    });
};

let modalAnimeWindow = document.getElementById('animeModal');
let spanAnimeModalWindow = document.getElementsByClassName("closeAnimeWindow")[0];
let heading = $('#animeName');
let animeContent = $('#animeContent');

spanAnimeModalWindow.onclick = function () {
    closeModalWindow(modalAnimeWindow);
    closeModalWindow(form);
};

function showAnimeInformation(animeId) {
    showModalWindow(modalAnimeWindow);
    loadNewAnimeInformation(animeId);
};

function loadNewAnimeInformation(animeId) {
    animeContent.html('');
    heading.html('');
    closeModalWindow(form);
    $.getJSON('controller?command=getAnimeByIdCommand&id=' + animeId, function (result) {
        let status = result.status;
        if (status != null) {
            if (status === 'OK') {
                let userAnime = result.value;
                let animeStatus = userAnime.status;
                let anime = userAnime.anime;
                let animeId = anime.id;
                let type = anime.type.typeName;
                let rusName = anime.rusName;
                let japName = anime.japName;
                let episodesCount = anime.episodesCount;
                let duration = anime.duration;
                let releaseDate = anime.releaseDate;
                let genresArray = anime.genres;
                let genres = "Genres: ";
                let button;
                $.each(genresArray, function (i, field) {
                    let genre = field.genreName;
                    genres = genres.concat(genre, ', ');
                })
                heading.append("<p animeid='" + animeId + "' id='header'>" + rusName + "/" + japName + "</p>");
                if (animeStatus === null) {
                    button = 'Add To Titllist';
                    animeContent.append("<input form='addToTitllist' type='hidden' name='command' value='addAnimeToUserTitllist'>");
                } else {
                    button = 'Change Tittlist';
                    animeContent.append("<input form='addToTitllist' type='hidden' name='command' value='changeAnimeStatusInTitllist'>");
                    animeContent.append("<div><p class='animeStatus'>" + animeStatus + "</p></div>");
                }
                animeContent.append("<div><p> Type: " + type + "</p>" +
                    "<p> Episodes: " + episodesCount + "</p>" +
                    "<p> Duration: " + duration + "</p>" +
                    "<p> Release date: " + releaseDate + "</p>" +
                    "<p>" + genres + "</p>" +
                    "<butoon class='butoon' id='addAnimeInMyListButton' onclick='showAnimeStatusChoice()'>" + button + "</butoon></div>");
            } else if (status === 'ERROR') {
                $('#forErrorMessage').html(data.value);
            } else {
                alert('Unknown response');
            }
        }

    });
};

let form = document.getElementById('addToTitllist');
let divInputStatus = $('#inputStatus');

function showAnimeStatusChoice() {
    divInputStatus.html('');
    changeDivStyle(form);
    $.getJSON('/getAnimeStatus', function (result) {
        $.each(result, function (i) {
            let status = result[i];
            if (i === result.length - 1) {
                divInputStatus.append("<input checked className='status' type='radio' name='animeStatus' value='" + status + "'>" +
                    "<label>" + status + "</label>");
            } else {
                divInputStatus.append("<input className='status' type='radio' name='animeStatus' value='" + status + "'>" +
                    "<label>" + status + "</label>");
            }
        });
    });
};

let formAddToTitllist = $('#addToTitllist');

formAddToTitllist.submit(function () {
    let anime = $('#header').attr("animeId");
    $(this).append('<input type="hidden" name="animeId" value="' + anime + '" /> ');
    return true;
});

formAddToTitllist.submit(function (event) {
    event.preventDefault();
    $.ajax({
        url: formAddToTitllist.attr('action'),
        type: 'post',
        dataType: 'json',
        data: formAddToTitllist.serialize(),
        success: function (data) {
            let status = data.status;
            if (status != null) {
                if (status === 'ERROR') {
                    $('#forErrorMessage').html(data.value);
                } else if (status === 'OK') {
                    let animeId = $('#header').attr("animeId");
                    loadNewAnimeInformation(animeId);
                }
            } else {
                alert('Unknown response');
            }
        }
    });
});

let addAnimeForm = $('#addNewAnime');

addAnimeForm.submit(function (event) {
    event.preventDefault();
    $.ajax({
        url: addAnimeForm.attr('action'),
        type: 'post',
        dataType: 'json',
        data: addAnimeForm.serialize(),
        success: function (data) {
            let status = data.status;
            if (status != null) {
                if (status === 'ERROR') {
                    $('#errorForSearch').html(data.value);
                } else if (status === 'OK') {
                    alert("A new anime has been successfully added.");
                    document.getElementById('addNewAnime').reset();
                    loadAllAnime();
                }
            } else {
                alert('Unknown response');
            }
        }
    });
});

let statusPanel = $('#statusPanel');

function showUserTitllist(status) {
    let commandName = '/showUserTitllist';
    if (status != null) {
        status = status.replace(/\s/g, '');
        let command = '/showUserTitllist';
        let property = '&status=' + status;
        commandName = command + property;
    }
    loadAllAnime(commandName, "You haven't added anime to your list yet");

    getStatusPanel();
};

function getStatusPanel() {
    statusPanel.html('');
    $.getJSON('/status', function (result) {
        statusPanel.append("<button onclick='showUserTitllist(" + null + ")'>All</button>");
        $.each(result, function (i) {
            let animeStatus = result[i];
            statusPanel.append("<button class='titllistButton' status='" + animeStatus + "'>" + animeStatus + "</button>");
        });
    });
}

$(document).on('click', '.titllistButton', function () {
    let status = $(this).attr('status');
    showUserTitllist(status);
});

let searchForm = $('#searchForm');

$("#searchInput").keyup(function (event) {
    if (event.keyCode === 13) {
        searchForm.submit();
        event.preventDefault();
    }
});

searchForm.submit(function (event) {
    event.preventDefault();
    $.ajax({
        url: searchForm.attr('action'),
        type: 'post',
        dataType: 'json',
        data: searchForm.serialize(),
        success: function (data) {
            let status = data.status;
            if (status != null) {
                if (status === 'ERROR') {
                    $('#errorMessagePlace').html(data.value);
                } else if (status === 'OK') {
                    byTypeDiv.html('');
                    if (Object.keys(data.value).length === 0) {
                        byTypeDiv.append("<h1>No anime was found for your search</h1>")
                    } else {
                        $.each(data.value, function (i, field) {
                            let animeId = field.id;
                            let rusName = field.rusName;
                            let japName = field.japName;
                            let buttonId = "animeDiv" + animeId;
                            byTypeDiv.append("<div class='anime' id='" + buttonId + "'onclick='showAnimeInformation(" + animeId + ", " + buttonId + ")'" + "'> <p> Name: " + rusName + "/" + japName + "</p></div>");
                        });
                    }
                }
            } else {
                alert('Unknown response');
            }
        }
    });
})

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
    } else if (event.target === modalAnimeWindow) {
        closeModalWindow(modalAnimeWindow);
        closeModalWindow(form);
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
