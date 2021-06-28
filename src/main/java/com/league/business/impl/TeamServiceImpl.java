package com.league.business.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.league.business.service.ITeamService;
import com.league.data.dto.TeamDto;
import com.league.data.entity.League;
import com.league.data.entity.Team;
import com.league.data.respository.LeagueRepository;
import com.league.data.respository.TeamRepository;
import com.league.utils.MyMapper;

@Service
public class TeamServiceImpl implements ITeamService{
	
	@Autowired 
	private MyMapper mapper;

	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	
	@Override
	public void saveTeam(TeamDto teamDto, long leagueID) {
		Team team = new Team();
		if(teamDto.getTeamID()!=0) {
			team = teamRepository.findById(teamDto.getTeamID()).orElse(null);
			mapper.convertToEntity(team, teamDto);
			teamRepository.save(team);
		}
		
		mapper.convertToEntity(team, teamDto);
		
		League league = leagueRepository.findById(leagueID).orElse(null);
		List<Team> teamList = league.getTeams();
		teamList.add(team);
		
		league.setTeams(teamList);
		league.setNumOfTeams(league.getNumOfTeams()+1);
		
		leagueRepository.save(league);
			
	}
	
 

	@Override
	@Transactional
	public void deleteTeam(Long teamID) {
		League league = teamRepository.findLeagueByTeamId(teamID);
		Team team = teamRepository.findById(teamID).orElse(null);
		
		if(team!=null) {
			league.setNumOfTeams(league.getNumOfTeams()-1);
			leagueRepository.save(league);
			teamRepository.deleteById(teamID);		
		}
//		
//		
//		league.setNumOfTeams(league.getNumOfTeams()+1);		
//		leagueRepository.save(league);
//		teamRepository.deleteById(teamID);		
	}


	@Override
	public boolean isExist(String teamName) {
 
		if(teamRepository.isExist(teamName)==1)
			return true;
		else 
			return false;
	}
	
	
	@Override
	public boolean isExist(String teamName, long teamID) {
		Team team = teamRepository.findById(teamID).orElse(null);
		if(teamName.equals(team.getTeamName())) 
			return false;		
		else 
			return isExist(teamName);
		
		
 
	}


	@Override
	public long getLeagueIDByTeam(Long teamID) {
		long leagueID = teamRepository.getLeagueID(teamID);
		return leagueID;
	}


	
	@Override
	public TeamDto getTeamByID(Long id) {
		Team team = teamRepository.findById(id).orElse(null);
		TeamDto teamDto = new TeamDto();
		
		if(team!=null) 
		{
			mapper.convertToDto(team, teamDto);
			return teamDto;
		}
				
		
		return null;

	}



}
