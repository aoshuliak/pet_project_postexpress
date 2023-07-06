package com.postexpress.Postrexpress.service.impl;

import com.postexpress.Postrexpress.model.Package;
import com.postexpress.Postrexpress.model.User;
import com.postexpress.Postrexpress.repository.PackageRepository;
import com.postexpress.Postrexpress.service.PackageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public Package create(Package pack) {
        return packageRepository.save(pack);
    }

    @Override
    public Package readById(long id) {
        Optional<Package> optional = packageRepository.findById(id);
        return optional.get();
    }

    @Override
    public Package update(Package pack) {
        return packageRepository.save(pack);
    }

    @Override
    public void delete(long id) {
        packageRepository.delete(readById(id));
    }

    @Override
    public List<Package> getAll() {
        return packageRepository.findAll();
    }

    public Package findByAddresser_Email(String email) {
        return packageRepository.getPackageByAddresser_Email(email);
    }

    public Package findByRecipient_Email(String email) {
        return packageRepository.getPackageByRecipient_Email(email);
    }

}
