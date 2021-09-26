class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_PHONE_CHARACTER(100, "Недопустимый символ в телефоне"),
    INVALID_PHONE_SIZE(110, "Недопустимый размер номера телефона, должна быть равна 11"),
    INVALID_MAIL_CHARACTER(90, "Недопустимый формат почты, необходимо использовать только латиницу"),
    INVALID_MAIL_LENGTH(80, "Недопустимая длинна почты, должно быть не более 32"),
    INVALID_FIRST_NAME_LENGTH(70, "Недопустимая длинна имени, не более 16"),
    INVALID_LAST_NAME_LENGTH(60, "Недопустимая длинна фамилии, не более 16"),
    INVALID_LAST_NAME_CHARACTER(50, "Недопустимый формат фамилии, необходимо использовать только кириллицу"),
    INVALID_FIRST_NAME_CHARACTER(40, "Недопустимый формат имени, необходимо использовать только кириллицу"),
    INVALID_SNILS_LENGTH(30, "Недопустимая длинна СНИЛС, должно быть 11 цифр"),
    INVALID_SNILS_CHARACTER(20, "Недопустимый формат СНИЛС, необходимо использовать только цифры"),
    INVALID_SNILS(10, "Некоректный СНИЛС"),
    INVALID_FIRST_DIGIT_PHONE(5, "Неверная первая цифра в телефоне"),
    EMPTY_INPUT(1, "Пустая строка")
}