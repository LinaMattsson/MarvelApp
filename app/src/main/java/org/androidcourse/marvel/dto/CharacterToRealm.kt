package org.androidcourse.marvel.dto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import org.androidcourse.testmarvel.dto.Thumbnail

@RealmClass
open class CharacterToRealm():RealmObject(){
    @PrimaryKey
    var id:String = ""
    var name: String? = null
    var imagePath: String = ""
    var imageExtention:String =""
    var description: String? = null
}