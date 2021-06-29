
getLeagueList();

function deleteLeague(id){
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/deleteleague/" + id,
        data:{ leagueID: id},
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
        	//location.reload();
			getLeagueList();
        },
        error: function (e) {
         	console.log(e)
        }
    });
}




function getLeagueList(){
	$('#leagueTable').dataTable().fnDestroy();
	$.ajax({
		url: "/leaguelist",
		type: "GET",
		dataType: "JSON",
		success:function(data)
		{
			$("#leagueTable").dataTable({
				"data": data,
				"columns": [
					{
						data: null,
						render: function(data, type, full, meta)
						{
						return '<a onClick="getTeamsOfLeague('+data.leagueID+')"  style="color:white" class="btn btn-primary">'+data.country+'</a>';
						
						}
						},
					{data: 'leagueName'},
					{data: 'numOfTeams'},
	 				{
						data: null,
						render: function(data, type, full, meta)
						{
						return '<a onClick="getLeague('+data.leagueID+')" style="color:white"" class="btn btn-primary">Update</a>';
						
						}
					},
					{
						data: null,
						render: function(data, type, full, meta)
						{
						return '<a onClick="deleteLeague('+data.leagueID+')"  style="color:white" class="btn btn-danger">Delete</a>';
						}
					}
				]
			});
		}
	});
}

function getLeague(leagueID){
	window.location.href = "/showleague/" + leagueID
}

function getTeamsOfLeague(leagueID){
	window.location.href = "/showteams/" + leagueID
}

 

