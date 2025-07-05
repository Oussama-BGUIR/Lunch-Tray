package com.example.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.*

enum class LunchTrayScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Entree(title = R.string.choose_entree),
    SideDish(title = R.string.choose_side_dish),
    Accompaniment(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout)
}

// TopBar composable for all screens
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun LunchTrayApp() {
    // Navigation controller
    val navCtrl = rememberNavController()
    val backStack by navCtrl.currentBackStackEntryAsState()

    // Identify current screen
    val currentScreen = LunchTrayScreen.valueOf(
        backStack?.destination?.route ?: LunchTrayScreen.Start.name
    )

    // ViewModel for order state
    val viewModel: OrderViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navCtrl.previousBackStackEntry != null,
                navigateUp = { navCtrl.navigateUp() }
            )
        }
    ) { contentPadding ->

        NavHost(
            navController = navCtrl,
            startDestination = LunchTrayScreen.Start.name,
            modifier = Modifier.padding(contentPadding)
        ) {
            composable(route = LunchTrayScreen.Start.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = {
                        navCtrl.navigate(LunchTrayScreen.Entree.name)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable(route = LunchTrayScreen.Entree.name) {
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navCtrl.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        navCtrl.navigate(LunchTrayScreen.SideDish.name)
                    },
                    onSelectionChanged = { item ->
                        viewModel.updateEntree(item)
                    },
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }

            composable(route = LunchTrayScreen.SideDish.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navCtrl.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        navCtrl.navigate(LunchTrayScreen.Accompaniment.name)
                    },
                    onSelectionChanged = { item ->
                        viewModel.updateSideDish(item)
                    },
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }

            composable(route = LunchTrayScreen.Accompaniment.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navCtrl.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        navCtrl.navigate(LunchTrayScreen.Checkout.name)
                    },
                    onSelectionChanged = { item ->
                        viewModel.updateAccompaniment(item)
                    },
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }

            composable(route = LunchTrayScreen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navCtrl.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        viewModel.resetOrder()
                        navCtrl.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(
                            start = dimensionResource(R.dimen.padding_medium),
                            end = dimensionResource(R.dimen.padding_medium)
                        )
                )
            }
        }
    }
}
