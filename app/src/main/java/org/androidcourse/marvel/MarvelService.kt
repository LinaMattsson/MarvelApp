package org.androidcourse.marvel

import io.reactivex.Single
import org.androidcourse.marvel.dto.ComicWrapper
import org.androidcourse.testmarvel.dto.CharacterWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    fun getAllCharacters(): Single<CharacterWrapper>

    @GET("comics")
    fun searchComics(@Query ("titleStartsWith") titleStartsWith:String) : Single<ComicWrapper>
}