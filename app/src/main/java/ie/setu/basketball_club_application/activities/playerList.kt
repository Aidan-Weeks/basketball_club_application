package ie.setu.basketball_club_application

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ie.setu.basketball_club_application.databinding.PlayerListBinding
import com.google.android.material.snackbar.Snackbar
import ie.setu.basketball_club_application.main.MainApp
import ie.setu.basketball_club_application.models.PlayerModel
import timber.log.Timber.i

class PlayerList : AppCompatActivity() {

    private lateinit var binding:PlayerListBinding
    var player = PlayerModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PlayerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Placemark Activity started...")
        binding.btnAdd.setOnClickListener() {
            player.title = binding.playerTitle.text.toString()
            player.description = binding.description.text.toString()
            player.team = binding.team.text.toString()

            if (player.title.isNotEmpty()) {
                app.players.add(player.copy())
                i("add Button Pressed: ${player}")
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar
                        .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                        .show()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_player, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }
}