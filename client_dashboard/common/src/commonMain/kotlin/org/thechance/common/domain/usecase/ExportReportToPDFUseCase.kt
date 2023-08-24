package org.thechance.common.domain.usecase

import org.thechance.common.domain.entity.Taxi
import org.thechance.common.domain.getway.ILocalDataGateway

interface IExportReportToPDFUseCase {

    fun createTaxiReport(title: String,taxi: List<Taxi>,colWidths: List<Float>, columnNames: List<String>)

}

class ExportReportToPDFUseCase(
    private val localGateway: ILocalDataGateway
) : IExportReportToPDFUseCase {
    override fun createTaxiReport(title: String,taxi: List<Taxi>,colWidths: List<Float>, columnNames: List<String>) {
        localGateway.createPDFReport(title, taxi, columnNames, colWidths){
            listOf(it.id, it.username, it.plateNumber, it.type, it.color, it.seats,it.status,it.trips)
        }
    }
}