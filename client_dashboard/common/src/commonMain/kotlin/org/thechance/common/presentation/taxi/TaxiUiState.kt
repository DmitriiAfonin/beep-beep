package org.thechance.common.presentation.taxi


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.beepbeep.designSystem.ui.theme.Theme
import org.thechance.common.domain.entity.*
import org.thechance.common.domain.util.TaxiStatus
import org.thechance.common.domain.util.TaxiStatus.ONLINE
import org.thechance.common.presentation.composables.table.Header
import org.thechance.common.presentation.util.ErrorState

data class TaxiUiState(
    val isLoading: Boolean = false,
    val error: ErrorState = ErrorState.UnKnownError,
    val isAddNewTaxiDialogVisible: Boolean = false,
    val newTaxiInfo: NewTaxiInfoUiState = NewTaxiInfoUiState(),
    val taxiFilterUiState: TaxiFilterUiState = TaxiFilterUiState(),
    val taxis: List<TaxiDetailsUiState> = emptyList(),
    val searchQuery: String = "",
    val isReportExportedSuccessfully: Boolean = false,
    val pageInfo: TaxiPageInfoUiState = TaxiPageInfoUiState(),
    val specifiedTaxis: Int = 10,
    val currentPage: Int = 1,
    val taxiMenu: MenuUiState = MenuUiState(),
    val isFilterDropdownMenuExpanded: Boolean = false,

    ) {
    val tabHeader = listOf(
        Header("No.", 1f),
        Header("Plate number", 3f),
        Header("Driver username", 3f),
        Header("Status", 3f),
        Header("Car model", 3f),
        Header("Car color", 3f),
        Header("Seats", 3f),
        Header("Trips", 3f),
        Header("", 1f),
    )
}

data class TaxiDetailsUiState(
    val id: String = "",
    val plateNumber: String = "",
    val color: CarColor = CarColor.WHITE,
    val type: String = "",
    val seats: Int = 4,
    val username: String = "",
    val status: TaxiStatus = ONLINE,
    val trips: String = "1",
) {
    val statusColor: Color
        @Composable get() = when (status) {
            TaxiStatus.OFFLINE -> Theme.colors.primary
            TaxiStatus.ONLINE -> Theme.colors.success
            TaxiStatus.ON_RIDE -> Theme.colors.warning
        }
}
data class TaxiFilterUiState(
    val carColor: CarColor = CarColor.WHITE,
    val seats: Int = 0,
    val status: TaxiStatus = ONLINE,
)

fun TaxiStatus.getStatusName():String{
    return when (this) {
        TaxiStatus.OFFLINE -> "Offline"
        TaxiStatus.ONLINE -> "Online"
        TaxiStatus.ON_RIDE -> "On Ride"
    }
}

data class TaxiPageInfoUiState(
    val data: List<TaxiDetailsUiState> = emptyList(),
    val numberOfTaxis: Int = 0,
    val totalPages: Int = 0,
)
data class NewTaxiInfoUiState(
    val plateNumber: String = "",
    val driverUserName: String = "",
    val carModel: String = "",
    val selectedCarColor: CarColor = CarColor.WHITE,
    val seats: Int = 0
)
data class MenuUiState(
    val username: String = "",
    val items: List<MenuItemUiState> = listOf(
        MenuItemUiState(
            iconPath = "ic_edit.xml",
            text = "Edit",
        ),
        MenuItemUiState(
            iconPath = "ic_delete.svg",
            text = "Delete",
            isSecondary = true,
        ),
    )
) {
    data class MenuItemUiState(
        val iconPath: String = "",
        val text: String = "",
        val isSecondary: Boolean = false,
    )
}
fun DataWrapper<Taxi>.toUiState(): TaxiPageInfoUiState {
    return TaxiPageInfoUiState(
        data = result.toUiState(),
        totalPages = totalPages,
        numberOfTaxis = numberOfResult
    )
}

fun Taxi.toUiState(): TaxiDetailsUiState = TaxiDetailsUiState(
    id = id,
    plateNumber = plateNumber,
    color = color,
    type = type,
    seats = seats,
    username = username,
    status = status,
    trips = trips,
)

fun List<Taxi>.toUiState() = map { it.toUiState() }
fun NewTaxiInfoUiState.toEntity() = NewTaxiInfo(plateNumber, driverUserName, carModel, selectedCarColor, seats)

fun TaxiDetailsUiState.toEntity(): Taxi = Taxi(
    id = id,
    plateNumber = plateNumber,
    color = color,
    type = type,
    seats = seats,
    username = username,
    status = status,
    trips = trips,
)

fun List<TaxiDetailsUiState>.toEntity() = map { it.toEntity() }

