//package com.steven.controller;
//
//import com.steven.PlayGameController;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//
//
//class MatchControllerTest {
//
//    @InjectMocks
//    MatchController controller;
//
//    StandaloneMockMvcBuilder mockMvc;
//
//    @BeforeEach
//    void setup(WebApplicationContext wac) {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(MatchController.class);
//    }
//
//    public MockMvc createMockMvc(){
//        return MockMvcBuilders.standaloneSetup(controller).build();
//    }
//
//    @Test
//    void assert_controller_setup(){
//        MockMvc mvc = createMockMvc();
//        ResultActions result = mvc.perform(get("/")).andExpect();
//    }
//
//}