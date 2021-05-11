package com.example.RetailStore.controller;


import com.example.RetailStore.model.User;
import com.example.RetailStore.model.UserType;
import com.example.RetailStore.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = UserController.class)
public class UseController {

@MockBean
UserService userService;

    ObjectMapper om = new ObjectMapper();
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before()
    public void setup() {
        //Init MockMvc Object and build
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAddOrder() throws Exception {
        User user=new User("Moustafa", UserType.Affiliate,"");
        String jsonRequest = om.writeValueAsString(user);

        RequestBuilder request = MockMvcRequestBuilders.post("http://localhost:8081/api/user/addOrder")
                .characterEncoding("utf-8")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE))
                .content(jsonRequest)
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE));
        MvcResult result = mvc.perform(request).andReturn();

        Assert.assertNotNull(user);
        Assert.assertNotNull(result.getResponse().getContentAsString());
        Assert.assertEquals(200, result.getResponse().getStatus());

}


}
