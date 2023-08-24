package presentation.meal

data class MealScreenUIState(
    val loading: Boolean = false,
    val error: String = "",
    val meal: MealUIState = MealUIState(),
    val cuisines: List<CuisineUIState> = emptyList(),
    val isAddEnable: Boolean = false
)

data class MealUIState(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: String = "",
    val currency: String = "USD",
    val flag: String = "",
    val image: ByteArray? = null,
    val imageUrl: String = "",
    val selectedCuisines: List<CuisineUIState> = emptyList(),
)


data class CuisineUIState(
    val id: String = "",
    val name: String = "",
    val isSelected: Boolean = false
)

