package org.thechance.common.data.local.datasource


interface ILocalDataSource {
    fun <T>createPDFReport(
        title: String,
        dataList: List<T>,
        columnNames: List<String>,
        colWidths: List<Float>,
        dataExtractor: (T) -> List<Any>
    )
}