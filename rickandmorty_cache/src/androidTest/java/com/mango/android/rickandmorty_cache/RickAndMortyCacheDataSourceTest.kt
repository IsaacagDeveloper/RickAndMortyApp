package com.mango.android.rickandmorty_cache

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mango.android.rickandmorty_cache.core.FAKE_CHARACTERS_LIST_ID
import com.mango.android.rickandmorty_cache.core.fakeCurrentPageDbModel
import com.mango.android.rickandmorty_cache.database.RickAndMortyDao
import com.mango.android.rickandmorty_cache.database.RickAndMortyDatabase
import com.mango.android.rickandmorty_cache.di.RickAndMortyDatabaseModule
import com.mango.android.rickandmorty_cache.source.RickAndMortyCacheDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Singleton

@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
@UninstallModules(RickAndMortyDatabaseModule::class)
class RickAndMortyCacheDataSourceTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var rickAndMortyCacheDataSource: RickAndMortyCacheDataSource

    @Inject
    lateinit var db: RickAndMortyDatabase

    @Before
    fun setUp() {
        hiltRule.inject()
        rickAndMortyCacheDataSource = RickAndMortyCacheDataSource(db)
    }

    @Test
    fun shouldInsertNewCurrentPageToDB() {
        runBlocking {
            // Given
            rickAndMortyCacheDataSource.insertListCurrentPageToDB(fakeCurrentPageDbModel)

            // When
            val result = rickAndMortyCacheDataSource.getCurrentPageForListID(FAKE_CHARACTERS_LIST_ID)

            // Then
            TestCase.assertEquals(result.listID, fakeCurrentPageDbModel.listID)
            TestCase.assertEquals(result.currentPage, 1)
        }
    }

    @Test
    fun shouldGetCurrentPageFromDBInsertingTwiceSameCurrentPage() {
        runBlocking {
            // Given
            rickAndMortyCacheDataSource.insertListCurrentPageToDB(fakeCurrentPageDbModel)
            rickAndMortyCacheDataSource.insertListCurrentPageToDB(fakeCurrentPageDbModel)

            // When
            val result = rickAndMortyCacheDataSource.getCurrentPageForListID(FAKE_CHARACTERS_LIST_ID)

            // Then
            TestCase.assertEquals(result, fakeCurrentPageDbModel)
            TestCase.assertEquals(result.currentPage, 1)
        }
    }

    @Test(expected = SQLiteConstraintException::class)
    fun shouldGetExceptionWhenInsertCharactersToDB() {
        runBlocking {
            // Given
            rickAndMortyCacheDataSource.insertListCurrentPageToDB(fakeCurrentPageDbModel)

            // When
            throw SQLiteConstraintException()

            // Then
        }
    }

    @Test
    fun shouldGetCurrentPageFromDB() {
        runBlocking {
            // Given
            rickAndMortyCacheDataSource.insertListCurrentPageToDB(fakeCurrentPageDbModel)

            // When
            val result = rickAndMortyCacheDataSource.getCurrentPageForListID(fakeCurrentPageDbModel.listID)

            // Then
            TestCase.assertEquals(result.currentPage, fakeCurrentPageDbModel.currentPage)
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object TestModule {

        @Provides
        @Singleton
        fun provideRickAndMortyDao(db: RickAndMortyDatabase) : RickAndMortyDao = db.rickAndMortyDao()

        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext app: Context): RickAndMortyDatabase {
            return Room
                .inMemoryDatabaseBuilder(app, RickAndMortyDatabase::class.java)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}