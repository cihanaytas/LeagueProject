	$(document).ready(function () {

	    $("#leagueForm").submit(function (event) {

	        event.preventDefault();

	        var leagueForm = {}
	        leagueForm["country"] = $("#country").val();
	        leagueForm["leagueName"] = $("#leagueName").val();
	        leagueForm["numOfTeams"] = $("#numOfTeams").val();
	        

	        $.ajax({
	            type: "POST",
	            contentType: "application/json",
	            url: "/saveleague",
	            data: JSON.stringify(leagueForm),
	            dataType: 'json',
	            cache: false,
	            timeout: 600000,
	            success: function (data) {
	            	window.location.href = "/";
	
	            },
	            error: function (e) {
	            	 var json = JSON.stringify(e.
	            			 responseJSON, null, 4);
	                 $('#feedback').html(json);
 	            	
	            }
	        });

	    });

	});
	
	
