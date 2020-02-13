package com.example;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.Assert.assertEquals;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;


import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.FlashAttributeResultMatchers;

import java.lang.reflect.Field;

class SampleControllerTest<MockMvc> {
    private SampleController demoValue;
    private MockMvc mockMvc;

    @Before
    public void indexGet() {
        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
    }

//    @Test
//    public void index() {
//        Assertions.assertEquals("content002", demoValue.index(null));
//    }
//
    @Test
    public void index() throws Exception {
        MvcResult result = mockMvc.perform(get("/sample"))
            .andExpect(status().isOk());
            //.andExpect(view().name(""))
            //.andReturn();
            // ここでmodelに詰められたformの値を取得
        Form resultForm = (Form) result.getModelAndView().getModel().get("list");
        // then
        assertEquals(resultForm.getName(),"content002");
    }
}
