function showDiv() {
    var div = document.getElementById('quickAddPlaylist');
    if (div.style.display == "block") {
        div.style.display = "none";
    } else {
        div.style.display = "block";
    }
}

function showDivEdit() {
    var div = document.getElementById('quickAddPlaylistEdit');
    if (div.style.display == "block") {
        div.style.display = "none";
    } else {
        div.style.display = "block";
    }
}

function validateAddPlaylistForm() {
    var x = document.forms["quickAddPlaylist"]["playlist.name"].value;
    if (x == null || x == "") {
        document.getElementById("pn").style.borderColor = "red";
        return false;
    }
    return true;
}