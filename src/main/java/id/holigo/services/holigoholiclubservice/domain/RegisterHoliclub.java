package id.holigo.services.holigoholiclubservice.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegisterHoliclub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    private String indexTitle;

    private String indexSubtitle;

    private String backgroundUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "registerHoliclub")
    private List<RegisterBenefit> benefit;

    @OneToOne
    private RegisterOfficialAccount officialAccount;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

}
