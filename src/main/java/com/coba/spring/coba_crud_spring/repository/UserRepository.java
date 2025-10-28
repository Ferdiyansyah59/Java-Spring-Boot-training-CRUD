package com.coba.spring.coba_crud_spring.repository;

import com.coba.spring.coba_crud_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Buat ngatasin masalah NullPointerException
import java.util.Optional;

/* <User, Long> --> Merujuk ke Model dan tipe data primarynya */
@Repository // Buat penanda kalo ini repository dan jadiin Spring Bean (Depedency Injection)
public interface UserRepository extends JpaRepository<User, Long> {
    // Qeury buat mencari user berdasarkan username atau email
    Optional<User> findByUsernameOrEmail(String username, String email);

    // Query buat mencari data user berdasarkan username
    Optional<User> findByUsername(String username);

    // Query buat ngecek username udah ada atau belum
    Boolean existsByUsername(String username);

    // Query buat ngecek email udah ada atau belum
    boolean existsByEmail(String email);
}


