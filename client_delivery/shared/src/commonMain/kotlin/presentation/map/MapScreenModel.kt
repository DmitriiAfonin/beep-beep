package presentation.map

import cafe.adriel.voyager.core.model.coroutineScope
import domain.entity.Location
import domain.entity.Order
import domain.usecase.IManageLoginUserUseCase
import domain.usecase.IManageOrderUseCase
import domain.usecase.IManageUserLocationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import presentation.base.BaseScreenModel
import presentation.base.ErrorState

class MapScreenModel(
    private val currentLocation: IManageUserLocationUseCase,
    private val manageLoginUser: IManageLoginUserUseCase,
    private val manageOrderUseCase: IManageOrderUseCase,
):BaseScreenModel<MapScreenUiState,MapScreenUiEffect>(MapScreenUiState()),MapScreenInteractionsListener {

    override val viewModelScope: CoroutineScope = coroutineScope

    init {
        getUserName()
        getCurrentLocation()
        getOrder()
    }
     private fun getUserName() {
        viewModelScope.launch {
            val username = manageLoginUser.getUsername()
            updateState {
                it.copy(
                    username = username,
                )
            }
        }
     }

    private fun getOrder() {
        updateState { it.copy(orderState = OrderState.LOADING) }
        tryToCollect(
            function = manageOrderUseCase::getOrders,
            onNewValue = ::onGetOrderSuccess,
            onError = ::onError
        )
    }

    private fun onGetOrderSuccess(order: Order) {
        val newOrder=order.toUiState()
        updateState { mapScreenUiState ->
            mapScreenUiState.copy(
                orderUiState = newOrder,
                orderState = OrderState.NEW_ORDER,
                tripId = newOrder.orderId,
            )
        }
    }

    private fun getCurrentLocation() {
        tryToCollect(
            function = currentLocation::trackCurrentLocation,
            onNewValue = ::onGetLiveLocationSuccess,
            onError = ::onError
        )
    }

    private fun updateOrderState() {
        viewModelScope.launch {
            delay(5000)
            updateState {
                it.copy(
                    orderState = OrderState.NEW_ORDER,
                    orderUiState = OrderUiState(
                        destinationLocation = LocationUiState(
                            31.2001,
                            29.9187,
                            ""
                        )
                    )//fake
                )
            }
        }
    }

    private fun onGetLiveLocationSuccess(location: Location) {
        viewModelScope.launch {
            delay(2000)
            updateState {
                it.copy(
                    errorState = null,
                    orderUiState = OrderUiState(restaurantLocation = location.toUiState())
                )
            }
        }
    }

    private fun onError(errorState: ErrorState) {
        updateState {
            it.copy(
                orderState = OrderState.LOADING,
                errorState = errorState,
            )
        }
    }

    override fun onAcceptClicked() {
        tryToExecute(
            function = { manageOrderUseCase.updateTrip("653cf6ef5a253b12181fa2d0",state.value.tripId) },
            onSuccess = ::onAcceptOrderSuccess,
            onError = ::onError
        )
    }

    private fun onAcceptOrderSuccess(order: Order) {
        updateState { mapScreenUiState ->
            mapScreenUiState.copy(
                //orderUiState = order.toUiState(),
                orderState = OrderState.ACCEPTED,
                //todo replace this with the restaurant actual location
                orderUiState = OrderUiState(
                        destinationLocation = LocationUiState(
                            31.2001,
                            29.9187,
                            ""
                        )
                    )//fake
            )
        }
    }

    override fun onRejectClicked() {
        viewModelScope.launch {
            updateState { it.copy(orderState = OrderState.LOADING) }
        }
    }

    override fun onReceivedClicked() {
        tryToExecute(
            function = { manageOrderUseCase.updateTrip("653cf6ef5a253b12181fa2d0", state.value.tripId) },
            onSuccess = ::onUpdateOrderAsReceivedSuccess,
            onError = ::onError
        )
    }

    private fun onUpdateOrderAsReceivedSuccess(order: Order) {
        updateState { mapScreenUiState ->
            mapScreenUiState.copy(
                orderUiState = order.toUiState(),
                orderState = OrderState.RECEIVED
            )
        }
    }

    override fun onDeliveredClicked() {
        tryToExecute(
            function = { manageOrderUseCase.updateTrip("653cf6ef5a253b12181fa2d0", state.value.tripId) },
            onSuccess = ::onUpdateOrderAsDeliveredSuccess,
            onError = ::onError
        )
    }

    private fun onUpdateOrderAsDeliveredSuccess(order: Order) {
        updateState { mapScreenUiState ->
            mapScreenUiState.copy(
                orderUiState = order.toUiState(),
                orderState = OrderState.DELIVERED
            )
        }
    }

    override fun onCloseClicked() {
        viewModelScope.launch {
            currentLocation.stopTracking()
        }
        sendNewEffect(MapScreenUiEffect.CloseMap)
    }

}