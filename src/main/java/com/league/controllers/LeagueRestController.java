package com.league.controllers;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.league.business.service.ILeagueService;
import com.league.data.dto.LeagueDto;

@RestController
public class LeagueRestController {


	@Autowired
	private ILeagueService leagueService;
	
	@PostMapping("saveleague")
	public ResponseEntity<?> saveLeague(@Valid  @RequestBody LeagueDto league,
			BindingResult result) {

		
		if(result.hasErrors()) {
		       List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
		       return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);       
		}
		
		else if(leagueService.isExist(league.getLeagueName())) {
			return new ResponseEntity<>("Bu lig isödfga", new HttpHeaders(), HttpStatus.BAD_REQUEST); }
		
		
		else {
			leagueService.saveLeague(league);
			//return new ResponseEntity<>(HttpStatus.OK);
			return ResponseEntity.ok(league);
		}
	}
	
	
	@DeleteMapping("deleteleague/{id}")
	public ResponseEntity<?> deleteLeague(@PathVariable(name="id") Long id) {
		return leagueService.deleteLeague(id);
	}
	
	@GetMapping("/leaguelist")
	public List<LeagueDto> getLeagueList(Model model){
		return leagueService.getLeagueList();
	}
 
}
