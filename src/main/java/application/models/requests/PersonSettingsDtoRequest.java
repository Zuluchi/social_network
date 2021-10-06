package application.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class PersonSettingsDtoRequest {

    @Pattern(regexp = "^(7?\\d{10})?$", message = "{phone.not.valid}")
    private String phone;
    private String about;
    private String city;
    private String country;

    @JsonProperty("photo_id")
    private String photoId;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("birth_date")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T00:00:00\\+\\d{2}:00$", message = "{date.not.valid}")
    private String birthDate;
}
