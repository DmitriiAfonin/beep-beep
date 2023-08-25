package org.thechance.common.data.local.gateway

import org.thechance.common.domain.getway.ILocalDataGateway
import java.io.File

class LocalDataGateway : ILocalDataGateway {
    override suspend fun saveTaxiReport(file: File) {
        //todo save file
    }
}