window.addEventListener('load', (event) => {
    $.getJSON('controller?command=getAllAnime', function (result) {
        let status = result.status;
        let byTypeDiv = $('#animeByTeg');
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    let animeId = field.id;
                    let type = field.type.typeName;
                    let rusName = field.rusName;
                    let japName = field.japName;
                    let episodesCount = field.episodesCount;
                    let duration = field.duration;
                    let releaseDate = field.releaseDate;
                    let genresArray = field.genres;
                    let genres = "Genres: ";
                    $.each(genresArray, function (i, field) {
                        let genre = field.genreName;
                        genres = genres.concat(genre, ', ');
                    })
                    byTypeDiv.append("<div class='anime' id='" + animeId + "'> <p> Name: " + rusName + "/" + japName + "</p>" +
                        "<p> Type: " + type + "</p>" +
                        "<p> Episodes: " + episodesCount + "</p>" +
                        "<p> Duration: " + duration + "</p>" +
                        "<p> Release date: " + releaseDate + "</p>" +
                        "<p>" + genres + "</p>" +
                        "<butoon class='butoon' id='addAnimeInMyListButton" + animeId + "'>Add To List</butoon></div>");
                })
            } else if (status === 'ERROR') {
                $('#errorMessage').html(data.value);
            } else {
                alert('Unknown response');
            }
        }
    });
})

let types = $('#types');
let typeBtn = $('#typeBtn');
let typeDropdown = $('#type-dropdown');

function getAnimesByTag(commandName, dataId) {
    $.getJSON('controller?command=' + commandName + '&id=' + dataId, function (result) {
        let status = result.status;
        let byTypeDiv = $('#animeByTeg');
        byTypeDiv.html('');
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    let animeId = field.id;
                    let type = field.type.typeName;
                    let rusName = field.rusName;
                    let japName = field.japName;
                    let episodesCount = field.episodesCount;
                    let duration = field.duration;
                    let releaseDate = field.releaseDate;
                    let genresArray = field.genres;
                    let genres = "Genres: ";
                    $.each(genresArray, function (i, field) {
                        let genre = field.genreName;
                        genres = genres.concat(genre, ', ');
                    })
                    byTypeDiv.append("<div class='anime' id='" + animeId + "'> <p> Name: " + rusName + "/" + japName + "</p>" +
                        "<p> Type: " + type + "</p>" +
                        "<p> Episodes: " + episodesCount + "</p>" +
                        "<p> Duration: " + duration + "</p>" +
                        "<p> Release date: " + releaseDate + "</p>" +
                        "<p>" + genres + "</p></div>");
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


