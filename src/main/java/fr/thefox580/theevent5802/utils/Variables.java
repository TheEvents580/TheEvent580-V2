package fr.thefox580.theevent5802.utils;

import org.bson.Document;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Variables {

    private static final Map<String, Object> variables = new HashMap<>();

    public static @Nullable Object getVariable(String name){
        return variables.getOrDefault(name, null);
    }

    public static void setVariable(String name, Object value) {
        if (variables.containsKey(name)){
            variables.replace(name, value);
        } else {
            variables.put(name, value);
        }
    }

    public static boolean equals(String variableName, Object valueToCheck){
        return Objects.equals(getVariable(variableName), valueToCheck);
    }

    public static Map<String, Object> getVariables(){
        return variables;
    }

    public void retrieveVariables(Document doc, boolean force){
        if (force){
            variables.clear();
        }
        for (String key : doc.keySet()){
            if (!key.equals("_id")){
                variables.putIfAbsent(key, doc.get(key, Object.class));
            }
        }
    }

    public Document sendVariablesDoc(){
        Document doc = new Document()
                .append("_id", "7");

        variables.forEach(doc::append);

        return doc;
    }

}
