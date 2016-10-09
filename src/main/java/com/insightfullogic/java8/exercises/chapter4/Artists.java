package com.insightfullogic.java8.exercises.chapter4;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.List;
import java.util.Optional;

public class Artists {

    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            Optional.empty();
        }
        return Optional.of(artists.get(index));
    }



    public String getArtistName(int index) {
        Optional<Artist> artist = getArtist(index);
        return artist.map(Artist::getName).orElse("unknown");
    }

}
