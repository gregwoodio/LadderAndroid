package tech.gregwood.ladderandroid.data;

import java.net.URL;
import java.util.Date;

/**
 * Created by greg on 5/3/2016.
 */
public class User extends Profile {
    protected String firstName;
    protected String lastName;
    protected String userDescription;
    protected String resume;
    protected int academicStatus;

    public User() {
        //fields from Profile superclass
        this.profileID = profileID;
        this.username = username;
        this.email = email;
        this.pictureURL = pictureURL;
        this.timestamp = timestamp;

        //User fields
        this.firstName = firstName;
        this.lastName = lastName;
        this.userDescription = userDescription;
        this.resume = resume;
        this.academicStatus = academicStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getAcademicStatus() {
        return academicStatus;
    }

    public void setAcademicStatus(int academicStatus) {
        this.academicStatus = academicStatus;
    }
}
