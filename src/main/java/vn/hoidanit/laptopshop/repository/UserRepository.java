package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    @SuppressWarnings({ "null", "unchecked" })
    User save(User user);
    User findByEmail(String email);
    User findById(long id);
}
