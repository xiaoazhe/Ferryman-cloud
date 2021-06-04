package com.ferry.consumer.comtroller;

import com.ferry.consumer.http.PageRequest;
import com.ferry.consumer.http.Result;
import com.ferry.consumer.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/3
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping(value="/findAll")
    public Result findPage(@RequestParam String token) {
        return typeService.findAll();
    }

    @PostMapping(value="/findBlogByTypeId")
    public Result findPage(@RequestBody PageRequest pageRequest, @RequestParam String token, @RequestParam Integer id) {
        pageRequest.setEnabled(id);
        return typeService.findBlogByTypeId(pageRequest);
    }
}
