plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.graphqlsampleapp.android.library)
    alias(libs.plugins.graphqlsampleapp.hilt)
    alias(libs.plugins.apollo)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.use.product.graphqlsampleapp.network"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.apollo.runtime)
    implementation(libs.androidx.security.crypto)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

apollo {
    service("service") {
        packageName.set("com.use.product.graphqlsampleapp")
        introspection {
            endpointUrl.set("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
            schemaFile.set(file("src/main/graphql/schema.graphqls"))
        }
    }
}