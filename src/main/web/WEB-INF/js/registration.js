let registrationForm = $('#registrationForm');
registrationForm.submit(function () {
    let registrationValue = document.getElementById("registrationField").value;
    let passwordValue = document.getElementById("passwordField").value;
    let repeatedPasswordValue = document.getElementById("repeatedPasswordField").value;

    let anyEmpty = registrationValue === '' || passwordValue === '' || repeatedPasswordValue === '';
    if (anyEmpty) {
        alert("All fields must be filled in");
    } else if (passwordValue !== repeatedPasswordValue) {
        alert("Passwords don't match")
    }
    return !anyEmpty;
});

registrationForm.submit(function (event) {
    $.post(registrationForm.action, registrationForm.serialize())
        .done(function (data) {
            let json = JSON.parse(data);
            let status = json.status;
            if (status != null) {
                if (status === 'NEW_PAGE') {
                    document.location.href = json.value;
                } else if (status === 'ERROR') {
                    $('#errorMessage').html(json.value);
                }
            } else {
                alert('Unknown response');
            }
        });
    event.preventDefault();
});