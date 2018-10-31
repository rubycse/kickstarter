package com.rubikslab.kickstarter.user.repository;

import com.rubikslab.kickstarter.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author lutfun
 * @since 10/29/18
 */
@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);

    int countUserByUsername(String username);

    int countUserByEmail(String email);
}
