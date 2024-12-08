package com.use.product.network.model

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val apolloApiToken: ApolloApiToken,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .apply {
                apolloApiToken.get()?.let { token ->
                    addHeader("Authorization", token)
                }
            }
            .build()
        return chain.proceed(request)
    }
}