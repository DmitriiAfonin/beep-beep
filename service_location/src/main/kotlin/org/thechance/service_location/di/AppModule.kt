package org.thechance.service_location.di

import org.koin.dsl.module


val BeepClient = module {
    includes(
        UseCasesModule,
        GatewaysModule,
        HelperModule
    )
}

