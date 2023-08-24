package org.thechance.common.data.local.gateway

import org.thechance.common.data.local.datasource.ILocalDataSource
import org.thechance.common.domain.getway.ILocalDataGateway

class LocalDataGateway(
    private val localDataSource: ILocalDataSource
) : ILocalDataGateway {
    override fun <T> createPDFReport(
        title: String,
        dataList: List<T>,
        columnNames: List<String>,
        colWidths: List<Float>,
        dataExtractor: (T) -> List<Any>
    ) = localDataSource.createPDFReport(title, dataList, columnNames, colWidths, dataExtractor)


}