package id.holigo.services.holigoholiclubservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WelcomeDto {

    private String backgroundUrl;

    private String imageUrl;

    private String title;

    private String subtitle;
}
