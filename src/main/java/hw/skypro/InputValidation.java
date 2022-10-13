package hw.skypro;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class InputValidation {

    public  String checkFirstNameValid(String s) {

        if (StringUtils.isBlank(s)
                && !StringUtils.isAlpha(s)) {
            new IncorrectFirstNameException();
        }
        return StringUtils.capitalize(s.toLowerCase());
    }
    public  String checkLastNameValid(String s) {

        String[] lastName = s.split("-");
        for (int i = 0; i < lastName.length; i++) {
            if (StringUtils.isBlank(lastName[i])
                    && !StringUtils.isAlpha(s)) {
                new IncorrectLastNameException();
            }
            lastName[i] = StringUtils.capitalize(lastName[i].toLowerCase());
        }

        return String.join("-", lastName);
    }

}
