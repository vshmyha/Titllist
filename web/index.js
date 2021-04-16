let onClickButton = function (command) {
    $.get("controller?command=" + command, function (data) {
        alert(data);
    })
};

let onClickButton2_0 = function () {
    $.get("controller?command=body to die for", function (data) {
        alert(data);
    })
};