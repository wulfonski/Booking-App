package ro.sda.booking.core.entity;

import ro.sda.booking.core.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "hosts", schema = "booking")
public class Host extends BaseEntity {

    @Column(name = "hosts", length = 50, nullable = false)
    private String name;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Host)) return false;
        Host host = (Host) o;
        return super.getId() == (host.getId()) &&
                Objects.equals(getName(), host.getName()) &&
                Objects.equals(getEmail(), host.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getId());
    }

    @Override
    public String toString() {
        return "Host{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
