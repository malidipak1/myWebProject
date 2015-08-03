function analytics(url,data) {
	$.ajax
    ({  
        type: "POST",
        url: url,
        crossDomain:true,
        dataType: 'json',
        async: false,
        data: data,
        success: function() {
        },
    	error: function() {
		},
		complete: function() {
		}

    });
	
}
