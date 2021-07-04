package com.example.application.servis;

import com.example.application.model.Eczane;
import com.example.application.model.Kullanici;
import com.example.application.repo.EczaneRepo;
import com.example.application.repo.KullaniciRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EczaneServisİmp implements EczaneServis{

    private final EczaneRepo eczaneRepo;

    public EczaneServisİmp(EczaneRepo eczaneRepo) {
        this.eczaneRepo = eczaneRepo;
    }

    @Override
    public Set<Eczane> getlist() {
        Set<Eczane> eczaneSet =new HashSet<>();
        eczaneRepo.findAll().iterator().forEachRemaining(eczaneSet::add);
        return eczaneSet;
    }

    @Override
    public Eczane save(Eczane k) {
        return eczaneRepo.save(k);
    }
}
