package ru.netology.carddelivery;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@Data
@RequiredArgsConstructor

public class dataUser {
    static Faker faker = new Faker(new Locale("ru"));

    static String city() {
        Random random = new Random();
        int rand = random.nextInt(15);
        String city[] = {"Уфа", "Петрозаводск", "Ижевск", "Чита", "Краснодар", "Ставрополь", "Астрахань",
                "Владимир", "Магадан", "Екатеринбург", "Тверь", "Тула", "Москва", "Санкт-Петербург", "Ханты-Мансийск"};
        return city[rand];
    }

    public static String date() {
        return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String newDate() {
        return LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String badDate() {
        return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String name() {
        return faker.name().fullName();
    }

    public static String phone() {
        return faker.phoneNumber().phoneNumber();
    }
}
