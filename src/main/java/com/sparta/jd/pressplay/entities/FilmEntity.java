package com.sparta.jd.pressplay.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.rmi.UnexpectedException;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "film", schema = "sakila")
public class FilmEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "film_id")
    private Integer filmId;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "release_year")
    private Integer releaseYear;
    @Basic
    @Column(name = "language_id")
    private Byte languageId;
    @Basic
    @Column(name = "original_language_id")
    private String originalLanguageId;
    @Basic
    @Column(name = "rental_duration")
    private Integer rentalDuration;
    @Basic
    @Column(name = "rental_rate")
    private BigDecimal rentalRate;
    @Basic
    @Column(name = "length")
    private Integer length;
    @Basic
    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;
    @Basic
    @Column(name = "rating")
    private String rating;
    @Basic
    @Column(name = "special_features")
    private String specialFeatures;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Byte getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Byte languageId) {
        this.languageId = languageId;
    }

    public String getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(String originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public Integer getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity that = (FilmEntity) o;
        return Objects.equals(filmId, that.filmId) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(releaseYear, that.releaseYear) && Objects.equals(languageId, that.languageId) && Objects.equals(originalLanguageId, that.originalLanguageId) && Objects.equals(rentalDuration, that.rentalDuration) && Objects.equals(rentalRate, that.rentalRate) && Objects.equals(length, that.length) && Objects.equals(replacementCost, that.replacementCost) && Objects.equals(rating, that.rating) && Objects.equals(specialFeatures, that.specialFeatures) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, title, description, releaseYear, languageId, originalLanguageId, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures, lastUpdate);
    }

    //Limit the description in the table
    public String getDisplayDescription() {
        try {
            return description.substring(0, 50) + "...";
        } catch (StringIndexOutOfBoundsException e) {
            return description;
        }
    }

    public String getLanguage() throws UnexpectedException {
        return switch(getLanguageId()) {
            case 1 -> "English";
            case 2 -> "Italian";
            case 3 -> "Japanese";
            case 4 -> "Mandarin";
            case 5 -> "French";
            case 6 -> "German";
            default -> throw new UnexpectedException("Language not readable!");
        };
    }
}
