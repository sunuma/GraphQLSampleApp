package com.use.product.network.model

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ApolloApiToken {
    fun set(token: String)
    fun get(): String?
    fun remove()
}

class ApolloApiTokenImpl @Inject constructor(
    @ApplicationContext private val context: Context,
): ApolloApiToken {
    private val tokenKey = "TOKEN"

    private val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val preferences = EncryptedSharedPreferences.create(
        context,
        "secret_shared_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )

    override fun set(token: String) {
        preferences.edit().apply {
            putString(tokenKey, token)
            apply()
        }
    }

    override fun get(): String? = preferences.getString(tokenKey, null)

    override fun remove() {
        preferences.edit().apply {
            remove(tokenKey)
            apply()
        }
    }

}