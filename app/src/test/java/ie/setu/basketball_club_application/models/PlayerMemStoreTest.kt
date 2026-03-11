import ie.setu.basketball_club_application.models.PlayerMemStore
import ie.setu.basketball_club_application.models.PlayerModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PlayerMemStoreTest {

    lateinit var store: PlayerMemStore

    @Before
    fun setup() {
        store = PlayerMemStore()
    }

    @Test
    fun testCreatePlayer() {
        val player = PlayerModel(title = "John Doe")
        store.create(player)
        assertTrue(store.findAll().contains(player))
    }

    @Test
    fun testUpdatePlayer() {
        val player = PlayerModel(title="John Doe")
        store.create(player)
        player.title = "Jane Doe"
        store.update(player)
        assertEquals("Jane Doe", store.findAll()[0].title)
    }

    @Test
    fun testDeletePlayer() {
        val player = PlayerModel(title="John Doe")
        store.create(player)
        store.delete(player)
        assertFalse(store.findAll().contains(player))
    }
}