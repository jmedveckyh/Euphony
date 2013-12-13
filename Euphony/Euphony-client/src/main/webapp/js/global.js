/* Validates empty artist name */
function validateArtistForm() {
    var x = document.forms["requiredForm"]["artist.name"].value;
    if (x == null || x == "") {
        document.getElementById("pn").style.borderColor = "red";
        return false;
    }
    return true;
}

/* Validates empty genre name */
function validateGenreForm() {
    var x = document.forms["requiredForm"]["genre.name"].value;
    if (x == null || x == "") {
        document.getElementById("pn").style.borderColor = "red";
        return false;
    }
    return true;
}