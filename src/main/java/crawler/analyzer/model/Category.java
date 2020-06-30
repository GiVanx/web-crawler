package crawler.analyzer.model;

public enum Category {
    CMS(1), BLOGS(11);

    private final Integer value;

    Category(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
