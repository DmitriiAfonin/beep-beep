package presentation.meals

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.coroutineScope
import domain.entity.Cuisine
import domain.entity.Meal
import domain.usecase.IManageCuisineUseCase
import domain.usecase.IManageMealUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import presentation.base.BaseScreenModel
import presentation.base.ErrorState
import util.DefaultPaginator

class MealsScreenModel(
    private val restaurantId: String,
    private val mangeCousin: IManageCuisineUseCase,
    private val mangeMeal: IManageMealUseCase
) :
    BaseScreenModel<MealsScreenUIState, MealsScreenUIEffect>(MealsScreenUIState()),
    MealScreenInteractionListener {
    override val viewModelScope: CoroutineScope = coroutineScope

   // var uiState by mutableStateOf(MealsScreenUIState())

    private val paginator = DefaultPaginator(
        initialKey = _state.value.page,
        onLoadUpdated = {
            _state.value = _state.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            mangeMeal.getAllMeals(restaurantId, nextPage, 10)
        },
        getNextKey = {
            _state.value.page + 1
        },
        onError = {
            _state.value = _state.value.copy(error = ErrorState.NoInternet)
        },
        onSuccess = { items, newKey ->
            _state.value = _state.value.copy(
                meals = _state.value.meals + items.toMealUIState(),
                page = newKey,
                endReached = items.isEmpty()
            )

        }
    )
    init {
        getCuisine()
        loadNextItems()
        //getMeals("64f372095fecc11e6d917656")
    }
    override fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()

        }
    }



    private fun getCuisine() {
        tryToExecute(
            function = {
                mangeCousin.getCuisines()
            },
            ::onGetCuisineSuccessfully,
            ::onError
        )
    }

    private fun getMeals(restaurantId: String) {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            function = {
                  mangeMeal.getAllMeals(restaurantId,1,10)
            },
            ::onGetMealSuccessfully,
            ::onError
        )
    }

    private fun onGetCuisineSuccessfully(cuisines: List<Cuisine>) {
        updateState {
            it.copy(cuisines = cuisines.toCuisineUIState(), selectedCuisine = cuisines.toCuisineUIState()
                .first())
        }
        getMeals(state.value.selectedCuisine.id)
    }

    private fun onGetMealSuccessfully(meals: List<Meal>) {
        updateState { it.copy(meals = meals.toMealUIState(), isLoading = false) }
    }

    private fun onError(error: ErrorState) {
        updateState { it.copy(error = error, isLoading = false) }
    }


    override fun onClickBack() {
        sendNewEffect(MealsScreenUIEffect.Back)
    }

    override fun onClickMeal(mealId: String) {
        sendNewEffect(MealsScreenUIEffect.NavigateToMealDetails(mealId))
    }

    override fun onAddMeaClick() {
        sendNewEffect(MealsScreenUIEffect.NavigateToAddMeal)
    }

    override fun onClickCuisineType(type: CuisineUIState) {
        updateState { it.copy(selectedCuisine = type) }
        getMeals(type.id)
    }
}
