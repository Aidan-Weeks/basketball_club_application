package ie.setu.basketball_club_application.helpers

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import ie.setu.basketball_club_application.R

fun showImagePicker(intentLauncher : ActivityResultLauncher<Intent>) {
    var chooseFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
    chooseFile.type = "image/*"
    chooseFile = Intent.createChooser(chooseFile, R.string.select_player_image.toString())
    intentLauncher.launch(chooseFile)
}