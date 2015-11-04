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
        helperClassesItem: null,
        position: {
            my: "left top",
            at: "left bottom",
            collision: "none"
        },
        responseObject: {
            value: "value",
            label: "label",
            id: "id"
        }
    },
    _init: function () {
        this._search();
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
    _searchMinLength: function () {
        this._inputValue = this._value();
        this._emptyAppended();
        this._initSource(this._inputValue);

        if (this._inputValue.length == 0)
            if (this._inputValue.length < this.options.minLength)
                this._emptyAppended();

    },
    _value: function () {
        return this.element.val();
    },
    _search: function (value) {
        var that = this;
        this.element.addClass("ui-autocomplete-loading");
        $.when(this._searchMinLength()).done(function(){
            that._appendMenu();
            that.element.removeClass("ui-autocomplete-loading");
        });
    },
    _initSource: function () {
        var that = this;
        var jqXHR = $.ajax({
            url: that.options.URL,
            data: that._inputValue,
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
            that.source = $.map(data, function (item) {
                var a1 = item[that.options.responseObject.label];
                var a1 = item[that.options.responseObject.label];
                    return {
                        label: item[that.options.responseObject.label],
                        value: item[that.options.responseObject.value],
                        id: item[that.options.responseObject.id]
                    };
            });
            that.source;
        }
    },
    
    _appendMenu: function () {
        var a = this.source;
        var wrap = $('<div>').append(this._renderMenu($("<ul>"), this.source));
    },
    
    _renderMenu: function (ul, items) {
        var that = this;
        $.each(items, function (index, item) {
            while (index <= that.options.maxDisplayItems) {
                that._renderItemData(ul, item);
            }
            //stop cicle
            return false;
        });
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
        }
        this._super(key, value);
    },
    _constrainURL: function (value) {
        if (value[value.length - 1] !== '/')
            return value.concat("/");
        else
            return value;
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
    
    _inputValue: null,
    
    source: null



});
