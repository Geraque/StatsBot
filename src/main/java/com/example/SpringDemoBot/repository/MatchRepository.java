package com.example.SpringDemoBot.repository;

import com.example.SpringDemoBot.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{

}
