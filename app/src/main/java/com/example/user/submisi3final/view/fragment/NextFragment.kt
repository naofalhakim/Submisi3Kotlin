package com.example.user.submisi3final.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.user.submisi3final.model.ApiRepository
import com.example.user.submisi3final.model.TeamMatch
import com.example.user.submisi3final.presenter.MainPresenter
import com.example.user.submisi3final.view.DetailActivity
import com.example.user.submisi3final.view.MainView
import com.example.user.submisi3final.view.adapter.MainAdapter
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.startActivity


class NextFragment : Fragment(), MainView {

    private var teams: MutableList<TeamMatch> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var myListTeam: RecyclerView
    private lateinit var myProgressBar: ProgressBar

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
                    myProgressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }.view

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getNextLeagueList("4328")

        adapter = MainAdapter(teams){
            startActivity<DetailActivity>("eventId" to it.eventId,
                "awayTeamId" to it.awayTeamId, "homeTeamId" to it.homeTeamId)
        }

        myListTeam.adapter = adapter

        return myView
    }

    override fun showTeamList(data: List<TeamMatch>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        myProgressBar.visible()
    }

    override fun hideLoading() {
        myProgressBar.invisible()
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

}
