package id.holigo.services.holigoholiclubservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.holigo.services.holigoholiclubservice.domain.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Byte> {
    
}
