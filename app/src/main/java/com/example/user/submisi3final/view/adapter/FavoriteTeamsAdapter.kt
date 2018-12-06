package com.example.user.submisi3final.view.adapter

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.user.submisi3final.R
import com.example.user.submisi3final.model.table.Favorite
import org.jetbrains.anko.*

class FavoriteTeamsAdapter(private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(FavoriteTeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size

}

class FavoriteTeamUI : AnkoComponent<ViewGroup> {
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
                    textColor = android.support.design.R.attr.colorPrimary
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

class FavoriteViewHolder(val view: View) : RecyclerView.ViewHolder(view){

    private val eventDate: TextView = view.find(R.id.text_date)
    private val teamNameH: TextView = view.find(R.id.text_nameHome)
    private val teamNameA: TextView = view.find(R.id.text_nameAway)
    private val teamScoreH: TextView = view.find(R.id.text_scoreHome)
    private val teamScoreA: TextView = view.find(R.id.text_scoreAway)

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        teamNameH.text = favorite.teamNameH
        teamNameA.text = favorite.teamNameA
        if (favorite.teamScoreA == null) {
            teamScoreA.text = "0"
        } else {
            teamScoreA.text = favorite.teamScoreA
        }
        if (favorite.teamScoreH == null) {
            teamScoreH.text = "0"
        } else {
            teamScoreH.text = favorite.teamScoreH
        }
        eventDate.text = favorite.dateEvent
        view.setOnClickListener { listener(favorite) }
    }
}