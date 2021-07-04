package com.example.application.views;

import com.example.application.model.Eczane;
import com.example.application.servis.EczaneServis;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route("tanim")
public class TanimlamaView extends VerticalLayout {

    private final EczaneServis eczaneServis;

    public TanimlamaView(EczaneServis eczaneServis){
        this.eczaneServis = eczaneServis;


        Label label = new Label("TARİHE GÖRE NÖBETÇİ ECZANE SORGULA");
        Label labell = new Label("HENÜZ LİSTENİZ BOŞ");
        Label labelll = new Label("** ECZANEYİ HARİTALAR ÜZERİNDE GÖREBİLMEK İÇİN ÜZERİNE ÇİFT TIKLAYINIZ..");
        labelll.getStyle().set("color","red");

        Grid<Eczane> grid = new Grid<>(Eczane.class);
        //grid.removeColumnByKey("id");
        //grid.removeColumnByKey("enlemboylam");
        grid.setColumns("isim","il","telefon","adres","enlemboylam");



        DatePicker datePicker = new DatePicker();
        datePicker.addValueChangeListener(event ->{
           String selected =datePicker.getValue().toString();
           datePicker.setLabel(selected);

           List<Eczane> eczaneList = new ArrayList<>();
           eczaneList.addAll(eczaneServis.getlist());

            labell.setText(selected+" TARİHLİ NÖBETÇİ ECZANELER");

           for (int i=0;i<100;i++){
               if (eczaneList.get(i).getTarih().toString().equals(datePicker.getLabel().toString())){
            grid.setItems(eczaneList.get(i));
            grid.setSelectionMode(Grid.SelectionMode.NONE);
            grid.setColumns("isim","telefon","adres","enlemboylam","tarih");


               }}});



        grid.addItemDoubleClickListener(e ->{

            String eklenti = e.getItem().getEnlemboylam().toString();
            UI.getCurrent().getPage().setLocation("https://www.google.com/maps/place/"+eklenti);

        });




        add(new H2(label),datePicker,new H2(labell),grid,new H2(labelll));



    }
}
