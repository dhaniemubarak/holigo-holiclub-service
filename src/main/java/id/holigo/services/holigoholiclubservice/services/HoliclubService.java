package id.holigo.services.holigoholiclubservice.services;

import id.holigo.services.common.model.IncrementUserClubDto;
import id.holigo.services.holigoholiclubservice.domain.UserClub;

public interface HoliclubService {
    void incrementUserClubExp(IncrementUserClubDto incrementUserClubDto);

    void createUserClub(UserClub userClub);

}
