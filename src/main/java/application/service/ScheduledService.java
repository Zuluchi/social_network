package application.service;

import application.dao.DaoNotification;
import application.dao.DaoPerson;
import application.models.NotificationType;
import application.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduledService {
    private final DaoPerson daoPerson;
    private final DaoNotification daoNotification;

    @Scheduled(cron = "0 0 0 * * *")
    private void getBirthDateId() {
        LocalDate now = LocalDate.now();
        Person currentPerson = daoPerson.getAuthPerson();
        List<Integer> idList = daoPerson.getFriends(currentPerson.getId()).stream().filter(person -> Instant
                .ofEpochMilli(person.getBirthDate()).atZone(ZoneId.systemDefault())
                .toLocalDate().getDayOfMonth() == now.getDayOfMonth() && Instant
                .ofEpochMilli(person.getBirthDate()).atZone(ZoneId.systemDefault())
                .toLocalDate().getMonth() == now.getMonth()).map(Person::getId).collect(Collectors.toList());

        daoNotification.addFriendBirthdateNotification(System.currentTimeMillis(), currentPerson.getId()
                , idList, currentPerson.getEmail(), NotificationType.FRIEND_BIRTHDAY);
    }
}