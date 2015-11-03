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
        URL: null
    },
    
    _create: function () {
        this.element.addClass("autosugest");
        this._on(this.element, {
            input: function (event) {
                this._searchMinLength(event);
            }
        });
    },
    
    _searchMinLength: function(event){
        var inputValue = this._value();
        if (inputValue.length < this.options.minLength)
           $(this.options.appendTo).empty();
        
    },
    
    _value: function() {
        return this.element.val();
    },
    
    _search: function(){
        
    }

});
