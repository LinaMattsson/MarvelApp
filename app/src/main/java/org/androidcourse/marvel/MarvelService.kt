package org.androidcourse.marvel

import io.reactivex.Single
import org.androidcourse.marvel.dto.ComicWrapper
import org.androidcourse.testmarvel.dto.CharacterWrapper
import org.androidcourse.testmarvel.dto.MarvelCharacter
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    fun getAllCharacters()

    @GET("characters")
    fun searchCharacters(@Query("nameStartsWith") nameStartsWith:String): Single<CharacterWrapper>

    @GET("comics")
    fun searchComics(@Query ("titleStartsWith") titleStartsWith:String) : Single<ComicWrapper>

    @GET("characters/{characterId}")
    fun getSingleCharacter(@Path("characterId") characterId:String): Single<CharacterWrapper>
}