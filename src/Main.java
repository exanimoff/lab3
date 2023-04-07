import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ФИО и дату рождения через пробел (Иванов Иван Иванович 15.01.1990): ");
        String input = scanner.nextLine();
        scanner.close();

        String[] parts = input.split(" ");
        String surname = parts[0];
        String birthDate = parts[3];

        try {
            LocalDate dateOfBirth = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            LocalDate currentDate = LocalDate.now();

            if (dateOfBirth.isAfter(currentDate)) {
                System.out.println("Внимание: дата рождения не может быть в будущем");
                throw new IllegalArgumentException();
            }

            Period age = Period.between(dateOfBirth, currentDate);

            if (age.getYears() > 150) {
                System.out.println("Внимание: максимально поддерживаемый возраст до 150 лет");
                throw new IllegalArgumentException();
            }

            String initials = parts[1].charAt(0) + "." + parts[2].charAt(0) + ".";
            String gender = (parts[2].endsWith("ич")) ? "М" : "Ж";
            String ageString = age.getYears() + " " + getAgeSuffix(age.getYears());

            System.out.println(surname + " " + initials + " " + gender + " " + ageString);
        } catch (Exception e) {
            System.out.println("Ошибка: некорректный ввод");
        }
    }

    private static String getAgeSuffix(int age) {
        if (age % 10 == 1 && age != 11) {
            return "год";
        } else if (age % 10 >= 2 && age % 10 <= 4 && !(age >= 12 && age <= 14)) {
            return "года";
        } else {
            return "лет";
        }
    }
}