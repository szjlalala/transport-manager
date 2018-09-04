package com.tms.controller.vo.response;

import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by song on 2018/9/4.
 */
@Data
public class PageWrapper {
    int getTotalPages;
    long total;
    List content;

    public PageWrapper(Page page){
        this.getTotalPages = page.getTotalPages();
        this.total = page.getTotalElements();
        content = page.getContent();
    }
}
