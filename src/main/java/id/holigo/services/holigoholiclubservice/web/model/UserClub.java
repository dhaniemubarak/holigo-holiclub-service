package id.holigo.services.holigoholiclubservice.web.model;

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
public class UserClub implements Serializable {

    private UserGroupEnum userGroup;

    private String name;

    private Integer exp;
}
