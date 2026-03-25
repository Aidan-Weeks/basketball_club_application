    package ie.setu.basketball_club_application.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ie.setu.basketball_club_application.databinding.PlayerCardBinding
import ie.setu.basketball_club_application.models.PlayerModel

interface playerListener {
    fun onPlayerClick(player: PlayerModel)
}

class playerAdapter(private var players: List<PlayerModel>,
                    private val listener: playerListener):
    RecyclerView.Adapter<playerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = PlayerCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val player = players[holder.adapterPosition]
        holder.bind(player, listener)
    }

    override fun getItemCount(): Int = players.size

    class MainHolder(private val binding : PlayerCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: PlayerModel, listener: playerListener) {
            binding.playerTitle.text = player.title
            binding.description.text = player.description
            binding.team.text = player.team
            Picasso.get().load(player.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onPlayerClick(player) }

        }
    }
}
