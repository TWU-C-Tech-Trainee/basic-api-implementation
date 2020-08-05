package com.thoughtworks.rslist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.dominate.RsEvent;
import com.thoughtworks.rslist.dominate.UserDetiles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RsListApplicationTests {

    @Autowired
    MockMvc mockMvc;

        @Test
    void shouldGetErrorIfIndexInvalid() throws Exception {
        mockMvc.perform(get("/rs/5"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error",is("invalid index")));
    }

    @Test
    void shouldGetErrorIfRangeInvalid() throws Exception {
        mockMvc.perform(get("/rs/list?start=1&end=2"))
                .andExpect(jsonPath("$[0].eventName",is("理财产品")))
                .andExpect(jsonPath("$[1].eventName",is("最新款5G手机")))
                .andExpect(jsonPath("$[0].keyWord",is("金融")))
                .andExpect(jsonPath("$[1].keyWord",is("科技")))
                .andExpect(status().isOk());
    }


    @Test
    void shouldGetErrorWhenUserInvalidCauseNameNull() throws Exception {
        UserDetiles userNew = new UserDetiles(
                null,22,"male","xiaowang@qq.com","18912341234");
        RsEvent postUserEvent = new RsEvent("PS5发布会","游戏",userNew);
        ObjectMapper objectMapper = new ObjectMapper();
        String postUserEventJson = objectMapper.writeValueAsString(postUserEvent);

        mockMvc.perform(post("/rs/event").content(postUserEventJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetErrorWhenUserInvalidCauseNameOutOfRange() throws Exception {
        UserDetiles userNew = new UserDetiles(
                "xiaozhang",22,"male","xiaowang@qq.com","18912341234");
        RsEvent postUserEvent = new RsEvent("PS5发布会","游戏",userNew);
        ObjectMapper objectMapper = new ObjectMapper();
        String postUserEventJson = objectMapper.writeValueAsString(postUserEvent);

        mockMvc.perform(post("/rs/event").content(postUserEventJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    void shouldGetErrorWhenUserInvalidCauseAgeOutOfRange() throws Exception {
        UserDetiles userNew = new UserDetiles(
                "Wang",120,"male","xiaowang@qq.com","18912341234");
        RsEvent postUserEvent = new RsEvent("PS5发布会","游戏",userNew);
        ObjectMapper objectMapper = new ObjectMapper();
        String postUserEventJson = objectMapper.writeValueAsString(postUserEvent);

        mockMvc.perform(post("/rs/event").content(postUserEventJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetErrorWhenUserInvalidCauseGenderNull() throws Exception {
        UserDetiles userNew = new UserDetiles(
                "Wang",20,null,"xiaowang@qq.com","18912341234");
        RsEvent postUserEvent = new RsEvent("PS5发布会","游戏",userNew);
        ObjectMapper objectMapper = new ObjectMapper();
        String postUserEventJson = objectMapper.writeValueAsString(postUserEvent);

        mockMvc.perform(post("/rs/event").content(postUserEventJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetErrorWhenUserInvalidCauseEmailInvalid() throws Exception {
        UserDetiles userNew = new UserDetiles(
                "Wang",20,"male","xiaowang","18912341234");
        RsEvent postUserEvent = new RsEvent("PS5发布会","游戏",userNew);
        ObjectMapper objectMapper = new ObjectMapper();
        String postUserEventJson = objectMapper.writeValueAsString(postUserEvent);

        mockMvc.perform(post("/rs/event").content(postUserEventJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetErrorWhenUserInvalidCausePhoneNumberInvalid() throws Exception {
        UserDetiles userNew = new UserDetiles(
                "Wang",20,"male","xiaowang@qq.com","189123412");
        RsEvent postUserEvent = new RsEvent("PS5发布会","游戏",userNew);
        ObjectMapper objectMapper = new ObjectMapper();
        String postUserEventJson = objectMapper.writeValueAsString(postUserEvent);

        mockMvc.perform(post("/rs/event").content(postUserEventJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetErrorWhenUserInvalidCausePhoneNumberNull() throws Exception {
        UserDetiles userNew = new UserDetiles(
                "Wang",20,"male","xiaowang@qq.com",null);
        RsEvent postUserEvent = new RsEvent("PS5发布会","游戏",userNew);
        ObjectMapper objectMapper = new ObjectMapper();
        String postUserEventJson = objectMapper.writeValueAsString(postUserEvent);

        mockMvc.perform(post("/rs/event").content(postUserEventJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetErrorWhenUserInvalid() throws Exception {
        UserDetiles userNew = new UserDetiles(
                "Ming",18,"male","xiaoming@qq.com","11234567890");
        RsEvent postUserEvent = new RsEvent("PS5发布会","游戏",userNew);
        ObjectMapper objectMapper = new ObjectMapper();
        String postUserEventJson = objectMapper.writeValueAsString(postUserEvent);

        mockMvc.perform(post("/rs/event").content(postUserEventJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$[3].eventName",is("PS5发布会")))
                .andExpect(jsonPath("$[3].keyWord",is("游戏")))
                .andExpect(jsonPath("$[3]",not(hasKey("user"))))
                .andExpect(status().isOk());
    }
}

