package hw.skypro;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class InputValidation {

    public  String checkValid(String s) {

        if (StringUtils.isBlank(s)
                && !StringUtils.isAlpha(s)) {
            new IncorrectException();
        }
        return StringUtils.capitalize(s.toLowerCase());
    }


}
