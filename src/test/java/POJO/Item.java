
package POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class Item {

    @SerializedName("region_id")
    @Expose
    private Integer regionId;
    @SerializedName("region_name")
    @Expose
    private String regionName;
    @SerializedName("links")
    @Expose
    private List<POJO.Link> links = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }

    /**
     * 
     * @param regionId
     * @param regionName
     * @param links
     */
    public Item(Integer regionId, String regionName, List<POJO.Link> links) {
        super();
        this.regionId = regionId;
        this.regionName = regionName;
        this.links = links;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<POJO.Link> getLinks() {
        return links;
    }

    public void setLinks(List<POJO.Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Item.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("regionId");
        sb.append('=');
        sb.append(((this.regionId == null)?"<null>":this.regionId));
        sb.append(',');
        sb.append("regionName");
        sb.append('=');
        sb.append(((this.regionName == null)?"<null>":this.regionName));
        sb.append(',');
        sb.append("links");
        sb.append('=');
        sb.append(((this.links == null)?"<null>":this.links));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
