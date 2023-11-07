package data.gateway.remote

import data.remote.mapper.toEntity
import data.remote.mapper.toOrderEntity
import data.remote.model.BaseResponse
import data.remote.model.OrderDto
import domain.entity.Order
import domain.gateway.remote.IOrderRemoteGateway
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OrderRemoteGateway(client: HttpClient) : IOrderRemoteGateway,
    BaseRemoteGateway(client = client) {

    override suspend fun getCurrentOrders(restaurantId: String): Flow<Order> {
        val result = client.tryToExecuteWebSocket<OrderDto>("/orders/$restaurantId")
        result.collect { order ->
            println("result: $order")
        }
        return result.map { it.toEntity() }
    }

    override suspend fun getActiveOrders(restaurantId: String): List<Order> {
        return tryToExecute<BaseResponse<List<OrderDto>>> {
            get("/order/$restaurantId/orders")
        }.value?.toOrderEntity() ?: emptyList()
    }

    override suspend fun updateOrderState(orderId: String, orderState: Int): Order {
        return tryToExecute<BaseResponse<OrderDto>>() {
            post("/order/$orderId/status") { setBody(orderState.toString()) }//todo check if correct
        }.value?.toEntity() ?: throw Exception("Error!")
    }

    override suspend fun getOrdersHistory(
        restaurantId: String,
        page: Int,
        limit: Int
    ): List<Order> {
        return tryToExecute<BaseResponse<List<OrderDto>>> {
            get("/order/history/$restaurantId?page=$page&limit=$limit")
        }.value?.toOrderEntity() ?: emptyList()
    }

    //for charts
    override suspend fun getOrdersRevenueByDaysBefore(
        restaurantId: String,
        daysBack: Int
    ): List<Map<String, Double>> {
        return tryToExecute<BaseResponse<List<Map<String, Double>>>> {
            get("/orders/revenue-by-days-back?restaurantId=$restaurantId&&daysBack=$daysBack")
        }.value ?: emptyList()
    }

    override suspend fun getOrdersCountByDaysBefore(
        restaurantId: String,
        daysBack: Int
    ): List<Map<String, Int>> {
        return tryToExecute<BaseResponse<List<Map<String, Int>>>> {
            get("/orders/count-by-days-back?restaurantId=$restaurantId&&daysBack=$daysBack")
        }.value ?: emptyList()
    }
}