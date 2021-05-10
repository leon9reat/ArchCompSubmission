package com.medialink.archcompsubmission.data.repository

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/** TvShowsRepositoryTest
* muat list tv show
* 1. memastikan list tv show tidak null
* 2. memastikan jumlah data dummy adalah 10
*
* muat detail tv show
* 1. memastikan data tidak null
* 2. memastikan data id, title tv show sesuai
*
*/

class TvShowsRepositoryTest {
    private lateinit var repo: TvShowsRepository

    @Before
    fun setUp() {
        repo = TvShowsRepository()
    }

    @Test
    fun getListData() {
        val listData = repo.getListData()
        assertNotNull(listData)
        assertEquals(10, listData.size)
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