package com.example.movieapp.model;

import java.util.List;

public class ResponseItem{
	private String primaryTitle;
	private int runtimeMinutes;
	private String primaryImage;
	private String releaseDate;
	private Long grossWorldwide;
	private int startYear;
	private String description;
	private int numVotes;
	private String type;
	private Object endYear;
	private String url;
	private List<String> countriesOfOrigin;
	private String originalTitle;
	private List<String> genres;
	private Object averageRating;
	private String contentRating;
	private List<String> externalLinks;
	private List<String> spokenLanguages;
	private String id;
	private List<String> interests;
	private List<String> filmingLocations;
	private List<ProductionCompaniesItem> productionCompanies;
	private boolean isAdult;
	private long budget;

	public String getPrimaryTitle(){
		return primaryTitle;
	}

	public int getRuntimeMinutes(){
		return runtimeMinutes;
	}

	public String getPrimaryImage(){
		return primaryImage;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public long  getGrossWorldwide(){
		return grossWorldwide;
	}

	public int getStartYear(){
		return startYear;
	}

	public String getDescription(){
		return description;
	}

	public int getNumVotes(){
		return numVotes;
	}

	public String getType(){
		return type;
	}

	public Object getEndYear(){
		return endYear;
	}

	public String getUrl(){
		return url;
	}

	public List<String> getCountriesOfOrigin(){
		return countriesOfOrigin;
	}

	public String getOriginalTitle(){
		return originalTitle;
	}

	public List<String> getGenres(){
		return genres;
	}

	public Object getAverageRating(){
		return averageRating;
	}

	public String getContentRating(){
		return contentRating;
	}

	public List<String> getExternalLinks(){
		return externalLinks;
	}

	public List<String> getSpokenLanguages(){
		return spokenLanguages;
	}

	public String getId(){
		return id;
	}

	public List<String> getInterests(){
		return interests;
	}

	public List<String> getFilmingLocations(){
		return filmingLocations;
	}

	public List<ProductionCompaniesItem> getProductionCompanies(){
		return productionCompanies;
	}

	public boolean isIsAdult(){
		return isAdult;
	}

	public long getBudget(){
		return budget;
	}
}