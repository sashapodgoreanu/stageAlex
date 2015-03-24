/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
tile = function () {
    $("body").append('See? It works.');
    
};*/

var items = [[1, 'Sony'], [2, 'Samsung'], [3, 'LG']];


fillSelect = function () {


    $.each(items, function (i, item) {
        $('#uno').append($('<option>', {
            value: item[0],
            text: item[1]
        }
        ));
    }
    );
};

findSelected = function () {

};




/**
 * Addinfg options to <select>
 * $('#mySelect').append($('<option>', {
 value: 1,
 text: 'My option'
 }));
 If you're adding options from a collection of items, you can do the following:
 
 $.each(items, function (i, item) {
 $('#mySelect').append($('<option>', { 
 value: item.value,
 text : item.text 
 }));
 });
 
 
 $('#uno').append($('<option>', {
 value: 1,
 text: "my text"
 }
 ));
 */
