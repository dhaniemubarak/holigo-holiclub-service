package id.holigo.services.holigoholiclubservice.web.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenefitDto implements Serializable {

    private Byte id;

    private String imageUrl;

    private String caption;
}
