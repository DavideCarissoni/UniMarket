/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables.records;


import java.time.LocalDate;

import jooq.generated.tables.HteSamplePerson;

import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class HteSamplePersonRecord extends TableRecordImpl<HteSamplePersonRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>HTE_sample_person.date_of_birth</code>.
     */
    public void setDateOfBirth(LocalDate value) {
        set(0, value);
    }

    /**
     * Getter for <code>HTE_sample_person.date_of_birth</code>.
     */
    public LocalDate getDateOfBirth() {
        return (LocalDate) get(0);
    }

    /**
     * Setter for <code>HTE_sample_person.important</code>.
     */
    public void setImportant(Boolean value) {
        set(1, value);
    }

    /**
     * Getter for <code>HTE_sample_person.important</code>.
     */
    public Boolean getImportant() {
        return (Boolean) get(1);
    }

    /**
     * Setter for <code>HTE_sample_person.rn_</code>.
     */
    public void setRn_(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>HTE_sample_person.rn_</code>.
     */
    public Integer getRn_() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>HTE_sample_person.version</code>.
     */
    public void setVersion(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>HTE_sample_person.version</code>.
     */
    public Integer getVersion() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>HTE_sample_person.id</code>.
     */
    public void setId(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>HTE_sample_person.id</code>.
     */
    public Long getId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>HTE_sample_person.hib_sess_id</code>.
     */
    public void setHibSessId(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>HTE_sample_person.hib_sess_id</code>.
     */
    public String getHibSessId() {
        return (String) get(5);
    }

    /**
     * Setter for <code>HTE_sample_person.email</code>.
     */
    public void setEmail(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>HTE_sample_person.email</code>.
     */
    public String getEmail() {
        return (String) get(6);
    }

    /**
     * Setter for <code>HTE_sample_person.first_name</code>.
     */
    public void setFirstName(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>HTE_sample_person.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(7);
    }

    /**
     * Setter for <code>HTE_sample_person.last_name</code>.
     */
    public void setLastName(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>HTE_sample_person.last_name</code>.
     */
    public String getLastName() {
        return (String) get(8);
    }

    /**
     * Setter for <code>HTE_sample_person.occupation</code>.
     */
    public void setOccupation(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>HTE_sample_person.occupation</code>.
     */
    public String getOccupation() {
        return (String) get(9);
    }

    /**
     * Setter for <code>HTE_sample_person.phone</code>.
     */
    public void setPhone(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>HTE_sample_person.phone</code>.
     */
    public String getPhone() {
        return (String) get(10);
    }

    /**
     * Setter for <code>HTE_sample_person.role</code>.
     */
    public void setRole(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>HTE_sample_person.role</code>.
     */
    public String getRole() {
        return (String) get(11);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached HteSamplePersonRecord
     */
    public HteSamplePersonRecord() {
        super(HteSamplePerson.HTE_SAMPLE_PERSON);
    }

    /**
     * Create a detached, initialised HteSamplePersonRecord
     */
    public HteSamplePersonRecord(LocalDate dateOfBirth, Boolean important, Integer rn_, Integer version, Long id, String hibSessId, String email, String firstName, String lastName, String occupation, String phone, String role) {
        super(HteSamplePerson.HTE_SAMPLE_PERSON);

        setDateOfBirth(dateOfBirth);
        setImportant(important);
        setRn_(rn_);
        setVersion(version);
        setId(id);
        setHibSessId(hibSessId);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setOccupation(occupation);
        setPhone(phone);
        setRole(role);
        resetChangedOnNotNull();
    }
}
