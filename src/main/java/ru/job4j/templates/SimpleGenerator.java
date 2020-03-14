package ru.job4j.templates;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author RVohmin
 * @since 0.1
 */
public class SimpleGenerator implements Template {
    private final Pattern pattern = Pattern.compile("\\$\\{.+?}");
    /**
     * Hello world, ${name}
     * @param template -starting String where we replace template ${...}
     * @param data Map, where key equals template, value replaced template
     * @return new String where template pelaced to value
     */
    @Override
    public String generate(String template, Map<String, String> data) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(template);
        Matcher matcher = pattern.matcher(stringBuilder.toString());
        while (matcher.find()) {
            String textWithoutBraces = matcher.group()
                    .replace("${", "")
                    .replace("}", "");
            String replacedText = "";
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (textWithoutBraces.equals(key)) {
                    replacedText = value;
                    if (replacedText.length() > key.length()) {
                        replacedText = value.substring(0, key.length());
                    }
                }
            }
            if (replacedText.length() == 0) {
                throw new Exception();
            }
            stringBuilder.replace(matcher.start(), matcher.end(), replacedText);
            matcher = pattern.matcher(stringBuilder.toString());
        }
        return stringBuilder.toString();
    }
}
