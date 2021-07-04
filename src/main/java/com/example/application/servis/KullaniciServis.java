package com.example.application.servis;

import com.example.application.model.Kullanici;

import java.util.Set;

public interface KullaniciServis {

    Set<Kullanici> getlist();
    Kullanici save(Kullanici k);
}
