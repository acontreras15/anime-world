package com.mangaworld.animeworld.data;

import com.mangaworld.animeworld.models.Anime;
import org.springframework.data.repository.CrudRepository;

public interface AnimeRepository extends CrudRepository<Anime, Long> {
}
