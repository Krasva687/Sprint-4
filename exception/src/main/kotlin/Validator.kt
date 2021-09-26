abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}
//Валидация телефона на размер (не более 11), что имеются только цифры и начинается с 8 или 7
class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (!value.isNullOrEmpty()) {
            if (value.first() == '8' || value.first() == '7') {
                if (value.length != 11) {
                    return listOf(ErrorCode.INVALID_PHONE_SIZE)
                } else if (value.length == 11 && !(value.all { Character.isDigit(it) })) {
                    return listOf(ErrorCode.INVALID_PHONE_CHARACTER)
                }
            } else {
                return listOf(ErrorCode.INVALID_FIRST_DIGIT_PHONE)
            }
        } else {
            return listOf(ErrorCode.EMPTY_INPUT)
        }
        return listOf()
    }
}
//Валидация почты, чтоиспользуется только латиница, есть знак @ и домен с регионом через точку, а также размер не превышает 32
class EmailValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        if (!value.isNullOrEmpty()) {
            if(value.matches("^[A-z]+@[A-z]+.[A-z]+".toRegex()) && value.length <= 32){
                return listOf()
            }else if (value.length > 32){
                return listOf(ErrorCode.INVALID_MAIL_LENGTH)
            }else{
                return listOf(ErrorCode.INVALID_MAIL_CHARACTER)
            }
        }else {
            return listOf(ErrorCode.EMPTY_INPUT)
        }
    }

}
//Валидация имени, что используется только кириллица и размер не превышает 16
class FirstNameValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        if (!value.isNullOrEmpty()){
            if(value.matches("[А-я]+".toRegex()) && value.length < 17){
                return listOf()
            }else if(value.matches("[А-я]+".toRegex()) && value.length > 16){
                return listOf(ErrorCode.INVALID_FIRST_NAME_LENGTH)
            }
        }else {
            return listOf(ErrorCode.EMPTY_INPUT)
        }
        return listOf(ErrorCode.INVALID_FIRST_NAME_CHARACTER)
    }
}

//Валидация фамилии, что используется только кириллица и размер не превышает 16
class LastNameValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        if (!value.isNullOrEmpty()){
            if(value.matches("[А-я]+".toRegex()) && value.length < 17){
                return listOf()
            }else if(value.matches("[А-я]+".toRegex()) && value.length > 16){
                return listOf(ErrorCode.INVALID_LAST_NAME_LENGTH)
            }
        }else {
            return listOf(ErrorCode.EMPTY_INPUT)
        }
        return listOf(ErrorCode.INVALID_LAST_NAME_CHARACTER)
    }
}
//Валидация СНИЛС, размер не должен быть больше 11, должны быть использованы лишь цифры, так же проверка контрольной суммы если код больше дефолтного значения 001-001-998
class SnilsValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        if(!value.isNullOrEmpty()){
            if(value.matches("\\d+".toRegex()) && value.length == 11){
                //Если код больше дефолтного значения, то суммируем произведение чисел с обратным индексом и проверяем с контрольной суммой
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
        }else {
            return listOf(ErrorCode.EMPTY_INPUT)
        }
        return listOf(ErrorCode.INVALID_SNILS_CHARACTER)
    }

}