package com.example.user.submisi3final.model

import com.google.gson.annotations.SerializedName

data class TeamMatch(
    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("strEvent") //ini isinya langsung teamA vs teamB
    var eventName: String? = null,

    @SerializedName("idHomeTeam")
    var homeTeamId: String? = null,

    @SerializedName("idAwayTeam")
    var awayTeamId: String? = null,

    @SerializedName("strHomeTeam")
    var homeTeamName: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeamName: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = "0",

    @SerializedName("intAwayScore")
    var awayScore: String? = "0",

    @SerializedName("dateEvent")
    var dateEvent: String? = null,

    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: String? = null,

    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper: String? = null,

    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield: String? = null,

    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes: String? = null,

    @SerializedName("intHomeShots")
    var intHomeShots: String? = "0",

    @SerializedName("intAwayShots")
    var intAwayShots: String? = "0"

)