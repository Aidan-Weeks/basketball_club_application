package ie.setu.basketball_club_application.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PlayerMemStore : PlayerStore {

    val players = ArrayList<PlayerModel>()

    override fun findAll(): List<PlayerModel> {
        return players
    }

    override fun create(player: PlayerModel) {
        player.id = getId()
        players.add(player)
        logAll()
    }

    override fun update(player: PlayerModel) {
        var foundPlayer: PlayerModel? = players.find { p -> p.id == player.id }
        if (foundPlayer != null) {
            foundPlayer.title = player.title
            foundPlayer.description = player.description
            foundPlayer.team = player.team
            foundPlayer.image = player.image
            logAll()
        }
    }

    override fun delete(player: PlayerModel) {
        i("Deleting player with ID: ${player.id}")
        val removed = players.removeIf { it.id == player.id }
        if (removed) {
            i("Player removed successfully. Remaining count: ${players.size}")
        } else {
            i("Failed to remove player: ID ${player.id} not found")
        }
        logAll()
    }

    private fun logAll() {
        i("Current Players in Store:")
        players.forEach { i("$it") }
    }
}
