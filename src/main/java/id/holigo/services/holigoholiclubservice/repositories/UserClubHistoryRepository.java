package id.holigo.services.holigoholiclubservice.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import id.holigo.services.holigoholiclubservice.domain.UserClubHistory;

public interface UserClubHistoryRepository extends JpaRepository<UserClubHistory, UUID> {

    Optional<UserClubHistory> findByInvoiceNumber(String invoiceNumber);

}
