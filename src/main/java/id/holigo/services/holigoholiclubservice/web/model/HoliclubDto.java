package id.holigo.services.holigoholiclubservice.web.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoliclubDto implements Serializable {
    
    private String backgroundUrl;

    private String imageUrl;

    private String caption;

    private WelcomeDto welcome;

    private List<UserGroupDto> userGroups;

    private UserClubDto userClub;

}
