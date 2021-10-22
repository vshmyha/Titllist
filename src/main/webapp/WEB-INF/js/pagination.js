let currentPage = 0;
const size = 30;
const errorMessage = 'There is no anime here yet.';
let commandNameForPagination = '/anime';


let nextButton = document.getElementById('next');


function nextContent() {
    let previousButton = document.getElementById('previous');
    currentPage = currentPage + 1;
    // if (previousButton.disabled === true) {
    //     previousButton.removeAttr('disabled');
    // }
    loadAllAnime(commandNameForPagination, errorMessage);
}

function previousContent() {
    let previousButton = document.getElementById('previous');
    if (currentPage !== 0) {
        currentPage = currentPage - 1;
        loadAllAnime(commandNameForPagination, errorMessage);
    }
    // if (previousButton.disabled === false) {
    //     previousButton.attr('disabled', 'disabled');
    // }
}