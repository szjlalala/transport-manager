package com.tms.controller;

import com.tms.controller.vo.request.PayloadDto;
import com.tms.controller.vo.request.UserDto;
import com.tms.security.TokenAuthenticationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by song on 2017/1110.
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @PostMapping("/user/login")
    public Response login(@RequestBody UserDto userDTO, HttpServletRequest request, HttpServletResponse response) {
//        后续需要整合到security中
        PayloadDto payloadDto = checkValidUser(userDTO);
        if (payloadDto != null) {
            Cookie cookie = new Cookie("token", TokenAuthenticationService.signToken(payloadDto));
            System.out.println(TokenAuthenticationService.signToken(payloadDto));
            cookie.setMaxAge(900000);
            cookie.setPath("/");
            response.addCookie(cookie);
            return new Response("200", "");
        }else
            return new Response("500", "登录失败!");
    }

    private PayloadDto checkValidUser(UserDto userDTO) {
        HashMap<String, PayloadDto> payloadMap = new HashMap<>();
        payloadMap.put("admin", new PayloadDto(0, "admin", "ADMIN", new int[]{}));
        payloadMap.put("customer", new PayloadDto(1, "customer", "CUSTOMER", new int[]{1, 2, 3, 33}));
        payloadMap.put("driver", new PayloadDto(2, "driver", "DRIVER", new int[]{1, 2, 3, 33}));
        if(!userDTO.getUsername().equals(userDTO.getPassword())){
            return null;
        }
        return payloadMap.get(userDTO.getUsername());
    }

    @GetMapping("/user/logout")
    public Response logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {
            System.out.println("没有cookie");
        } else {
            for(Cookie cookie : cookies){
                //如果找到同名cookie，就将value设置为null，将存活时间设置为0，再替换掉原cookie，这样就相当于删除了。
                if(cookie.getName().equals("token")){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return new Response("200", "已退出");
    }


    @GetMapping("/user")
    public Object check(@CookieValue(value = "token",
            defaultValue = "") String cookieValue) {
        System.out.println("check");
        if(cookieValue.equals(""))
            return new Response("200", "");
        else
            return new CookieDTO(true, new UserDto());
    }


    @Data
    @AllArgsConstructor
    class Response{
        String status;
        String msg;
    }

    @Data
    @AllArgsConstructor
    class CookieDTO{
        boolean success;
        UserDto user;
    }

}
