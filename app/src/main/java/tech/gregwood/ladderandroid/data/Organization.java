package tech.gregwood.ladderandroid.data;

import java.net.URL;
import java.util.Date;

/**
 * Created by greg on 5/3/2016.
 */
public class Organization extends Profile {

    protected String organizationName;
    protected String address;
    protected URL url;
    protected String missionStatement;

    public Organization() {

    }

    public Organization(int profileID, String username, String email, URL pictureURL, Date timestamp,
                        String organizationName, String address, URL url, String missionStatement) {
        //fields from Profile superclass
        this.profileID = profileID;
        this.username = username;
        this.email = email;
        this.pictureURL = pictureURL;
        this.timestamp = timestamp;

        //organization fields
        this.organizationName = organizationName;
        this.address = address;
        this.url = url;
        this.missionStatement = missionStatement;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getMissionStatement() {
        return missionStatement;
    }

    public void setMissionStatement(String missionStatement) {
        this.missionStatement = missionStatement;
    }
}
