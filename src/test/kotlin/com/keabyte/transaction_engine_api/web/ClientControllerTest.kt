package com.keabyte.transaction_engine_api.web

import com.keabyte.transaction_engine_api.fixture.ClientFixture
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@MicronautTest
class ClientControllerTest(private val clientController: ClientController) {

    @Test
    fun `create client`() {
        val request = ClientFixture.createClientRequest_john()
        val client = clientController.createClient(request)

        assertNotNull(client.id)
        assertTrue(client.id > 0)

        Assertions.assertThat(client)
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(request)
    }

    @Test
    fun `get client`() {
        val client1 = clientController.createClient(ClientFixture.createClientRequest_jane())
        val client2 = clientController.getClientById(client1.id)

        Assertions.assertThat(client2)
            .usingRecursiveComparison()
            .isEqualTo(client1)
    }
}