package com.league.data.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.league.data.entity.League;

public interface LeagueRepository extends CrudRepository<League, Long>{
 
	@Query("select count(*) from League l where l.country= :country")
	public int isExist(String country);


}
