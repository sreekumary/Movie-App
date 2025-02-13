package com.example.movieapp.model;

import java.util.List;

public class CastItem{
	private List<String> characters;
	private String fullName;
	private String id;
	private String job;
	private String url;

	public List<String> getCharacters(){
		return characters;
	}

	public String getFullName(){
		return fullName;
	}

	public String getId(){
		return id;
	}

	public String getJob(){
		return job;
	}

	public String getUrl(){
		return url;
	}
}