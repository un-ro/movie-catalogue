package com.unero.moviecatalogue.util

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito
import org.mockito.Mockito.mock

object PagedListUtil {

    fun <T : Any> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = mock(PagedList::class.java) as PagedList<T>
        Mockito.lenient().`when`(pagedList[anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        Mockito.lenient().`when`(pagedList.size).thenReturn(list.size)

        return pagedList
    }
}