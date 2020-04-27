package com.muiezarif.inspirationalquotes.db.models

import java.io.Serializable

data class QuotesModel(var id:Int,var quote:String,var author:String,var fav:Int):Serializable