package presentation.meal

import cafe.adriel.voyager.core.model.coroutineScope
import domain.entity.Cuisine
import domain.entity.Meal
import domain.usecase.IManageMealUseCase
import domain.usecase.IMangeCousinUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.inject
import presentation.base.BaseScreenModel
import presentation.base.ErrorState

class MealScreenModel(private val mealId: String?) :
    BaseScreenModel<MealScreenUIState, MealScreenUIEffect>(MealScreenUIState()),
    MealScreenInteractionListener {

    override val viewModelScope: CoroutineScope
        get() = coroutineScope

    private val manageMeal: IManageMealUseCase by inject()
    private val cuisines: IMangeCousinUseCase by inject()

    init {
        getCuisines()
        getMeal()
    }

    private fun getCuisines() {
        tryToExecute({ cuisines.getCuisine() }, ::onGetCuisinesSuccess, ::onGetCuisinesError)
    }

    private fun getMeal() {
        mealId?.let {
            tryToExecute({ manageMeal.getMeal(mealId) }, ::onGetMealSuccess, ::onAddMealError)
        }
    }

    private fun onGetCuisinesSuccess(cuisines: List<Cuisine>) {
        updateState { state ->
            state.copy(cuisines = cuisines.toCuisineUIState())
        }
    }

    override fun onAddMeal() {
        tryToExecute(
            { manageMeal.addMeal(state.value.meal.toMealEntity()) },
            ::onMealAddedSuccessfully,
            ::onAddMealError
        )
    }

    override fun onCuisineClick() {
//        updateState { state ->
//            val selectedCuisineIds = state.selectedCuisines.map { it.id }
//            val cuisines = state.cuisines.map {
//                if (it.id in selectedCuisineIds) {
//                    it.copy(isSelected = true)
//                }
//                it
//            }
//            state.copy(cuisines = cuisines)
//        }
    }

    private fun onMealAddedSuccessfully(result: Boolean) {
        sendNewEffect(MealScreenUIEffect.MealResponseSuccessfully)
    }


    private fun onGetMealSuccess(meal: Meal) {
        val mealCuisines = state.value.cuisines.filter { it.id in meal.cuisines.map { it.id } }
        val cuisines = state.value.cuisines
        updateState {
            it.copy(
                meal = meal.toMealUIState().copy(selectedCuisines = mealCuisines),
                cuisines = cuisines,
                isAddEnable = it.meal.isValid()
            )
        }
    }

    private fun onAddMealError(error: ErrorState) {
        println("MEAL SCREEN ${error}")
    }

    private fun onGetCuisinesError(error: ErrorState) {
        println("MEAL SCREEN ${error}")
    }

    override fun onSaveCuisineClick() {
//        updateState { it.copy(selectedCuisines = it.cuisines.filter { it.isSelected }) }
//        updateState { it.copy(isAddEnable = it.isValid()) }
    }

    override fun onCuisineSelected(id: String) {
        updateState { state ->
            val updatedCuisines = state.cuisines.map { cuisine ->
                if (cuisine.id == id) {
                    cuisine.copy(isSelected = !cuisine.isSelected)
                } else {
                    cuisine
                }
            }
            state.copy(cuisines = updatedCuisines)
        }
    }

    override fun onImagePicked(image: ByteArray) {
        updateState { it.copy(meal = it.meal.copy(image = image)) }
    }

    override fun onClickBack() {
        sendNewEffect(MealScreenUIEffect.Back)
    }

    override fun onNameChange(name: String) {
        updateState { it.copy(meal = it.meal.copy(name = name), isAddEnable = it.meal.isValid()) }
    }

    override fun onDescriptionChange(description: String) {
        updateState {
            it.copy(
                meal = it.meal.copy(description = description),
                isAddEnable = it.meal.isValid()
            )
        }

    }

    override fun onPriceChange(price: String) {
        updateState { it.copy(meal = it.meal.copy(price = price), isAddEnable = it.meal.isValid()) }
    }
}
