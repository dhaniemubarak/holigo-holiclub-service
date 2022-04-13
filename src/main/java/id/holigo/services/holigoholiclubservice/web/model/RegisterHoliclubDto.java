package id.holigo.services.holigoholiclubservice.web.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterHoliclubDto implements Serializable {

    private String backgroundUrl;

    private String title;

    private String subtitle;

    private List<RegisterBenefitDto> benefit;

    private RegisterOfficialAccountDto officialAccount;

}
