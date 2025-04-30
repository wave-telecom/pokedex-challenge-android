package br.com.wave.pokedex_challenge_android

data class PokemonType(
    val name: String,
    val url: String,
    val type: PokType,
)

enum class PokType {
    NORMAL,
    FIGHTING,
    GRASS,
    WATER,
    FIRE
}