$('#loginForm').submit(function () {

    let loginValue = document.getElementById("loginField").value;
    let passwordValue = document.getElementById("passwordField").value;

    let anyEmpty = loginValue === '' || passwordValue === '';
    if(anyEmpty) {
        alert("All fields must be filled in");
    }
    return !anyEmpty;
});
