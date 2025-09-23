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

        // Construir mensaje con saludo + nombre
        val message = "$greeting, $name!"

        return mapOf(
            "message" to message,
            "timestamp" to Instant.now().toString()
        )
    }
}
