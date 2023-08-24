package org.thechance.common.domain.getway


interface ILocalDataGateway {
    fun <T>createPDFReport(
        title: String,
        dataList: List<T>,
        columnNames: List<String>,
        colWidths: List<Float>,
        dataExtractor: (T) -> List<Any>
    )
}
