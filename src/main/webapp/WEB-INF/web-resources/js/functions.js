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

var activateMenuTable = function () {
    //assign event to body because .tables-nav will be replaced with new from server
    $("body").on("click", ".tables-nav a", function (event) {
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
};

//Scripts for UP Nav Bar 
var initTablesNav = function (saveURL) {
    //Add listener for button + (add new table)
    activateMenuTable();
    //Save new created Table
    $("#addTableModal").on("click", "#createNewTableButton", function () {
        var instanceTable = new Table();
        instanceTable.name = $("#createNewTableInput").val();
        instanceTable.saveURL = saveURL;
        $('#addTableModal').modal('hide');
        instanceTable.save();
    });
};

/*
 * Sends dataJson to URL, on ajaxcomplete will run ajaxComplete method of instance object
 * @param {STring} URL - Url were to send dataJson
 * @param {String} dataJson - Json plain object
 * @param {Object} instance - will be object that called sendJSONAssnc or the object that need some data from server
 * @returns {Boolean} true if dataJson was send to server successfully
 */
$.sendJSONAssnc = function (URL, dataJson, instance) {
    var response;
    var ok = false;
    if (!isJson(dataJson)) {
        ok = false;
    }
    else {
        $.ajax({
            url: URL,
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: dataJson,
            success: function (data) {
                response = data;
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr);
                $('html').html(xhr.responseText);
            },
            /*
             * On ajax complete, i will call ajaxComplete(response) function of instance
             */
            complete: function () {
                if (instance && ok)
                    instance.complete(response);
            }
        });
    }
    return ok;
};

function isJson(str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}


/*
 Robot.prototype = Person.prototype;        // Set prototype to Person's
 Robot.prototype.constructor = Robot;   // Set constructor back to Robot
 */

ObserverList = function () {
    this.observerList = [];
};
$.extend(ObserverList.prototype, {
    add: function (obj) {
        return this.observerList.push(obj);
    },
    count: function () {
        return this.observerList.length;
    },
    get: function (index) {
        if (index > -1 && index < this.observerList.length) {
            return this.observerList[ index ];
        }
    },
    indexOf: function (obj, startIndex) {
        var i = startIndex;

        while (i < this.observerList.length) {
            if (this.observerList[i] === obj) {
                return i;
            }
            i++;
        }

        return -1;
    },
    removeAt: function (index) {
        this.observerList.splice(index, 1);
    }
});
function Observable() {
    this.observers = new ObserverList();
}
$.extend(Observable.prototype, {
    addObserver: function (observer) {
        this.observers.add(observer);
    },
    removeObserver: function (observer) {
        this.observers.removeAt(this.observers.indexOf(observer, 0));
    },
    notify: function (context) {
        var observerCount = this.observers.count();
        for (var i = 0; i < observerCount; i++) {
            var obs = this.observers.get(i);
            obs.update(context);
        }
    }
});


// The Observer
var Observer = function () {
    /*
     * To be implemented by extended class
     */
    this.update = function (context) {//to be implemented
    };
}



var ListPanel = function (listPanel, addElement, modal, addURL) {
    this.listPanel = listPanel;
    this.addElement = addElement;
    this.modal = modal;
    this.addURL = addURL;
};
ListPanel.prototype = Observer.prototype;        // Set prototype to Observer's
ListPanel.prototype.constructor = ListPanel;    // Set constructor back to ListPanel
$.extend(ListPanel.prototype, {
    init: function () {
        var thiz = this;
        //add new element to wardrobe
        $(this.listPanel).on("click", this.addElement, function () {
            $(thiz.modal).modal("show");
        });
    },
    update: function (context) {
        var tableID= $(this.addElement).attr("table-id");
        $.sendJSONAssnc(this.addURL, JSON.stringify($.extend(context, {'id':tableID})), this);
    },
    ajaxComplete: function (response) {
        //TODO
    }
});

/*
 * Modal that is used to save data on server
 */
var SaveModal = function (modal, saveButon, type) {
    Observable.call(this);
    this.saveButon = saveButon;
    this.modal = modal;
    this.type = type;
    this.inputValues = {};
};
SaveModal.prototype = Observable.prototype;        // Set prototype to Observer's
SaveModal.prototype.constructor = SaveModal;    // Set constructor back to ListPanel
$.extend(SaveModal.prototype, {
    init: function () {
        var thiz = this;
        $(this.modal).on("click", this.saveButon, function () {
            var ok = true;
            
            if (thiz.type === "A") {
                thiz.inputValues = {'name': "", 'url': ""};
                $(thiz.modal + " input").each(function () {
                    if ($(this).val() !== "") {
                        if ($(this).attr("name") === "name")
                            thiz.inputValues.name = $(this).val();
                        if ($(this).attr("name") === "url")
                            thiz.inputValues.url = $(this).val();
                    } else {
                        alert($(this).attr("name") + " non puo essere vuouto!");
                        ok = false;
                    }
                });
            }
            
            if (ok) {
                $(thiz.modal + " input").each(function () {
                    $(this).val("");

                });
                thiz.hide();
                thiz.notify(thiz.inputValues);
            }
        });
    },
    show: function () {
        $(this.modal).modal('show');
    },
    hide: function () {
        $(this.modal).modal('hide');
    }
});


var Table = function () {
};
$.extend(Table.prototype, {
    name: "",
    saveURL: "",
    closeURL: "",
    crentOpenedTableId: "",
    init: function (saveUrl, closeUrl) {
        this.saveURL = saveUrl;
        this.closeURL = closeUrl;

        var thiz = this;
        /*
         * Init Listeners
         */
        //Close Table Listener
        $("body").on("click", ".remove", function () {
            var idTable = $(this).attr("table-id");
            /*
             * Set current table id
             */
            thiz.crentOpenedTableId = $('#navbar-up li:nth-last-child(3)').attr("table-id");
            thiz.closeTable(idTable);
        });


    },
    save: function () {
        var thiz = this;
        var responseFromServ;
        $.ajax({
            url: thiz.saveURL,
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            //data to be sent
            data: JSON.stringify({'name': thiz.name}),
            success: function (data) {
                responseFromServ = data;
                console.log("resp:" + responseFromServ);
                console.log("data " + data);
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            },
            complete: function () {
                thiz.open(responseFromServ.idTable);
            }
        });
        console.log("saved");
    },
    open: function (id) {
        $.ajax({
            url: '/stageAlex/workingarea/' + id,
            dataType: 'html',
            success: function (response) {
                //console.log($('#navbar-up').html(response));
                var result = $('<div />').append(response).find('#navbar-up').html();
                $('#navbar-up').html(result);

                //focus on new table 
                /*$('#navbar-up li').each(function () {
                 $(this).removeClass("active");
                 });
                 $('#navbar-up li:nth-last-child(2)').addClass("active");*/

            }
        });
        ///this.openUtils.loadTable(5);
    },
    /*openUtils: {
     loadTable: function(id){
     console.log(id);
     }
     },
     /*
     * Close table
     */
    closeTable: function (tableId) {
        var thiz = this;
        console.log(thiz.closeURL);
        $.ajax({
            url: thiz.closeURL,
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            //data to be sent
            data: JSON.stringify({'id': tableId}),
            success: function (data) {
                console.log(data);
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            },
            complete: function () {
                console.log("open table with id " + thiz.crentOpenedTableId);
                thiz.open(thiz.crentOpenedTableId);
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