package ie.setu.basketball_club_application.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ie.setu.basketball_club_application.R
import ie.setu.basketball_club_application.databinding.PlayerListBinding
import ie.setu.basketball_club_application.helpers.showImagePicker
import ie.setu.basketball_club_application.main.MainApp
import ie.setu.basketball_club_application.models.PlayerModel
import timber.log.Timber.i

class PlayerList : AppCompatActivity() {

    private lateinit var binding: PlayerListBinding
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>

    var player = PlayerModel()
    lateinit var app: MainApp
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PlayerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Player Activity started...")

        registerImagePickerCallback()

        if (intent.hasExtra("player_edit")) {
            edit = true
            player = intent.extras?.getParcelable("player_edit")!!
            binding.playerTitle.setText(player.title)
            binding.description.setText(player.description)
            binding.team.setText(player.team)
            if (player.image != Uri.EMPTY) {
                binding.playerImage.setImageURI(player.image)
                binding.chooseImage.setText(R.string.change_player_image)
            }
            binding.btnAdd.setImageResource(R.drawable.ic_save)
            binding.btnAdd.contentDescription = getString(R.string.save_Player)
            Picasso.get()
                .load(player.image)
                .into(binding.playerImage)
            if (player.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_player_image)
            }
        }

        binding.btnAdd.setOnClickListener {
            player.title = binding.playerTitle.text.toString()
            player.description = binding.description.text.toString()
            player.team = binding.team.text.toString()

            if (player.title.isNotEmpty()) {
                if (edit) {
                    app.players.update(player.copy())
                } else {
                    app.players.create(player.copy())
                }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar.make(it, "Please Enter a Player Name", Snackbar.LENGTH_LONG).show()
            }
        }

        binding.chooseImage.setOnClickListener {
            i("Select image")
            showImagePicker(imageIntentLauncher)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_player, menu)
        val deleteItem = menu.findItem(R.id.item_delete)
        if (deleteItem != null) {
            deleteItem.isVisible = edit
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                i("Delete menu item selected for player: ${player.id}")
                app.players.delete(player)
                setResult(RESULT_OK)
                finish()
                return true
            }
            R.id.item_cancel -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            player.image = result.data!!.data!!
                            Picasso.get()
                                .load(player.image)
                                .into(binding.playerImage)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}
