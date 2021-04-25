package com.mangaworld.animeworld.data;

import com.mangaworld.animeworld.models.Anime;
import com.mangaworld.animeworld.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimeRepository extends CrudRepository<Anime, Long> {

    public List<Anime> findAnimeByUser(User user);

}
