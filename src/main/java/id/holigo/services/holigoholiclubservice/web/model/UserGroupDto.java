package id.holigo.services.holigoholiclubservice.web.model;

import java.util.ArrayList;
import java.util.List;

import id.holigo.services.common.model.UserGroupEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGroupDto {

    private UserGroupEnum userGroup;

    private String name;

    private String imageUrl;

    private String bannerUrl;

    private Integer minExp;

    private Integer maxExp;

    private String caption;

    @Builder.Default
    private List<BenefitDto> benefit = new ArrayList<>();
}
