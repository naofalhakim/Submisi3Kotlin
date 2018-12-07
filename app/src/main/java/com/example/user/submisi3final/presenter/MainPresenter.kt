package com.example.user.submisi3final.presenter

import android.util.Log
import com.example.user.submisi3final.model.ApiRepository
import com.example.user.submisi3final.model.MyLigaModel
import com.example.user.submisi3final.view.MainView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson
) {
    fun getNextLeagueList(league: String) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getNextLeague(league)).await(),
                MyLigaModel::class.java)

            view.showTeamList(data.events)
            view.hideLoading()
        }
    }

    fun getPastLeagueList(league: String) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getPastLeague(league)).await(),
                MyLigaModel::class.java)

            view.showTeamList(data.events)
            view.hideLoading()
        }
    }

}