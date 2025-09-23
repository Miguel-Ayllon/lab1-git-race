package es.unizar.webeng.hello.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.ui.Model
import org.springframework.ui.ExtendedModelMap
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HelloControllerUnitTests {

    private lateinit var controller: HelloController
    private lateinit var model: Model

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @BeforeEach
    fun setup() {
        controller = HelloController("Test Message")
        model = ExtendedModelMap()
    }

    @Test
    fun `should return welcome view with default message`() {
        val view = controller.welcome(model, "")

        assertThat(view).isEqualTo("welcome")
        assertThat(model.getAttribute("message")).isEqualTo("Test Message")
        assertThat(model.getAttribute("name")).isEqualTo("")
    }

    @Test
    fun `should return welcome view with personalized message`() {
        val view = controller.welcome(model, "Developer")

        assertThat(view).isEqualTo("welcome")
        assertThat(model.getAttribute("message")).isEqualTo("Hello, Developer!")
        assertThat(model.getAttribute("name")).isEqualTo("Developer")
    }

    @Test
    fun `should return API response with timestamp and proper greeting`() {
        val response = restTemplate.getForEntity(
            "http://localhost:$port/api/hello?name=Test", String::class.java
        )

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.headers.contentType).isEqualTo(MediaType.APPLICATION_JSON)

        val json = jacksonObjectMapper().readTree(response.body)
        assertThat(json["message"].asText()).contains("Test")
        assertThat(json["timestamp"].asText()).isNotEmpty()
    }
}
