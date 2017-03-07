package bg.tilchev.models.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created on 2017-03-05.
 */
public class GameBindingModel implements Serializable {

    private Long id;

    @Pattern(regexp = "^[A-Z].{3,100}$",
            message = "Title has to begin with uppercase letter and has length between 3 and 100 symbols (inclusive)")
    private String title;

    @Pattern(regexp = "^\\d+.\\d{1,2}$",
            message = "Price must be a positive number with precision up to 2 digits after floating point")
    private String price;

    @Pattern(regexp = "^\\d+.\\d$",
            message = "Size must be a positive number with precision up to 1 digit after floating point")
    private String size;

    @Size(min = 11, max = 11, message = "Trailer id must be exactly 11 symbols long")
    private String trailer;

    private String thumbnail;

    @Size(min = 20, message = "Description – must be at least 20 symbols")
    private String description;

    @NotNull(message = "You must se a valid release date (yyyy-MM-dd)")
    private String releaseDate;

    public GameBindingModel() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
