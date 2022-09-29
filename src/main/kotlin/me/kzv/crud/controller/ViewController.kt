package me.kzv.crud.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {

    @GetMapping("/")
    fun home() = "index"

    @GetMapping("/login")
    fun signin() = "signin"

    @GetMapping("/signup")
    fun signup() = "signup"

    @GetMapping("/admin")
    fun admin() = "admin"

    @GetMapping("/my-page")
    fun myPage() = "my-page"

    @GetMapping("/register")
    fun register() = "register"

    @GetMapping("/edit")
    fun edit() = "edit"
}