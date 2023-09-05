package org.thechance.service_location.data

import com.mongodb.reactivestreams.client.MongoClient

class DataBaseContainer(client: MongoClient) {

    companion object {
        private val DATA_BASE_NAME = System.getenv("DB_NAME").toString()
        const val LOCATION_COLLECTION = "location"
    }

}