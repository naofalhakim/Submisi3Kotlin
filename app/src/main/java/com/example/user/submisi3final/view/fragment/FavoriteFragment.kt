package com.example.user.submisi3final.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.example.user.submisi3final.model.databaseconfig.database
import com.example.user.submisi3final.model.table.Favorite
import com.example.user.submisi3final.view.DetailActivity
import com.example.user.submisi3final.view.adapter.FavoriteTeamsAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.startActivity


class FavoriteFragment : Fragment() {
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteTeamsAdapter
    private lateinit var myListTeam: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = UI {
            linearLayout {
                lparams(width = matchParent, height = matchParent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                relativeLayout {
                    lparams(width = matchParent, height = matchParent)

                    myListTeam = recyclerView {
                        lparams(width = matchParent, height = matchParent)
                        layoutManager = LinearLayoutManager(context)
                    }

                }
            }
        }.view

        adapter = FavoriteTeamsAdapter(favorites){
            startActivity<DetailActivity>("eventId" to it.eventId,
                "awayTeamId" to it.teamIDA, "homeTeamId" to it.teamIDH)
        }
        myListTeam.adapter = adapter

        return myView
    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
//            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

}
