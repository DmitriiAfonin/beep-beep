package org.thechance.common.domain.getway

import java.io.File


interface ILocalDataGateway {
    suspend fun saveTaxiReport(file: File)
}
