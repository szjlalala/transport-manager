package com.tms.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/9/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {
    int page;
    int pageSize;
}
