package analyzer.model;

public enum Category {
    JAVASCRIPT_LIBRARY(59);

    private Integer value;

    Category(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
