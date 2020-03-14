package ru.job4j.templates;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TemplateTest {
    @Test
    public void whenTakeTextWithDateShouldReplaceParamsToData() {
        //assign назначаем
        Template template = new SimpleGenerator();
        String text = "I am a ${name}, Who are ${subject}?";
        Map<String, String> data = Map.of("name", "Petr", "subject", "you");
        String checked = "I am a Petr, Who are you?";
        //act действия
        String result = template.generate(text, data);
        //action события
        assertThat(result, is(checked));
    }

    @Test
    public void whenTakeTextWithMachDateShouldReplaceParamsToData() {
        //assign назначаем
        Template template = new SimpleGenerator();
        String text = " Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> data = Map.of("sos", "Aaaa");
        String checked = " Help, Ааа, Ааа, Ааа";
        //act действия
        String result = template.generate(text, data);
        //action события
        assertThat(result, is(checked));
    }
}