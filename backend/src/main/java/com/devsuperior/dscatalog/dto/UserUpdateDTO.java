package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.services.validation.UserInsertValid;
import com.devsuperior.dscatalog.services.validation.UserUpdateValid;
import jakarta.validation.Valid;

@UserUpdateValid
public class UserUpdateDTO extends UserDTO {

}