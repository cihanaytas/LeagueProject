package com.league.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long playerID;
	
	private String name;
	private String surname;
	private int age;
	
	@ManyToOne
	@JoinColumn(name="teamID")
	private Team team;
}
