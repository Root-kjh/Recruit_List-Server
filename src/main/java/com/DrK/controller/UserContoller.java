package com.DrK.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.DrK.DTO.CompanyInfoDTO;
import com.DrK.DTO.EditPasswordDTO;
import com.DrK.DTO.UpdateUserDTO;
import com.DrK.DTO.UserinfoDTO;
import com.DrK.Entities.UserEntity;
import com.DrK.Errors.PermissionDeniedException;
import com.DrK.service.UserService;

@RestController
@RequestMapping("/user/{userId}")
@RequiredArgsConstructor
@CrossOrigin
public class UserContoller {
	
	private final UserService UserService;
	
	private boolean checkPermission(Long userId, Authentication authentication) throws Exception{
		return userId==((UserEntity)authentication.getPrincipal()).getIdx();
	}

	@GetMapping("/companies")
	public List<CompanyInfoDTO> getLikeCompany(
		Authentication authentication,
		@PathVariable Long userId
		) throws Exception{
		if (!this.checkPermission(userId, authentication))
			throw new PermissionDeniedException();
		return this.UserService.getUserLikeCompany(userId);
	}
	
	@PostMapping("/companies/{companyId}")
	public boolean addLikeCompany(
			Authentication authentication,
			@PathVariable Long userId,
			@PathVariable String companyId) throws Exception{
		if (!this.checkPermission(userId, authentication))
		throw new PermissionDeniedException();
		return UserService.setLikeCompany(userId, companyId);
	}
	
	@DeleteMapping("/companies/{companyID}")
	public boolean delCompany(
			Authentication authentication,
			@PathVariable Long userId,
			@PathVariable String companyId) throws Exception{
		if (!this.checkPermission(userId, authentication))
			throw new PermissionDeniedException();
		return UserService.deleteLikeCompany(userId, companyId);
	}

	@PutMapping()
	public UserinfoDTO editUserInfo(
		Authentication authentication,
		@PathVariable Long userId,
		@RequestBody @Valid UpdateUserDTO updateUserDTO,
		Errors errors
	) throws Exception{
		if (!this.checkPermission(userId, authentication))
			throw new PermissionDeniedException();
		return this.UserService.editUserinfo(userId, updateUserDTO);
	}

	@PatchMapping()
	public boolean editPassword(
		Authentication authentication,
		@PathVariable Long userId,
		@RequestBody @Valid EditPasswordDTO editPasswordDTO,
		Errors errors
	)throws Exception{
		if (!this.checkPermission(userId, authentication))
			throw new PermissionDeniedException();
		return this.UserService.editPassword(userId, editPasswordDTO.getPassword());
	}
}
