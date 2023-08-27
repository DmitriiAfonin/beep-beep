package presentation.info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.beepbeep.designSystem.ui.composable.BpButton
import com.beepbeep.designSystem.ui.composable.BpTextField
import com.beepbeep.designSystem.ui.theme.Theme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.base.BaseScreen
import presentation.composable.BpAppBar
import presentation.composable.BpCard
import presentation.composable.BpRating
import presentation.composable.BpTitleWithContentSection
import presentation.login.LoginScreen
import resources.Resources

class RestaurantInfoScreen :
    BaseScreen<RestaurantInfoScreenModel, RestaurantInfoUiState, RestaurantInfoUiEffect, RestaurantInfoInteractionListener>() {

    @Composable
    override fun Content() {
        initScreen(getScreenModel())
    }


    @Composable
    override fun onRender(
        state: RestaurantInfoUiState,
        listener: RestaurantInfoInteractionListener
    ) {
        Column {
            BpAppBar(
                onNavigateUp = { listener.onClickBackArrow() },
                title = Resources.strings.restaurantInfoInSingleLine,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Theme.colors.surface)
                    .border(width = 1.dp, color = Theme.colors.divider, shape = RectangleShape)
            )
            LazyColumn(Modifier.fillMaxSize().background(Theme.colors.background)) {
                item {
                    RestaurantInfoCard(
                        state.restaurant.ownerUsername,
                        state.restaurant.address,
                        state.restaurant.rating,
                        state.restaurant.priceLevel
                    )
                }
                item {
                    RestaurantUpdateInfoCard(
                        state.restaurant.restaurantName,
                        state.restaurant.openingTime,
                        state.restaurant.closingTime,
                        state.restaurant.description,
                        state.restaurant.phoneNumber,
                        listener
                    )
                }
                item {
                    LogoutCard(listener)
                }
            }
        }
    }

    override fun onEffect(effect: RestaurantInfoUiEffect, navigator: Navigator) {
        when (effect) {
            is RestaurantInfoUiEffect.NavigateToLogin -> navigator.replaceAll(LoginScreen())
            is RestaurantInfoUiEffect.NavigateUp -> navigator.pop()
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun RestaurantInfoCard(
        ownerUsername: String,
        address: String,
        rating: Double,
        priceLevel: String
    ) {
        BpCard(
            modifier = Modifier.fillMaxWidth().padding(
                start = Theme.dimens.space16,
                end = Theme.dimens.space16,
                top = Theme.dimens.space16
            )
        ) {

            BpTitleWithContentSection(title = Resources.strings.ownerUsername) {
                Text(
                    ownerUsername,
                    modifier = Modifier.padding(
                        start = Theme.dimens.space16,
                        end = Theme.dimens.space16,
                        bottom = Theme.dimens.space16
                    ),
                    style = Theme.typography.body,
                    color = Theme.colors.contentPrimary
                )
            }
            BpTitleWithContentSection(title = Resources.strings.address) {
                Text(
                    address,
                    modifier = Modifier.padding(
                        start = Theme.dimens.space16,
                        end = Theme.dimens.space16,
                        bottom = Theme.dimens.space16
                    ),
                    style = Theme.typography.body,
                    color = Theme.colors.contentPrimary
                )
            }
            BpTitleWithContentSection(title = Resources.strings.rating) {
                BpRating(
                    rating,
                    selectedIcon = painterResource(Resources.images.filledStar),
                    halfSelectedIcon = painterResource(Resources.images.halfFilledStar),
                    modifier = Modifier.padding(
                        start = Theme.dimens.space16,
                        end = Theme.dimens.space16,
                        bottom = Theme.dimens.space16
                    ),
                    iconsSize = Theme.dimens.space16
                )
            }
            BpTitleWithContentSection(title = Resources.strings.priceLevel) {
                Text(
                    priceLevel,
                    modifier = Modifier.padding(
                        start = Theme.dimens.space16,
                        end = Theme.dimens.space16,
                        bottom = Theme.dimens.space16
                    ),
                    style = Theme.typography.titleMedium,
                    color = Theme.colors.success
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun RestaurantUpdateInfoCard(
        restaurantName: String,
        openingTime: String,
        closingTime: String,
        description: String,
        phoneNumber: String,
        listener: RestaurantInfoInteractionListener
    ) {
        BpCard(
            modifier = Modifier.fillMaxWidth().padding(Theme.dimens.space16)
        ) {
            BpTextField(
                label = Resources.strings.restaurantName,
                text = restaurantName,
                onValueChange = { listener.onRestaurantNameChange(it) },
                modifier = Modifier.padding(
                    horizontal = Theme.dimens.space16,
                    vertical = Theme.dimens.space16
                ),
                errorMessage = Resources.strings.restaurantNameErrorMessage,
                isError = restaurantName.length !in 4..25
            )
            BpTextField(
                label = Resources.strings.phoneNumber,
                text = phoneNumber,
                onValueChange = { listener.onPhoneNumberChange(it) },
                modifier = Modifier.padding(
                    start = Theme.dimens.space16,
                    end = Theme.dimens.space16,
                    bottom = Theme.dimens.space16
                )
            )
            Row(modifier = Modifier.padding(bottom = Theme.dimens.space16)) {
                BpTextField(
                    label = Resources.strings.workingHours,
                    text = openingTime,
                    onValueChange = { listener.onOpeningTimeChange(it) },
                    modifier = Modifier.padding(
                        start = Theme.dimens.space16,
                        end = Theme.dimens.space4
                    ).weight(1f)
                )
                BpTextField(
                    label = "",
                    text = closingTime,
                    onValueChange = { listener.onClosingTimeChange(it) },
                    modifier = Modifier.padding(
                        start = Theme.dimens.space4,
                        end = Theme.dimens.space16
                    ).weight(1f)
                )
            }
            BpTextField(
                label = Resources.strings.description,
                text = description,
                onValueChange = { listener.onDescriptionChanged(it) },
                modifier = Modifier.padding(
                    start = Theme.dimens.space16,
                    end = Theme.dimens.space16,
                    bottom = Theme.dimens.space24
                ),
                errorMessage = Resources.strings.descriptionErrorMessage,
                isError = description.length > 255,
                singleLine = false,
                textFieldModifier = Modifier.fillMaxWidth().height(104.dp)
            )
            BpButton(
                title = Resources.strings.save,
                onClick = { listener.onClickSave() },
                enabled = restaurantName.isNotEmpty() && phoneNumber.isNotEmpty()
                        && openingTime.isNotEmpty() && closingTime.isNotEmpty(),
                modifier = Modifier.fillMaxWidth().padding(
                    start = Theme.dimens.space16,
                    end = Theme.dimens.space16,
                    bottom = Theme.dimens.space16
                )
            )
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun LogoutCard(listener: RestaurantInfoInteractionListener) {
        BpCard(modifier = Modifier.fillMaxWidth().padding(
            start = Theme.dimens.space16,
            end = Theme.dimens.space16,
            bottom = Theme.dimens.space16
        ).clickable { listener.onClickLogout() }
        ) {
            Row {
                Icon(
                    painterResource(Resources.images.logout), modifier = Modifier.padding(
                        start = Theme.dimens.space16,
                        top = Theme.dimens.space16,
                        bottom = Theme.dimens.space16,
                        end = Theme.dimens.space8
                    ), contentDescription = null,
                    tint = Theme.colors.primary
                )
                Text(
                    Resources.strings.logout,
                    style = Theme.typography.title.copy(color = Theme.colors.primary),
                    modifier = Modifier.padding(vertical = Theme.dimens.space16)
                )
            }
        }
    }
}