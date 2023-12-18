package com.example.demo.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController ( @Autowired private val userRepository: UserRepository ) {

    //get all users
    @GetMapping("")
    fun getAllUsers(): List<User> = userRepository.findAll().toList()

    // create user
    @PostMapping("")
    fun creatUser(@RequestBody user: User): ResponseEntity<User> {
        val savedUser = userRepository.save(user)
        return ResponseEntity(savedUser, HttpStatus.CREATED)
    }

    //get by userId
    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: Int): ResponseEntity<User> {
        val user = userRepository.findById(id)
        return if (user.isPresent)
            ResponseEntity(user.get(), HttpStatus.FOUND)
        else
            ResponseEntity(HttpStatus.NOT_FOUND)
    }

    //delete by userId
    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Int): ResponseEntity<User> {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            return ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    //Update by userId
    @PutMapping("/{id}")
    fun updateUserById(@PathVariable id: Int, @RequestBody user: User) : ResponseEntity<User> {

        val existingUser = userRepository.findById(id).orElse(null) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedUser = existingUser.copy(name = user.name, email = user.email)
        userRepository.save(updatedUser)
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }
}
