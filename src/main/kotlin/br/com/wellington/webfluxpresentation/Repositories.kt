package br.com.wellington.webfluxpresentation

import org.springframework.data.repository.reactive.*
import org.springframework.stereotype.*
import reactor.core.publisher.*

@Repository
interface LeagueRepository: ReactiveCrudRepository<League, Int>

@Repository
interface TeamRepository: ReactiveCrudRepository<Team, Int> {

    fun findByLeagueId(leagueId: Int): Flux<Team>
}