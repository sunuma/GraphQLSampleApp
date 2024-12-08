package com.use.product.network

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.apollographql.apollo.api.Mutation
import com.apollographql.apollo.api.Optional
import com.use.product.graphqlsampleapp.LaunchDetailsQuery
import com.use.product.graphqlsampleapp.LaunchListQuery
import com.use.product.graphqlsampleapp.LoginMutation
import com.use.product.graphqlsampleapp.TripsBookedSubscription
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ApolloNetwork @Inject constructor(
    private val apolloClient: ApolloClient
): NetworkDataSource {

    override suspend fun launchList(cursor: String?): ApolloResponse<LaunchListQuery.Data> {
        return apolloClient.query(LaunchListQuery(Optional.present(cursor))).execute()
    }

    override suspend fun launchDetail(launchId: String): ApolloResponse<LaunchDetailsQuery.Data> {
        return apolloClient.query(LaunchDetailsQuery(launchId)).execute()
    }

    override suspend fun login(email: String): ApolloResponse<LoginMutation.Data> {
        return apolloClient.mutation(LoginMutation(email)).execute()
    }

    override fun tripsBooked(): Flow<ApolloResponse<TripsBookedSubscription.Data>> {
        return apolloClient.subscription(TripsBookedSubscription()).toFlow()
    }

    override fun <D : Mutation.Data> mutation(mutation: Mutation<D>): ApolloCall<D> {
        return apolloClient.mutation(mutation)
    }

}

