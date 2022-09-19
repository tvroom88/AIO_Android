package AndroidAdvanced.MVVM.Data;

import AndroidAdvanced.MVVM.RoomDB.Converters;
import androidx.room.Embedded;
import androidx.room.TypeConverters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VolumeInfo {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("authors")
    @Expose
    @TypeConverters(Converters.class)
    private List<String> authors;

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("pageCount")
    @Expose
    private int pageCount;

    @SerializedName("printType")
    @Expose
    private String printType;

    @SerializedName("imageLinks")
    @Expose
    @Embedded
    private VolumeImageLinks imageLinks;

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public VolumeImageLinks getImageLinks() {
        return imageLinks;
    }

    // ----

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public void setImageLinks(VolumeImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }
}