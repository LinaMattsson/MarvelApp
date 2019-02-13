package org.androidcourse.marvel.dto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class ComicToRealm () :RealmObject(){
    @PrimaryKey
    var id:String= ""
    var name:String? = null
    var imagePath:String? = null
    var imageExtenstion:String? = null
    var description:String? = null
}