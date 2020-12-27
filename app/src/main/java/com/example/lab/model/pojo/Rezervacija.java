package com.example.lab.model.pojo;

public class Rezervacija {

    String imeParking;
    String imeKorisnik;
    String imeGrad;
    String timeslot;


    public Rezervacija() {
    }


    public String getImeParking() {
        return imeParking;
    }

    public void setImeParking(String imeParking) {
        this.imeParking = imeParking;
    }

    public String getImeKorisnik() {
        return imeKorisnik;
    }

    public void setImeKorisnik(String imeKorisnik) {
        this.imeKorisnik = imeKorisnik;
    }

    public String getImeGrad() {
        return imeGrad;
    }

    public void setImeGrad(String imeGrad) {
        this.imeGrad = imeGrad;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }
}
