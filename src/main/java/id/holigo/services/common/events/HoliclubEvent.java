package id.holigo.services.common.events;

import java.io.Serializable;

import id.holigo.services.common.model.IncrementUserClubDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HoliclubEvent implements Serializable {

    static final long serialVersionUID = 234L;

    private IncrementUserClubDto incrementUserClubDto;

    public HoliclubEvent(IncrementUserClubDto incrementUserClubDto) {
        this.incrementUserClubDto = incrementUserClubDto;
    }

}
