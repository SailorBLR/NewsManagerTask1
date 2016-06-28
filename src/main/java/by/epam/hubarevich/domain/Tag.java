package by.epam.hubarevich.domain;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
public class Tag extends Domain {

    private Integer tagId;
    private String tagName;

    public Tag() {
    }

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
