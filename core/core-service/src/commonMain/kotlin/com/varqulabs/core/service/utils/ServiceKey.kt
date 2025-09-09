package com.varqulabs.core.service.utils

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

sealed interface ServiceKey { val id: String }

fun qualifierOf(key: ServiceKey): Qualifier = named("service_${key.id}")

inline fun <reified T> Scope.get(key: ServiceKey): T = get(qualifier = qualifierOf(key))

inline fun <reified T> Scope.getOrNull(key: ServiceKey): T? = getOrNull(qualifier = qualifierOf(key))