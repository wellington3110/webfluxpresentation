package br.com.wellington.webfluxpresentation

import org.springframework.data.annotation.*


data class League(
        @Id
        val id: Int? = null,
        val name: String? = null
)

data class Team(
        @Id
        val id: Int? = null,
        val name: String? = null,
        val leagueId: Int? = null
)