package com.eslirodrigues.tutorials.graphql.data.model

import com.eslirodrigues.tutorials.SamplePokeAPIqueryQuery

data class PokeSpecie(
    val id: Int = 0,
    val name: String = ""
)

fun SamplePokeAPIqueryQuery.Gen3_specy.toPokeSpecies() = PokeSpecie(
    id = this.id,
    name = this.name
)
