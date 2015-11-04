/*!
 * SemT++ AutoSugest custom JQuery Widget
 * 
 */

$.semt = $.custom || {};

$.widget("semt.autosugest", {
    options: {
        appendTo: this,
        response: null,
        minLength: 3,
        maxDisplayItems: 10,
        URL: null,
        helperClassesItem: "",
        source: [],
        position: {
            my: "left top",
            at: "left bottom",
            collision: "none"
        },
        responseObject: {
            value: "value",
            label: "label",
            id: "id"
        },
        callbacks: []
    },
    _init: function () {
    },
    _create: function () {
        this.element.addClass("autosugest");
        this._on(this.element, {
            //when user inputs a value
            input: function (event) {
                this._search();
            }
        });
    },
    _search: function (value) {
        var that = this;
        this._emptyAppended();
        this.element.addClass("ui-autocomplete-loading");
        $.when(this._searchMinLength()).done(function () {
            that._appendMenu();
            that.element.removeClass("ui-autocomplete-loading");
            for (var i = 0, max = this.options.callbacks.length; i < max; i++) {
                this.options.callbacks[i]();
            }
        });
    },
    _searchMinLength: function () {

        return this._initSource(this._value());

        /*if (this._inputValue.length == 0)
         if (this._inputValue.length < this.options.minLength)
         this._emptyAppended();*/

    },
    _value: function () {
        return this.element.val();
    },
    _initSource: function (candidate) {
        var that = this;
        var jqXHR = $.ajax({
            url: that.options.URL,
            data: candidate,
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                that._response(data);
            },
            error: function () {
                that._response([]);
            }
        });
        return jqXHR;
    },
    _response: function (data) {
        var that = this;
        var da = data;
        if (data !== null && data.length !== 0) {
            that.options.source = $.map(data, function (item) {
                return {
                    label: item[that.options.responseObject.label],
                    value: item[that.options.responseObject.value],
                    id: item[that.options.responseObject.id]
                };
            });
        }
    },
    _appendMenu: function () {                              //to do add class
        $(this.options.appendTo).append(this._renderMenu($("<ul>").attr("id","wardrobeList"), this.options.source));
    },
    _renderMenu: function (ul, items) {
        var that = this;
        for (var i = 0, max = items.length; i < max; i++) {
            that._renderItemData(ul, items[i])
        }
        return ul;
       /* $.each(items, function (index, item) {
            ;/*
            if (index > that.options.maxDisplayItems)
                return false;
        });*/
    },
    _renderItemData: function (ul, item) {
        return this._renderItem(ul, item).data("semt-autosugest-item", item);
    },
    _renderItem: function (ul, item) {
        return $("<li>").text(item.label).addClass(this.options.helperClassesItem).appendTo(ul);
    },
    _emptyAppended: function () {
        $(this.options.appendTo).empty();
    },
    _setOption: function (key, value) {
        if (key === "URL") {
            value = this._constrainURL(value);
        } else if (key === "helperClasses") {
            value = this._constrainHelperClasses(value);
        } else if (key === "callbacks"){
            value = this._constrainCallbacks(value);
        }
        this._super(key, value);
    },
    _constrainURL: function (value) {
        if (value[value.length - 1] !== '/')
            return value.concat("/");
        else
            return value;
    },
    _constrainCallbacks: function (value) {
        return this.options.callbacks.push(value);
    },
    _constrainHelperClasses: function (value) {
        if (typeof value === 'array') {
            var newVal = "";
            $.each(value, function (index, val) {
                if (index !== value.length - 1)
                    newVal += val + " ";
                else
                    newVal += val;
            });
            return newVal;
        } else
            return value;
    },
});
