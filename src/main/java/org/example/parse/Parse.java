package org.example.parse;

import org.example.parse.valid.ValidationObject;

import java.util.List;
import java.util.Map;

public interface Parse {

    void makeParse(String message);

    Map<String, Integer> getParams();

    List<ValidationObject> validationsResult();

    void clear();
}
