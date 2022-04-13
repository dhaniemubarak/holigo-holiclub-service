package id.holigo.services.holigoholiclubservice.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegisterOfficialAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Byte id;

    private String indexTitle;

    private String indexSubtitle;

    private String imageUrl;

    private String buttonLabel;

    @OneToMany(mappedBy = "officialAccount")
    private List<OfficialUser> officialUsers;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

}
