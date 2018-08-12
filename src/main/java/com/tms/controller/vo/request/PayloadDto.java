package com.tms.controller.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/8/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayloadDto {
    long id;
    String username;
    String role;
    int[] visit;
}
