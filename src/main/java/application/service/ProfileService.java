package application.service;

import application.models.City;
import application.models.Country;
import application.models.PersonDto;
import application.responses.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    public GeneralResponse<PersonDto> getPerson(){
        PersonDto personDto = new PersonDto();
        personDto.setId(2);
        personDto.setFirstName("Борис");
        personDto.setLastName("Булкин");
        personDto.setRegDate(System.currentTimeMillis() - 567);
        personDto.setBirthDate(System.currentTimeMillis() - 1997);
        personDto.setEmail("gsdfhgsh@skdjfhskdj.ru");
        personDto.setPhone("9163332211");
        personDto.setPhoto("");
        personDto.setAbout("Немного обо мне");

        City city = new City(1, "Москва");
        personDto.setCity(city.getTitle());
        Country country = new Country(1, "Россия");
        personDto.setCountry(country.getTitle());
        personDto.setMessagesPermission("All");
        personDto.setLastOnlineTime(System.currentTimeMillis() - 40);
        personDto.setBlocked(false);
        GeneralResponse<PersonDto> response = new GeneralResponse<>(personDto);
        return response;
    }
}
