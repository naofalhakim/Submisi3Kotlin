package com.example.user.submisi3final.presenter

import com.example.user.submisi3final.model.ApiRepository
import com.example.user.submisi3final.model.TeamMatch
import com.example.user.submisi3final.model.MyLigaModel
import com.example.user.submisi3final.view.fragment.NextFragment
import com.example.user.submisi3final.view.fragment.TeamsFragment
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock
    private
    lateinit var view: TeamsFragment

    @Mock
    private
    lateinit var viewNext: NextFragment

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, apiRepository, gson)
    }

    @Before
    fun setUp2() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(viewNext, apiRepository, gson)
    }

    @Test
    fun getNextLeagueList() {
        val teams: MutableList<TeamMatch> = mutableListOf()
        val response = MyLigaModel(teams)
        val league = "4328"

        GlobalScope.launch {
            `when`(
                gson.fromJson(apiRepository
                        .doRequest(ApiRepository.TheSportDBApi.getNextLeague(league)).await(),
                    MyLigaModel::class.java
                )
            ).thenReturn(response)

            presenter.getNextLeagueList(league)

            verify(view).showLoading()
            verify(view).showTeamList(teams)
            verify(view).hideLoading()
        }
    }

    @Test
    fun getPastLeagueList() {
        val teams: MutableList<TeamMatch> = mutableListOf()
        val response = MyLigaModel(teams)
        val league = "4328"
        GlobalScope.launch {
            `when`(
                gson.fromJson(apiRepository
                    .doRequest(ApiRepository.TheSportDBApi.getPastLeague(league)).await(),
                    MyLigaModel::class.java
                )
            ).thenReturn(response)

            presenter.getPastLeagueList(league)

            verify(view).showLoading()
            verify(view).showTeamList(teams)
            verify(view).hideLoading()
        }
    }
}