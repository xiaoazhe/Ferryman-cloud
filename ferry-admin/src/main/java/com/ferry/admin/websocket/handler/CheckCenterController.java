package com.ferry.admin.websocket.handler;


import com.ferry.admin.websocket.response.CommonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckCenterController {

    /**
     * 静态页面的return默认是跳转到/static/目录下，当在pom.xml中引入了thymeleaf组件，
     * 动态跳转会覆盖默认的静态跳转，
     * 默认就会跳转到/templates/下，注意看两者return代码也有区别，动态没有html后缀。
     *
     * @return
     */
    @RequestMapping("client/{id}")
    public String demo2(@PathVariable String id, Model model) {
        model.addAttribute("uid", id);
        return "socket";
    }

    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public CommonResponse pushToWeb(@PathVariable String cid, String message) {

        return CommonResponse.ok(cid);
    }
}
