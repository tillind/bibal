package com.miage.bibal.ressource;

import com.miage.bibal.api.GestionAPI;
import com.miage.bibal.api.OeuvreManagement;
import com.miage.bibal.api.UserManagementAPI;
import com.miage.bibal.entity.Auteur;
import com.miage.bibal.entity.Emprunt;
import com.miage.bibal.entity.Exemplaire;
import com.miage.bibal.entity.Oeuvre;
import com.miage.bibal.entity.Reservation;
import com.miage.bibal.entity.Usager;
import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author alex
 */
public class EntityToRessource {
    public static Resources<Resource<Oeuvre>> oeuvreToRessource(Iterable<Oeuvre> oeuvres){
        Link selfLink = linkTo(methodOn(OeuvreManagement.class).getAllOeuvre()).withSelfRel();
        List<Resource<Oeuvre>> usagerRessources = new ArrayList<>();
        oeuvres.forEach(usager -> 
                usagerRessources.add(oeuvreToRessource(usager, false)));
        
        return new Resources<>(usagerRessources,selfLink);
    }
    public static Resource<Oeuvre> oeuvreToRessource (Oeuvre oeuvre, Boolean collection){
        Link selfLink = linkTo(UserManagementAPI.class).slash(oeuvre.getIdOeuvre()).withSelfRel();
        if(collection){
            Link collectionLink = linkTo(methodOn(OeuvreManagement.class).getAllOeuvre()).withRel("collection");
            return  new Resource<>(oeuvre, selfLink,collectionLink);
        }else{
            return new Resource<>(oeuvre,selfLink);
        }       
    }
    public static Resources<Resource<Auteur>> auteurToRessource(Iterable<Auteur> auteurs){
        Link selfLink = linkTo(methodOn(OeuvreManagement.class).getAllAuteur()).withSelfRel();
        List<Resource<Auteur>> oeuvreRessources = new ArrayList<>();
        auteurs.forEach(auteur -> 
                oeuvreRessources.add(auteurToRessource(auteur, false)));
        
        return new Resources<>(oeuvreRessources,selfLink);
    }
    public static Resource<Auteur> auteurToRessource (Auteur auteur, Boolean collection){
        Link selfLink = linkTo(UserManagementAPI.class).slash(auteur.getIdAuteur()).withSelfRel();
        if(collection){
            Link collectionLink = linkTo(methodOn(UserManagementAPI.class).getAllUsager()).withRel("collection");
            return  new Resource<>(auteur, selfLink,collectionLink);
        }else{
            return new Resource<>(auteur,selfLink);
        }       
    }
    
    public static Resources<Resource<Reservation>> reservationToRessource(Iterable<Reservation> reservations){
        Link selfLink = linkTo(methodOn(GestionAPI.class).getAllReservation()).withSelfRel();
        List<Resource<Reservation>> reservationRessources = new ArrayList<>();
        reservations.forEach(reservation -> 
                reservationRessources.add(reservationToRessource(reservation, false)));
        
        return new Resources<>(reservationRessources,selfLink);
    }
    public static Resource<Reservation> reservationToRessource (Reservation reservation, Boolean collection){
        Link selfLink = linkTo(GestionAPI.class).slash(reservation.getID()).withSelfRel();
        if(collection){
            Link collectionLink = linkTo(methodOn(UserManagementAPI.class).getAllUsager()).withRel("collection");
            return  new Resource<>(reservation, selfLink,collectionLink);
        }else{
            return new Resource<>(reservation,selfLink);
        }       
    }
    
    public static Resources<Resource<Emprunt>> empruntToRessource(Iterable<Emprunt> emprunts){
        Link selfLink = linkTo(methodOn(GestionAPI.class).getAllEmprunt()).withSelfRel();
        List<Resource<Emprunt>> empruntRessources = new ArrayList<>();
        emprunts.forEach(emprunt -> 
                empruntRessources.add(empruntToRessource(emprunt, false)));
        
        return new Resources<>(empruntRessources,selfLink);
    }
    public static Resource<Emprunt> empruntToRessource (Emprunt emprunt, Boolean collection){
        Link selfLink = linkTo(GestionAPI.class).slash(emprunt.getID()).withSelfRel();
        if(collection){
            Link collectionLink = linkTo(methodOn(UserManagementAPI.class).getAllUsager()).withRel("collection");
            return  new Resource<>(emprunt, selfLink,collectionLink);
        }else{
            return new Resource<>(emprunt,selfLink);
        }       
    }
}
