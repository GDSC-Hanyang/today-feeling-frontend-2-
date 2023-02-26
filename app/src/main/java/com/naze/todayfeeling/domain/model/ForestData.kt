package com.naze.todayfeeling.domain.model

import java.util.*

data class ForestData(
    var id: Int,
    var content: String,
    var date: Date,
    var favCount: Int,
    var favCheck: Boolean
) {

}