let modal = document.getElementById("modal");
let settingBtn = document.getElementById("setting");
let span = document.getElementsByClassName("close")[0];

settingBtn.onclick = function () {
    modal.style.display = "block";
};

span.onclick = function () {
    modal.style.display = "none";
};

window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
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
            url: changePasswordForm.attr('action'),
            type: 'post',
            dataType: 'json',
            data: changePasswordForm.serialize(),
            success: function (data) {
                let status = data.status;
                if (status != null) {
                    if (status === 'ERROR') {
                        $('#errorMessage').html(data.value);
                    } else if (status === 'NEW_PAGE') {
                        alert("Password changed successfully");
                        document.location.href = data.value;
                    }
                } else {
                    alert('Unknown response');
                }
            }
        });
    }
});
