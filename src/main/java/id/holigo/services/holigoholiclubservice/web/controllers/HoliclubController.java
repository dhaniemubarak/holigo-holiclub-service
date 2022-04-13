package id.holigo.services.holigoholiclubservice.web.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import id.holigo.services.holigoholiclubservice.domain.UserClub;
import id.holigo.services.holigoholiclubservice.repositories.HoliclubRepository;
import id.holigo.services.holigoholiclubservice.repositories.RegisterHoliclubRepository;
import id.holigo.services.holigoholiclubservice.repositories.UserClubRepository;
import id.holigo.services.holigoholiclubservice.web.mappers.HoliclubMapper;
import id.holigo.services.holigoholiclubservice.web.mappers.RegisterHoliclubMapper;
import id.holigo.services.holigoholiclubservice.web.model.HoliclubDto;
import id.holigo.services.holigoholiclubservice.web.model.RegisterHoliclubDto;

@RestController
public class HoliclubController {

    @Autowired
    private HoliclubRepository holiclubRepository;

    @Autowired
    private UserClubRepository userClubRepository;

    @Autowired
    private RegisterHoliclubRepository registerHoliclubRepository;

    @Autowired
    private HoliclubMapper holiclubMapper;

    @Autowired
    private RegisterHoliclubMapper registerHoliclubMapper;

    private Byte id = 1;

    @GetMapping("/api/v1/holiclub")
    public ResponseEntity<HoliclubDto> getHoliclub(@RequestHeader("user-id") Long userId) {
        HoliclubDto holiclubDto;
        UserClub userClub = null;
        Optional<UserClub> fetchUserCLub = userClubRepository.findById(userId);
        if (fetchUserCLub.isPresent()) {
            userClub = fetchUserCLub.get();
            holiclubDto = holiclubMapper.holiclubToHoliclubDto(holiclubRepository.getById(id), userClub);
        } else {
            holiclubDto = holiclubMapper.holiclubToHoliclubDto(holiclubRepository.getById(id), userClub);
        }
        return new ResponseEntity<>(holiclubDto, HttpStatus.OK);
    }

    // This is for holiclub page in register form
    @GetMapping("/api/v1/holiclubRegister")
    public ResponseEntity<RegisterHoliclubDto> getRegisterHoliclub(@RequestHeader("user-id") Long userId) {

        return new ResponseEntity<>(
                registerHoliclubMapper.registerHoliclubToRegisterHoliclubDto(registerHoliclubRepository.getById(id)),
                HttpStatus.OK);
    }

    @PutMapping("/api/v1/holiclub")
    public ResponseEntity<HoliclubDto> updateHoliclub(@RequestHeader("user-id") Long userId,
            @RequestBody HoliclubDto holiclubDto) {
        UserClub userClub = null;
        Optional<UserClub> fetchUserCLub = userClubRepository.findById(userId);
        if (fetchUserCLub.isPresent()) {
            userClub = fetchUserCLub.get();
            userClub.setOpenAt(Timestamp.valueOf(LocalDateTime.now()));
            userClubRepository.save(userClub);
        }
        return new ResponseEntity<HoliclubDto>(HttpStatus.NO_CONTENT);

    }
}
