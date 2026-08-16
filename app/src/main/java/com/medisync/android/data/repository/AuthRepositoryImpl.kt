package com.medisync.android.data.repository

import com.medisync.android.core.network.ApiResponse
import com.medisync.android.core.network.NetworkClient
import com.medisync.android.core.storage.AuthTokenManager
import com.medisync.android.data.model.AuthResponseData
import com.medisync.android.data.model.LoginRequest
import com.medisync.android.data.model.RegisterRequest
import com.medisync.android.data.model.UserProfile
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val tokenManager: AuthTokenManager
) : AuthRepository {

    override suspend fun login(request: LoginRequest): Result<AuthResponseData> {
        return try {
            val response: ApiResponse<AuthResponseData> = httpClient.post("${NetworkClient.BASE_URL}/auth/login") {
                setBody(request)
            }.body()

            if (response.success && response.data != null) {
                tokenManager.saveTokens(response.data.accessToken, response.data.refreshToken)
                tokenManager.saveUser(response.data.user)
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message ?: response.error ?: "Login failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(request: RegisterRequest): Result<AuthResponseData> {
        return try {
            val response: ApiResponse<AuthResponseData> = httpClient.post("${NetworkClient.BASE_URL}/auth/register") {
                setBody(request)
            }.body()

            if (response.success && response.data != null) {
                tokenManager.saveTokens(response.data.accessToken, response.data.refreshToken)
                tokenManager.saveUser(response.data.user)
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message ?: response.error ?: "Registration failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCachedUser(): UserProfile? = tokenManager.getUser()

    override fun isLoggedIn(): Boolean = tokenManager.isLoggedIn()

    override fun logout() {
        tokenManager.clear()
    }
}
