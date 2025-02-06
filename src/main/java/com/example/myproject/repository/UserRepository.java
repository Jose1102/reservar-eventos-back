package com.example.myproject.repository;

import com.example.myproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value="SELECT * FROM usuarios WHERE correo = :email",nativeQuery = true)
    User findByEmail(@Param("email") String email);

}
