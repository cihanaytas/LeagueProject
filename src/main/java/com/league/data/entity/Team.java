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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long teamID;
	
	@Column(unique = true)
	private String teamName;
	
	private int numOfPlayers;
	
	private String city;

	@ManyToOne
	@JoinColumn(name="leagueID")
	private League league;
	
    @OneToMany(targetEntity = Player.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "teamID")
    private List<Player> players = new ArrayList<Player>();
}
