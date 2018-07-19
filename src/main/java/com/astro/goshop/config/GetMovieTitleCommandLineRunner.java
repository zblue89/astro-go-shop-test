package com.astro.goshop.config;

import com.astro.goshop.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GetMovieTitleCommandLineRunner implements CommandLineRunner {

    @Autowired
    private MovieService movieService;

    @Override
    public void run(String... args) {
        System.out.println("Getting movie titles...");
        String title = args.length == 0 ? null : args[0];
        String[] movieTitles = movieService.getMovieTitles(title);
        for (String movieTitle : movieTitles) {
            System.out.print(" - ");
            System.out.println(movieTitle);
        }
        System.out.println("End of getting movie titles...");
    }
}
