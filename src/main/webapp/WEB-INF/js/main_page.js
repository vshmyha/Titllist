let byTypeDiv = $('#animeByTeg');
window.addEventListener('load', (event) => {
    getAdminSettings('/role/check');
    currentPage = 0;
    loadAllAnime('/anime?', errorMessage);
});

function getAdminSettings(commandName) {
    $.getJSON(commandName, function (result) {
        if(result === 'SUPER_ADMIN' || result === 'ADMIN') {
            changeDivStyle(document.getElementById('adminSetting'));
        }
    })
}

function nullifyForAnime() {
    currentPage = 0;
    loadAllAnime('/anime?', 'There is no anime here yet.');
}

function loadAllAnime(command, errorMessage) {
    commandNameForPagination = command;
    let pagination = '&page=' + currentPage + '&size=' + size;
    command = command + pagination;
    byTypeDiv.html('');
    statusPanel.html('');
    $.getJSON(command, function (result) {
        if (Object.keys(result).length === 0) {
            byTypeDiv.html(errorMessage);
        } else {
            $.each(result, function (i, field) {

                let animeId = field.id;
                let rusName = field.rusName;
                let japName = field.japName;
                let buttonId = "animeDiv" + animeId;
                byTypeDiv.append("<div class='anime' id='" + buttonId + "'onclick='showAnimeInformation(" + animeId + ", " + buttonId + ")'" + "'> <p> Name: " + rusName + "/" + japName + "</p></div>");
            })
            byTypeDiv.append("<div id='pagination'><nav><ul class='pagination'>\n" +
                "                <li class='page-item'><button onclick='previousContent()' id='previous'>Previous</button></li>\n" +
                "                <li class='page-item'><button onclick='nextContent()' id='next'>Next</button></li></ul></nav></div>")
        }
    });
};

let types = $('#types');
let typeBtn = $('#typeBtn');
let typeDropdown = $('#type-dropdown');

function configurePropertyButton(button, content, getPropertiesCommand, getByPropertyCommand, attributeClassName, extractProperty) {
    button.mouseenter(function () {
        content.html('');
        content.show();
        $.getJSON(getPropertiesCommand, function (result) {
            $.each(result, function (i, field) {
                let property = extractProperty(field);
                types.append("<button type='button' class='" + attributeClassName + "' data_id='" + field + "'>" + property + "</button>");
            });
            $(".typeButton").each(function () {
                $(this).click(function () {
                    let data_id = $(this).attr('data_id');
                    let command = getByPropertyCommand + data_id;
                    currentPage = 0;
                    loadAllAnime(command, errorMessage);
                });
            });
        });
    });
}


configurePropertyButton(
    typeBtn,
    types,
    '/anime/component/type',
    'anime?type=',
    'typeButton',
    function (field) {
        return field;
    }
);

typeDropdown.mouseleave(function () {
    $('#types').hide();
});


let genres = $('#genres');
let genreBtn = $('#genreBtn');
let genreDropdown = $('#genre-dropdown');

genreBtn.mouseenter(function () {
    genres.html('');
    genres.show();
    $.getJSON('/genre', function (result) {
        $.each(result, function (i, field) {
            let genreName = field.name;
            let id = field.id;
            genres.append("<button type='button' class='genreButton' name='command' data_id='" + id + "'>" + genreName + "</button>");
        });
        $(".genreButton").each(function () {
            $(this).click(function () {
                let data_id = $(this).attr('data_id');
                let command = '/anime?genreId=' + data_id;
                currentPage = 0;
                loadAllAnime(command, errorMessage);
            });
        });
    });
});

genreDropdown.mouseleave(function () {
    $('#genres').hide();
});


let releaseDates = $('#releaseDates');
let releaseDateBtn = $('#releaseDateBtn');
let releaseDateDropdown = $('#releaseDate-dropdown');

releaseDateBtn.mouseenter(function () {
    releaseDates.html('');
    releaseDates.show();
    $.getJSON('/anime/component/date', function (result) {
        $.each(result, function (i, field) {

            releaseDates.append("<button type='button' class='releaseDateButton' name='command' data_id='" + field + " '>" + field + "</button>");
        });
        $(".releaseDateButton").each(function () {
            $(this).click(function () {
                let data_id = $(this).attr('data_id');
                let command = '/anime?releaseDate=' + data_id;
                currentPage = 0;
                loadAllAnime(command, errorMessage)
            });
        });
    });
});

releaseDateDropdown.mouseleave(function () {
    $('#releaseDates').hide();
});


