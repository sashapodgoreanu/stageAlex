/**
 * This funcion get from server suggestions with tags. 
 * @param {String} requestUrl - URL of Rest Controller
 * @returns Jquery
 */
function performTagging(requestUrl) {
    //
    $('.tag-container').on("keydown.autocomplete",/*input class*/".input-tag", function (event, ui) {
        $(this).autocomplete({
            //The delay in milliseconds between when a keystroke occurs and when a search is performed.
            delay: 500,
            //minimum number of characters that the request to be sent to server
            minLength: 2,
            //if input.lenght()  >=  minLength,  execute
            source: function (request, response) {
                //ajax call
                $.ajax({
                    url: requestUrl,
                    type: 'POST',
                    contentType: 'application/json',
                    //data to be sent
                    data: JSON.stringify({'value': request.term}),
                    dataType: 'json',
                    success: function (data) {
                        //fill autocomplete suggestions box
                        response(data);
                    }
                });
            },
            //if select an item from autocomplete suggestions box
            select: function (event, ui) {
                //Add a new input after selecting a value from autocomplete suggestions box
                
                //remove all next inputs if there is one and update .cloneable class
                var div = $(this).parent().parent();
                if (!div.hasClass( "cloneable" ) ){
                    div.addClass('cloneable');
                }
                div.nextAll().remove();
                var clonedHtml = $('.cloneable').clone();
                div.removeClass('cloneable');
                
                //change label value with selected value 
                clonedHtml.find('.label-tag').empty();
                clonedHtml.find('.label-tag').append(ui.item.label);
                
                clonedHtml.find('.input-tag').val('');
                div.after(clonedHtml);
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