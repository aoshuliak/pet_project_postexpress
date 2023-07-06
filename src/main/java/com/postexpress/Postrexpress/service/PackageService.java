package com.postexpress.Postrexpress.service;

import com.postexpress.Postrexpress.model.Package;
import com.postexpress.Postrexpress.model.User;

import java.util.List;

public interface PackageService {
    Package create(Package pack);
    Package readById(long id);
    Package update(Package pack);
    void delete(long id);
    List<Package> getAll();
}
