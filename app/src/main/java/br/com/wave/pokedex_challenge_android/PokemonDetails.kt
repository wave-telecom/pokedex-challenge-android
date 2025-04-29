package br.com.wave.pokedex_challenge_android

data class PokemonDetails(
    val name: String,
    val sprites: Sprites,
    val types: List<Type>, val abilities: List<Ability>,
    val stats: List<Stat>
)

data class Sprites(
    val front_default: String
)

data class Type(
    val type: TypeDetails
)

data class TypeDetails(
    val name: String
)

data class Ability(
    val ability: AbilityDetails
)

data class AbilityDetails(
    val name: String
)

data class Stat(
    val base_stat: Int,
    val stat: StatDetails
)

data class StatDetails(
    val name: String)