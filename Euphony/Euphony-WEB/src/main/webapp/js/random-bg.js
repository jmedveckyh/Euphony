$(document).ready(function(){
    var classCycle=['intro1', 'intro2', 'intro3', 'intro4'];

    var randomNumber = Math.floor(Math.random() * classCycle.length);
    var classToAdd = classCycle[randomNumber];
    
    $('body').addClass(classToAdd);

});