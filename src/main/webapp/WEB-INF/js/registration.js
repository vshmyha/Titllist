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
    console.log(event);
    if (isValid) {
        $.ajax({
            url: registrationForm.attr('action'),
            type: 'post',
            dataType: 'json',
            data: registrationForm.serialize(),
            success: function (data) {
                let status = data.status;
                if (status != null) {
                    isError(status);
                } else {
                    alert('Unknown response');
                }
            }
        });
    }
});
