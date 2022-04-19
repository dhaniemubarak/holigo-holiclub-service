package id.holigo.services.common.model;

import java.io.Serializable;

import id.holigo.services.common.model.UserGroupEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserClubDto implements Serializable {

    private Long userId;

    private UserGroupEnum userGroup;

    private String name;

    private Integer exp;
}
