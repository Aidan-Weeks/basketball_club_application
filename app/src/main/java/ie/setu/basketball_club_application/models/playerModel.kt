package ie.setu.basketball_club_application.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerModel(var id: Long = 0,
                       var title: String = "",
                       var description: String = "",
                       var team: String = "",
                       var image: Uri = Uri.EMPTY): Parcelable