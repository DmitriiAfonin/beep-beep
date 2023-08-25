package org.thechance.common.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.thechance.common.data.local.LocalGateway
import org.thechance.common.data.remote.gateway.RemoteGateway
import org.thechance.common.domain.getway.ILocalGateway
import org.thechance.common.data.local.gateway.LocalDataGateway
import org.thechance.common.data.remote.gateway.FakeRemoteGateway
import org.thechance.common.domain.getway.ILocalDataGateway
import org.thechance.common.domain.getway.IRemoteGateway

val GatewayModule=  module {
    singleOf(::RemoteGateway) { bind<IRemoteGateway>() }
    singleOf(::FakeRemoteGateway){ bind<IRemoteGateway>()}
    singleOf(::LocalDataGateway){ bind<ILocalDataGateway>()}
}