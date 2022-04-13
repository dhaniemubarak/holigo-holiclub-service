package id.holigo.services.holigoholiclubservice.web.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBenefitDto implements Serializable {

    private String imageUrl;

    private String caption;
}
