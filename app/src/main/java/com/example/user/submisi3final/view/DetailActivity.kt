package com.example.user.submisi3final.view

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.user.submisi3final.R
import com.example.user.submisi3final.model.ApiRepository
import com.example.user.submisi3final.model.myBadge
import com.example.user.submisi3final.model.myLigaModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    private var homeId: String = ""
    private var awayId: String = ""
    private var eventID: String = ""

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            gravity = Gravity.CENTER_HORIZONTAL
            orientation = LinearLayout.VERTICAL

            scrollView {

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
            }.lparams(matchParent, wrapContent)

        }

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        eventID = intent.getStringExtra("eventId")
        getDetailData(eventID,Gson(), ApiRepository())

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
//                if (isFavorite) removeFromFavorite() else addToFavorite()
//
//                isFavorite = !isFavorite
//                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getBadge(idTeam: String, gson : Gson, apiRepository : ApiRepository, img:ImageView) {
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(ApiRepository.TheSportDBApi.getTeamBadge(idTeam)),
                myBadge::class.java
            )
            Log.e("Gambar", data.teams.get(0).strTeamBadge)
            uiThread {
                Picasso.get().load(data.teams.get(0).strTeamBadge).into(img)
            }
        }
    }

    fun getDetailData(idEvent: String, gson : Gson, apiRepository : ApiRepository) {
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(ApiRepository.TheSportDBApi.getDetailEvents(idEvent)),
                myLigaModel::class.java
            )
            val eventDetail = data.events.get(0)

            uiThread {
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
    }
}