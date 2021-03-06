/*
 * EventManager
 * Copyright (c) 2008-2017 James Watmuff & Leonard Hall
 *
 * This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package au.com.jwatmuff.eventmanager.model.vo;

import au.com.jwatmuff.eventmanager.util.IDGenerator;
import au.com.jwatmuff.genericdb.distributed.DistributableObject;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author James
 */
public class CompetitionInfo extends DistributableObject<Integer> implements Serializable {
    private String name;
    private String location;
    private Date startDate;
    private Date endDate;
    private Date ageThresholdDate;
    private int mats;
    private int passwordHash;
    private int weighInPasswordHash;
    private int personalDetailsPasswordHash;
    private int scoreboardPasswordHash;

    private String licenseName;
    private String licenseType;
    private String licenseContact;
    private String directorName;
    private String directorContact;

    private String drawConfiguration;
    
    private boolean closed;
    
    /** Creates a new instance of Competition */
    public CompetitionInfo() {
        setID(IDGenerator.generate());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getAgeThresholdDate() {
        return ageThresholdDate;
    }

    public void setAgeThresholdDate(Date ageThresholdDate) {
        this.ageThresholdDate = ageThresholdDate;
    }

    public int getMats() {
        return mats;
    }

    public void setMats(int mats) {
        this.mats = mats;
    }

    public int getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(int passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getWeighInPasswordHash() {
        return weighInPasswordHash;
    }

    public void setWeighInPasswordHash(int weighInPasswordHash) {
        this.weighInPasswordHash = weighInPasswordHash;
    }

    public int getPersonalDetailsPasswordHash() {
        return personalDetailsPasswordHash;
    }

    public void setPersonalDetailsPasswordHash(int personalDetailsPasswordHash) {
        this.personalDetailsPasswordHash = personalDetailsPasswordHash;
    }

    public int getScoreboardPasswordHash() {
        return scoreboardPasswordHash;
    }

    public void setScoreboardPasswordHash(int scoreboardPasswordHash) {
        this.scoreboardPasswordHash = scoreboardPasswordHash;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getLicenseContact() {
        return licenseContact;
    }

    public void setLicenseContact(String licenseContact) {
        this.licenseContact = licenseContact;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getDirectorContact() {
        return directorContact;
    }

    public void setDirectorContact(String directorContact) {
        this.directorContact = directorContact;
    }

    public String getDrawConfiguration() {
        return drawConfiguration;
    }

    public void setDrawConfiguration(String drawConfiguration) {
        this.drawConfiguration = drawConfiguration;
    }
}
