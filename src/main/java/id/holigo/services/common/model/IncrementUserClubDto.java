package id.holigo.services.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncrementUserClubDto implements Serializable {

    static final long serialVersionUID = 8L;

    private String invoiceNumber;

    private Long userId;

    private BigDecimal fareAmount;
}
