package ru.mmishak.bicyclewalks.domain.exceptions

class LoginAlreadyExistsException(login: String) : Exception("Login '$login' already exists in database")