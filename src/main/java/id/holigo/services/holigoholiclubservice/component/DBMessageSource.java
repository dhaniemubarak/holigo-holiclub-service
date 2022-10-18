package id.holigo.services.holigoholiclubservice.component;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import id.holigo.services.holigoholiclubservice.domain.Language;
import id.holigo.services.holigoholiclubservice.repositories.LanguageRepository;
// import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor
@Component("messageSource")
public class DBMessageSource extends AbstractMessageSource {

    private LanguageRepository languageRepository;

    private static final String DEFAULT_LOCALE_CODE = "id";

    @Autowired
    public void setLanguageRepository(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    protected MessageFormat resolveCode(String messageKey, Locale locale) {
        Language message = languageRepository.findByMessageKeyAndLocale(messageKey, locale.getLanguage());
        if (message == null) {
            message = languageRepository.findByMessageKeyAndLocale(messageKey, DEFAULT_LOCALE_CODE);
        }
        return new MessageFormat(message.getMessageContent(), locale);
    }

}
