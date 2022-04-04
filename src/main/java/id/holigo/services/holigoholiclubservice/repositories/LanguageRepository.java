package id.holigo.services.holigoholiclubservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.holigo.services.holigoholiclubservice.domain.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

    Language findByMessageKeyAndLocale(String messageKey, String locale);
}
