package coza.dcmgroup.entity.legacy;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the Users database table.
 *
 * @author theresa.renney
 */
@Entity
@Table(name = "aspnet_Users")
public class LegacyUser implements Serializable {

    @Id
    @GenericGenerator(name = "guidGenerator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "guidGenerator")
    @Column(name = "UserId")
    protected String userId;

    @Column(name = "UserName")
    protected String userName;

    @Column(name = "ApplicationId")
    protected String applicationId;

    @Column(name = "IsAnonymous")
    protected Boolean anonymous;

    @Column(name = "LastActivityDate")
    protected Timestamp lastActivityDate;

    @Column(name = "NCRRegistrationNumber")
    protected String ncrRegistrationNumber;

    @Column(name = "FirstName")
    protected String firstName;

    @Column(name = "Initials")
    protected String initials;

    @Column(name = "Surname")
    protected String surname;

    @Column(name = "IdentityNumber")
    protected String identityNumber;

    @Column(name = "DateOfBirth")
    protected Date dateOfBirth;

    @Column(name = "WorkNumber")
    protected String workNumber;

    @Column(name = "FaxNumber")
    protected String faxNumber;

    @Column(name = "MobileNumber")
    protected String mobileNumber;

    @Column(name = "EffectiveDate")
    protected Date effectiveDate;

    @Column(name = "EndDate")
    protected Date endDate;

    @Column(name = "IsPostalSameAsPhysical")
    protected Boolean postalSameAsPhysical;

    @Column(name = "HasDefaultPassword")
    protected String hasDefaultPassword;

    @Column(name = "OptionalFunctionalities")
    protected String optionalFunctionalities;

    @Transient
    protected String ldapUID;


    public LegacyUser() {
    }

    public LegacyUser(String userId, String userName, String firstName, String surname) {
        this.setUserId(userId);
        this.setUserName(userName);
        this.setFirstName(firstName);
        this.setSurname(surname);
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the anonymous
     */
    public Boolean isAnonymous() {
        return anonymous;
    }

    /**
     * @param anonymous the anonymous to set
     */
    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    /**
     * @return the lastActivityDate
     */
    public Timestamp getLastActivityDate() {
        return lastActivityDate;
    }

    /**
     * @param lastActivityDate the lastActivityDate to set
     */
    public void setLastActivityDate(Timestamp lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    /**
     * @return the ncrRegistrationNumber
     */
    public String getNcrRegistrationNumber() {
        return ncrRegistrationNumber;
    }

    /**
     * @param ncrRegistrationNumber the ncrRegistrationNumber to set
     */
    public void setNcrRegistrationNumber(String ncrRegistrationNumber) {
        this.ncrRegistrationNumber = ncrRegistrationNumber;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the initials
     */
    public String getInitials() {
        return initials;
    }

    /**
     * @param initials the initials to set
     */
    public void setInitials(String initials) {
        this.initials = initials;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the identityNumber
     */
    public String getIdentityNumber() {
        return identityNumber;
    }

    /**
     * @param identityNumber the identityNumber to set
     */
    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the workNumber
     */
    public String getWorkNumber() {
        return workNumber;
    }

    /**
     * @param workNumber the workNumber to set
     */
    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    /**
     * @return the faxNumber
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * @param faxNumber the faxNumber to set
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return the effectiveDate
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * @param effectiveDate the effectiveDate to set
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the postalSameAsPhysical
     */
    public Boolean isPostalSameAsPhysical() {
        return postalSameAsPhysical;
    }

    /**
     * @param postalSameAsPhysical the postalSameAsPhysical to set
     */
    public void setPostalSameAsPhysical(Boolean postalSameAsPhysical) {
        this.postalSameAsPhysical = postalSameAsPhysical;
    }

    /**
     * @return the hasDefaultPassword
     */
    public String getHasDefaultPassword() {
        return hasDefaultPassword;
    }

    /**
     * @param hasDefaultPassword the hasDefaultPassword to set
     */
    public void setHasDefaultPassword(String hasDefaultPassword) {
        this.hasDefaultPassword = hasDefaultPassword;
    }

    /**
     * @return the optionalFunctionalities
     */
    public String getOptionalFunctionalities() {
        return optionalFunctionalities;
    }

    /**
     * @param optionalFunctionalities the optionalFunctionalities to set
     */
    public void setOptionalFunctionalities(String optionalFunctionalities) {
        this.optionalFunctionalities = optionalFunctionalities;
    }

    /**
     * @return the applicationId
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * @param applicationId the applicationId to set
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * @return the ldapUID
     */
    public String getLdapUID() {
        return ldapUID;
    }

    /**
     * @param ldapUID the ldapUID to set
     */
    public void setLdapUID(String ldapUID) {
        this.ldapUID = ldapUID;
    }

}
