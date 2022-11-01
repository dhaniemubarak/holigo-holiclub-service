package id.holigo.services.holigoholiclubservice.web.controllers;


import id.holigo.services.common.model.IncrementUserClubDto;
import id.holigo.services.holigoholiclubservice.domain.UserClubHistory;
import id.holigo.services.holigoholiclubservice.repositories.UserClubHistoryRepository;
import id.holigo.services.holigoholiclubservice.services.HoliclubService;
import id.holigo.services.holigoholiclubservice.web.mappers.UserClubHistoryMapper;
import id.holigo.services.holigoholiclubservice.web.model.UserClubHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserClubHistoryController {


    private HoliclubService holiclubService;

    private UserClubHistoryRepository userClubHistoryRepository;

    private UserClubHistoryMapper userClubHistoryMapper;

    @Autowired
    public void setUserClubHistoryMapper(UserClubHistoryMapper userClubHistoryMapper) {
        this.userClubHistoryMapper = userClubHistoryMapper;
    }

    @Autowired
    public void setUserClubHistoryRepository(UserClubHistoryRepository userClubHistoryRepository) {
        this.userClubHistoryRepository = userClubHistoryRepository;
    }

    @Autowired
    public void setHoliclubService(HoliclubService holiclubService) {
        this.holiclubService = holiclubService;
    }

    @GetMapping("/api/v1/userClubHistory")
    public ResponseEntity<UserClubHistoryDto> getUserClubHistory(@RequestParam("invoiceNumber") String invoiceNumber) {
        Optional<UserClubHistory> fetchUserClubHistory = userClubHistoryRepository.findByInvoiceNumber(invoiceNumber);
        return fetchUserClubHistory.map(userClubHistory -> new ResponseEntity<>(userClubHistoryMapper
                        .userClubHistoryToUserClubHistoryDto(userClubHistory), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/api/v1/testIncrementUserClub")
    public ResponseEntity<HttpStatus> testIncrementUserClub(@RequestBody IncrementUserClubDto incrementUserClubDto) {
        holiclubService.incrementUserClubExp(incrementUserClubDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
