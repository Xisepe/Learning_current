package ru.golubev.learning_current.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.golubev.learning_current.data.UserRepository
import javax.inject.Inject

data class EntryState(
    var username: String = "",
    var password: String = "",
    var isPasswordVisible: Boolean = false
)


@HiltViewModel
class EntryViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val entryState by mutableStateOf(EntryState())


}