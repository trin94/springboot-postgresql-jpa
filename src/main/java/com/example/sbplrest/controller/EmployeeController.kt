package com.example.sbplrest.controller

import com.example.sbplrest.exception.ResourceNotFoundException
import com.example.sbplrest.model.Employee
import com.example.sbplrest.repository.EmployeeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class EmployeeController(
    val repo: EmployeeRepository
) {

    @GetMapping("/employees")
    fun getAllEmployee(): List<Employee> = repo.findAll()

    @GetMapping("/employee/{id}")
    fun getEmployeeById(
        @PathVariable(value = "id") id: Long
    ): ResponseEntity<Employee> = ResponseEntity.ok(repo.findBy(id))

    @PostMapping("/employee")
    fun createEmployee(
        @RequestBody employee: Employee
    ): Employee = repo.save(employee)

    @PutMapping("/employee/{id}")
    fun updateEmployee(
        @PathVariable(value = "id") id: Long,
        @Validated @RequestBody details: Employee
    ): ResponseEntity<Employee> {
        val employee = repo.findBy(id).apply {
            firstName = details.firstName
            lastName = details.lastName
            email = details.email
        }
        return ResponseEntity.ok(repo.save(employee))
    }

    @DeleteMapping("/employee/{id}")
    fun deleteEmployee(@PathVariable(value = "id") id: Long) = repo.delete(repo.findBy(id))

    @Throws(ResourceNotFoundException::class)
    private fun EmployeeRepository.findBy(id: Long) = repo.findByIdOrNull(id)
        ?: throw ResourceNotFoundException("employee", id)
}
