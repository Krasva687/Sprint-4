abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value != null) {
            if(value.length != 11 && (value.first() != '8' || value.first() != '7' )){
                return listOf(ErrorCode.INVALID_PHONE_SIZE)
            }else if (value.length == 11 && !(value.all { Character.isDigit(it) })){
                    return listOf(ErrorCode.INVALID_PHONE_CHARACTER)
            }
        }
        return listOf()
    }
}

class EmailValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        if (value != null) {
            if(value.matches("^[A-z]+@[A-z]+.[A-z]+".toRegex()) && value.length <= 32){
                return listOf()
            }else if (value.length > 32){
                return listOf(ErrorCode.INVALID_MAIL_LENGTH)
            }else{
                return listOf(ErrorCode.INVALID_MAIL_CHARACTER)
            }
        }
        return listOf(ErrorCode.INVALID_MAIL_CHARACTER)
    }

}

class FirstNameValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        if (value != null){
            if(value.matches("[А-я]+".toRegex()) && value.length < 17){
                return listOf()
            }else if(value.matches("[А-я]+".toRegex()) && value.length > 16){
                return listOf(ErrorCode.INVALID_FIRST_NAME_LENGTH)
            }
        }
        return listOf(ErrorCode.INVALID_FIRST_NAME_CHARACTER)
    }
}


class LastNameValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        if (value != null){
            if(value.matches("[А-я]+".toRegex()) && value.length < 17){
                return listOf()
            }else if(value.matches("[А-я]+".toRegex()) && value.length > 16){
                return listOf(ErrorCode.INVALID_LAST_NAME_LENGTH)
            }
        }
        return listOf(ErrorCode.INVALID_LAST_NAME_CHARACTER)
    }
}

class SnilsValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        if(value != null){
            if(value.matches("\\d+".toRegex()) && value.length == 11){
                if(value.toLong() > 1001998) {
                    val snilsArr = value.toCharArray()
                    var snilsSum = 0
                    var reverseIndex = 9
                    for (i in 0..8) {
                        snilsSum += snilsArr[i].toString().toInt() * reverseIndex
                        reverseIndex--
                    }
                    if (snilsSum % 101 == (snilsArr[9].toString() + snilsArr[10].toString()).toInt()) {
                        return listOf()
                    }else{
                        return listOf(ErrorCode.INVALID_SNILS)
                    }
                }
                return listOf()
            }else if(value.length != 11 && value.matches("\\d+".toRegex())){
                return listOf(ErrorCode.INVALID_SNILS_LENGTH)
            }else if(!(value.matches("\\d+".toRegex()))){
                return listOf(ErrorCode.INVALID_SNILS_CHARACTER)
            }
        }
        return listOf(ErrorCode.INVALID_SNILS_CHARACTER)
    }

}