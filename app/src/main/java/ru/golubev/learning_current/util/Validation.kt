package ru.golubev.learning_current.util

import android.os.Parcelable
import androidx.compose.runtime.saveable.mapSaver

interface Validator {
    fun validate(text: String): ValidationResult
}

class ValidationOptions(
    val minLength: Int = 8,
    val containsDigit: Boolean = false,
    val containsSpecialSymbol: Boolean = false,
    val containsUppercase: Boolean = false,
    val containsLowercase: Boolean = false
)


class ValidationResult(
    val isValid: Boolean = true,
    val validationMessage: String = "OK",
)

val ValidationResultSaver = run {
    val isValidKey = "IsValid"
    val validationMessageKey = "Message"
    mapSaver(
        save = { mapOf(isValidKey to it.isValid, validationMessageKey to it.validationMessage)},
        restore = {ValidationResult(it[isValidKey] as Boolean, it[validationMessageKey] as String)}
    )
}

private fun INVALID_LENGTH_MSG(value: Int) = "must be at least $value symbols"
private const val WHITESPASE_MSG = "no whitespace allowed"
private const val DIGIT_MSG = "a digit must occur at least once"
private const val SPECIAL_SYMBOL_MSG = "a special character must occur at least once"
private const val LOWER_CASE_MSG = "a lower case letter must occur at least once"
private const val UPPER_CASE_MSG = "an upper case letter must occur at least once"
private const val CYRILLIC_MSG = "no cyrillic"

class PasswordValidator(
    private val options: ValidationOptions = ValidationOptions()
): Validator {
    override fun validate(text: String): ValidationResult {
        return when {
            text.contains("[А-ЯЁ][-А-яЁё]".toRegex()) -> ValidationResult(false, CYRILLIC_MSG)
            text.length < options.minLength -> ValidationResult(false, INVALID_LENGTH_MSG(options.minLength))
            text.contains("\\s".toRegex()) -> ValidationResult(false, WHITESPASE_MSG)
            options.containsDigit && !text.contains("[0-9]".toRegex()) -> ValidationResult(false, DIGIT_MSG)
            options.containsSpecialSymbol && !text.contains("[@#\$%^&+=_]".toRegex()) -> ValidationResult(false, SPECIAL_SYMBOL_MSG)
            options.containsLowercase && !text.contains("[a-z]".toRegex()) -> ValidationResult(false, LOWER_CASE_MSG)
            options.containsUppercase && !text.contains("[A-Z]".toRegex()) -> ValidationResult(false, UPPER_CASE_MSG)
            else -> ValidationResult(true, "OK")
        }
    }

}