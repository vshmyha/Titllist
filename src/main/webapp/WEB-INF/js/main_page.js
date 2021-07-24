let byTypeDiv = $('#animeByTeg');
window.addEventListener('load', (event) => {
    loadAllAnime('/anime', 'There is no anime here yet.');
});

function loadAllAnime(command, errorMessage) {
    byTypeDiv.html('');
    statusPanel.html('');
    console.log(command)
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
        }
    });
};

let types = $('#types');
let typeBtn = $('#typeBtn');
let typeDropdown = $('#type-dropdown');

function getAnimesByTag(commandName, dataId) {
    $.getJSON('controller?command=' + commandName + '&id=' + dataId, function (result) {
        let status = result.status;
        let byTypeDiv = $('#animeByTeg');
        byTypeDiv.html('');
        statusPanel.html('');
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    let animeId = field.id;
                    let rusName = field.rusName;
                    let japName = field.japName;
                    let buttonId = "animeDiv" + animeId;
                    byTypeDiv.append("<div class='anime' id='" + buttonId + "'onclick='showAnimeInformation(" + animeId + ", " + buttonId + ")'" + "'> <p> Name: " + rusName + "/" + japName + "</p></div>");
                })
            } else if (status === 'ERROR') {
                $('#errorMessage').html(data.value);
            } else {
                alert('Unknown response');
            }
        }
    });
}


function configurePropertyButton(button, content, getPropertiesCommand, getByPropertyCommand, attributeClassName, extractProperty) {
    button.mouseenter(function () {
        content.html('');
        content.show();
        $.getJSON('controller?command=' + getPropertiesCommand, function (result) {
            let status = result.status;
            if (status != null) {
                if (status === 'OK') {
                    $.each(result.value, function (i, field) {
                        let id = field.id;
                        let property = extractProperty(field);
                        types.append("<button type='button' class='" + attributeClassName + "' data_id='" + id + "'>" + property + "</button>");
                    });
                }
            }
            $(".typeButton").each(function () {
                $(this).click(function () {
                    getAnimesByTag(getByPropertyCommand, $(this).attr('data_id'))
                });
            });
        });
    });
}


configurePropertyButton(
    typeBtn,
    types,
    'getAllTypesCommand',
    'getByTypeCommand',
    'typeButton',
    function (field) {
        return field.typeName;
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
    $.getJSON('controller?command=getAllGenresCommand', function (result) {
        let status = result.status;
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    let id = field.id;
                    let genreName = field.genreName;
                    genres.append("<button type='button' class='genreButton' name='command' data_id='" + id + "'>" + genreName + "</button>");
                });
            }
        }
        $(".genreButton").each(function () {
            $(this).click(function () {
                getAnimesByTag('getByGenreCommand', $(this).attr('data_id'));
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
    $.getJSON('controller?command=getAllReleaseDatesCommand', function (result) {
        let status = result.status;
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    releaseDates.append("<button type='button' class='releaseDateButton' name='command' data_id='" + field + " '>" + field + "</button>");
                });
            }
        }
        $(".releaseDateButton").each(function () {
            $(this).click(function () {
                getAnimesByTag('getByReleaseDateCommand', $(this).attr('data_id'));
            });
        });
    });
});

releaseDateDropdown.mouseleave(function () {
    $('#releaseDates').hide();
});


