package com.yq.base.controller;

import com.yq.base.entity.Admin;
import com.yq.base.entity.Result;
import com.yq.base.entity.StatusCode;
import com.yq.base.service.AdminService;
import com.yq.base.service.UserService;
import com.yq.com.yq.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    //  xxxx/login
    @GetMapping("login")
    public Result findAdminByLoginNameAndPassword(@RequestBody Map<String, String> loginMap) {
        Admin admin = adminService.findAdminByLoginNameAndPassword(loginMap.get("loginName"), loginMap.get("password"));
        if (admin == null) {
            return new Result(false, StatusCode.LOGINERROR, "用户名或密码有误！");
        }

        //记录token     ,admin 一般代表管理员
        String token = jwtUtil.creatJWT(admin.getId(), admin.getLoginName(), "admin");
        Map map = new HashMap();
        map.put("token", token);
        map.put("admin", admin);

        return new Result(true, StatusCode.OK, "登录成功", map);
    }

    @PostMapping("register")
    public Result addAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
        return new Result(true, StatusCode.OK, "注册成功！");
    }


    //删除：先判断token(Authrorization)
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        Claims claims = (Claims)request.getAttribute("admin_claims");
        if(claims == null){
            return new Result(false, StatusCode.ACCESSERROR, "权限不足！");
        }
        adminService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功！");
    }
}
