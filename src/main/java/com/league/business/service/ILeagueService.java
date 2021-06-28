package com.league.business.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.league.data.dto.LeagueDto;
import com.league.data.dto.TeamDto;
import com.league.data.entity.League;

public interface ILeagueService {
	
	public List<LeagueDto> getLeagueList();

	public void saveLeague(LeagueDto league);
	
	//public LeagueDto getLeagueByIdd(Long id);
	
	public LeagueDto getLeagueById(Long id);

	public ResponseEntity<?> deleteLeague(Long id);

	//public List<TeamDto> getTeamList(Long id);

	public ResponseEntity<?> getTeamList(Long id);
	
	public boolean isExist(String leagueName);
	
	public boolean isExist(String leagueName, long leagueID);

}
