package com.use.product.network.di

import android.content.Context
import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.use.product.network.model.ApolloApiToken
import com.use.product.network.model.ApolloApiTokenImpl
import com.use.product.network.model.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.delay
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApolloToken(
        @ApplicationContext context: Context
    ): ApolloApiToken = ApolloApiTokenImpl(context)

    @Provides
    @Singleton
    fun provideAuthorizationInterceptor(
        apolloApiToken: ApolloApiToken
    ): Interceptor = AuthorizationInterceptor(apolloApiToken)

    @Provides
    @Singleton
    fun provideHttpClient(
        interceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideApolloClient(
        httpClient: OkHttpClient
    ): ApolloClient = ApolloClient.Builder()
        .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
        .webSocketServerUrl("wss://apollo-fullstack-tutorial.herokuapp.com/graphql")
        .okHttpClient(httpClient)
        .webSocketReopenWhen { throwable, attempt ->
            Log.d("Apollo", "WebSocket got disconnected, reopening after a delay", throwable)
            delay(attempt * 1000)
            true
        }
        .build()
}