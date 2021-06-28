package com.league.data.respository;

import org.springframework.data.repository.CrudRepository;

import com.league.data.entity.Player;

public interface PlayerRepository extends CrudRepository<Player, Long>{

}
