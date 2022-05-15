package org.example.parse;

import org.example.parse.valid.ValidationObject;
import org.telegram.telegrambots.meta.api.interfaces.Validable;

import java.util.List;
import java.util.Map;

public interface Parse {

    void makeParse(String message);

    Map<String, Integer> getParams();

    List<ValidationObject> validationsResult();

    void clear();
}
