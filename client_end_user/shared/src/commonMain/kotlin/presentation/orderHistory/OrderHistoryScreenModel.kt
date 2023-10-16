package presentation.orderHistory

import androidx.paging.PagingData
import androidx.paging.map
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.model.coroutineScope
import domain.entity.Order
import domain.usecase.GetOrderHistoryUseCase
import domain.usecase.IManageAuthenticationUseCase
import domain.usecase.IPaginationTestUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import presentation.base.BaseScreenModel
import presentation.base.ErrorState

class OrderHistoryScreenModel(
    private val paginationOrder: IPaginationTestUseCase,
    private val orderHistoryUseCase: GetOrderHistoryUseCase,
    private val manageAuthentication: IManageAuthenticationUseCase
) : BaseScreenModel<OrderScreenUiState, OrderHistoryScreenUiEffect>(OrderScreenUiState()),
    OrderHistoryScreenInteractionListener {

    override val viewModelScope: CoroutineScope = coroutineScope

    init {
        checkIfLoggedIn()
        getOrdersHistory()
        getTripsHistory()
    }

    private fun checkIfLoggedIn() {
        tryToExecute(
            { manageAuthentication.getAccessToken() },
            ::onCheckIfLoggedInSuccess,
            ::onCheckIfLoggedInError
        )
    }

    private fun onCheckIfLoggedInSuccess(accessToken: Flow<String>) {
        coroutineScope.launch {
            accessToken.collect { token ->
                if (token.isNotEmpty()) {
                    updateState { it.copy(isLoggedIn = true) }
                } else {
                    updateState { it.copy(isLoggedIn = false) }
                }
            }
        }
    }

    private fun onCheckIfLoggedInError(errorState: ErrorState) {
        updateState { it.copy(isLoggedIn = false) }
    }

    private fun getOrdersHistory() {
        tryToExecute(
            { paginationOrder.getPaginationTest() },
            ::onGetOrdersHistorySuccess,
            ::onError
        )
    }

    private fun getTripsHistory() {
        tryToExecute(
            { orderHistoryUseCase.getTripsHistory().map { it.toTripHistoryUiState() } },
            ::onGetTripsHistorySuccess,
            ::onError
        )
    }

    private fun onGetTripsHistorySuccess(tripsHistory: List<TripHistoryUiState>) {
        updateState { it.copy(tripsHistory = tripsHistory) }
    }

    private fun onGetOrdersHistorySuccess(result :Flow<PagingData<Order>>) {
        val mappedResult = result.map { pagingData ->
            pagingData.map { it.toOrderHistoryUiState()
            }
        }


        updateState { it.copy(ordersHistory = mappedResult) }
    }

    private fun onError(errorState: ErrorState) {
        // catch errors here
    }

    override fun onClickTab(type: OrderScreenUiState.OrderSelectType) {
        updateState { it.copy(selectedType = type) }
    }

    override fun onClickLogin() {
        sendNewEffect(OrderHistoryScreenUiEffect.NavigateToLoginScreen)
    }
}