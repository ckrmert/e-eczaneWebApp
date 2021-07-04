package com.example.application.views;

import com.example.application.model.Eczane;
import com.example.application.model.Kullanici;
import com.example.application.servis.EczaneServis;
import com.example.application.servis.KullaniciServis;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Route
public class MainView extends VerticalLayout {

    private final KullaniciServis kullaniciServis;
    private final EczaneServis eczaneServis;


    public MainView(KullaniciServis kullaniciServis,EczaneServis eczaneServis){
        this.kullaniciServis = kullaniciServis;
        this.eczaneServis = eczaneServis;


       // TextField textoda = new TextField("Eczacı Odası","Kayıtlı olduğunuz ili giriniz..");
        Select<String> select = new Select<>("The List");
        select.setItems("İstanbul","Edirne","Erzincan","Ankara","Konya","Samsun","Şanlıurfa","Diyarbakır","Bursa","Rize","Kahramanmaraş","Zonguldak","Tekirdağ","Sakarya","Sivas","Trabzon","Kars","Malatya","Mersin","Kastamonu","Hatay","Manisa","Balıkesir","Kocaeli","Çanakkale","Van","Giresun","Çorum","Uşak","Mardin","Yozgat","Osmaniye","Adıyaman");
        select.setLabel("Eczacı Odası");
        Label label = new Label("KULLANICI BİLGİLERİ");
        Label label2 = new Label("E-ECZANE");
        Label label3 = new Label("Türkiye'deki bütün nöbetçi eczanelerini bulabilirsiniz. Bazı eczaneler, gece boyu nöbet tutmuyor olabilir; bazıları ise icap nöbeti tutuyor veya mücbir bir sebeple nöbet başında bulunamıyor olabilir. Bu nedenle eczaneye gitmeden önce telefonla açık olduğunu teyit etmeniz tavsiye olunur.");
        label.getStyle().set("color","red");
        label2.getStyle().set("color","red");


        TextField textisim = new TextField("İsim","İsminizi giriniz..");
        TextField textmail = new TextField("Mail Adresi","Mail adresinizi giriniz..");
        TextField textsifre = new TextField("Sifre","Sifrenizi giriniz..");


        FormLayout formLayout = new FormLayout();
        //formLayout.add(textoda,textisim,textmail,textsifre);
        formLayout.add(select,textisim,textmail,textsifre);



       // Button butongiris = new Button("GİRİŞ YAP");
        //Button buttonyenikayit = new Button("KAYIT OL");
        //Button buttonekle = new Button("NÖBETÇİ ECZANE EKLE");
        //Button buttoncikis = new Button("ÇIKIŞ YAP");
        //Button buttonanonim = new Button("ANONİM OLARAK NÖBETÇİ ECZANE ARA");

        Button butongiris = new Button("GİRİŞ YAP",
                new Icon(VaadinIcon.KEY)
        );
        butongiris.addThemeVariants(ButtonVariant.LUMO_LARGE,
                ButtonVariant.LUMO_ERROR);
        butongiris.setIconAfterText(true);
        butongiris.setAutofocus(true);

        Button buttonyenikayit = new Button("KAYIT OL",
                new Icon(VaadinIcon.USER)
        );
        buttonyenikayit.addThemeVariants(ButtonVariant.LUMO_LARGE,
                ButtonVariant.LUMO_ERROR);
        buttonyenikayit.setIconAfterText(true);


        Button buttonekle = new Button("NÖBETÇİ ECZANE EKLE",
                new Icon(VaadinIcon.PAPERCLIP)
        );
        buttonekle.addThemeVariants(ButtonVariant.LUMO_LARGE,
                ButtonVariant.LUMO_ERROR);
        buttonekle.setIconAfterText(true);


        Button buttoncikis = new Button("ÇIKIŞ YAP",
                new Icon(VaadinIcon.EXIT)
        );
        buttoncikis.addThemeVariants(ButtonVariant.LUMO_LARGE,
                ButtonVariant.LUMO_ERROR);
        buttoncikis.setIconAfterText(true);


        Button buttonanonim = new Button("ANONİM OLARAK NÖBETÇİ ECZANE ARA",
                new Icon(VaadinIcon.USER_CARD)
        );
        buttonanonim.addThemeVariants(ButtonVariant.LUMO_LARGE,
                ButtonVariant.LUMO_ERROR);
        buttonanonim.setIconAfterText(true);




        Image img = new Image("https://www.forumistanbul.com.tr/media/image/eczane.jpg","adsa");


        HorizontalLayout horizontalLayout2 = new HorizontalLayout();
        horizontalLayout2.add(img,new H1(label3));

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(butongiris,buttonyenikayit);


       butongiris.addClickListener(buttonClickEvent ->{

            List<Kullanici> kullaniciListe = new ArrayList<>();
            kullaniciListe.addAll(kullaniciServis.getlist());

            for (int i=0;i<100;i++)
            if (kullaniciListe.get(i).getMail().equals(textmail.getValue()) && kullaniciListe.get(i).getSifre().equals(textsifre.getValue())){
                //System.out.println("Başarılı");
                Notification.show("Giriş başarılı");
                kullaniciListe.get(i).setIsim("True");
                kullaniciServis.save(kullaniciListe.get(i));
                UI.getCurrent().getPage().setLocation("/");
                break;
            }
            //else System.out.println("Yanlış giriş..");
           else UI.getCurrent().getPage().setLocation("/");
       } );

        select.addValueChangeListener(event -> {
            String selected = event.getValue();
            select.setLabel(selected);

        });

       buttonyenikayit.addClickListener(buttonClickEvent -> {


                    Kullanici kullanici = new Kullanici();
                   kullanici.setIsim(textisim.getValue());
                   kullanici.setMail(textmail.getValue());
                   kullanici.setSifre(textsifre.getValue());
                   kullanici.setOda(select.getLabel());


                   kullaniciServis.save(kullanici);
                   UI.getCurrent().getPage().setLocation("/");


               });




        buttonanonim.addClickListener(buttonClickEvent -> {

            UI.getCurrent().getPage().setLocation("/tanim");

        });

        buttoncikis.addClickListener(buttonClickEvent -> {
            List<Kullanici> kullaniciListe = new ArrayList<>();
            kullaniciListe.addAll(kullaniciServis.getlist());
            for (int i=0;i<100;i++){
                if (kullaniciListe.get(i).getIsim().equals("True")){
                    kullaniciListe.get(i).setIsim("False");
                    kullaniciServis.save(kullaniciListe.get(i));
                    Notification.show("ÇIKIŞ YAPILIYOR..."); // en son ekledik
                    UI.getCurrent().getPage().setLocation("/");
                    }



        }});

        List<Kullanici> kullaniciList = new ArrayList<>();
        kullaniciList.addAll(kullaniciServis.getlist());

        Grid<Kullanici> grid = new Grid<>(Kullanici.class);
        grid.setItems(kullaniciList);

        grid.removeColumnByKey("id");
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setColumns("isim","oda","mail","sifre");

        /*grid.addItemDoubleClickListener(event ->{
            String x = event.getItem().getIsim().toString();
            UI.getCurrent().getPage().setLocation(x);
        });*/



        //List<Kullanici> personList = new ArrayList<>();

        //personList.add(new Kullanici(1l, "İstanbul", "mertcakar@icloud.com", 12345));
        //personList.add(new Kullanici(2l,"İstanbul","berkayulguel@icloud.com",12345));


        //Grid<Kullanici> grid = new Grid<>(Kullanici.class);
        //grid.setItems(personList);

        //grid.removeColumnByKey("id");


        //grid.setColumns("oda","mail","sifre");
        add(new H1(label),formLayout,horizontalLayout,buttonanonim,new H1(label2),horizontalLayout2,buttonekle,buttoncikis);


        buttonekle.addClickListener(buttonClickEvent -> {
            List<Kullanici> kullaniciListe = new ArrayList<>();
            kullaniciListe.addAll(kullaniciServis.getlist());
            for (int i=0;i<100;i++){
                if (kullaniciListe.get(i).getIsim().equals("True")){
                    System.out.println(kullaniciListe.get(i).getIsim());
                    String x = kullaniciListe.get(i).getOda();


                    Dialog dialog = new Dialog();
                    dialog.setModal(true);

                    if (x.equals("İstanbul")){
                        //TextField textField = new TextField("Avcılar,Florya,Bahçeşehir");
                        Select<String> selectt = new Select<>("The List");
                        selectt.setLabel("İstanbul'dan Bir Bölge Seçimiz Yapınız..");
                        selectt.setItems("Fatih","Beyoğlu","Avcılar","Bahçeşehir","Florya","Şişli","Maltepe","Üsküdar");
                        selectt.addValueChangeListener(event ->{
                            String selected = event.getValue();
                            selectt.setLabel(selected);
                        });

                        TextField textField1 = new TextField("Eczane ismi giriniz..");
                        TextField textField2 = new TextField("Eczacı adı soyadı giriniz..");
                        TextField textField3 = new TextField("İl giriniz..");
                        TextField textField4 = new TextField("İlçe giriniz..");
                        TextField textField5 = new TextField("Mahalle giriniz..");
                        TextField textField6 = new TextField("Açık adres giriniz..");
                        TextField textField7 = new TextField("Telefon giriniz..");
                        TextField textField8 = new TextField("Enlem ve boylam giriniz..");
                        DatePicker datePicker = new DatePicker();
                        datePicker.setLabel("Tarih seçiniz..");
                        datePicker.addValueChangeListener(event ->{
                            String tarih = event.getValue().toString();
                            datePicker.setLabel(tarih);
                        });
                        FormLayout formLayout1 = new FormLayout();
                        formLayout1.add(selectt,textField1,textField2,textField3,textField4,textField5,textField6,textField7,textField8,datePicker);
                        Button buttonkaydet = new Button("KAYDET");
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            Eczane eczane = new Eczane();
                            eczane.setKonum(selectt.getLabel());
                            eczane.setIsim(textField1.getValue());
                            eczane.setAdsoyad(textField2.getValue());
                            eczane.setIl(textField3.getValue());
                            eczane.setIlce(textField4.getValue());
                            eczane.setMahalle(textField5.getValue());
                            eczane.setAdres(textField6.getValue());
                            eczane.setTelefon(textField7.getValue());
                            eczane.setEnlemboylam(textField8.getValue());
                            eczane.setTarih(datePicker.getLabel());

                            eczaneServis.save(eczane);
                            Notification.show("ECZANE KAYDI BAŞARILI");

                        });
                        ProgressBar progressBar = new ProgressBar();
                        progressBar.setIndeterminate(true);

                        add(progressBar);
                        dialog.add(formLayout1,buttonkaydet,progressBar);
                        dialog.open();
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            System.out.println("Tıklandı..");
                        });
                    }
                    if (x.equals("Edirne")){
                        //TextField textField = new TextField("Avcılar,Florya,Bahçeşehir");
                        Select<String> selectt = new Select<>("The List");
                        selectt.setLabel("Edirne'den Bir Bölge Seçimiz Yapınız..");
                        selectt.setItems("Lalapaşa","Meriç","Merkez","Uzunköprü","Süloğlu","Havsa");
                        selectt.addValueChangeListener(event ->{
                            String selected = event.getValue();
                            selectt.setLabel(selected);
                        });

                        TextField textField1 = new TextField("Eczane ismi giriniz..");
                        TextField textField2 = new TextField("Eczacı adı soyadı giriniz..");
                        TextField textField3 = new TextField("İl giriniz..");
                        TextField textField4 = new TextField("İlçe giriniz..");
                        TextField textField5 = new TextField("Mahalle giriniz..");
                        TextField textField6 = new TextField("Açık adres giriniz..");
                        TextField textField7 = new TextField("Telefon giriniz..");
                        TextField textField8 = new TextField("Enlem ve boylam giriniz..");
                        DatePicker datePicker = new DatePicker();
                        datePicker.setLabel("Tarih seçiniz..");
                        datePicker.addValueChangeListener(event ->{
                            String tarih = event.getValue().toString();
                            datePicker.setLabel(tarih);
                        });
                        FormLayout formLayout1 = new FormLayout();
                        formLayout1.add(selectt,textField1,textField2,textField3,textField4,textField5,textField6,textField7,textField8,datePicker);
                        Button buttonkaydet = new Button("KAYDET");
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            Eczane eczane = new Eczane();
                            eczane.setKonum(selectt.getLabel());
                            eczane.setIsim(textField1.getValue());
                            eczane.setAdsoyad(textField2.getValue());
                            eczane.setIl(textField3.getValue());
                            eczane.setIlce(textField4.getValue());
                            eczane.setMahalle(textField5.getValue());
                            eczane.setAdres(textField6.getValue());
                            eczane.setTelefon(textField7.getValue());
                            eczane.setEnlemboylam(textField8.getValue());
                            eczane.setTarih(datePicker.getLabel());

                            eczaneServis.save(eczane);
                            Notification.show("ECZANE KAYDI BAŞARILI");

                        });
                        ProgressBar progressBar = new ProgressBar();
                        progressBar.setIndeterminate(true);

