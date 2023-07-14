package library.dto.request;

public class BookRequestDto {
    private String name;
    private String authorName;
    private String description;
    private Long categoryId;

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDescription() {
        return description;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
