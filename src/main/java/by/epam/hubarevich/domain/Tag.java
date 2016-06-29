package by.epam.hubarevich.domain;

/**
 * Class used to represent Tag entity
 * @author Anton_Hubarevich
 * @version 1.0
 */

public class Tag extends Domain {
    private static final long serialVersionUID = 1L;

    /**
     * Unique tag identifier
     */
    private Integer tagId;
    /**
     * Tag name
     */
    private String tagName;

    public Tag() {
    }

    /**
     * Constructor
     * @param tagId positive Integer value
     * @param tagName String value. Limit 30 symbols
     */
    public Tag(Integer tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Tag tag = (Tag) o;

        if (!tagId.equals(tag.tagId)) {
            return false;
        }
        return tagName.equals(tag.tagName);

    }

    @Override
    public int hashCode() {
        int result = tagId.hashCode();
        result = 31 * result + tagName.hashCode();
        return result;
    }
}
