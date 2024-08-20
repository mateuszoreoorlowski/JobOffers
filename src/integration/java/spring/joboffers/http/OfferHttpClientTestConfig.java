package spring.joboffers.http;

import spring.joboffers.domain.offer.OfferFetchable;
import spring.joboffers.infrastructure.offer.http.OfferHttpClientConfig;
import org.springframework.web.client.RestTemplate;

import static spring.joboffers.BaseIntegrationTest.WIRE_MOCK_HOST;

public class OfferHttpClientTestConfig extends OfferHttpClientConfig {

    public OfferFetchable remoteOfferTestClient(int port, int connectionTimeout, int readTimeout) {
        final RestTemplate restTemplate = restTemplate(connectionTimeout, readTimeout, restTemplateResponseErrorHandler());
        return remoteOfferClient(restTemplate, WIRE_MOCK_HOST, port);
    }
}
