/**
 * This funcion get from server suggestions with tags. 
 * @param {String} requestUrl - URL of Rest Controller
 * @returns Jquery
 */
//Globals
$.rightCliked = new Object();

//Functions
function performTagging(requestUrl) {
    //
    $('.tag-container').on("keydown.autocomplete", /*input class*/".input-tag", function (event, ui) {
        var divThtaContainsInput = $(this).parent().parent();
        $(this).autocomplete({
            //The delay in milliseconds between when a keystroke occurs and when a search is performed.
            delay: 500,
            //minimum number of characters that the source to be launched
            minLength: 2,
            //if input.lenght()  >=  minLength
            source: function (request, response) {
                //remove all next divThtaContainsInput if there is one and update .cloneable class
                if (!divThtaContainsInput.hasClass("cloneable")) {
                    divThtaContainsInput.addClass('cloneable');
                }
                divThtaContainsInput.nextAll().remove();

                //ajax call
                $.ajax({
                    url: requestUrl,
                    type: 'POST',
                    contentType: 'application/json',
                    dataType: 'json',
                    //data to be sent
                    data: JSON.stringify({'value': request.term}),
                    success: function (data) {
                        //fill autocomplete suggestions box
                        response(data);
                    }
                });
            },
            //at select an item from autocomplete suggestions box
            select: function (event, ui) {
                //add to this input (value = selected tag from autocomplete suggestions box)
                $(this).attr('value', ui.item.label);

                //Add a new input after selecting a value from autocomplete suggestions box
                var clonedHtml = $('.cloneable').clone();
                divThtaContainsInput.removeClass('cloneable');

                //change label value with selected value 
                clonedHtml.find('.label-tag').empty();
                clonedHtml.find('.label-tag').append(ui.item.label);

                //clear data from clone
                clonedHtml.find('.input-tag').val('');
                clonedHtml.find('.input-tag').attr('value', "");
                divThtaContainsInput.after(clonedHtml);
            },
            //open autocomplete suggestion
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            //close autocomplete suggestion
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });
    });
}

function enableMenu() {

    alert("succ");
    var $contextMenu = $('#context-menu');
    //if right cliked on a button show html element with id = "context-menu"
    $("body").on("contextmenu", ".btn", function (e) {
        $contextMenu.css({
            display: "block",
            left: e.pageX,
            top: e.pageY
        });
        return false;
    });

    //disableRightClick('button');
}

function disableRightClick(elem) {
    $(elem).bind('contextmenu', function (e) {
        e.preventDefault();
    });
}

//Scripts for UP Nav Bar 
var initTablesNav = function (saveURL) {
    //Add listener for button + (add new table)
    $(".tables-nav").on("click", "a", function (event) {
        event.preventDefault();
        //on click + button
        if ($(this).attr('id') === 'addTable') {
            //show modal to insert table name
            $('#addTableModal').modal('show');
        } else //else was clicked a Table name
        {
            alert("not yet developed: ID = " + $(this).attr('id'));
        }
    });

    //Save new created Table
    $("#addTableModal").on("click", "#createNewTableButton", function () {
        var instanceTable = new Table($("#createNewTableInput").val(), saveURL)
        instanceTable.save();
        $('#addTableModal').modal('hide');
    });
};


var Table = function (name, saveURL) {
    this.name = name;
    this.saveURL = saveURL;
    this.saveCompleted = false;

};

$.extend(Table.prototype, {
    save: function () {
        $.ajax({
            url: saveURL,
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            //data to be sent
            data: JSON.stringify({'name': this.name}),
            success: function (data) {
                console.log(data);
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            },
            complete: function () {
                this.open();
            }
        });
    },
    open: function () {
        $.ajax({
            url: '/stageAlex/workingarea',
            dataType: 'html',
            success: function (response) {
                console.log(response);
                $('#navbar-up').html(response);
            }
        });
    }
});



(function ($) {

    var APP = {
        Elements: {
            // elementi DOM

        },
        Utils: {
            // metodi helper o di utility

        },
        fn: {
            // il core

        },
        init: function () {

            // metodo di inizializzazione

        }

    };

    $(document).ready(function () {

        APP.init(); // esegue tutto


    });

})(jQuery);
//ajax call






