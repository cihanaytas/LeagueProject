package com.league.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.league.business.service.ILeagueService;
import com.league.data.dto.LeagueDto;



@Controller
public class LeagueController {

	@Autowired
	private ILeagueService leagueService;
	
	@GetMapping("/")
	public String getLeagueList(Model model){
		return "league/HomePage";
	}
	
	@GetMapping("addleague")
	public String addLeague(Model model) {
		return "league/NewLeague2";
	}
	
	@PostMapping("updateleague")
	public String saveLeague(@Valid @ModelAttribute("league") LeagueDto leagueDto,
			BindingResult result) {
		if(result.hasErrors()) {
			return "league/UpdateLeague";
		}
		else {			
			if(leagueDto.getLeagueID()!=0) {
				if(leagueService.isExist(leagueDto.getLeagueName(), leagueDto.getLeagueID()))
					return "league/UpdateLeague";
			}
			
			else {
				if(leagueService.isExist(leagueDto.getLeagueName()));
					return "league/UpdateLeague";
			}
			
			
		leagueService.saveLeague(leagueDto);
		return "redirect:/";
		}
	}


	
	@GetMapping("showleague/{id}")
	public String showLeagueForm(@PathVariable(name="id") Long id, Model model) {
		LeagueDto league = leagueService.getLeagueById(id);
		
		if(league==null)
			return "team/NoTeam";
				
		model.addAttribute("league",league);
		return "league/UpdateLeague";
	}
	
 
	
	@GetMapping("showteams/{id}")
	public String showTeams(@PathVariable(name="id") Long id, Model model) {
		ResponseEntity<?> teamList = leagueService.getTeamList(id);
		
		if(teamList.getBody()==null)
			return "NotFound";
		
		model.addAttribute("teamList",teamList.getBody());
		model.addAttribute("leagueID",id);
		return "team/TeamList";
	}
	
	
 
	

}
