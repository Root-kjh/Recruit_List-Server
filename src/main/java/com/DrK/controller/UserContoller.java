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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.DrK.DTO.EditPasswordDTO;
import com.DrK.DTO.UpdateUserDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.Entities.UserEntity;
import com.DrK.service.UserService;

@RestController
@RequestMapping("/user/{userId}")
@RequiredArgsConstructor
@CrossOrigin
public class UserContoller {
	
	private final UserService UserService;
	
	@GetMapping("/companies")
	public List<CompanyEntity> getLikeCompany(
		Authentication authentication,
		@PathVariable Long userId
		) throws Exception{
		authentication.getPrincipal();
		return null;
	}
	
	@PostMapping("/companies/{companyId}")
	public boolean addLikeCompany(
			Authentication authentication,
			@PathVariable Long userId,
			@PathVariable String companyId) throws Exception{
		Long userIdx = ((UserEntity) authentication.getPrincipal()).getIdx();
		return UserService.setLikeCompany(userIdx, companyId);
	}
	
	@DeleteMapping("/companies/{companyID}")
	public boolean delCompany(
			Authentication authentication,
			@PathVariable Long userId,
			@PathVariable String companyId) {
		return UserService.deleteLikeCompany(userId, companyId);
	}

	@PutMapping()
	public void editUserInfo(
		Authentication authentication,
		@PathVariable Long userId,
		@RequestBody @Valid UpdateUserDTO updateUserDTO,
		Errors errors
	) throws Exception{

	}

	@PatchMapping()
	public void editPassword(
		Authentication authentication,
		@PathVariable Long userId,
		@RequestBody @Valid EditPasswordDTO editPasswordDTO,
		Errors errors
	)throws Exception{

	}
}
