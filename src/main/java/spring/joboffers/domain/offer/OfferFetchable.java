package spring.joboffers.domain.offer;

import spring.joboffers.domain.offer.dto.JobOfferResponse;

import java.util.List;

public interface OfferFetchable {
    List<JobOfferResponse> fetchOffers();
}
