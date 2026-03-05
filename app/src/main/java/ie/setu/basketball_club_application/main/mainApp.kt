package ie.setu.basketball_club_application.main

import android.app.Application
import ie.setu.basketball_club_application.models.PlayerMemStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {
    lateinit var players: PlayerMemStore
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Player List started")
        players = PlayerMemStore()
    }
}
