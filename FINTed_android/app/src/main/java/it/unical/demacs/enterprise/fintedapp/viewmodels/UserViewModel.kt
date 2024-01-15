package it.unical.demacs.enterprise.fintedapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import it.unical.demacs.enterprise.fintedapp.apis.UserControllerApi
import it.unical.demacs.enterprise.fintedapp.models.UserDto
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto
import it.unical.demacs.enterprise.fintedapp.models.UserProfileDto
import it.unical.demacs.enterprise.fintedapp.models.UserRegistrationDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime

data class UserRegistrationState(
    val id: Long = 0,
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",

    val credentialsEmail: String = "",
    val credentialsPassword: String = "",

    val addressRoute: String = "",
    val addressNumber: String = "",
    val addressCity: String = ""
)

data class UserPersonalProfileState(
    val id: Long = 0,
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",

    val credentialsEmail: String = "",

    val addressRoute: String = "",
    val addressNumber: String = "",
    val addressCity: String = ""
)

class UserViewModel: ViewModel() {
    private val _userRegistrationState = MutableStateFlow(UserRegistrationState())
    val userRegistrationState: StateFlow<UserRegistrationState> = _userRegistrationState.asStateFlow()

    private val _userPersonalProfileState = MutableStateFlow(UserPersonalProfileState())
    val userPersonalProfileState: StateFlow<UserPersonalProfileState> = _userPersonalProfileState.asStateFlow()

    private val userApi: UserControllerApi = UserControllerApi()
    val userProfileList: MutableState<List<UserProfileDto>> = mutableStateOf(listOf())
    val personalProfile: MutableState<UserPersonalProfileDto> = mutableStateOf(
        UserPersonalProfileDto())

    fun registration(): UserDto {
        return userApi.save(
            UserRegistrationDto(
                id = null,
                firstName = userRegistrationState.value.firstName,
                lastName = userRegistrationState.value.lastName,
                username = userRegistrationState.value.username,
                credentialsEmail = userRegistrationState.value.credentialsEmail,
                credentialsPassword = userRegistrationState.value.credentialsPassword,
                addressCity = userRegistrationState.value.addressCity,
                addressNumber = userRegistrationState.value.addressNumber,
                addressRoute = userRegistrationState.value.addressRoute
            )
        )
    }

    fun updatePersonalProfile() {
        personalProfile.value = userApi.update(
            UserPersonalProfileDto(
                id = userPersonalProfileState.value.id,
                firstName = userPersonalProfileState.value.firstName,
                lastName = userPersonalProfileState.value.lastName,
                username = userPersonalProfileState.value.username,
                credentialsEmail = userPersonalProfileState.value.credentialsEmail,
                addressCity = userPersonalProfileState.value.addressCity,
                addressNumber = userPersonalProfileState.value.addressNumber,
                addressRoute = userPersonalProfileState.value.addressRoute
            )
        )
    }

    fun getPersonalProfile(id: Long){
        personalProfile.value = userApi.getPersonalProfile(id)
    }

    fun getUser(id: Long): UserDto {
        return userApi.get(id);
    }

    fun getUserList(page: Int) {
        userProfileList.value = userApi.getAll(page).toList()
    }

}