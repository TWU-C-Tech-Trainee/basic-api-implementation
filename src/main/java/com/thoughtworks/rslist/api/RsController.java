package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.RsEventResponse;
import com.thoughtworks.rslist.entity.RsEvent;
import com.thoughtworks.rslist.service.RsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping(path = "/rs")
@RestController
public class RsController {

    @Autowired
    RsService rsService;

    private List<String> rsList = Arrays.asList("第一条事件", "第二条事件", "第三条事件");


    @GetMapping("/list")
    @ResponseBody
    public RsEventResponse<List<RsEvent>> getAllList() {
        return rsService.getAllList();
    }
}
