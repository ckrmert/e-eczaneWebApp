package com.example.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eczane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String konum;
    private String isim;
    private String adsoyad;
    private String il;
    private String ilce;
    private String mahalle;
    private String adres;
    private String telefon;
    private String enlemboylam;
    private String tarih;


}
