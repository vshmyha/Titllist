let types = $('#types');
let typeBtn = $('#typeBtn');
let typeDropdown = $('#type-dropdown');

typeBtn.mouseenter(function () {
    $('#types').html('');
    $('#types').show();
    $.getJSON('controller?command=getAllTypesCommand', function (result) {
        let status = result.status;

        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    let id = field.id;
                    let typeName = field.typeName;
                    types.append("<button type='button' class='typeButton' name='command' id='" + id + "'>" + typeName + "</button>");
                });
            }
        }
        $(".typeButton").each(function () {
            $(this).click(function () {
                let id = $(this).attr('id');

                $.getJSON('controller?command=getByTypesCommand&id=' + id, function (result) {
                    let status = result.status;
                    let byTypeDiv = $('#animeByTeg');
                    if (status != null) {
                        if (status === 'OK') {

                            $.each(result.value, function (i, field) {
                                let type = field.type.typeName;
                                let rusName = field.rusName;
                                let japName = field.japName;
                                let episodsCount = field.episodesCount;
                                let duration = field.duration;
                                let releaseDate = field.releaseDate;
                                let genresArray = field.genres;
                                byTypeDiv.append("<div> Name: " + rusName + "/" + japName +
                                    "Type: " + type +
                                    "Episodse: " + episodsCount +
                                    "Duration: " + duration +
                                    "Release date: " + releaseDate +
                                    "Genres:" +"</div>");
                                $.each(genresArray, function (i, field) {
                                    let genre = field.genreName;
                                    byTypeDiv.append(genre + ", ").after("Genres: ");
                                })
                            })
                        }

                    }
                });
            });
        });
    });
});

typeDropdown.mouseleave(function () {
    $('#types').hide();
});


let genres = $('#genres');
let genreBtn = $('#genreBtn');
let genreDropdown = $('#genre-dropdown');

genreBtn.mouseenter(function () {
    $('#genres').html('');
    $('#genres').show();
    $.getJSON('controller?command=getAllGenresCommand', function (result) {
        let status = result.status;
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    console.log(field);
                    let id = field.id;
                    let genreName = field.genreName;
                    genres.append("<button type='button' class='genreButton' name='command' id='" + id + "'>" + genreName + "</button>");
                });
            }
        }
        $(".genreButton").each(function () {
            $(this).click(function () {
                let id = $(this).attr('id');

                $.getJSON('controller?command=getByGenresCommand&id=' + id, function (result) {
                    let status = result.status;
                    let byGenreDiv = $('#animeByTeg');
                    if (status != null) {
                        if (status === 'OK') {

                            $.each(result.value, function (i, field) {
                                let type = field.type.typeName;
                                let rusName = field.rusName;
                                let japName = field.japName;
                                let episodsCount = field.episodesCount;
                                let duration = field.duration;
                                let releaseDate = field.releaseDate;
                                let genresArray = field.genres;
                                byGenreDiv.append("<div> Name: " + rusName + "/" + japName +
                                    "Type: " + type +
                                    "Episodse: " + episodsCount +
                                    "Duration: " + duration +
                                    "Release date: " + releaseDate + "</div>");
                                $.each(genresArray, function (i, field) {
                                    let genre = field.genreName;
                                    byGenreDiv.append(genre + ", ").after("Genres: ");
                                })
                            })
                        }
                    }
                });
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
    $('#releaseDates').html('');
    $('#releaseDates').show();
    $.getJSON('controller?command=getAllReleaseDateCommand', function (result) {
        let status = result.status;
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    releaseDates.append("<button>" + field + "</button>");
                });
            }
        }
    });
});

releaseDateDropdown.mouseleave(function () {
    $('#releaseDates').hide();
});
