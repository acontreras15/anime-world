package com.mangaworld.animeworld.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Weekly {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Anime name cannot be blank")
    @Column(unique = true)
    private String animeName;

    @NotBlank(message = "Anime needs a description")
    @Size(min = 1, message = "Description must be more than one character")
    private String description;

    @NotNull(message = "Episodes are required")
    @Min(value = 1, message = "There is no anime with 0 episodes")
    private int episodes;

    private String genre;

    public Weekly(){
        this.animeName = "";
        this.genre = "";
        this.description = "";
        this.episodes = 0;
    }

    public Weekly(String animeName, String animeGenre, String description, int episodes) {
        this.animeName = animeName;
        this.genre = animeGenre;
        this.description = description;
        this.episodes = episodes;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString(){return this.animeName;}

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Weekly))
            return false;

        Weekly w = (Weekly) o;

        return this.animeName.equals(w.animeName) && this.description.equals(w.description) && this.genre.equals(w.genre);
    }

    @Override
    public int hashCode() {
    return this.animeName.hashCode();
    }
}
