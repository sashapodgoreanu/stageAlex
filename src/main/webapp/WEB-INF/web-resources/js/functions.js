/**
 * This funcion get from server suggestions with tags. 
 * @param {String} requestUrl - URL of Rest Controller
 * @returns Jquery
 */
//Globals
$.rightCliked = new Object();
$.URLs = {};

//Functions
//
//Old Tag 
//Non usato
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
    $("body").on("tipo1", function (e) {
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
var initTablesNav = function (saveURL, closeURL, workingareaURL) {
    //create new table
    var instanceTable = new Table(saveURL, closeURL, workingareaURL);
    //Add listener for button + (add new table)
    //assign event to body because .tables-nav will be replaced with new one from server
    $("body").on("click", ".tables-nav a", function (event) {
        event.preventDefault();
        var thiz = this;
        //on click + button
        if ($(this).attr('id') === 'addTable') {
            //show modal to insert table name
            $('#addTableModal').modal('show');
        } else //else was clicked a Table name
        {
            window.location = $(thiz).attr('id');
        }
    });
    //Save new created Table
    $("#addTableModal").on("click", "#createNewTableButton", function () {

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
var Observer = function () {
    /*
     * To be implemented by extended class
     */
    this.update = function (context) {//to be implemented
    };
};



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
        var tableID = $(this.addElement).attr("table-id");
        $.sendJSONAssnc(this.addURL, JSON.stringify($.extend(context, {'id': tableID})), this);
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


var Table = function (saveUrl, closeUrl, workingareaUrl) {
    this.workingareaUrl = workingareaUrl;
    this.saveURL = saveUrl;
    this.closeURL = closeUrl;
};

$.extend(Table.prototype, {
    name: "",
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
        var succes = false;
        $.ajax({
            url: thiz.saveURL,
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            //data to be sent
            data: JSON.stringify({'name': thiz.name}),
            success: function (data) {
                succes = true;
                responseFromServ = data;
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            },
            complete: function () {
                if (succes)
                    window.location = responseFromServ.idTable;
            }
        });
        console.log("saved");
    },
    open: function (id) {
        var thiz = this;
        var URL = thiz.workingareaUrl + "" + id
        $.ajax({
            url: URL,
            dataType: 'html',
            success: function (response) {
                //refresh navbar
                var result = $('<div />').append(response).find('#navbar-up').html();
                $('#navbar-up').html(result);
                //load table
                result = $('<div />').append(response).find('#main').html();
                $('#main').html(result);
            }
        });
        //focus on new table 
        $('#navbar-up li').each(function () {
            $(this).removeClass("active");
        });
        $('#navbar-up li:nth-last-child(2)').addClass("active");
    },
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

/*
 * Right click on a object -> Properties 
 */
var ObjProperties = function (idMenu, idProp) {
    this.idMenu = idMenu;
    this.idProp = idProp;
    this.idObj = "";
    this.panels = [];
};
$.extend(ObjProperties.prototype, {
    position: 0,
    init: function () {
        //to use inside jquery functions
        var thiz = this;
        //hide on init
        thiz.hide();
        //
        $(function () {
            var elem = $(thiz.idMenu).find(".panel-collapse");
            var sections = elem.parent().siblings();
            var L = 0;
            $.each(sections, function (i, elem) {
                if (L !== 0)
                    $(this).addClass('closed');
                L++;
            });
            console.log(L);
        });

        //Listener that close Properties-rightclickMenu
        $(this.idMenu).on("click", ".glyphicon-remove-circle", function (e) {
            e.preventDefault();
            thiz.hide();
            thiz.destroyPanels();
            thiz.closeAllPanels(0);
        });

        /*
         * Show Object Properties
         */
        $(this.idProp).on("click", function (e) {
            thiz.show(e);
            thiz.loadPanels();
        });

        //Drag On
        $(this.idMenu).draggable({
            containment: 'window',
            scroll: false,
            handle: '#propBar',
            cursor: "crosshair"
        });

        /*
         * Handles the close and open panels of object Properties
         */
        $(".panel-collapse").click(function () {

            var thisSlide = $(this).parent();
            var content1;
            if (thisSlide.hasClass("open")) {
                content1 = thisSlide.children().next();

                thisSlide.removeClass("open");
                thisSlide.addClass("closed");
                content1.slideToggle(300);
                $(".triangle").each(function () {
                    var thiz = this;
                    $(this).attr("class", "glyphicon glyphicon-triangle-right triangle");
                });
            } else {
                //close the one that are open
                thiz.closeAllPanels(300);
                //open the clicked one
                content1 = thisSlide.children().next();
                thisSlide.removeClass("closed");
                thisSlide.addClass("open");
                content1.slideDown(300, function () {
                    //execute this after slideToggle is done
                    var spanArrow = thisSlide.find(".triangle");
                    spanArrow.attr("class", "glyphicon glyphicon-triangle-bottom triangle");
                });
            }
        });

        //drag and scroll setup
        $(window).scroll(function () {
            var top = $(window).scrollTop();
            var position = thiz.position;
            $(thiz.idMenu).css({
                top: position - top
            });

        });
        $("body").on("mousedown", "#propBar", function (event, ui) {
            thiz.closeAllPanels(0);
        })/*.children().on("mousedown",function(){return false;})*/;

        $(this.idMenu).on("dragstop", function (event, ui) {
            thiz.position = ui.position.top;
        });

    },
    addPanel: function (panel) {
        this.panels.push(panel);
    },
    loadPanels: function () {
        for (var i = 0; i < this.panels.length; i++) {
            this.panels[i].load(this.idObj);
        }
    },
    destroyPanels: function () {
        for (var i = 0; i < this.panels.length; i++) {
            this.panels[i].destroy();
        }
    },
    show: function (event) {
        $(this.idMenu).show();
        $(this.idMenu).position({
            my: "center center",
            of: event,
            collision: "fit"
        });
        this.position = $(this.idMenu).position().top;
    },
    hide: function () {
        $(this.idMenu).hide();
    },
    closeAllPanels: function (duration) {
        //close the one that are open
        var openSlide = $("#hiddenMenu").find(".open");
        if (openSlide !== null) {
            openSlide.removeClass("open");
            openSlide.addClass("closed");
            var content1 = openSlide.children().next();
            content1.slideUp(duration, function () {
                //execute this after slideUp is done
                var spanArrow = openSlide.find(".triangle");
                spanArrow.attr("class", "glyphicon glyphicon-triangle-right triangle");
            });
        }
    },
    setIdObject: function (idObj) {
        this.idObj = idObj;
    }

});

var Panel = function () {
    this.hide = function () {
    };
    this.show = function () {
    };
    this.load = function () {
    };
    this.destroy = function () {
    };
};


//Object of discourse
var ObjectOfDiscourse = function (conectedUserId, personalContainerId, sharedContainerId, personalTagsUrl, sharedTagsUrl) {
    this.conectedUserId = conectedUserId;
    this.personalContainerId = personalContainerId;
    this.sharedContainerId = sharedContainerId;
    this.personalTagsUrl = personalTagsUrl;
    this.sharedTagsUrl = sharedTagsUrl;
    this.personalTags = [];
    this.sharedTags = [];
    this.idObj;
    this.init = function () {
        var thiz = this;

        $(this.sharedContainerId + ", " + this.personalContainerId)
                .on("click", ".actionTagRRSD", function () {
                    var action;
                    var idTag = $(this).parents(".atag").attr("data-idtag");
                    var ownerId = $(this).parents(".atag").attr("data-idowner");
                    var deleted = $(this).parents(".atag").attr("data-deleted") === "true" ? true : false;

                    $(this).tooltip({
                        position: {
                            my: "left bottom-5",
                            at: "left top"
                        }
                    });
                    if ($(this).hasClass("glyphicon-repeat")) {
                        if (thiz.conectedUserId === ownerId)
                            action = "restore";
                        else
                        if (deleted)
                            action = "like"
                        else
                            action = "default";
                    }

                    else if ($(this).hasClass("glyphicon-remove")) {
                        if (thiz.conectedUserId === ownerId)
                            action = "delete";
                        else
                            action = "unlike";
                    }
                    else if ($(this).hasClass("glyphicon-share-alt"))
                        action = "share";
                    else if ($(this).hasClass("glyphicon-heart"))
                        action = "like";
                    var clicked = this;
                    $.when($.ajax({
                        url: $.URLs.tagManagerURL + "/doAction/" + action + "/" + idTag,
                        type: 'GET',
                        contentType: 'application/json',
                        dataType: 'json'
                    })).then(
                            function (ok, textStatus, jqXHR) {
                                if (ok) {
                                    thiz.destroy();
                                    thiz.load(thiz.idObj);
                                } else {
                                    $(clicked).attr("title", "Work in Progress:: " + action + "ing!");
                                    $(clicked).tooltip("open");
                                }
                            });

                });
    };
    this.load = function (objId) {
        var thiz = this;
        var completed = 0;
        var ok = false;
        //ajax call
        $.ajax({
            url: thiz.personalTagsUrl,
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            //data to be sent
            data: JSON.stringify({'id': objId}),
            success: function (response) {
                ok = true;
                thiz.personalTags = response;
            },
            complete: function () {
                if (ok) {

                    completed++;
                }
                if (completed == 2)
                    thiz.complete();
            }
        });
        ok = false;
        $.ajax({
            url: thiz.sharedTagsUrl,
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            //data to be sent
            data: JSON.stringify({'id': objId}),
            success: function (response) {
                ok = true;
                thiz.sharedTags = response;
            },
            complete: function () {
                if (ok) {
                    completed++;
                }
                if (completed == 2)
                    thiz.complete();
            }
        });
    };
    this.destroy = function () {
        this.personalTags = [];
        this.otherTags = [];
        $(this.personalContainerId).empty();
        $(this.sharedContainerId).empty();
    };
    this.complete = function () {
        this.addTags();
    };
    this.addTags = function () {
        this._addToViewPersonalTags();
        this._addToViewSharedTags();


    };

    this._addToViewPersonalTags = function () {
        //for every personal tag, add buttons, add tag to view
        for (var i = 0; i < this.personalTags.length; i++) {
            var idUserDetails = this.personalTags[i].ownerId;
            var shared = this.personalTags[i].shared;
            var deleted = this.personalTags[i].deleted;
            var color;
            //color of this tag is the color of tag owner user icon
            $(".semtUsers").each(function () {
                if ($(this).attr("data-user-id") == idUserDetails)
                    color = $(this).css("color");
            });

            //clone this div
            var divTag = $("#cloneableTagHtml")
                    .clone()
                    .removeAttr("id style")
                    .css({
                        "background": color,
                        "color": getContrastYIQ(color)
                    })
                    .addClass("atag")
                    .attr({
                        "data-idTag": this.personalTags[i].id,
                        "data-idOwner": this.personalTags[i].ownerId
                    });
            //add Tag value to cloned div to the specific div
            $(divTag).find("#valueTag").append(this.personalTags[i].value);

            //Add remove button if is not deleted
            var rem = $(document.createElement("span")).addClass("actionTagRRSD");
            if (!deleted)
                $(rem).addClass("glyphicon glyphicon-remove");
            else
                $(rem).addClass("glyphicon glyphicon-repeat");
            $(divTag).find("#remTag")
                    .append(rem)
                    .removeAttr("id");


            //add share button if is not shared
            var span = $(document.createElement("span")).addClass("actionTagRRSD");
            if (!shared && !deleted)
                $(span).addClass("glyphicon glyphicon-share-alt");
            else
                $(span).addClass("glyphicon");
            $(divTag).find("#ldTag")
                    .append(span)
                    .removeAttr("id");

            //add class to tag depending what tipe of tag it is
            if (!deleted) {
                if (shared)
                    $(divTag).addClass("sharedtag");
                else
                    $(divTag).addClass("personaltag");
            }
            else
                $(divTag).addClass("deletedtag");
            //append cloned div to view
            $(this.personalContainerId).append(divTag);
        }
    };
    this._addToViewSharedTags = function () {
        //for every shared tag, add buttons, add tag to view
        for (var i = 0; i < this.sharedTags.length; i++) {
            var idUserDetails = this.sharedTags[i].ownerId;
            var liked = this.sharedTags[i].liked;
            var deleted = this.sharedTags[i].deleted;
            var color;
            //tag color is the color of tag owner
            $(".semtUsers").each(function () {
                if ($(this).attr("data-user-id") == idUserDetails)
                    color = $(this).css("color");
            });

            //clone this div
            var divTag = $("#cloneableTagHtml")
                    .clone()
                    .removeAttr("id style")
                    .css({
                        "background": color,
                        "color": getContrastYIQ(color)
                    })
                    .addClass("atag")
                    .attr({
                        "data-idTag": this.sharedTags[i].id,
                        "data-idOwner": this.sharedTags[i].ownerId,
                        "data-deleted": this.sharedTags[i].deleted,
                        "data-shared": this.sharedTags[i].shared,
                        "data-liked": this.sharedTags[i].liked
                    });
            //add Tag value to cloned div to the specific div
            $(divTag).find("#valueTag").append(this.sharedTags[i].value);

            //Add remove button if is not deleted
            var rem = $(document.createElement("span")).addClass("actionTagRRSD");
            if (deleted || liked == 0) {
                $(rem).addClass("glyphicon glyphicon-repeat");
            } else {
                $(rem).addClass("glyphicon glyphicon-remove ");
            }
            $(divTag).find("#remTag")
                    .append(rem)
                    .removeAttr("id");


            //add class to tag depending what tipe of tag it is
            if (deleted || liked == 0) {
                $(divTag).addClass("deletedtag");
            } else {
                $(divTag).addClass("sharedtag");
                var span = $(document.createElement("span")).addClass("actionTagRRSD");
                $(span).addClass("glyphicon glyphicon-heart");
                $(divTag).find("#ldTag")
                        .append(span)
                        .attr("data-idShare", this.sharedTags[i].id)
                        .removeAttr("id");
            }

            //append cloned div to view
            $(this.sharedContainerId).append(divTag);
        }
    };
    this.setIdObject = function (idObj) {
        this.idObj = idObj;
    };
};
//implements Panel
ObjectOfDiscourse.prototype = Panel.prototype;
ObjectOfDiscourse.prototype.constructor = ObjectOfDiscourse;

var InputTagAdder = function (
        searchSharedTagsId, addToSharedTagsId, addTaggButtonId,
        autocompleteTagId, tagManagerURL, tagViewObj) {
    this.searchSharedTagsId = searchSharedTagsId;
    this.addToSharedTagsId = addToSharedTagsId;
    this.addTaggButtonId = addTaggButtonId;
    this.autocompleteTagId = autocompleteTagId;
    this.tagManagerURL = tagManagerURL;
    this.$personalTagsContainer = $(tagViewObj.personalContainerId);
    this.$sharedTagsContainer = $(tagViewObj.personalContainerId);
    this.ood = tagViewObj;
    this.idObj = "";
    this.tagToBeAdded = {};

    this.init = function () {

        $("body").on("change", this.searchSharedTagsId, function () {
            var $checked = $(".sschecked1", $(this).parent());
            if ($(this).is(":checked")) {
                $checked.addClass("glyphicon-ok-circle");
                $checked.removeClass("glyphicon-search");
            } else {
                $checked.removeClass("glyphicon-ok-circle");
                $checked.addClass("glyphicon-search");
            }
        });
        $("body").on("change", this.addToSharedTagsId, function () {
            var $checked = $(".sschecked1", $(this).parent());
            if ($(this).is(":checked")) {
                $checked.addClass("glyphicon-ok-circle");
                $checked.removeClass("glyphicon-share-alt");
            } else {
                $checked.removeClass("glyphicon-ok-circle");
                $checked.addClass("glyphicon-share-alt");
            }
        });
        var thiz = this;
        //autocomplete tag
        $(this.autocompleteTagId).autocomplete({
            source: function (request, response) {
                var type = $(thiz.searchSharedTagsId).is(":checked") ? "shared" : "personal";
                $.when($.ajax({
                    url: thiz.tagManagerURL + "/get-tags/" + type + "/" + request.term,
                    type: 'GET',
                    contentType: 'application/json',
                    dataType: 'json'
                })).then(
                        function (data, textStatus, jqXHR) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.value,
                                    value: item
                                };
                            }));
                        });

            },
            select: function (event, ui) {
                event.preventDefault();
                $(thiz.autocompleteTagId).val(ui.item.value.value);
                thiz.tagToBeAdded = ui.item.value;
            }
        });

        $(thiz.autocompleteTagId).tooltip({
            position: {
                my: "left bottom-5",
                at: "left top"
            }
        });


        //Sends to server the new tag
        //it can be personal or shared
        $("body").on("click", this.addTaggButtonId, function () {
            var addToShared = $(thiz.addToSharedTagsId).is(":checked") ? "shared" : "personal";
            var empty = $(thiz.autocompleteTagId).val().length === 0 ? true : false;
            if (empty) {
                $(thiz.autocompleteTagId).attr("title", "Insert a value!");
                $(thiz.autocompleteTagId).tooltip("open");
            } else {
                $.when($.ajax({
                    url: thiz.tagManagerURL + "/add-tag/" + addToShared + "/" + $(thiz.autocompleteTagId).val(),
                    type: 'POST',
                    contentType: 'application/json',
                    dataType: 'json'
                })).then(function (ok, textStatus, jqXHR) {
                    if (ok) {
                        thiz.ood.destroy();
                        thiz.ood.load(thiz.idObj);
                        thiz.destroy();
                    } else {
                        $(thiz.autocompleteTagId).attr("title", "Canot add this tag!");
                        $(thiz.autocompleteTagId).tooltip("open");
                    }
                }).fail(function (data, textStatus, jqXHR) {
                    alert("No tag was added");
                });
            }
        });

    };

    this.setIdObject = function (idObj) {
        this.idObj = idObj;
    };

    this.destroy = function () {
        $(this.autocompleteTagId).val("");
        $(this.autocompleteTagId).attr("title", "");
    };

    this.load = function () {
    };
};

//implements Panel
InputTagAdder.prototype = Panel.prototype;
InputTagAdder.prototype.constructor = InputTagAdder;


/*
 * This function returns black or white depending on backgroundcolor that the 
 * combination color - backgroundcolor to be more easy readeable
 * @param {type} color background color 
 * @returns {String} black or white
 */
function getContrastYIQ(color) {

    /**
     * Converts a color to hex color format
     * @param {type} color in rgb or hex format
     * @returns {String} color in hex format
     */
    function colorToHex(color) {
        if (color.substr(0, 1) === '#') {
            return color.substr(1, color.length - 1);
        }
        var digits = /(.*?)rgb\((\d+), (\d+), (\d+)\)/.exec(color);

        var red = parseInt(digits[2]);
        var green = parseInt(digits[3]);
        var blue = parseInt(digits[4]);

        var rgb = blue | (green << 8) | (red << 16);
        return digits[1] + rgb.toString(16);
    }
    ;

    var hexcolor = colorToHex(color);
    var r = parseInt(hexcolor.substr(0, 2), 16);
    var g = parseInt(hexcolor.substr(2, 2), 16);
    var b = parseInt(hexcolor.substr(4, 2), 16);
    var yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000;
    return (yiq >= 128) ? 'black' : 'white';
}
var z_index = 100;
setup1 = function () {
    $("p", "#tableTagContainer").draggable({
        containment: "document",
        revert: "invalid",
        drag: function (event, ui) {
            var a = ui;
            var p = event.target;
            $(p).css({
                "z-index": ""+z_index++
            }).removeClass("tipo1");
        }
    });
    $(".droppable, #centerTable").droppable({
        drop: function (event, ui) {
        }
    });
}