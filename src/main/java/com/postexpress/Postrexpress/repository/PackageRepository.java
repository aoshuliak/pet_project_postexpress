package com.postexpress.Postrexpress.repository;

import com.postexpress.Postrexpress.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {

    @Query(value = "select * from packages where id = ?1", nativeQuery = true)
    List<Package> getByUserId(long userId);

    Package getPackageByAddresser_Email(String email);
    Package getPackageByRecipient_Email(String email);
}
