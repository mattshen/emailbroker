package io.github.mattshen.emailbroker;

import io.github.mattshen.emailbroker.models.ProviderResponse;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;
import io.github.mattshen.emailbroker.services.EmailDeliveryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Endpoints.class)
public class EndpointsTest {

    @Autowired
    Endpoints endpoints;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmailDeliveryService service;

    @Test
    public void valid_input_should_yield_200() throws Exception {

        ProviderResponse res = new ProviderResponse(true, 200, "abc123", null);
        when(service.send((SimpleEmailRequest) notNull())).thenReturn(res);

        String content = TestUtils.loadTextFile("valid_email_request.json");
        mockMvc.perform(post("/v1/emails")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk());
    }

    @Test
    public void invalid_email_should_yield_400() throws Exception {
        ProviderResponse res = new ProviderResponse(true, 200, "abc123", null);
        when(service.send((SimpleEmailRequest) notNull())).thenReturn(res);

        String content = TestUtils.loadTextFile("email_request_with_invalid_email.json");
        mockMvc.perform(post("/v1/emails")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void missing_info_should_yield_400() throws Exception {
        ProviderResponse res = new ProviderResponse(true, 200, "abc123", null);
        when(service.send((SimpleEmailRequest) notNull())).thenReturn(res);

        String content = TestUtils.loadTextFile("email_request_missing_infomation.json");
        mockMvc.perform(post("/v1/emails")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isBadRequest());
    }

}
