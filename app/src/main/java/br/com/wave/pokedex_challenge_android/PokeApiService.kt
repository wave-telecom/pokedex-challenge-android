package br.com.wave.pokedex_challenge_android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon?limit=151")
    suspend fun getPokemonList(
        @Header("X-Authorization") token: String = "k45xAxvhWH9xyRGLnhDWvHCSsMEadDX4OiRko03wOw588"
    ): PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name") name: String,
        @Header("X-Authorization") token: String = "k45xAxvhWH9xyRGLnhDWvHCSsMEadDX4OiRko03wOw588"
    ): PokemonDetails
}

data class PokemonListResponse(
    val results: List<Pokemon>
)