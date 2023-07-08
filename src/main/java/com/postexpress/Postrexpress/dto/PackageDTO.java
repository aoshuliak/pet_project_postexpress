package com.postexpress.Postrexpress.dto;

import com.postexpress.Postrexpress.model.Status;
import com.postexpress.Postrexpress.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDTO {

    private long id;

    @Pattern(regexp = "[^\\/:*?\"<>|]",
            message = "Name shouldn't consist characters")
    @Size(min = 3, max = 12, message = "Size should be from 3 to 12 letters/numbers")
    private String name;

    @Pattern(regexp = "[^\\/:*?\"<>|]",
            message = "Description shouldn't consist characters")
    @Size(min = 5, max = 120, message = "Size should be from 5 to 120 letters/numbers")
    private String description;

    private User recipient;

    private User addresser;

    private Status status;

}