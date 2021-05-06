
$(".typeButton").each(function() {
    $(this).click(function () {
        alert($(this).attr('id'));
    })
});
/*    let btn = $(this).attr('id');
    console.log(btn);
    $.getJSON('controller?command=getAllTypesCommand', function (result) {
        let status = result.status;
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    speshl.append(field + " ");
                });
            }
        }
    })
})
});

*/
/*
function myFunction1(data) {
    let btn = $('#ONA');

    console.log(btn);
    $.getJSON('controller?command=getAllTypesCommand', function (result) {
        let status = result.status;
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    speshl.append(field + " ");
                });
            }
        }
    });
}

function myFunction2(data) {
    let btn = $('#OVA');

    console.log(btn);
    $.getJSON('controller?command=getAllTypesCommand', function (result) {
        let status = result.status;
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    speshl.append(field + " ");
                });
            }
        }
    });
}

function myFunction3(data) {
    let btn = $('#Клип');

    console.log(btn);
    $.getJSON('controller?command=getAllTypesCommand', function (result) {
        let status = result.status;
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    speshl.append(field + " ");
                });
            }
        }
    });
}

function myFunction4(data) {
    let btn = $('#TV_Сериал');

    console.log(btn);
    $.getJSON('controller?command=getAllTypesCommand', function (result) {
        let status = result.status;
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    speshl.append(field + " ");
                });
            }
        }
    });
}

function myFunction5(data) {
    let btn = $('#Фильм');

    console.log(btn);
    $.getJSON('controller?command=getAllTypesCommand', function (result) {
        let status = result.status;
        if (status != null) {
            if (status === 'OK') {
                $.each(result.value, function (i, field) {
                    speshl.append(field + " ");
                });
            }
        }
    });
}

 */
