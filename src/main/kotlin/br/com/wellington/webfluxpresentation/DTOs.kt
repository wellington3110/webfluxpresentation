package br.com.wellington.webfluxpresentation

data class CreateLeagueDTO(val name: String? = null)
data class CreateTeamDTO(
        val name: String? = null,
        val leagueId: Int?)
data class TeamDTO(val id: Int, val name: String)
data class LeagueDTO(val id: Int, val name: String, val teams: MutableList<TeamDTO> = mutableListOf())
data class ErrorDTO(val message: String)