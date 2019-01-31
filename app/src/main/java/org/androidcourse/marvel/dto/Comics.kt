package org.androidcourse.testmarvel.dto

import org.androidcourse.testmarvel.dto.Comic

data class Comics (val available:String, val collectionURI:String, val items: List<Comic>){
}