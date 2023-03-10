package id.holigo.services.common.events;

import id.holigo.services.common.model.UserClubDto;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class CreateHoliclubEvent implements Serializable {

    static final long serialVersionUID = 567L;

    private UserClubDto userClubDto;

    public CreateHoliclubEvent(UserClubDto userClubDto) {
        this.userClubDto = userClubDto;
    }
}
