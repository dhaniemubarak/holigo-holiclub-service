package id.holigo.services.holigoholiclubservice.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import id.holigo.services.common.model.UserGroupEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class UserGroup {

    @Id
    private Byte id;

    @ManyToOne
    private Holiclub holiclub;

    @Convert(converter = UserGroupEnumConverter.class)
    private UserGroupEnum userGroup;

    private String name;

    private String imageUrl;

    private String bannerUrl;

    private Integer minExp;

    private Integer maxExp;

    private String indexCaption;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Benefit> benefit;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
