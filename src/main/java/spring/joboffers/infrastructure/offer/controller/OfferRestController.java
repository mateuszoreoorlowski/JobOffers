package spring.joboffers.infrastructure.offer.controller;

import spring.joboffers.domain.offer.OfferFacade;
import spring.joboffers.domain.offer.dto.OfferRequestDto;
import spring.joboffers.domain.offer.dto.OfferResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
@AllArgsConstructor
public class OfferRestController {

    private final OfferFacade offerFacade;

    @GetMapping
    public ResponseEntity<List<OfferResponseDto>> findAllOffers() {
        List<OfferResponseDto> allOffers = offerFacade.findAllOffers();
        return ResponseEntity.ok(allOffers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferResponseDto> findOfferById(@PathVariable String id) {
        OfferResponseDto offerById = offerFacade.findOfferById(id);
        return ResponseEntity.ok(offerById);
    }

    @PostMapping
    public ResponseEntity<OfferResponseDto> addOffer(@RequestBody @Valid OfferRequestDto offerDto) {
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(offerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(offerResponseDto);
    }

}
