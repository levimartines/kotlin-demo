package com.levimartines.kotlindemo.controllers

import com.levimartines.kotlindemo.models.Account
import com.levimartines.kotlindemo.repositories.AccountRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("accounts")
class AccountController(val repository: AccountRepository) {

/*
    Java way
    @PostMapping
    fun create(@RequestBody account: Account): ResponseEntity<Account> {
        return ResponseEntity.ok(repository.save(account));
    }
    */

    @PostMapping
    fun create(@RequestBody account: Account) = ResponseEntity.ok(repository.save(account))

    @GetMapping
    fun read() = ResponseEntity.ok(repository.findAll())

    @PutMapping("{document}")
    fun update(@PathVariable document: String,
               @RequestBody account: Account): ResponseEntity<Account> {
        val accountDb = repository.findByDocument(document)
                .orElseThrow { RuntimeException("Account document: $document not found") }
        val updatedAccount = repository.save(accountDb.copy(name = account.name, balance = account.balance))
        return ResponseEntity.ok(updatedAccount)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: String) =
            repository.findById(id)
                    .ifPresent { repository.delete(it) }

}
