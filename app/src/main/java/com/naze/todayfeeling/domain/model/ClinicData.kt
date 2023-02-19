package com.naze.todayfeeling.domain.model

import java.util.Date

data class ClinicData(
    var title: String,
    var content: String,
    var date: Date,
    var favCount: Int,
    var favCheck: Boolean
)
