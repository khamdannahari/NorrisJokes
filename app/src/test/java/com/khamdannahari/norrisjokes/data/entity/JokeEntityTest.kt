package com.khamdannahari.norrisjokes.data.entity

import org.junit.*
import org.junit.Assert.*

class JokeEntityTest {
    private companion object {

        const val ICON_URL = "https://assets.chucknorris.host/img/avatar/chuck-norris.png"
        const val ID = "tdttygk4rgygwgquno2t9a"
        const val URL = "https://api.chucknorris.io/jokes/tdttygk4rgygwgquno2t9a"
        const val VALUE = "Coroners refer to dead people as \"ABC's\". Already Been Chucked."
        const val UPDATED_AT = "2020-01-05 13:42:19.104863"
    }

    @Test
    fun `all fields`() {
        val model = JokeEntity(ICON_URL, ID, URL, VALUE, UPDATED_AT).toJoke()

        assertEquals(ICON_URL, model.iconUrl)
        assertEquals(ID, model.id)
        assertEquals(URL, model.url)
        assertEquals(VALUE, model.value)
        assertEquals(UPDATED_AT, model.updatedAt)
    }

    @Test
    fun `missing optional fields`() {
        val model = JokeEntity(ICON_URL, ID, URL, VALUE).toJoke()

        assertEquals(ICON_URL, model.iconUrl)
        assertEquals(ID, model.id)
        assertEquals(URL, model.url)
        assertEquals(VALUE, model.value)
        assertEquals("", model.updatedAt)
    }
}
