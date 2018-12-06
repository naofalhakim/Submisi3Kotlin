package com.example.user.submisi3final.view.adapter

import android.content.ClipData
import android.content.Context
import android.graphics.Typeface
import android.support.design.R.attr.colorPrimary
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.user.submisi3final.R
import com.example.user.submisi3final.model.TeamMatch
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class MainAdapter (private val teams: List<TeamMatch>, private val listener: (TeamMatch) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(p0.context, p0)))
    }
    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(p0: TeamViewHolder, p1: Int) {
        p0.bindItem(teams[p1],listener)
    }

}

class TeamViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val eventDate: TextView = view.find(R.id.text_date)
    private val teamNameH: TextView = view.find(R.id.text_nameHome)
    private val teamNameA: TextView = view.find(R.id.text_nameAway)
    private val teamScoreH: TextView = view.find(R.id.text_scoreHome)
    private val teamScoreA: TextView = view.find(R.id.text_scoreAway)


    fun bindItem(teams: TeamMatch, listener: (TeamMatch) -> Unit) {
//        Picasso.get().load(teams.teamBadge).into(teamBadge)
        teamNameH.text = teams.eventName
        teamNameH.text = teams.homeTeamName
        teamNameA.text = teams.awayTeamName
        if (teams.awayScore == null) {
            teamScoreA.text = "0"
        } else {
            teamScoreA.text = teams.awayScore.toString()
        }
        if (teams.homeScore == null) {
            teamScoreH.text = "0"
        } else {
            teamScoreH.text = teams.homeScore.toString()
        }
        eventDate.text = teams.dateEvent
        view.setOnClickListener { listener(teams) }
    }
}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(8)
                gravity = Gravity.CENTER_HORIZONTAL
                orientation = LinearLayout.VERTICAL

                textView {
                    id = R.id.text_date
                    textSize = 16f
                    text = "2018-09-09"
                    textColor = colorPrimary
                }.lparams {
                    margin = dip(15)
                }

                linearLayout {
                    lparams(width = wrapContent, height = wrapContent)
                    padding = dip(8)
                    orientation = LinearLayout.HORIZONTAL

                    textView {
                        id = R.id.text_nameHome
                        textSize = 16f
                        text = "Arsenal"
                    }.lparams {
                        margin = dip(4)
                    }

                    textView {
                        id = R.id.text_scoreHome
                        textSize = 16f
                        text = "02"
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

                    textView {
                        id = R.id.text_scoreAway
                        textSize = 16f
                        text = "00"
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        margin = dip(4)
                    }

                    textView {
                        id = R.id.text_nameAway
                        textSize = 16f
                        text = "Chelsea"
                    }.lparams {
                        margin = dip(4)
                    }

                }

            }
        }
    }
}

