package br.com.wellington.webfluxpresentation

import org.springframework.beans.factory.annotation.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.*
import java.net.*

@RestController
class LeagueController {

    @Autowired
    lateinit var leagueRepository: LeagueRepository

    @Autowired
    lateinit var teamRepository: TeamRepository

    @PostMapping("/league")
    fun save(@RequestBody createLeagueDTO: CreateLeagueDTO): Mono<ResponseEntity<Nothing>> {
        return leagueRepository.save(League(name = createLeagueDTO.name))
                .map { ResponseEntity.created(URI.create("/league/${it.id}")).build<Nothing>() }
    }

    @GetMapping("/league/{id}")
    fun getById(@PathVariable("id") id: Int): Mono<ResponseEntity<Any>> {
        return leagueRepository.findById(id)
                .map(::toLeagueDTO)
                .flatMap(::getTeams)
                .map { ResponseEntity.ok<Any>(it) }
                .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @PostMapping("/team")
    fun save(@RequestBody createTeamDTO: CreateTeamDTO): Mono<ResponseEntity<Any>> {
        return leagueRepository
                .existsById(createTeamDTO.leagueId!!)
                .flatMap { saveTeam(createTeamDTO) }
                .map { ResponseEntity.created(URI.create("/team/${it.id}")).build<Any>() }
                .defaultIfEmpty(notFoundError(message = "there is no league with id ${createTeamDTO.leagueId}"))
    }

    private fun notFoundError(message: String) = ResponseEntity<Any>(ErrorDTO(message),  HttpStatus.NOT_FOUND)

    private fun toLeagueDTO(it: League) = LeagueDTO(id = it.id!!, name = it.name!!)

    private fun saveTeam(createTeamDTO: CreateTeamDTO) =
            teamRepository.save(Team(name = createTeamDTO.name, leagueId = createTeamDTO.leagueId))

    private fun getTeams(leagueDTO: LeagueDTO): Mono<LeagueDTO> {
        return teamRepository.findByLeagueId(leagueDTO.id)
                .reduce(leagueDTO, { league, team ->
                    league.teams.add(TeamDTO(id = team.id!!, name = team.name!!))
                    league
                })
    }
}