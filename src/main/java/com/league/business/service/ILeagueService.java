package com.league.business.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.league.data.dto.LeagueDto;

public interface ILeagueService {
	
	public List<LeagueDto> getLeagueList();

	public void saveLeague(LeagueDto league);
	
	public LeagueDto getLeagueById(Long id);

	public ResponseEntity<?> deleteLeague(Long id);

	public ResponseEntity<?> getTeamList(Long id);
	
	public boolean isExist(String leagueName);
	
	public boolean isExist(String leagueName, long leagueID);

}
