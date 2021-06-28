package com.league.utils;

import org.springframework.stereotype.Component;

import com.league.data.dto.LeagueDto;
import com.league.data.dto.TeamDto;
import com.league.data.entity.League;
import com.league.data.entity.Team;

@Component
public class MyMapper {

	public void convertToEntity(League league, LeagueDto leagueDto) {
		league.setCountry(leagueDto.getCountry());
		league.setLeagueName(leagueDto.getLeagueName());
		league.setNumOfTeams(leagueDto.getNumOfTeams());
	}
	
	public void convertToEntity(Team team, TeamDto teamDto) {
		team.setCity(teamDto.getCity());
		team.setNumOfPlayers(teamDto.getNumOfPlayers());
		team.setTeamName(teamDto.getTeamName());
		
	}

	public void convertToDto(League league, LeagueDto leagueDto) {
		leagueDto.setLeagueID(league.getLeagueID());
		leagueDto.setCountry(league.getCountry());
		leagueDto.setLeagueName(league.getLeagueName());
		leagueDto.setNumOfTeams(league.getNumOfTeams());
	
	}
	
	public void convertToDto(Team team, TeamDto teamDto) {
		teamDto.setTeamID(team.getTeamID());
		teamDto.setLeagueName(team.getLeague().getLeagueName());
		teamDto.setCity(team.getCity());
		teamDto.setNumOfPlayers(team.getNumOfPlayers());
		teamDto.setTeamName(team.getTeamName());
		//player list;

	}
}
