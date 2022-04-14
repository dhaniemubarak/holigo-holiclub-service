package id.holigo.services.holigoholiclubservice.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import id.holigo.services.common.model.UserGroupEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserClub {
    @Id
    private Long userId;

    @Convert(converter = UserGroupEnumConverter.class)
    private UserGroupEnum userGroup;

    private String name;

    private Integer exp;

    private BigDecimal fareAmount;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(nullable = true)
    private Timestamp openAt;

    private Boolean hasCheck;
}
