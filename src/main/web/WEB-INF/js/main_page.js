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
                        console.log(i);
                        let id = field;
                        types.append("<button type='button' class='typeButton' name='command' id='" + id + "'>" + field + "</button>");
                    });
                }
            }
        });
    }
);

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
                    genres.append("<button>" + field + "</button>");
                });
            }
        }
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
