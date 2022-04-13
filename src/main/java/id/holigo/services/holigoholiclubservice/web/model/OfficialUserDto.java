package id.holigo.services.holigoholiclubservice.web.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficialUserDto implements Serializable {

    private String referral;

    private String profilePictureUrl;

    private String name;

    private String caption;

}
