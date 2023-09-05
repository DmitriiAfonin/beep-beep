package org.thechance.service_location.di

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import org.bson.UuidRepresentation
import org.koin.dsl.module
import org.litote.kmongo.reactivestreams.KMongo

val DataBaseModule = module {
    single {
        val cluster = System.getenv("cluster")
        val username = System.getenv("username")
        val password = System.getenv("password")
        val connectionString = ConnectionString("mongodb+srv://$username:$password@$cluster.mongodb.net/")

        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .uuidRepresentation(UuidRepresentation.STANDARD)
            .build()
        KMongo.createClient(settings)
    }

    // todo add database if needed
//    single { DataBaseContainer(get()) }
}