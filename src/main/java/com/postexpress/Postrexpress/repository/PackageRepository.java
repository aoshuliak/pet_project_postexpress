package com.postexpress.Postrexpress.repository;

import com.postexpress.Postrexpress.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    Package getPackageByAddresser_Email(String email);
    Package getPackageByRecipient_Email(String email);
}
