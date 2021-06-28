package com.league.business.service;

import org.springframework.http.ResponseEntity;

import com.league.data.dto.TeamDto;

public interface TeamService {

	public void saveTeam(TeamDto teamDto, long leagueID);

	public void deleteTeam(Long id);

	public boolean isExist(String teamName);

//	public TeamDto getTeamByID(Long id);
	
	public TeamDto getTeamByID(Long id);

 

	public long getLeagueIDByTeam(Long teamID);

	public boolean isExist(String teamName, long teamID);
}
