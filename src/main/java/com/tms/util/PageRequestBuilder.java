package com.tms.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by song on 2018/8/24.
 */
public class PageRequestBuilder {
    public static PageRequest buildPageRequest(int pageNumber, int pageSize) {
        return new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "id"));
    }

    public static PageRequest buildPageRequest(Pageable pageable) {
        int page = pageable.getPageNumber();
        if(page == 0 )
            page = 1;
        int pageSize = pageable.getPageSize();
        if(pageSize == 0)
            pageSize = 10;
        return new PageRequest(page - 1, pageSize, new Sort(Sort.Direction.DESC, "id"));
    }
}
