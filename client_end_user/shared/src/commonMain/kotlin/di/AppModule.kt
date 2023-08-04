package di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import ui.screens.details.DetailsViewModel
import ui.screens.search.SearchViewModel

fun appModule() = module {
    factory { SearchViewModel() }
    factory {(text :String ) ->  DetailsViewModel(text) }
}


