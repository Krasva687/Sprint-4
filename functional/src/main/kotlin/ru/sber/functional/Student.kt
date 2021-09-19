package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Отчество отсутствует",
    val age: Int = 999,
    val averageRate: Double,
    val city: String = "Город не указан",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = "образование не указано",
)
