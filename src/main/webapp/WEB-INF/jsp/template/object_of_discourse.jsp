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
        <label for="autocomplete" class="col-sm-3 control-label label-tag">Mine: </label>
        <div id="autocompleteContainer" class="autocompleteContainer">
            <span class="atag">
                <span class="wtag">TAG</span> 
                <span class="ctag"><a>x</a></span>
            </span>
        </div>
        
        <label for="tagContainer" class="col-sm-3 control-label label-tag">Others: </label>
        <div id="tagContainer" class="tagContainer">
            <input id="autocompleteOthers" class="autocomplete-hidden input-tag form-control">
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

