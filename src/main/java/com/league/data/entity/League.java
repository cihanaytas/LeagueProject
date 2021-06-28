package com.league.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class League {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long leagueID;
	
	@Column(unique = true)
	private String leagueName;
	
	private String country;
	
	private int numOfTeams;
	
    @OneToMany(targetEntity = Team.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "leagueID")
    private List<Team> teams = new ArrayList<Team>();
	
	
	
	
	
}
