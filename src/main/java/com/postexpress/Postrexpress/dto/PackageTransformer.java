package com.postexpress.Postrexpress.dto;

import com.postexpress.Postrexpress.model.Package;
import com.postexpress.Postrexpress.model.Status;
import com.postexpress.Postrexpress.model.User;

public class PackageTransformer {
    public static PackageDTO convertToDto(Package pack) {
        return new PackageDTO(
                pack.getId(),
                pack.getName(),
                pack.getDescription(),
                pack.getRecipient(),
                pack.getAddresser(),
                pack.getStatus()
        );
    }

    public static Package convertToEntity(PackageDTO packageDTO, User recipient, User addresser, Status status) {
        Package pack = new Package();
        pack.setId(packageDTO.getId());
        pack.setName(packageDTO.getName());
        pack.setDescription(packageDTO.getDescription());
        pack.setRecipient(recipient);
        pack.setAddresser(addresser);
        pack.setStatus(status);
        return pack;
    }

}