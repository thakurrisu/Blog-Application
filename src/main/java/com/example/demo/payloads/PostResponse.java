package com.example.demo.payloads;

import java.util.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	public List<PostDto> postDto;
	public Integer pageNumber;
	public Integer totalPages;
	public Boolean isLast;

}
