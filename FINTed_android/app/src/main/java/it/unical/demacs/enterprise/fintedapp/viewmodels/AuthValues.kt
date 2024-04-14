package it.unical.demacs.enterprise.fintedapp.viewmodels

import it.unical.demacs.enterprise.fintedapp.models.AccessTokenResponse
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto

object AuthValues {
    var accessTokenResponse: AccessTokenResponse = AccessTokenResponse()
    var personalProfile: UserPersonalProfileDto = UserPersonalProfileDto()
}