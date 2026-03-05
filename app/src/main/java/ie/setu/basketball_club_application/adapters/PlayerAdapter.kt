package ie.setu.basketball_club_application.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.setu.basketball_club_application.databinding.PlayerCardBinding
import ie.setu.basketball_club_application.models.PlayerModel


class playerAdapter(private var players: List<PlayerModel>) :
    RecyclerView.Adapter<playerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = PlayerCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val player = players[holder.adapterPosition]
        holder.bind(player)
    }

    override fun getItemCount(): Int = players.size

    class MainHolder(private val binding : PlayerCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: PlayerModel) {
            binding.playerTitle.text = player.title
            binding.description.text = player.description
            binding.team.text = player.team

        }
    }
}
