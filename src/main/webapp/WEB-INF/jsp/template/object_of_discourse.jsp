<%-- 
    Document   : resource_properties
    Created on : Oct 7, 2015, 3:35:38 PM
    Author     : Sasha
--%>
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
        <div>
            <a href="#" class="btn btn-default disabled pull-left" role="button">Search Shared &nbsp;&nbsp;<span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
            <div class="pull-right"><a href="#" class="btn btn-default disabled" role="button">Add to Shared &nbsp;&nbsp;<span class="glyphicon glyphicon-user" aria-hidden="true"></span></a> 
                &nbsp;&nbsp;<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            </div>
        </div>
    </div>
</form>

<!----------------- click show/hide tags v1 -------------------------------->
<div id="contextMenuShowHideTagsv1" class="dropdown clearfix" style="z-index: 51;">
    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu" style="display:block;position:static;margin-bottom:5px; text-align: right;">
        <li><a><label for="seeSharedv1">
                    <input id ="seeSharedv1" type="checkbox" value ="">
                    See Shared &nbsp;&nbsp;<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                </label></a>
        </li>
        <li><a><label for="seePersonalv1">
                    <input id ="seePersonalv1" type="checkbox" value ="">
                    See Personal &nbsp;&nbsp;<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                </label></a>
        </li>
        <li><a><label for="seeTrash">
                    <input id ="seeTrash" type="checkbox" value ="">
                    See Trash &nbsp;&nbsp;<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                </label></a>
        </li>
    </ul>
</div>

