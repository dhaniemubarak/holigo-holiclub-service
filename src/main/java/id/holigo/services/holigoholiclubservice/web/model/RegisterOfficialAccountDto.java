package id.holigo.services.holigoholiclubservice.web.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterOfficialAccountDto implements Serializable {

    private String title;

    private String subtitle;

    private String imageUrl;

    private String buttonLabel;

    private List<OfficialUserDto> officialUsers;
}
