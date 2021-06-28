package com.league.data.respository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.league.data.entity.League;
import com.league.data.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Long>{

	@Query("select count(*) from Team t where t.teamName= :teamName")
	public int isExist(String teamName);
	
	@Query(value="select leagueID from team where teamID= :teamID",nativeQuery = true)
	public long getLeagueID(long teamID);

	@Query("select t.league from Team t where t.teamID= :teamID")
	public League findLeagueByTeamId(Long teamID);


}
