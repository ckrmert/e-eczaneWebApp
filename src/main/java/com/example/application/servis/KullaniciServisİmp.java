package com.example.application.servis;


import com.example.application.model.Kullanici;
import com.example.application.repo.KullaniciRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class KullaniciServisİmp implements KullaniciServis{

    private final KullaniciRepo kullaniciRepo;

    public KullaniciServisİmp(KullaniciRepo kullaniciRepo) {
        this.kullaniciRepo = kullaniciRepo;
    }

    @Override
    public Set<Kullanici> getlist() {
         Set<Kullanici> kullaniciSet =new HashSet<>();
         kullaniciRepo.findAll().iterator().forEachRemaining(kullaniciSet::add);
         return kullaniciSet;
    }

    @Override
    public Kullanici save(Kullanici k) {
        return kullaniciRepo.save(k);
    }
}
