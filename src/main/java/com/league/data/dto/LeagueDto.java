package com.league.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeagueDto {
	
	private long leagueID;
	
	@NotNull
	@Size(min = 5 , max = 20 , message = "League name must be between 5-20 characters ")
	private String leagueName;
	
	@NotNull
	@Size(min = 5 , max = 20 , message = "Country name must be between 5-20 characters ")
	private String country;
	
	private int numOfTeams;
    private List<TeamDto> teams = new ArrayList<TeamDto>();
	
}
