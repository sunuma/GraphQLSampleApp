package com.use.product.network

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.ApolloResponse
import com.apollographql.apollo.api.Mutation
import com.use.product.graphqlsampleapp.LaunchDetailsQuery
import com.use.product.graphqlsampleapp.LaunchListQuery
import com.use.product.graphqlsampleapp.LoginMutation
import com.use.product.graphqlsampleapp.TripsBookedSubscription
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {
    /**
     *
     */
    suspend fun launchList(cursor: String?): ApolloResponse<LaunchListQuery.Data>

    /**
     *
     */
    suspend fun launchDetail(launchId: String): ApolloResponse<LaunchDetailsQuery.Data>

    /**
     *
     */
    suspend fun login(email: String): ApolloResponse<LoginMutation.Data>

    /**
     *
     */
    fun tripsBooked(): Flow<ApolloResponse<TripsBookedSubscription.Data>>

    /**
     *
     */
    fun <D : Mutation.Data> mutation(mutation: Mutation<D>): ApolloCall<D>
}