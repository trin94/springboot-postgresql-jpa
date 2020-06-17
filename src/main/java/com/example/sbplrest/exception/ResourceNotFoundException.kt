package com.example.sbplrest.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException(entity: String, id: Long) : Exception("Can not find resource '$entity' for id '$id'")