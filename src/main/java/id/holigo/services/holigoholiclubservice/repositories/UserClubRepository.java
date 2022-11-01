package id.holigo.services.holigoholiclubservice.repositories;

import id.holigo.services.common.model.UserGroupEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import id.holigo.services.holigoholiclubservice.domain.UserClub;

import java.util.List;

public interface UserClubRepository extends JpaRepository<UserClub, Long> {
    List<UserClub> findAllByUserGroup(UserGroupEnum userGroupEnum);
}
