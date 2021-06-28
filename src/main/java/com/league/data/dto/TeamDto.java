package com.league.data.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamDto {
	
	private long teamID;
	
	@NotBlank(message = "Team Name cannot be empty.")
	private String teamName;	
	
	
	@NotBlank(message = "Team City cannot be empty.")
	private String city;
	
	
	private int numOfPlayers;
	private String leagueName;

	
	
}
