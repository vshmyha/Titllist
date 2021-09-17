function configPropertyOption(command, selectionContainer, parameter) {
    $.getJSON(command, function (result) {
        $.each(result, function (i, field) {
            let id = field.id;
            let property = parameter(field);
            selectionContainer.append("<option class='option' data_id='" + id + "' value='" + id + "'>" + property + "</option>");
        });
    })
};

configPropertyOption(
    '/type',
    $('#typeSelection'),
    function (field) {
        return field.typeName;
    }
);
configPropertyOption(
    '/genre',
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
    $.getJSON('/user_roles', function (users) {
        let availableRoles = [];
        $.getJSON('/role', function (roles) {
            $.each(roles, function (i) {
                let role = roles[i];
                availableRoles.push(role);
            })
            tableUsers.append("<tr><td class='tdUser'>User</td>" +
                "<td class='tdRole'>Role</td></tr>" +
                "<tr id='supperAdmin'><td>Lerkin</td>" +
                "<td>SUPER ADMIN</td></tr>");
            $.each(users, function (i, field) {
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
        });
        // } else if (status === 'NEW_PAGE') {
        //     alert("Your rights were changed by the administrator, now you will be redirected to the login page.");
    });
};

function showRoleSelectionForUser(roleContainerStr, userId, buttonId) {
    let roleContainer = $(roleContainerStr);
    roleContainer.html('');
    $.getJSON('/role', function (result) {
        let changeRoleButton = buttonId.id;
        let butt = document.getElementById(changeRoleButton);
        butt.style.display = "none";
        roleContainer.append("<form id='changeRole' action='/role/change' method='put'>" +
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
            url: '/role/change',
            type: 'PUT',

            data: changeRoleForm.serialize(),
        }).done(function () {
            refreshUsersAndRoles();
        })
    });
};

let modalAnimeWindow = document.getElementById('animeModal');
let spanAnimeModalWindow = document.getElementsByClassName("closeAnimeWindow")[0];
let heading = $('#animeName');
let animeContent = $('#animeContent');
let addOrChange = $('#forForm');

spanAnimeModalWindow.onclick = function () {
    closeModalWindow(modalAnimeWindow);
    closeModalWindow(document.getElementById('inputStatus'));

};

function showAnimeInformation(animeId) {
    showModalWindow(modalAnimeWindow);
    loadNewAnimeInformation(animeId);
};

function loadNewAnimeInformation(animeId) {
    animeContent.html('');
    heading.html('');
    addOrChange.html(' ');
    $.getJSON('/anime/' + animeId, function (result) {
        let animeStatus = result.status;
        let animeId = result.id;
        let type = result.type.name;
        let rusName = result.rusName;
        let japName = result.japName;
        let episodesCount = result.episodes;
        let duration = result.duration;
        let releaseDate = result.releaseDate;
        let genresArray = result.genres;
        let genres = "Genres: ";
        let buttonText;
        $.each(genresArray, function (i, field) {
            let genre = field.name;
            genres = genres.concat(genre, ', ');
        })
        heading.append("<p animeid='" + animeId + "' id='header'>" + rusName + "/" + japName + "</p>");
        if (animeStatus === null) {
            buttonText = 'Add To Titllist';
            $('#forForm').append("<form id='addAnimeToTitllist' action='/titllist/add' method='POST'>" +
                "<div id='inputStatus'></div></form>");
            $('#addAnimeToTitllist').submit(function (event) {
                event.preventDefault();
                $.ajax({
                    url: '/titllist/add',
                    type: 'POST',
                    data: $('#addAnimeToTitllist').serialize(),
                }).done(function () {
                    let animeId = $('#header').attr("animeId");
                    loadNewAnimeInformation(animeId);
                }).fail(function (data) {
                    $('#forErrorMessage').html(data.responseText);
                })
            });
        } else {
            buttonText = 'Change Tittlist';
            $('#forForm').append("<form id='changeAnimeStatus' action='/status/change' method='PUT'>" +
                "<div id='inputStatus'></div></form>");
            animeContent.append("<div><p class='animeStatus'>" + animeStatus + "</p></div>");
            $('#changeAnimeStatus').submit(function (event) {
                event.preventDefault();
                $.ajax({
                    url: '/status/change',
                    type: 'PUT',
                    data: $('#changeAnimeStatus').serialize(),
                }).done(function () {
                    let animeId = $('#header').attr("animeId");
                    loadNewAnimeInformation(animeId);
                }).fail(function (data) {
                    $('#forErrorMessage').html(data.responseText);
                })
            });
        }

        animeContent.append("<div><p> Type: " + type + "</p>" +
            "<p> Episodes: " + episodesCount + "</p>" +
            "<p> Duration: " + duration + "</p>" +
            "<p> Release date: " + releaseDate + "</p>" +
            "<p>" + genres + "</p>" +
            "<butoon class='butoon' id='addAnimeInMyListButton' onclick='showAnimeStatusChoice()' >" + buttonText + "</butoon></div>");
        // $('#addAnimeInMyListButton').click(function(){
        //     showAnimeStatusChoice();
        // })

    });
};

function showAnimeStatusChoice() {
    let divInputStatus = $('#inputStatus');
    let inputStatus = document.getElementById('inputStatus');
    divInputStatus.html('');
    changeDivStyle(inputStatus);
    $.getJSON('/status', function (result) {
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
        divInputStatus.append("<input type='submit' value='Apply'>");
        let anime = $('#header').attr("animeId");
        $('#inputStatus').append('<input type="hidden" name="animeId" value="' + anime + '" /> ');
    });

};

let addAnimeForm = $('#addNewAnime');

addAnimeForm.submit(function (event) {
    event.preventDefault();
    $.ajax({
        url: '/anime/add',
        type: 'POST',
        data: addAnimeForm.serialize()
    }).done(function () {
        alert("A new anime has been successfully added.");
        document.getElementById('addNewAnime').reset();
        loadAllAnime();
    }).fail(function (data) {
        $('#errorForSearch').html(data.responseText);
    })
});

let statusPanel = $('#statusPanel');

function showUserTitllist(status) {
    let commandName = '/titllist';
    if (status != null) {
        status = status.replace(/\s/g, '');
        commandName = '/titllist/' + status;
    }
    loadAllAnime(commandName, "You haven't added anime to your list yet");

    getStatusPanel();
};

function getStatusPanel() {
    statusPanel.html('');
    $.getJSON('/status', function (result) {
        statusPanel.append("<button onclick='showUserTitllist(" + null + ")'>All</button>");
        $.each(result, function (i, field) {
            let id = field.id;
            let animeStatus =field.name;
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
        url: '/anime/search',
        type: 'POST',
        dataType: 'json',
        data: searchForm.serialize(),
        success: function (data) {
            byTypeDiv.html('');
            if (Object.keys(data).length === 0) {
                byTypeDiv.append("<h1>No anime was found for your search</h1>")
            } else {
                $.each(data, function (i, field) {
                    let animeId = field.id;
                    let rusName = field.rusName;
                    let japName = field.japName;
                    let buttonId = "animeDiv" + animeId;
                    byTypeDiv.append("<div class='anime' id='" + buttonId + "'onclick='showAnimeInformation(" + animeId + ", " + buttonId + ")'" + "'> <p> Name: " + rusName + "/" + japName + "</p></div>");
                });
            }
        },
        error: [function (data) {
            $('#errorMessage').html(data.responseText);
        }]
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
        closeModalWindow(document.getElementById('inputStatus'));
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
            url: '/change',
            type: 'PUT',
            data: changePasswordForm.serialize(),
        }).done(function () {
            alert("Password changed successfully");
            window.location = "/start_page";
        }).fail(function (data) {
            console.log(data);
            $('#errorMessage').html(data.responseText);
        })
    }
});
