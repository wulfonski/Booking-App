package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "properties", schema = "booking")
public class Property extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false)
    private String name;  

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Host host;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "contact_no", length = 50, nullable = false)
    private String contact;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Property)) return false;
        Property that = (Property) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getHost(), that.getHost()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getContact(), that.getContact()) &&
                Objects.equals(getCity(), that.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHost(), getEmail(), getContact(), getCity());
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", host=" + host +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
