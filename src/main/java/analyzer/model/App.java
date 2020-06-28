package analyzer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App {
    List<Integer> cats;
    List<Pattern> scriptPatterns;

    @JsonCreator
    public App(@JsonProperty("script") Object scriptPatterns) {
        if (scriptPatterns != null) {
            if (scriptPatterns instanceof ArrayList) {
                this.scriptPatterns = ((ArrayList<String>) scriptPatterns).stream().map(pattern -> Pattern.compile(pattern)).collect(Collectors.toList());
            } else {
                this.scriptPatterns = new ArrayList<>();
                this.scriptPatterns.add(Pattern.compile((String) scriptPatterns));
            }
        }
    }

    public List<Integer> getCats() {
        return cats;
    }

    public void setCats(List<Integer> cats) {
        this.cats = cats;
    }

    public List<Pattern> getScriptPatterns() {
        return scriptPatterns;
    }

    public void setScriptPatterns(List<Pattern> scriptPatterns) {
        this.scriptPatterns = scriptPatterns;
    }

    @Override
    public String toString() {
        return "{" +
                "script='" + scriptPatterns + '\'' +
                '}';
    }
}
