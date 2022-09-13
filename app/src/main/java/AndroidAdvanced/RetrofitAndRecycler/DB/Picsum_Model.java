package AndroidAdvanced.RetrofitAndRecycler.DB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "picsum")
public class Picsum_Model {

    public Picsum_Model(){

    }

//    @PrimaryKey(autoGenerate = true)
//    private int picsumId;

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id") private String id;

    @SerializedName("author")
    @Expose
    @ColumnInfo(name = "author")  private String author;

    @SerializedName("width")
    @Expose
    @ColumnInfo(name = "width") private String width;

    @SerializedName("height")
    @Expose
    @ColumnInfo(name = "height") private String height;

    @SerializedName("url")
    @Expose
    @ColumnInfo(name = "url") private String url;

    @SerializedName("download_url")
    @Expose
    @ColumnInfo(name = "download_url") private String download_url;


    // Getter methods
    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }

    public String getDownload_url() {
        return download_url;
    }

//    public int getPicsumId() {
//        return picsumId;
//    }
//
//    // Setter methods
//
//    public void setPicsumId(int picsumId) {
//        this.picsumId = picsumId;
//    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

}
