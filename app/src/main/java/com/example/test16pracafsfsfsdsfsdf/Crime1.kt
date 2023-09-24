package com.example.test16pracafsfsfsdsfsdf

import java.util.UUID
import java.util.Date

data class Crime1 (val id:UUID = UUID.randomUUID()) {

    var data=Date()
    var isSolved: Boolean = false
    var title: String= ""
}
