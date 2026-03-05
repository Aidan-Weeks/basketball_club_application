package ie.setu.basketball_club_application.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.basketball_club_application.R
import ie.setu.basketball_club_application.adapters.playerAdapter
import ie.setu.basketball_club_application.adapters.playerListener
import ie.setu.basketball_club_application.databinding.ActivityPlayerListBinding
import ie.setu.basketball_club_application.main.MainApp
import ie.setu.basketball_club_application.models.PlayerModel


class PlayerListActivity : AppCompatActivity(), playerListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityPlayerListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = playerAdapter(app.players.findAll(),this)

        binding.btnAdd.setOnClickListener {
            val launcherIntent = Intent(this, PlayerList::class.java)
            getResult.launch(launcherIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.players.findAll().size)
            }
        }
    override fun onPlayerClick(player: PlayerModel) {
        val launcherIntent = Intent(this, PlayerList::class.java)
        launcherIntent.putExtra("player_edit", player)
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.players.findAll().size)
            }
        }
}