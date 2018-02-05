package io.github.mattshen.emailbroker.services;

import io.github.mattshen.emailbroker.models.ProviderResponse;
import io.github.mattshen.emailbroker.models.SimpleEmailRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailDeliveryServiceTest {

    @Mock
    EmailDeliveryProvider first;

    @Mock
    EmailDeliveryProvider second;

    EmailDeliveryService service;

    @Before
    public void setUp() {
        service = new EmailDeliveryService(first, second);
    }

    @Test
    public void when_1st_success_return_1st() {

        SimpleEmailRequest req1 = new SimpleEmailRequest();
        ProviderResponse res1 = new ProviderResponse(true, 200, "id-res1", null);

        SimpleEmailRequest req2 = new SimpleEmailRequest();
        ProviderResponse res2 = new ProviderResponse(true, 200, "id-res2", null);

        when(first.send(req1)).thenReturn(res1);
        when(second.send(req2)).thenReturn(res2);

        ProviderResponse res = service.send(req1);
        Assert.assertEquals("id-res1", res.getId());

    }


    @Test
    public void when_1st_fail_return_2st() {

        SimpleEmailRequest req1 = new SimpleEmailRequest();
        ProviderResponse res1 = new ProviderResponse(false, 400, "id-res1", null);

        SimpleEmailRequest req2 = new SimpleEmailRequest();
        ProviderResponse res2 = new ProviderResponse(true, 200, "id-res2", null);

        when(first.send(req1)).thenReturn(res1);
        when(second.send(req2)).thenReturn(res2);

        ProviderResponse res = service.send(req1);
        Assert.assertEquals("id-res2", res.getId());

    }

}
