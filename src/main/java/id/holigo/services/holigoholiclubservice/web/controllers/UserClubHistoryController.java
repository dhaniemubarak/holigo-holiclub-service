package id.holigo.services.holigoholiclubservice.web.controllers;


import id.holigo.services.holigoholiclubservice.domain.UserClubHistory;
import id.holigo.services.holigoholiclubservice.repositories.UserClubHistoryRepository;
import id.holigo.services.holigoholiclubservice.web.mappers.UserClubHistoryMapper;
import id.holigo.services.holigoholiclubservice.web.model.UserClubHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserClubHistoryController {


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

    @GetMapping("/api/v1/userClubHistory")
    public ResponseEntity<UserClubHistoryDto> getUserClubHistory(@RequestParam("invoiceNumber") String invoiceNumber) {
        Optional<UserClubHistory> fetchUserClubHistory = userClubHistoryRepository.findByInvoiceNumber(invoiceNumber);
        return fetchUserClubHistory.map(userClubHistory -> new ResponseEntity<>(userClubHistoryMapper
                        .userClubHistoryToUserClubHistoryDto(userClubHistory), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
