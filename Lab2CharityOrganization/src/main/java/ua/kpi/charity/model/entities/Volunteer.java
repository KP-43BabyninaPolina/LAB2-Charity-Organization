package ua.kpi.charity.model.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Volunteer {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;
    private Event event;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return Objects.equals(id, volunteer.id) &&
                Objects.equals(firstName, volunteer.firstName) &&
                Objects.equals(lastName, volunteer.lastName) &&
                Objects.equals(birthDate, volunteer.birthDate) &&
                Objects.equals(phoneNumber, volunteer.phoneNumber) &&
                Objects.equals(event, volunteer.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, phoneNumber, event);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", event=" + event +
                '}';
    }

    public static class Builder {
        Volunteer instance = new Volunteer();

        public Builder setId(Integer id) {
            instance.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            instance.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            instance.lastName = lastName;
            return this;
        }

        public Builder setBirthDate(LocalDate birthDate) {
            instance.birthDate = birthDate;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            instance.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEvent(Event event) {
            instance.event = event;
            return this;
        }

        public Volunteer build() {
            return instance;
        }
    }
}