<%-- 
    Document   : object_of_discourse.jsp
    Created on : Oct 7, 2015, 3:35:38 PM
    Author     : Sasha
--%>

<!-- object_of_discourse.jsp -->

<!-- Tags Panel -->
<form class="form-horizontal tag-container margin-20-right">
    <div class="row padding-top-15 padding-bottom-15">
        <button id ="showTags" type="button" class="btn btn-default tipo2 pull-right btn-sm">
            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
        </button>
    </div>
    <div class="form-group cloneable">
        <label for="personalContainer" class="col-sm-3 control-label label-tag">Mine: </label>
        <div id="personalContainer" class="autocompleteContainer">
        </div>

        <label for="tagContainer" class="col-sm-3 control-label label-tag">Others: </label>
        <div id="sharedContainer" class="tagContainer">
        </div>
        <div id="searchTagContainer" class="searchTag">
            <input id="searchTag" class="autocomplete-hidden input-tag form-control">
        </div>
        <div class="" >
            <div data-toggle="buttons">
                <label class="btn btn-default active pull-left">
                    <input id="searchSharedTags" type="checkbox" autocomplete="off" checked> 
                    <span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
                    &nbsp;&nbsp;
                    Search Shared 
                    &nbsp;&nbsp;<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                </label></div>

            <div class="pull-right" data-toggle="buttons">
                <label class="btn btn-default active">
                    <input id="addToSharedTags" type="checkbox" autocomplete="off" checked> 
                    <span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
                    &nbsp;&nbsp;
                    Add to Shared &nbsp;&nbsp;<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                </label>
                &nbsp;&nbsp;
                <span id="addTaggButton" class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            </div>
        </div>
    </div>
</form>

<!----------------- click show/hide tags v1 -------------------------------->
<div id="contextMenuShowHideTagsv1" class="dropdown clearfix" style="z-index: 51;">
    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu" style="display:block;position:static;margin-bottom:5px; text-align: right;">
        <li><a><label for="seeSharedv1">
                    <input id ="seeSharedv1" type="checkbox" checked="true">
                    See Shared &nbsp;&nbsp;<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                </label></a>
        </li>
        <li><a><label for="seePersonalv1">
                    <input id ="seePersonalv1" type="checkbox" checked="true">
                    See Personal &nbsp;&nbsp;<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                </label></a>
        </li>
        <li><a><label for="seeTrashv1">
                    <input id ="seeTrashv1" type="checkbox" checked="true">
                    See Trash &nbsp;&nbsp;<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                </label></a>
        </li>
    </ul>
</div>


<div id="cloneableTagHtml" class="row" style="display: hidden;">
    <div id="valueTag" class="col-md-8 wtag"></div>
    <div class="col-md-4 ctag">
        <div id="remTag"></div>
        <div id="ldTag"></div>
    </div>
</div>
