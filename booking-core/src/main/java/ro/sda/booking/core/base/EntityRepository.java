package ro.sda.booking.core.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.sda.booking.core.base.BaseEntity;

@NoRepositoryBean
public interface EntityRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
    public T findById(long id);
}
