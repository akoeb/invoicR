$( document ).ready(function() {
// Handler for .ready() called.
});

function loadProjectList() {
	
	$.getJSON( "/rest/projects/", function( data ) {
		var items = [];
		$.each( data, function( key, val ) {
			items.push( "<li id='" + key + "'>" + val + "</li>" );
		});
		
		$( "<ul />", {
			html: items.join( "" )
		}).appendTo( "#proj-list" );
	});
	
	
}