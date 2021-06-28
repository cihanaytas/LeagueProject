package com.league.business.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.league.business.service.ILeagueService;
import com.league.data.dto.LeagueDto;
import com.league.data.dto.TeamDto;
import com.league.data.entity.League;
import com.league.data.entity.Team;
import com.league.data.respository.LeagueRepository;
import com.league.utils.MyMapper;

@Service
public class LeagueServiceImpl implements ILeagueService{
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired 
	private MyMapper mapper;

	@Override
	public List<LeagueDto> getLeagueList() {
		Iterable<League> leagueList = leagueRepository.findAll();
		List<LeagueDto> leagueListDto = new ArrayList<LeagueDto>();
		
		for(League league : leagueList) {
			LeagueDto leagueDto = new LeagueDto();
			mapper.convertToDto(league, leagueDto);
			leagueListDto.add(leagueDto);
		}
		

		return leagueListDto;
				
	}
	
	@Override
	public void saveLeague(LeagueDto leagueDto) {
		League league = new League();
		
		//for update
		if(leagueDto.getLeagueID()!=0) {
			league = leagueRepository.findById(leagueDto.getLeagueID()).orElse(null);
		}
		
		mapper.convertToEntity(league, leagueDto);
		leagueRepository.save(league);
	}
	
	
	
	@Override
	public LeagueDto getLeagueById(Long id) {
		League league = leagueRepository.findById(id).orElse(null);
		LeagueDto leagueDto = new LeagueDto();
		
		if(league!=null) 
		{
			mapper.convertToDto(league, leagueDto);
			return leagueDto;
		}
		
		return null;

	}
	

	
	@Override
	@Transactional
	public ResponseEntity<?> deleteLeague(Long id) {
		
		boolean isExist = leagueRepository.existsById(id);
		if(isExist) {
			 leagueRepository.deleteById(id);
			 return ResponseEntity.ok(id);
		}
		   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	public ResponseEntity<?> getTeamList(Long id) {
		League league = leagueRepository.findById(id).orElse(null);
		if(league!=null) {
			List<Team> teamList = league.getTeams();
			List<TeamDto> teamListDto = new ArrayList<TeamDto>();
			
			for(Team team : teamList) {
				TeamDto teamDto = new TeamDto();
				mapper.convertToDto(team, teamDto);
				teamListDto.add(teamDto);
			}
			
			return new ResponseEntity<>(teamListDto,new HttpHeaders(), HttpStatus.OK);
			
		}
		return new ResponseEntity<>(null,new HttpHeaders(), HttpStatus.NOT_FOUND);

	}

	@Override
	public boolean isExist(String country) {
		if(leagueRepository.isExist(country)!=0)
			return true;
		
		return false;
	}

	@Override
	public boolean isExist(String country, long leagueID) {
		League league = leagueRepository.findById(leagueID).orElse(null);


		if(country.equals(league.getCountry()))
			return false;
		else 
			return isExist(country);
	}

 	

}
