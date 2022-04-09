package com.example.musify.repo;

import com.example.musify.dto.ArtistDTO;
import com.example.musify.entities.Artist;
import storage.repository.ArtistRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestRepo {
    public static void main(String[] args) throws ParseException {
        Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse("1972-10-17");
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse("1988-00-00");
        Date end = new SimpleDateFormat("yyyy-MM-dd").parse("1988-00-00");

        java.sql.Date birth = new java.sql.Date(birthday.getTime());
        java.sql.Date startDate = new java.sql.Date(start.getTime());
        java.sql.Date endDate = new java.sql.Date(end.getTime());


        Artist artist= new Artist(0,"Marshall","Mathers","Eminem",  birth,startDate,endDate);
        ArtistRepo artistRepo = new ArtistRepo();
//        Artist artist1 = artistRepo.save(artist);
//        Artist artist1 = artistRepo.delete(artist);
//        List<Artist> artists = artistRepo.getAll();
//        for(Artist a:artists){
//            System.out.println(a.getId());
//        }
//        Artist artistUpdated= new Artist(17,"Modified","Mathers","Eminem",  birth,startDate,endDate);
//        Artist artist1 = artistRepo.update(artistUpdated);
        Artist artist1 = artistRepo.getById(17);
        System.out.println(artist1.getId());
    }
}
