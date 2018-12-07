package com.example.user.submisi3final.model

import android.net.Uri
import android.util.Log
import com.example.user.submisi3final.BuildConfig
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class ApiRepository{

    fun doRequest(url: String): Deferred<String> = GlobalScope.async  {
        URL(url).readText()
    }

    object TheSportDBApi {
        fun getDetailEvents(idEvent: String?): String {
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupevent.php")
                .appendQueryParameter("id", idEvent)
                .build()
                .toString()
        }

        fun getNextLeague(leagueId: String?): String {
            val myurql = Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id", leagueId)
                .build()
                .toString()
            return myurql
        }

        fun getPastLeague(leagueId: String?): String {
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventspastleague.php")
                .appendQueryParameter("id", leagueId)
                .build()
                .toString()
        }

        fun getTeamBadge(teamId: String?): String {
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", teamId)
                .build()
                .toString()
        }
    }
}
