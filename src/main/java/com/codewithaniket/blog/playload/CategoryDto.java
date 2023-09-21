package com.codewithaniket.blog.playload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer categoryId;
	@NotBlank(message=" categoryId should not be empty and null")
	
	private String catogeryTitle;
	@NotEmpty(message="cotegoryDiscription should not be empty and null")
	@Size(min=5,max=1000)
	private String cotegoryDiscription;

}
