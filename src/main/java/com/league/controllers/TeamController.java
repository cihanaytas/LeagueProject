package com.league.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.league.business.service.TeamService;
import com.league.data.dto.TeamDto;

@Controller
public class TeamController {
	
	@Autowired
	private TeamService teamService;

	@GetMapping("addteam/{leagueID}")
	public String addTeam(Model model,@PathVariable(name="leagueID") Long leagueID) {
		TeamDto team = new TeamDto();
		model.addAttribute("team",team);
		model.addAttribute("leagueID",leagueID);
		return "team/NewTeam";
	}
	

	
	@PostMapping("saveteam/{leagueID}")
	public String saveTeam(@Valid @ModelAttribute("team") TeamDto teamDto,BindingResult result
			, Model model,
			@PathVariable(name="leagueID") Long leagueID) {
		
		if(result.hasErrors()) {
			return "team/NewTeam";
		}
		
		else {			 		
			 	//update
				if(teamDto.getTeamID()!=0) {   
					 if(teamService.isExist(teamDto.getTeamName(),teamDto.getTeamID()))
							return "team/NewTeam";						
					}
								
				//new record
				else { 
					 if(teamService.isExist(teamDto.getTeamName()))
							return "team/NewTeam";
				    }
				
				  teamService.saveTeam(teamDto, leagueID);			
			
				return "redirect:/showteams/{leagueID}";
					
		}

					
	}
	
	
	
	
	@GetMapping("deleteteam/{id}")
	public String deleteLeague(@PathVariable(name="id") Long id) {
		teamService.deleteTeam(id);
		return "redirect:/";
	}
	
 
	
	
	@GetMapping("showteam/{teamID}")
	public String showTeamForm(@PathVariable(name="teamID") Long teamID, Model model) {
		TeamDto team = teamService.getTeamByID(teamID);
		if(team==null) 
			return "team/NoTeam";
		
		long leagueID = teamService.getLeagueIDByTeam(teamID);
		model.addAttribute("team",team);
		model.addAttribute("leagueID",leagueID);
		//return "team/UpdateTeam";
		return "team/Newteam";
	}
	
 
	

	
	
}
