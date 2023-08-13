package org.thechance.service_identity.di


import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

@Module
@ComponentScan("org.thechance.service_identity")
class IdentityModule {

    private val cluster = System.getenv("cluster")
    private val username = System.getenv("username")
    private val password = System.getenv("password")

    @Single
    fun provideKmongoClient() = KMongo
        .createClient("mongodb+srv://$username:$password@$cluster.mongodb.net/").coroutine

}