function configPropertyOption(command, selectionContainer, parameter) {
    $.getJSON(command, function (result) {
        $.each(result, function (i, field) {
            let id = field.id;
            let property = parameter(field);
            selectionContainer.append("<option class='option' data_id='" + id + "' value='" + property + "'>" + property + "</option>");
        });
    })
};

configPropertyOption(
    '/anime/component/type',
    $('#typeSelection'),
    function (field) {
        return field;
    }
);
configPropertyOption(
    '/genre',
    $('#genreSelection'),
    function (field) {
        return field.name;
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
        if (users === null) {
            window.location = "/start_page";
        }
        let availableRoles = [];
        $.getJSON('/role', function (roles) {
            $.each(roles, function (i) {
                let role = roles[i];
                availableRoles.push(role);
            })
            tableUsers.append("<tr><td class='tdUser'>User</td>" +
                "<td class='tdRole'>Role</td></tr>");
            $.each(users, function (i, field) {
                console.log(field)
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
            url: '/user_roles/change',
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
    $('#forErrorMessage').html(' ');
    $.getJSON('/anime/' + animeId, function (result) {
        let animeStatus = result.status;
        let animeId = result.anime.id;
        let type = result.anime.type;
        let rusName = result.anime.rusName;
        let japName = result.anime.japName;
        let episodesCount = result.anime.episodes;
        let duration = result.anime.duration;
        let releaseDate = result.anime.releaseDate;
        let genresArray = result.anime.genres;
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
                    url: '/titllist/change',
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
    $.getJSON('/anime/component/status', function (result) {
        $.each(result, function (i) {
            let status = result[i];
            if (i === result.length - 1) {
                divInputStatus.append("<label><input checked className='status' type='radio' name='animeStatus' value='" + status + "'>" + status + "</label>");
            } else {
                divInputStatus.append("<label><input className='status' type='radio' name='animeStatus' value='" + status + "'>" + status + "</label>");
            }
        });
        divInputStatus.append("<input id='inputSubmitButton' type='submit' value='Apply'>");
        let anime = $('#header').attr("animeId");
        $('#inputStatus').append('<input type="hidden" name="animeId" value="' + anime + '" /> ');
    });

};

$(document).ready(function () {
    $('#genreSelection').select2();
})

let addAnimeForm = $('#addNewAnime');


addAnimeForm.submit(function (event) {
    event.preventDefault();
    const dataJson = formAsJSON(this);
    $.ajax({
        url: '/anime/add',
        type: 'POST',
        dataType: 'json',
        data: dataJson,
        contentType: 'application/json; charset=utf-8',
    }).done(function () {
        console.log(dataJson)
        alert("A new anime has been successfully added.");
        document.getElementById('addNewAnime').reset();
        loadAllAnime('/anime?', errorMessage);
    }).fail(function (data) {
        console.log(dataJson)
        $('#errorForSearch').html(data.responseText);
    })
});

let statusPanel = $('#statusPanel');

function showUserTitllist(status) {
    let commandName = '/titllist';
    if (status != null) {
        commandName = '/titllist?status=' + status;
    }
    loadAllAnime(commandName, "You haven't added anime to your list yet");

    getStatusPanel();
};

function getStatusPanel() {
    statusPanel.html('');
    $.getJSON('anime/component/status', function (result) {
        statusPanel.append("<button onclick='showUserTitllist(" + null + ")'>All</button>");
        $.each(result, function (i, field) {
            let animeStatus = field;
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
                    console.log(field)
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
    if (getComputedStyle(div, null).getPropertyValue("display") === "none") {
        div.style.display = "block";
    } else {
        div.style.display = "none";
    }
};

addAnime.onclick = function () {
    let serialize = addAnimeForm.serialize();

    // console.log(formAsJSON(addAnimeForm));
    console.log(serialize);
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
