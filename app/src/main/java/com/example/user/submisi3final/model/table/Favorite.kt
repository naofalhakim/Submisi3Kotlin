package com.example.user.submisi3final.model.table

data class Favorite(val id: Long?, val eventId: String?,
                    val dateEvent: String?,
                    val teamNameH: String?,
                    val teamNameA: String?,
                    val teamIDH: String?,
                    val teamIDA: String?,
                    val teamScoreH: String?, val teamScoreA: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val EVENT_TEAMH: String = "EVENT_TEAMH"
        const val EVENT_TEAMA: String = "EVENT_TEAMA"
        const val EVENT_IDTEAMH: String = "EVENT_IDTEAMH"
        const val EVENT_IDTEAMA: String = "EVENT_IDTEAMA"
        const val EVENT_SCOREA: String = "EVENT_SCOREA"
        const val EVENT_SCOREH: String = "EVENT_SCOREH"


    }
}