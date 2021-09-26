import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - validation errors phone`() {
        var client = getClientFromJson("/fail/user_with_bad_phone1.json")
        var exception = assertThrows<ValidationException>("Ожидаемая ошибка неверный формат телефона") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_PHONE_CHARACTER)

        client = getClientFromJson("/fail/user_with_bad_phone2.json")
        exception = assertThrows<ValidationException>("Ожидаемая ошибка, неверный размер телефона") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_PHONE_SIZE)

        client = getClientFromJson("/fail/user_with_bad_phone4.json")
        exception = assertThrows<ValidationException>("Ожидаемая ошибка, пустая строка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.EMPTY_INPUT)
    }

    @Test
    fun `fail save client - validation errors first name`() {
        var client = getClientFromJson("/fail/first_name_bad1.json")
        var exception = assertFailsWith<ValidationException>("Ожидаемая ошибка неверный формат имени") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_FIRST_NAME_CHARACTER)

        client = getClientFromJson("/fail/first_name_bad2.json")
        exception = assertFailsWith<ValidationException>("Ожидаемая ошибка неверный размер имени") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_FIRST_NAME_LENGTH)

        client = getClientFromJson("/fail/first_name_bad3.json")
        exception = assertThrows<ValidationException>("Ожидаемая ошибка, пустая строка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.EMPTY_INPUT)
    }

    @Test
    fun `fail save client - validation errors last name`(){
        var client = getClientFromJson("/fail/last_name_bad1.json")
        var exception = assertFailsWith<ValidationException> ("Ожидаемая ошибка неверный формат фамилии" ){
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LAST_NAME_CHARACTER)

        client = getClientFromJson("/fail/last_name_bad2.json")
        exception = assertFailsWith<ValidationException> ("Ожидаемая ошибка неверный размер фамилии" ){
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LAST_NAME_LENGTH)

        client = getClientFromJson("/fail/last_name_bad3.json")
        exception = assertThrows<ValidationException>("Ожидаемая ошибка, пустая строка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.EMPTY_INPUT)
    }

    @Test
    fun `fail save client - validation errors email`(){
        var client = getClientFromJson("/fail/email_bad1.json")
        var exception = assertFailsWith<ValidationException> ("Ожидаемая ошибка неверный формат почты" ){
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_MAIL_CHARACTER)

        client = getClientFromJson("/fail/email_bad2.json")
        exception = assertFailsWith<ValidationException> ("Ожидаемая ошибка неверный размер почты" ){
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_MAIL_LENGTH)

        client = getClientFromJson("/fail/email_bad3.json")
        exception = assertThrows<ValidationException>("Ожидаемая ошибка, пустая строка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.EMPTY_INPUT)
    }

    @Test
    fun `fail save client - validation errors snils`(){
        var client = getClientFromJson("/fail/snils_bad1.json")
        var exception = assertFailsWith<ValidationException> ("Ожидаемая ошибка неверный формат СНИЛС" ){
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_SNILS_CHARACTER)

        client = getClientFromJson("/fail/snils_bad2.json")
        exception = assertFailsWith<ValidationException> ("Ожидаемая ошибка неверный размер СНИЛС" ){
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_SNILS_LENGTH)


        client = getClientFromJson("/fail/snils_bad3.json")
        exception = assertFailsWith<ValidationException> ("Ожидаемая ошибка, контрольная сумма не совпадает" ){
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_SNILS)

        client = getClientFromJson("/fail/snils_bad4.json")
        exception = assertThrows<ValidationException>("Ожидаемая ошибка, пустая строка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.EMPTY_INPUT)
    }


    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}