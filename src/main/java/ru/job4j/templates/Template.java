package ru.job4j.templates;

import java.util.Map;

/**
 * @author RVohmin
 * @since 13.03.2020
 */
public interface Template {
    /**
     * Hello world, ${name}
     * @param template -starting String where we replace template ${...}
     * @param data Map, where key equals template, value replaced template
     */
    String generate(String template, Map<String, String> data) throws Exception;
}
