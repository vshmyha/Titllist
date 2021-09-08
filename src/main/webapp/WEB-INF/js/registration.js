let registrationForm = $('#registrationForm');
let isValid = false;
registrationForm.submit(function () {
    let registrationValue = document.getElementById("registrationField").value;
    let passwordValue = document.getElementById("passwordField").value;
    let repeatedPasswordValue = document.getElementById("repeatedPasswordField").value;

    let success = true;
    let anyEmpty = registrationValue === '' || passwordValue === '' || repeatedPasswordValue === '';
    if (anyEmpty) {
        success = !anyEmpty;
        alert("All fields must be filled in");
    } else if (passwordValue !== repeatedPasswordValue) {
        success = false;
        alert("Passwords don't match")
    }
    return isValid = success;
});

registrationForm.submit(function (event) {
    event.preventDefault();

    if (isValid) {
        $.ajax({
            type: 'POST',
            url: '/registration',
            dataType: 'json',
            data: registrationForm.serialize(),
        }).done(
            window.location = "/main_page"
        ).fail(function (data) {
                $('#errorMessage').html(data.responseText);
            })
    }
});
