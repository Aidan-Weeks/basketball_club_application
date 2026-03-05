package ie.setu.basketball_club_application.models

interface PlayerStore {
    fun findAll(): List<PlayerModel>
    fun create(player: PlayerModel)
    fun update(player: PlayerModel)
    fun delete(player: PlayerModel)
}
