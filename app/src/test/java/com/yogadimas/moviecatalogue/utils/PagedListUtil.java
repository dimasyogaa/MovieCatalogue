package com.yogadimas.moviecatalogue.utils;

import androidx.paging.PagedList;

import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PagedListUtil {

    public static <T> PagedList<T> mockPagedList(List<T> list) {
        PagedList<T> pagedList = mock(PagedList.class);
        Answer<T> answers = invocation -> {
            Integer index = (Integer) invocation.getArguments()[0];
            return list.get(index);
        };

        when(pagedList.get(anyInt())).thenAnswer(answers);
        when(pagedList.size()).thenReturn(list.size());

        return pagedList;
    }
}
