package response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    private ImageCredit imageCredit;
    private List<String> tags = new ArrayList<String>();
    private String imageUrl;
    private String filename;
    private String imageHash;
    private Double price;
    private String name;
    private String description;
    private String slug;
    private Long added;
    private String manufacturer;
    private String itemType;
    private String productImg;
    public ImageCredit getImageCredit() {
        return imageCredit;
    }
    public void setImageCredit(ImageCredit imageCredit) {
        this.imageCredit = imageCredit;
    }
    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getImageHash() {
        return imageHash;
    }
    public void setImageHash(String imageHash) {
        this.imageHash = imageHash;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Long getAdded() {
        return added;
    }

    public void setAdded(Long added) {
        this.added = added;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getItemType() {
        return itemType;
    }
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public String getProductImg() {
        return productImg;
    }
    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
}
