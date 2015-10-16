/* 
 * 
 */

/**
 * Contains all Gui elements: object -> right click -> Resource Properties
 * @param {type} showTagsId
 * @param {type} contextMenuId
 * @returns {GUI_ood}
 */
var GUI_ood = function (showTagsId, contextMenuId) {
    //Variables
    var thiz = this;
    this.showTagsId = showTagsId;
    this.contextMenuId = contextMenuId;
    //Functions
    this.init = function () {
        //initialize listeners
        //listener that hides contextMenuId
        //prevent triggering of this event if contextMenuId is not visible
        $("body").on("click", function (e) {
            e.target;
            if ($(thiz.contextMenuId).css('display') != 'none')
                $(thiz.contextMenuId).hide();
        });

        //on click on show tags opens a list menu where the user 
        //can select to show or
        //hide trash or shared tags
        $("body").on("click", this.showTagsId, function (e) {
            $(thiz.contextMenuId).css({
                display: "block"
            });
            $(thiz.contextMenuId).position({
                my: "right top",
                at: "right bottom",
                of: thiz.showTagsId
            });
            //prevent triggering of other listeners
            e.stopPropagation();
        });
        //will hide list menu of tags type
        $("body").on("click", thiz.contextMenuId, function (e) {

            //prevent triggering of other listeners
            e.stopPropagation();
        });
    };
};

var ShowHidePSDtags = function (contextMenuShowHideTagsId, seeSharedId, seePersonalId, seeDeletedId) {
    this.contextMenuShowHideTagsId = contextMenuShowHideTagsId;
    this.seeSharedId = seeSharedId;
    this.seePersonalId = seePersonalId;
    this.seeDeletedId = seeDeletedId;

    this.init = function () {
        var thiz = this;
        $(this.contextMenuShowHideTagsId).on("change", "input",
                function () {
                    switch ($(this).attr("id")) {
                        case $(thiz.seeSharedId).attr("id"):
                            if ($(this).is(":checked"))
                                $(".sharedtag").show();
                            else
                                $(".sharedtag").hide();
                            break;
                        case $(thiz.seePersonalId).attr("id"):
                            if ($(this).is(":checked"))
                                $(".personaltag").show();
                            else
                                $(".personaltag").hide();
                            break;
                        case $(thiz.seeDeletedId).attr("id"):
                            if ($(this).is(":checked"))
                                $(".deletedtag").show();
                            else
                                $(".deletedtag").hide();
                            break;
                    }
                });
    };
};


