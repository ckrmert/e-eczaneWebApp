package com.example.application.servis;

import com.example.application.model.Eczane;


import java.util.Set;

public interface EczaneServis {

    Set<Eczane> getlist();
    Eczane save(Eczane k);
}
