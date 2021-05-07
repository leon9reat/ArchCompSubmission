package com.medialink.archcompsubmission.data.repository

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

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
class MoviesRepositoryTest {
    private lateinit var repo: MoviesRepository

    @Before
    fun setUp() {
        repo = MoviesRepository()
    }

    @Test
    fun getListData() {
        val listData = repo.getListData()
        assertNotNull(listData)
        assertEquals(11, listData.size)
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