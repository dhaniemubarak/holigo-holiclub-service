package id.holigo.services.holigoholiclubservice.web.model;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class UserClubHistoryDto implements Serializable {

    private UUID id;

    private Long userId;

    private String invoiceNumber;

    private BigDecimal fareAmount;

    private Integer exp;

    private Integer currentExp;

    private Integer finalExp;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
