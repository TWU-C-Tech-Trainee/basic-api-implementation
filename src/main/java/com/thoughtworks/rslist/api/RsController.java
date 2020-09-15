package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.domain.RsEvent;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class RsController {

  private List<RsEvent> rsList = initRsEventList();

  private List<RsEvent> initRsEventList() {
    List<RsEvent> rsEventList = new ArrayList<>();
    rsEventList.add(new RsEvent("第一条事件","无标签"));
    rsEventList.add(new RsEvent("第二条事件","无标签"));
    rsEventList.add(new RsEvent("第三条事件","无标签"));
    return rsEventList;
  }

  @GetMapping("/rs/{index}")
  public RsEvent getOneRsEvent(@PathVariable int index){
    return rsList.get(index - 1);
  }

  @GetMapping("/rs/list")
  public List<RsEvent> getOneRsEvent(@RequestParam (required = false) Integer start, @RequestParam (required = false)  Integer end){
    if (start == null || end == null) {
      return rsList;
    }
    return rsList.subList(start - 1, end);
  }

  @PostMapping("/rs/event")
  public void addRsEvent(@RequestBody String rsEvent) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    RsEvent event = objectMapper.readValue(rsEvent, RsEvent.class);
    rsList.add(event);
  }

}
