package di

import data.gateway.FakeRemoteGateWay
import data.gateway.local.LocalConfigurationGateway
import data.gateway.remote.RemoteIdentityGateway
import domain.gateway.IFakeRemoteGateway
import domain.gateway.local.ILocalConfigurationGateway
import domain.gateway.remote.IRemoteIdentityGateway
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val GatewayModule = module {
    singleOf(::FakeRemoteGateWay) { bind<IFakeRemoteGateway>() }
    singleOf(::RemoteIdentityGateway) { bind<IRemoteIdentityGateway>() }
    singleOf(::LocalConfigurationGateway) { bind<ILocalConfigurationGateway>() }
}