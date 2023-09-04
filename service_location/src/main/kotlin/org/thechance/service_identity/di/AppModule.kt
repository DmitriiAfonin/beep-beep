package org.thechance.service_identity.di

import org.koin.dsl.module


val BeepClient = module {
    includes(
        HelperModule
    )
}

