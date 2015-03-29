/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 tile = function () {
 $("body").append('See? It works.');
 
 };*/




function fillSelect(items) {

    $.each(items, function (i, item) {
        $('.tag').append($('<option>', {
            value: item.id,
            text: item.value
        }
        ));
    }
    );
}
;
/*
 onChange = function () {
 $('.tag').change(function () {
 var val = this.value;
 var sel = $('<select>').addClass('form-control tag');
 var opt1 =$('<option>');
 opt1.append(val);
 opt1.attr("value",val);
 
 var opt2 =$('<option>');
 opt2.append(val);
 opt2.attr("value",val);
 sel.append(opt1);
 sel.append(opt2);
 
 //$$('#due').append($('<option>'));
 //var ensel = $('</input>');
 $('#field').append(sel);
 //$('#select2').append($('<option>')).attr("value", value);
 // findSelected();
 });
 };*/
var count = 0;

function onChange(urlI) {

    $('#field').on("change", ".tag", function () {
        $(this).nextAll().remove();
        var sel = $('<select>').addClass('form-control tag');

        var id = this.value;
        var ajaxTag = {'id': id, 'value': ' '};
        $.ajax({
            url: urlI,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(ajaxTag),
            dataType: 'json'
        }).done(function (data) {
            $.each(data, function (i, item) {
                var opt = $('<option>');
                opt.append(item.value);
                opt.attr("value", item.id);
                sel.append(opt);
            });
        });
        $('#field').append(sel);
        //$(this).after("<p>Another paragraph! </p>");
    });

}
;



function initializeField(urlI) {
    var ajaxTag = {'id': 0, 'value': ' '};
    $.ajax({
        url: urlI,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(ajaxTag),
        dataType: 'json'
    }).done(function (data) {
        $.each(data, function (i, item) {
            $('.tag').append($('<option>', {
                value: item.id,
                text: item.value
            }));
        });
    });

    /*$.get(url, function (tag, status) {
     fillSelect(tag);
     });*/

}


/*
 $('.tag').change(function () {
 var val = this.value;
 var sel = $('<select>').addClass('form-control tag');
 var opt1 = $('<option>');
 opt1.append(val);
 opt1.attr("value", val);
 
 var opt2 = $('<option>');
 opt2.append(val);
 opt2.attr("value", val);
 sel.append(opt1);
 sel.append(opt2);
 
 //$$('#due').append($('<option>'));
 //var ensel = $('</input>');
 $('#field').append(sel);
 //$('#select2').append($('<option>')).attr("value", value);
 // findSelected();
 });
 };
 
 findSelected = function () {
 
 };*/




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

/*
 FUNZIONE OGGETTO 
 
 dentro il oggetto funzione le varie funzioni o oggetti sono separati da ->  ,  <-  virgola
 var functionObject = {
 doOne: function() {
 console.log("ONE");
 }, 
 doTwo: function() {
 console.log("TWO");
 }
 }
 functionObject.doOne();
 functionObject.doTwo();
 
 
 */
