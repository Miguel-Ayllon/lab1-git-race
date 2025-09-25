package es.unizar.webeng.hello.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.LocalTime
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Controller
class HelloController(

    @param:Value("\${app.message:Hello World}")
    private val message: String
) {

    @GetMapping("/")
    fun welcome(
        model: Model,
        @RequestParam(defaultValue = "") name: String
    ): String {
        
        val greeting = if (name.isNotBlank()) "Hello, $name!" else message
        
        model.addAttribute("message", greeting)
        model.addAttribute("name", name)
        
        return "welcome"
    }
}


@RestController
class HelloApiController {

    @GetMapping("/api/hello", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun helloApi(
        @RequestParam(defaultValue = "World") name: String

    ): Map<String, String> {

        // Determinar saludo según la hora
        val nowTime = LocalTime.now()
        
        val greeting = when {
            nowTime.hour < 12 -> "Good morning"
            nowTime.hour < 18 -> "Good afternoon"
            else -> "Good evening"
        }

        // Obtener el día de la semana
        val today = LocalDate.now()
        val dayOfWeek = today.dayOfWeek.getDisplayName(
            TextStyle.FULL, 
            Locale.ENGLISH
        )

        // Construir mensaje con saludo + nombre + día de la semana
        val message = "$greeting, $name! Today is $dayOfWeek."

        return mapOf(
            "message" to message,
            "dayOfWeek" to dayOfWeek,
            "timestamp" to Instant.now().toString()
        )
    }
}