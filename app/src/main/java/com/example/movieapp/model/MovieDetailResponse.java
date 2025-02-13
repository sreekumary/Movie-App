package com.example.movieapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.movieapp.util.Converters;

import java.util.List;

@Entity(tableName = "movie_details")
@TypeConverters({Converters.class}) // Use TypeConverters to handle lists
public class MovieDetailResponse {

	@PrimaryKey
	@NonNull
	private String id; // Primary Key

	private String primaryTitle;

	public void setId(@NonNull String id) {
		this.id = id;
	}

	public void setPrimaryTitle(String primaryTitle) {
		this.primaryTitle = primaryTitle;
	}

	public void setRuntimeMinutes(int runtimeMinutes) {
		this.runtimeMinutes = runtimeMinutes;
	}

	public void setGrossWorldwide(int grossWorldwide) {
		this.grossWorldwide = grossWorldwide;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setNumVotes(int numVotes) {
		this.numVotes = numVotes;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public void setAverageRating(String averageRating) {
		this.averageRating = averageRating;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public void setAdult(boolean adult) {
		isAdult = adult;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public void setPrimaryImage(String primaryImage) {
		this.primaryImage = primaryImage;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public void setContentRating(String contentRating) {
		this.contentRating = contentRating;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public void setExternalLinks(List<String> externalLinks) {
		this.externalLinks = externalLinks;
	}

	public void setFilmingLocations(List<String> filmingLocations) {
		this.filmingLocations = filmingLocations;
	}

	public void setCountriesOfOrigin(List<String> countriesOfOrigin) {
		this.countriesOfOrigin = countriesOfOrigin;
	}

	public void setSpokenLanguages(List<String> spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	public void setDirectors(List<DirectorsItem> directors) {
		this.directors = directors;
	}

	public void setCast(List<CastItem> cast) {
		this.cast = cast;
	}

	public void setWriters(List<WritersItem> writers) {
		this.writers = writers;
	}

	public void setProductionCompanies(List<ProductionCompaniesItem> productionCompanies) {
		this.productionCompanies = productionCompanies;
	}

	private int runtimeMinutes;
	private int grossWorldwide;
	private String description;
	private int numVotes;
	private String type;
	private String endYear;
	private String averageRating;
	private String releaseDate;
	private int startYear;
	private boolean isAdult;
	private int budget;
	private String primaryImage;
	private String url;
	private String originalTitle;
	private String contentRating;

	// Lists (Handled using TypeConverters)
	private List<String> genres;
	private List<String> externalLinks;
	private List<String> filmingLocations;
	private List<String> countriesOfOrigin;
	private List<String> spokenLanguages;
	private List<String> interests;

	// Use TypeConverters for these complex objects
	private List<DirectorsItem> directors;
	private List<CastItem> cast;
	private List<WritersItem> writers;
	private List<ProductionCompaniesItem> productionCompanies;

	// Getters and Setters...
	@NonNull
	public String getId() {
		return id;
	}

	public String getPrimaryTitle() {
		return primaryTitle;
	}

	public int getRuntimeMinutes() {
		return runtimeMinutes;
	}

	public int getGrossWorldwide() {
		return grossWorldwide;
	}

	public String getDescription() {
		return description;
	}

	public int getNumVotes() {
		return numVotes;
	}

	public String getType() {
		return type;
	}

	public String getEndYear() {
		return endYear;
	}

	public String getAverageRating() {
		return averageRating;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public int getStartYear() {
		return startYear;
	}

	public boolean isAdult() {
		return isAdult;
	}

	public int getBudget() {
		return budget;
	}

	public String getPrimaryImage() {
		return primaryImage;
	}

	public String getUrl() {
		return url;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public String getContentRating() {
		return contentRating;
	}

	public List<String> getGenres() {
		return genres;
	}

	public List<String> getExternalLinks() {
		return externalLinks;
	}

	public List<String> getFilmingLocations() {
		return filmingLocations;
	}

	public List<String> getCountriesOfOrigin() {
		return countriesOfOrigin;
	}

	public List<String> getSpokenLanguages() {
		return spokenLanguages;
	}

	public List<String> getInterests() {
		return interests;
	}

	public List<DirectorsItem> getDirectors() {
		return directors;
	}

	public List<CastItem> getCast() {
		return cast;
	}

	public List<WritersItem> getWriters() {
		return writers;
	}

	public List<ProductionCompaniesItem> getProductionCompanies() {
		return productionCompanies;
	}
}
