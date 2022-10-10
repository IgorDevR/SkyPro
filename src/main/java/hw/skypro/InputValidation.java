package hw.skypro;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class InputValidation {

    public static String checkValid(String textName) {

        if (StringUtils.isEmpty(textName)
                && StringUtils.isBlank(textName)) {
            return null;
        }
        if (!StringUtils.isAlpha(textName)){
            return null;
        }
        return StringUtils.capitalize(textName);
    }


}
