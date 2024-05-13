package org.example.exo1.entity;

import lombok.Builder;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
@Builder
    public class Film {
        private int id;
        private String titre;
        private String realisateur;

        private String genre;



        @Override
        public String toString() {
            return "Film{" +
                    "titre='" + titre + '\'' +
                    ", realisateur='" + realisateur + '\'' +

                    ", genre='" + genre + '\'' +
                    '}';
        }


    }


