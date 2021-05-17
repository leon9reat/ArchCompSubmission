package com.medialink.archcompsubmission.data.repository

import android.content.Context
import android.os.Build.VERSION_CODES.Q
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** MoviesRepositoryTest
 * muat list movie
 * 1. memastikan list movie tidak null
 * 2. memastikan jumlah data dummy adalah 11
 *
 * muat detail movie
 * 1. memastikan data tidak null
 * 2. memastikan data id, title movie sesuai
 *
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Q])
class MoviesRepositoryTest {
    private lateinit var repo: MoviesRepository
    private val context: Context = ApplicationProvider.getApplicationContext()


    @Before
    fun setUp() {
        repo = MoviesRepository(context)
    }

    @Test
    fun test_getPackageName() {
        assertEquals(context.packageName,"com.medialink.archcompsubmission")
    }

    @Test
    fun getListData() {
        val listData = repo.getListData()
        assertNotNull(listData)
        assertEquals(20, listData.size)
    }

    @Test
    fun getCurrentData() {
        val listData = repo.getListData()
        var data = listData[0].id?.let { repo.getCurrentData(it) }
        assertNotNull(data)
        assertEquals(data?.id, listData[0].id)
        assertEquals(data?.title, listData[0].title)

        data = listData[3].id?.let { repo.getCurrentData(it) }
        assertNotNull(data)
        assertEquals(data?.id, listData[3].id)
        assertEquals(data?.title, listData[3].title)
    }
}