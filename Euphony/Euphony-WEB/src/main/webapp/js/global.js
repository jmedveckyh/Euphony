/* Show and hide field for adding playlist name */
function showDiv() {
    var div = document.getElementById('quickAddPlaylist');
    if (div.style.display == "block") {
        div.style.display = "none";
    } else {
        div.style.display = "block";
    }
}

/* Show and hide field for editing playlist name */
function showDivEdit() {
    var divEdit = document.getElementById('quickAddPlaylistEdit');
    var divDel = document.getElementById('playlistDelete');
    
    if (divDel.style.display == "block") {
        divDel.style.display = "none";
    }
    
    if (divEdit.style.display == "block") {
        divEdit.style.display = "none";
    } else {
        divEdit.style.display = "block";
    }
}

/* Show and hide field for deleting playlist */
function showDivDelete() {
    var divEdit = document.getElementById('quickAddPlaylistEdit');
    var divDel = document.getElementById('playlistDelete');
    
    if (divEdit.style.display == "block") {
        divEdit.style.display = "none";
    }
    
    if (divDel.style.display == "block") {
        divDel.style.display = "none";
    } else {
        divDel.style.display = "block";
    }
}

/* Validates empty search box */
function validateSearch() {
    var x = document.forms["searchForm"]["phrase"].value;
    if (x == null || x == "") {
        document.getElementById("sp").style.borderColor = "red";
        return false;
    }
    return true;
}

/* Validates empty playlist */
function validateAddPlaylistForm() {
    var x = document.forms["quickAddPlaylist"]["playlist.name"].value;
    if (x == null || x == "") {
        document.getElementById("pn").style.borderColor = "red";
        return false;
    }
    return true;
}

/* Validates password (min. 8 chars) */
function validatePasswordMinChars() {
    var y = document.forms["registerWithPass"]["password"].value;
    if (y.length < 8) {
        document.getElementById("b2").style.borderColor = "red";
        document.getElementById("b3").style.borderColor = "red";
        return false;
    }
    return true;
}

/* Checks checkboxes in rows */ 
$(document).ready(function () {
    $('.basic tr').click(function (event) {
        if (event.target.type !== 'checkbox') {
            $(':checkbox', this).trigger('click');
        }
    });
});