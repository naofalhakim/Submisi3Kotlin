package com.example.user.submisi3final.view
import com.example.user.submisi3final.model.TeamMatch

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<TeamMatch>)
}