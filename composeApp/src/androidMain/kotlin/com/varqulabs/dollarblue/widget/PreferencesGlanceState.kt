package com.varqulabs.dollarblue.widget

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.glance.state.GlanceStateDefinition
import com.varqulabs.dollarblue.core.preferences.DOLLAR_BLUE_FILE_NAME
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File

object PreferencesGlanceState : GlanceStateDefinition<Preferences>, KoinComponent {

    private val dataStore: DataStore<Preferences> by inject()

    override suspend fun getDataStore(
        context: Context,
        fileKey: String
    ): DataStore<Preferences> = dataStore

    override fun getLocation(
        context: Context,
        fileKey: String
    ): File = context.preferencesDataStoreFile(DOLLAR_BLUE_FILE_NAME)

}
