package com.orilinc.portfolio.test.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orilinc.portfolio.configuration.ApplicationPropertiesMockBeans;
import com.orilinc.portfolio.configuration.ApplicationControllerBeans;
import com.orilinc.portfolio.configuration.ApplicationRepositoryMockBeans;
import com.orilinc.portfolio.configuration.ApplicationServiceMockBeans;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ApplicationControllerBeans.class,
        ApplicationRepositoryMockBeans.class,
        ApplicationServiceMockBeans.class,
        ApplicationPropertiesMockBeans.class
})
public abstract class ApplicationControllerRunner {

    protected final ObjectMapper mapper = new ObjectMapper();

    public MockMvc mvc;

    public void beforeByResource(Object object) {
        mvc = MockMvcBuilders
                .standaloneSetup(object)
                .build();
    }
}