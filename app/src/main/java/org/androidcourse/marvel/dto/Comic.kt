package org.androidcourse.testmarvel.dto

data class Comic (val resourceURI:String,
                  val title:String,
                  val id:String,
                  val thumbnail: Thumbnail,
                  val description:String){
}