                        add(progressBar);
                        dialog.add(formLayout1,buttonkaydet,progressBar);
                        dialog.open();
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            System.out.println("Tıklandı..");
                        });
                    }
                    if (x.equals("Ankara")){
                        //TextField textField = new TextField("Avcılar,Florya,Bahçeşehir");
                        Select<String> selectt = new Select<>("The List");
                        selectt.setLabel("Ankara'dan Bir Bölge Seçimiz Yapınız..");
                        selectt.setItems("Mamak","Etimesgut","Merkez 1.Bölge","Akyurt","Altındağ","Kalecik");
                        selectt.addValueChangeListener(event ->{
                            String selected = event.getValue();
                            selectt.setLabel(selected);
                        });

                        TextField textField1 = new TextField("Eczane ismi giriniz..");
                        TextField textField2 = new TextField("Eczacı adı soyadı giriniz..");
                        TextField textField3 = new TextField("İl giriniz..");
                        TextField textField4 = new TextField("İlçe giriniz..");
                        TextField textField5 = new TextField("Mahalle giriniz..");
                        TextField textField6 = new TextField("Açık adres giriniz..");
                        TextField textField7 = new TextField("Telefon giriniz..");
                        TextField textField8 = new TextField("Enlem ve boylam giriniz..");
                        DatePicker datePicker = new DatePicker();
                        datePicker.setLabel("Tarih seçiniz..");
                        datePicker.addValueChangeListener(event ->{
                            String tarih = event.getValue().toString();
                            datePicker.setLabel(tarih);
                        });
                        FormLayout formLayout1 = new FormLayout();
                        formLayout1.add(selectt,textField1,textField2,textField3,textField4,textField5,textField6,textField7,textField8,datePicker);
                        Button buttonkaydet = new Button("KAYDET");
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            Eczane eczane = new Eczane();
                            eczane.setKonum(selectt.getLabel());
                            eczane.setIsim(textField1.getValue());
                            eczane.setAdsoyad(textField2.getValue());
                            eczane.setIl(textField3.getValue());
                            eczane.setIlce(textField4.getValue());
                            eczane.setMahalle(textField5.getValue());
                            eczane.setAdres(textField6.getValue());
                            eczane.setTelefon(textField7.getValue());
                            eczane.setEnlemboylam(textField8.getValue());
                            eczane.setTarih(datePicker.getLabel());

                            eczaneServis.save(eczane);
                            Notification.show("ECZANE KAYDI BAŞARILI");

                        });
                        ProgressBar progressBar = new ProgressBar();
                        progressBar.setIndeterminate(true);

                        add(progressBar);
                        dialog.add(formLayout1,buttonkaydet,progressBar);
                        dialog.open();
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            System.out.println("Tıklandı..");
                        });
                    }
                    if (x.equals("Erzincan")){
                        //TextField textField = new TextField("Avcılar,Florya,Bahçeşehir");
                        Select<String> selectt = new Select<>("The List");
                        selectt.setLabel("Erzincan'dan Bir Bölge Seçimiz Yapınız..");
                        selectt.setItems("Kemah","Kemaliye","Merkez","Refahiye","Tercan","Çayırlı");
                        selectt.addValueChangeListener(event ->{
                            String selected = event.getValue();
                            selectt.setLabel(selected);
                        });

                        TextField textField1 = new TextField("Eczane ismi giriniz..");
                        TextField textField2 = new TextField("Eczacı adı soyadı giriniz..");
                        TextField textField3 = new TextField("İl giriniz..");
                        TextField textField4 = new TextField("İlçe giriniz..");
                        TextField textField5 = new TextField("Mahalle giriniz..");
                        TextField textField6 = new TextField("Açık adres giriniz..");
                        TextField textField7 = new TextField("Telefon giriniz..");
                        TextField textField8 = new TextField("Enlem ve boylam giriniz..");
                        DatePicker datePicker = new DatePicker();
                        datePicker.setLabel("Tarih seçiniz..");
                        datePicker.addValueChangeListener(event ->{
                            String tarih = event.getValue().toString();
                            datePicker.setLabel(tarih);
                        });
                        FormLayout formLayout1 = new FormLayout();
                        formLayout1.add(selectt,textField1,textField2,textField3,textField4,textField5,textField6,textField7,textField8,datePicker);
                        Button buttonkaydet = new Button("KAYDET");
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            Eczane eczane = new Eczane();
                            eczane.setKonum(selectt.getLabel());
                            eczane.setIsim(textField1.getValue());
                            eczane.setAdsoyad(textField2.getValue());
                            eczane.setIl(textField3.getValue());
                            eczane.setIlce(textField4.getValue());
                            eczane.setMahalle(textField5.getValue());
                            eczane.setAdres(textField6.getValue());
                            eczane.setTelefon(textField7.getValue());
                            eczane.setEnlemboylam(textField8.getValue());
                            eczane.setTarih(datePicker.getLabel());

                            eczaneServis.save(eczane);
                            Notification.show("ECZANE KAYDI BAŞARILI");

                        });
                        ProgressBar progressBar = new ProgressBar();
                        progressBar.setIndeterminate(true);

                        add(progressBar);
                        dialog.add(formLayout1,buttonkaydet,progressBar);
                        dialog.open();
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            System.out.println("Tıklandı..");
                        });
                    }
                    if (x.equals("Erzurum")){
                        //TextField textField = new TextField("Avcılar,Florya,Bahçeşehir");
                        Select<String> selectt = new Select<>("The List");
                        selectt.setLabel("Erzurum'dan Bir Bölge Seçimiz Yapınız..");
                        selectt.setItems("Aşkale","Çat","İspir","Horasan","Uzundere","Yakutiye");
                        selectt.addValueChangeListener(event ->{
                            String selected = event.getValue();
                            selectt.setLabel(selected);
                        });

                        TextField textField1 = new TextField("Eczane ismi giriniz..");
                        TextField textField2 = new TextField("Eczacı adı soyadı giriniz..");
                        TextField textField3 = new TextField("İl giriniz..");
                        TextField textField4 = new TextField("İlçe giriniz..");
                        TextField textField5 = new TextField("Mahalle giriniz..");
                        TextField textField6 = new TextField("Açık adres giriniz..");
                        TextField textField7 = new TextField("Telefon giriniz..");
                        TextField textField8 = new TextField("Enlem ve boylam giriniz..");
                        DatePicker datePicker = new DatePicker();
                        datePicker.setLabel("Tarih seçiniz..");
                        datePicker.addValueChangeListener(event ->{
                            String tarih = event.getValue().toString();
                            datePicker.setLabel(tarih);
                        });
                        FormLayout formLayout1 = new FormLayout();
                        formLayout1.add(selectt,textField1,textField2,textField3,textField4,textField5,textField6,textField7,textField8,datePicker);
                        Button buttonkaydet = new Button("KAYDET");
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            Eczane eczane = new Eczane();
                            eczane.setKonum(selectt.getLabel());
                            eczane.setIsim(textField1.getValue());
                            eczane.setAdsoyad(textField2.getValue());
                            eczane.setIl(textField3.getValue());
                            eczane.setIlce(textField4.getValue());
                            eczane.setMahalle(textField5.getValue());
                            eczane.setAdres(textField6.getValue());
                            eczane.setTelefon(textField7.getValue());
                            eczane.setEnlemboylam(textField8.getValue());
                            eczane.setTarih(datePicker.getLabel());

                            eczaneServis.save(eczane);
                            Notification.show("ECZANE KAYDI BAŞARILI");

                        });
                        ProgressBar progressBar = new ProgressBar();
                        progressBar.setIndeterminate(true);

                        add(progressBar);
                        dialog.add(formLayout1,buttonkaydet,progressBar);
                        dialog.open();
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            System.out.println("Tıklandı..");
                        });
                    }

                    if (x.equals("Şanlıurfa")){
                        //TextField textField = new TextField("Avcılar,Florya,Bahçeşehir");
                        Select<String> selectt = new Select<>("The List");
                        selectt.setLabel("Şanlıurfa'dan Bir Bölge Seçimiz Yapınız..");
                        selectt.setItems("Akçakale","Birecik","Bozova","Halfeti","Harran","Eyyübiye");
                        selectt.addValueChangeListener(event ->{
                            String selected = event.getValue();
                            selectt.setLabel(selected);
                        });

                        TextField textField1 = new TextField("Eczane ismi giriniz..");
                        TextField textField2 = new TextField("Eczacı adı soyadı giriniz..");
                        TextField textField3 = new TextField("İl giriniz..");
                        TextField textField4 = new TextField("İlçe giriniz..");
                        TextField textField5 = new TextField("Mahalle giriniz..");
                        TextField textField6 = new TextField("Açık adres giriniz..");
                        TextField textField7 = new TextField("Telefon giriniz..");
                        TextField textField8 = new TextField("Enlem ve boylam giriniz..");
                        DatePicker datePicker = new DatePicker();
                        datePicker.setLabel("Tarih seçiniz..");
                        datePicker.addValueChangeListener(event ->{
                            String tarih = event.getValue().toString();
                            datePicker.setLabel(tarih);
                        });
                        FormLayout formLayout1 = new FormLayout();
                        formLayout1.add(selectt,textField1,textField2,textField3,textField4,textField5,textField6,textField7,textField8,datePicker);
                        Button buttonkaydet = new Button("KAYDET");
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            Eczane eczane = new Eczane();
                            eczane.setKonum(selectt.getLabel());
                            eczane.setIsim(textField1.getValue());
                            eczane.setAdsoyad(textField2.getValue());
                            eczane.setIl(textField3.getValue());
                            eczane.setIlce(textField4.getValue());
                            eczane.setMahalle(textField5.getValue());
                            eczane.setAdres(textField6.getValue());
                            eczane.setTelefon(textField7.getValue());
                            eczane.setEnlemboylam(textField8.getValue());
                            eczane.setTarih(datePicker.getLabel());

                            eczaneServis.save(eczane);
                            Notification.show("ECZANE KAYDI BAŞARILI");

                        });
                        ProgressBar progressBar = new ProgressBar();
                        progressBar.setIndeterminate(true);

                        add(progressBar);
                        dialog.add(formLayout1,buttonkaydet,progressBar);
                        dialog.open();
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            System.out.println("Tıklandı..");
                        });
                    }

                    if (x.equals("Konya")){
                        //TextField textField = new TextField("Avcılar,Florya,Bahçeşehir");
                        Select<String> selectt = new Select<>("The List");
                        selectt.setLabel("Konya'dan Bir Bölge Seçimiz Yapınız..");
                        selectt.setItems("Selçuklu","Beyşehir","Bozkır","Çeltik","Ahırlı","Akören");
                        selectt.addValueChangeListener(event ->{
                            String selected = event.getValue();
                            selectt.setLabel(selected);
                        });

                        TextField textField1 = new TextField("Eczane ismi giriniz..");
                        TextField textField2 = new TextField("Eczacı adı soyadı giriniz..");
                        TextField textField3 = new TextField("İl giriniz..");
                        TextField textField4 = new TextField("İlçe giriniz..");
                        TextField textField5 = new TextField("Mahalle giriniz..");
                        TextField textField6 = new TextField("Açık adres giriniz..");
                        TextField textField7 = new TextField("Telefon giriniz..");
                        TextField textField8 = new TextField("Enlem ve boylam giriniz..");
                        DatePicker datePicker = new DatePicker();
                        datePicker.setLabel("Tarih seçiniz..");
                        datePicker.addValueChangeListener(event ->{
                            String tarih = event.getValue().toString();
                            datePicker.setLabel(tarih);
                        });
                        FormLayout formLayout1 = new FormLayout();
                        formLayout1.add(selectt,textField1,textField2,textField3,textField4,textField5,textField6,textField7,textField8,datePicker);
                        Button buttonkaydet = new Button("KAYDET");
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            Eczane eczane = new Eczane();
                            eczane.setKonum(selectt.getLabel());
                            eczane.setIsim(textField1.getValue());
                            eczane.setAdsoyad(textField2.getValue());
                            eczane.setIl(textField3.getValue());
                            eczane.setIlce(textField4.getValue());
                            eczane.setMahalle(textField5.getValue());
                            eczane.setAdres(textField6.getValue());
                            eczane.setTelefon(textField7.getValue());
                            eczane.setEnlemboylam(textField8.getValue());
                            eczane.setTarih(datePicker.getLabel());

                            eczaneServis.save(eczane);
                            Notification.show("ECZANE KAYDI BAŞARILI");

                        });
                        ProgressBar progressBar = new ProgressBar();
                        progressBar.setIndeterminate(true);

                        add(progressBar);
                        dialog.add(formLayout1,buttonkaydet,progressBar);
                        dialog.open();
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            System.out.println("Tıklandı..");
                        });
                    }

                    if (x.equals("Samsun")){
                        //TextField textField = new TextField("Avcılar,Florya,Bahçeşehir");
                        Select<String> selectt = new Select<>("The List");
                        selectt.setLabel("Samsun'dan Bir Bölge Seçimiz Yapınız..");
                        selectt.setItems("Bafta","Çarşamba","Kavak","Tekkeköy","Vezirköprü","Alaçam");
                        selectt.addValueChangeListener(event ->{
                            String selected = event.getValue();
                            selectt.setLabel(selected);
                        });

                        TextField textField1 = new TextField("Eczane ismi giriniz..");
                        TextField textField2 = new TextField("Eczacı adı soyadı giriniz..");
                        TextField textField3 = new TextField("İl giriniz..");
                        TextField textField4 = new TextField("İlçe giriniz..");
                        TextField textField5 = new TextField("Mahalle giriniz..");
                        TextField textField6 = new TextField("Açık adres giriniz..");
                        TextField textField7 = new TextField("Telefon giriniz..");
                        TextField textField8 = new TextField("Enlem ve boylam giriniz..");
                        DatePicker datePicker = new DatePicker();
                        datePicker.setLabel("Tarih seçiniz..");
                        datePicker.addValueChangeListener(event ->{
                            String tarih = event.getValue().toString();
                            datePicker.setLabel(tarih);
                        });
                        FormLayout formLayout1 = new FormLayout();
                        formLayout1.add(selectt,textField1,textField2,textField3,textField4,textField5,textField6,textField7,textField8,datePicker);
                        Button buttonkaydet = new Button("KAYDET");
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            Eczane eczane = new Eczane();
                            eczane.setKonum(selectt.getLabel());
                            eczane.setIsim(textField1.getValue());
                            eczane.setAdsoyad(textField2.getValue());
                            eczane.setIl(textField3.getValue());
                            eczane.setIlce(textField4.getValue());
                            eczane.setMahalle(textField5.getValue());
                            eczane.setAdres(textField6.getValue());
                            eczane.setTelefon(textField7.getValue());
                            eczane.setEnlemboylam(textField8.getValue());
                            eczane.setTarih(datePicker.getLabel());

                            eczaneServis.save(eczane);
                            Notification.show("ECZANE KAYDI BAŞARILI");

                        });
                        ProgressBar progressBar = new ProgressBar();
                        progressBar.setIndeterminate(true);

                        add(progressBar);
                        dialog.add(formLayout1,buttonkaydet,progressBar);
                        dialog.open();
                        buttonkaydet.addClickListener(buttonClickEvent1 -> {
                            System.out.println("Tıklandı..");
                        });
                    }


                }
            }
        });





    }




}
