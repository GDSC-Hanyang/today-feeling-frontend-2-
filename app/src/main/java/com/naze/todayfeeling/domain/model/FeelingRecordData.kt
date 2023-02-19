package com.naze.todayfeeling.domain.model

import java.util.Date
data class FeelingRecordData(
    var feeling: String,
    var content: String,
    var date: Date,
    var favCount: Int,
    var favCheck: Boolean
)
