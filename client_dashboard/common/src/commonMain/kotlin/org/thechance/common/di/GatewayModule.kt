package org.thechance.common.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.thechance.common.data.remote.gateway.FakeRemoteGateway
import org.thechance.common.domain.getway.ILocalDataGateway
import org.thechance.common.domain.getway.IRemoteGateway

val GatewayModule=  module {
    //TODO remove comment when finish testing
//    singleOf(::RemoteGateway) { bind<IRemoteGateway>() }
    singleOf(org.thechance.common.data.local.gateway::LocalDataGateway){ bind<ILocalDataGateway>()}
    singleOf(::FakeRemoteGateway) { bind<IRemoteGateway>() }
}