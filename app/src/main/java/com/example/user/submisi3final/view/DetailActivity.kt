package com.example.user.submisi3final.view

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.user.submisi3final.R
import com.example.user.submisi3final.R.drawable.ic_add_to_favorites
import com.example.user.submisi3final.R.drawable.ic_added_to_favorites
import com.example.user.submisi3final.model.ApiRepository
import com.example.user.submisi3final.model.TeamMatch
import com.example.user.submisi3final.model.databaseconfig.database
import com.example.user.submisi3final.model.MyBadge
import com.example.user.submisi3final.model.MyLigaModel
import com.example.user.submisi3final.model.table.Favorite
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DetailActivity : AppCompatActivity(){

    private var homeId: String = ""
    private var awayId: String = ""
    private var eventID: String = ""

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var nameHome: TextView
    private lateinit var nameAway: TextView
    private lateinit var scoreAway: TextView
    private lateinit var scoreHome: TextView
    private lateinit var shotAway: TextView
    private lateinit var shotHome: TextView

    private lateinit var goalDetailHome: TextView
    private lateinit var goalDetailAway: TextView

    private lateinit var eventDate: TextView

    private lateinit var awayDf: TextView
    private lateinit var homeDf: TextView

    private lateinit var homeFw: TextView
    private lateinit var awayFw: TextView

    private lateinit var awayGk: TextView
    private lateinit var homeGk: TextView

    private lateinit var homeMd: TextView
    private lateinit var awayMd: TextView

    private lateinit var awaySub: TextView
    private lateinit var homeSub: TextView

    private lateinit var awayBadges: ImageView
    private lateinit var homeBadges: ImageView

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var teamMatch: TeamMatch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            gravity = Gravity.CENTER_HORIZONTAL
            orientation = LinearLayout.VERTICAL

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )
                scrollView {
                    isVerticalScrollBarEnabled = false
                    relativeLayout{
                        lparams(width = matchParent, height = wrapContent)

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            gravity = Gravity.CENTER_HORIZONTAL
                            orientation = LinearLayout.VERTICAL

                            eventDate = textView {
                                textSize = 16f
                                text = "2018-09-09"
                                textColor = android.support.design.R.attr.colorPrimary
                            }.lparams {
                                margin = dip(15)
                            }

                            linearLayout {
                                lparams(width = wrapContent, height = wrapContent)
                                padding = dip(8)
                                orientation = LinearLayout.HORIZONTAL

                                homeBadges = imageView {
                                }.lparams {
                                    height = dip(50)
                                    width = dip(50)
                                }

                                scoreHome = textView {
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                }.lparams {
                                    margin = dip(4)
                                }

                                textView {
                                    textSize = 16f
                                    text = "vs"
                                }.lparams {
                                    margin = dip(4)
                                }

                                scoreAway = textView {
                                    textSize = 16f
                                    text = "00"
                                    typeface = Typeface.DEFAULT_BOLD
                                }.lparams {
                                    margin = dip(4)
                                }

                                awayBadges = imageView {
                                }.lparams {
                                    height = dip(50)
                                    width = dip(50)
                                }

                            }

                            linearLayout {
                                lparams(width = wrapContent, height = wrapContent)
                                padding = dip(8)
                                orientation = LinearLayout.HORIZONTAL

                                nameHome = textView {
                                    id = R.id.text_nameHome
                                    textSize = 16f
                                    text = "Arsenal"
                                }.lparams {
                                    margin = dip(4)
                                    weight = 0.4f
                                }

                                nameAway = textView {
                                    textSize = 16f
                                    text = "Chelsea"
                                }.lparams {
                                    margin = dip(4)
                                    weight = 0.4f
                                }

                            }

                            linearLayout {
                                lparams(width = wrapContent, height = wrapContent)
                                padding = dip(8)
                                orientation = LinearLayout.VERTICAL

                                textView {
                                    textSize = 16f
                                    text = "Shots"
                                }.lparams {
                                    margin = dip(4)
                                }

                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)
                                    padding = dip(8)
                                    orientation = LinearLayout.HORIZONTAL

                                    shotHome = textView {
                                        textSize = 12f
                                        text = "Goal"
                                    }.lparams {
                                        margin = dip(4)
                                        weight = 0.4f
                                    }

                                    shotAway = textView {
                                        textSize = 12f
                                        text = "Goal"
                                    }.lparams {
                                        margin = dip(4)
                                        weight = 0.4f

                                    }

                                }

                            }

                            linearLayout {
                                lparams(width = wrapContent, height = wrapContent)
                                padding = dip(8)
                                orientation = LinearLayout.VERTICAL

                                textView {
                                    textSize = 16f
                                    text = "Goal Detail"
                                }.lparams {
                                    margin = dip(4)
                                }

                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)
                                    padding = dip(8)
                                    orientation = LinearLayout.HORIZONTAL

                                    goalDetailHome = textView {
                                        textSize = 12f
                                    }.lparams {
                                        margin = dip(4)
                                        gravity = Gravity.LEFT
                                        weight = 0.4f

                                    }

                                    goalDetailAway = textView {
                                        textSize = 12f
                                    }.lparams {
                                        margin = dip(4)
                                        gravity = Gravity.RIGHT
                                        weight = 0.4f

                                    }

                                }

                            }

                            linearLayout {
                                lparams(width = wrapContent, height = wrapContent)
                                padding = dip(8)
                                orientation = LinearLayout.VERTICAL

                                textView {
                                    textSize = 16f
                                    text = "Goal Keeper"
                                }.lparams {
                                    margin = dip(4)
                                }

                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)
                                    padding = dip(8)
                                    orientation = LinearLayout.HORIZONTAL

                                    homeGk = textView {
                                        textSize = 12f
                                    }.lparams {
                                        margin = dip(4)
                                        weight = 0.4f
                                    }

                                    awayGk = textView {
                                        textSize = 12f
                                    }.lparams {
                                        margin = dip(4)
                                        weight = 0.4f
                                    }

                                }

                            }

                            linearLayout {
                                lparams(width = wrapContent, height = wrapContent)
                                padding = dip(8)
                                orientation = LinearLayout.VERTICAL

                                textView {
                                    textSize = 16f
                                    text = "Defense"
                                }.lparams {
                                    margin = dip(4)
                                }

                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)
                                    padding = dip(8)
                                    orientation = LinearLayout.HORIZONTAL

                                    homeDf = textView {
                                        textSize = 12f
                                    }.lparams {
                                        margin = dip(4)
                                        weight = 0.4f
                                    }

                                    awayDf = textView {
                                        textSize = 12f
                                    }.lparams {
                                        margin = dip(4)
                                        weight = 0.4f
                                    }

                                }

                            }

                            linearLayout {
                                lparams(width = wrapContent, height = wrapContent)
                                padding = dip(8)
                                orientation = LinearLayout.VERTICAL

                                textView {
                                    textSize = 16f
                                    text = "Middle Field"
                                }.lparams {
                                    margin = dip(4)

                                }

                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)
                                    padding = dip(8)
                                    orientation = LinearLayout.HORIZONTAL

                                    homeMd = textView {
                                        textSize = 12f
                                    }.lparams {
                                        margin = dip(4)
                                    }

                                    awayMd = textView {
                                        textSize = 12f
                                    }.lparams {
                                        margin = dip(4)
                                    }

                                }

                            }

                            linearLayout {
                                lparams(width = wrapContent, height = wrapContent)
                                padding = dip(8)
                                orientation = LinearLayout.VERTICAL

                                textView {
                                    textSize = 16f
                                    text = "Forward"
                                }.lparams {
                                    margin = dip(4)
                                }

                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)
                                    padding = dip(8)
                                    orientation = LinearLayout.HORIZONTAL

                                    homeFw = textView {
                                        textSize = 12f
                                    }.lparams {
                                        margin = dip(4)
                                    }

                                    awayFw = textView {
                                        textSize = 12f
                                    }.lparams {
                                        margin = dip(4)
                                    }

                                }

                            }

                            linearLayout {
                                lparams(width = wrapContent, height = wrapContent)
                                padding = dip(8)
                                orientation = LinearLayout.VERTICAL

                                textView {
                                    textSize = 14f
                                    text = "Subtitutes"
                                }.lparams {
                                    margin = dip(4)
                                }

                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)
                                    padding = dip(8)
                                    orientation = LinearLayout.HORIZONTAL

                                    homeSub = textView {
                                        textSize = 12f
                                    }.lparams {
                                        margin = dip(4)
//                                weight = 0.8f
                                        gravity = Gravity.LEFT
                                    }

                                    awaySub = textView {
                                        textSize = 12f
                                    }.lparams {
                                        margin = dip(4)
//                                weight = 0.8f
                                        gravity = Gravity.RIGHT
                                    }

                                }

                            }
                        }
                    }
                }
            }
        }

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        eventID = intent.getStringExtra("eventId")

        getDetailData(eventID,Gson(), ApiRepository())
        favoriteState()

        awayId = intent.getStringExtra("awayTeamId")
        homeId = intent.getStringExtra("homeTeamId")

        getBadge(awayId, Gson(), ApiRepository(),awayBadges)
        getBadge(homeId, Gson(), ApiRepository(),homeBadges)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if(this::teamMatch.isInitialized) {
                    if (isFavorite) removeFromFavorite() else addToFavorite()

                    isFavorite = !isFavorite
                    setFavorite()

                }else{
                    Toast.makeText(this,"Please Cek Your Internet",Toast.LENGTH_SHORT).show()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getBadge(idTeam: String, gson : Gson, apiRepository : ApiRepository, img:ImageView) {
        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getTeamBadge(idTeam)).await(),
                MyBadge::class.java)

            Picasso.get().load(data.teams.get(0).strTeamBadge).into(img)
        }
    }

    fun getDetailData(idEvent: String, gson : Gson, apiRepository : ApiRepository) {

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getDetailEvents(idEvent)).await(),
                MyLigaModel::class.java)

            val eventDetail = data.events.get(0)
            teamMatch = eventDetail

            nameHome.text = eventDetail.homeTeamName
            nameAway.text = eventDetail.awayTeamName
            scoreAway.text = eventDetail.awayScore
            scoreHome.text = eventDetail.homeScore
            eventDate.text = eventDetail.dateEvent

            shotAway.text = eventDetail.intAwayShots
            shotHome.text = eventDetail.intHomeShots

            goalDetailAway.text = eventDetail.strAwayGoalDetails
            goalDetailHome.text = eventDetail.strHomeGoalDetails

            awayDf.text = eventDetail.strAwayLineupDefense
            awayFw.text = eventDetail.strAwayLineupForward
            awayGk.text = eventDetail.strAwayLineupGoalkeeper
            awayMd.text = eventDetail.strAwayLineupMidfield
            awaySub.text = eventDetail.strAwayLineupSubstitutes

            homeDf.text = eventDetail.strHomeLineupDefense
            homeFw.text = eventDetail.strHomeLineupForward
            homeGk.text = eventDetail.strHomeLineupGoalkeeper
            homeMd.text = eventDetail.strHomeLineupMidfield
            homeSub.text = eventDetail.strHomeLineupSubstitutes
        }

    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to teamMatch.eventId,
                    Favorite.EVENT_DATE to teamMatch.dateEvent,
                    Favorite.EVENT_TEAMA to teamMatch.awayTeamName,
                    Favorite.EVENT_TEAMH to teamMatch.homeTeamName,
                    Favorite.EVENT_IDTEAMA to teamMatch.awayTeamId,
                    Favorite.EVENT_IDTEAMH to teamMatch.homeTeamId,
                    Favorite.EVENT_SCOREA to teamMatch.awayScore,
                    Favorite.EVENT_SCOREH to teamMatch.homeScore
                    )
            }
            swipeRefresh.snackbar("Added to favorite").show()
        } catch (e: Exception){
            swipeRefresh.snackbar("Please Cek Your Internet Connection").show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                    "id" to eventID)
            }
            swipeRefresh.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to eventID)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
