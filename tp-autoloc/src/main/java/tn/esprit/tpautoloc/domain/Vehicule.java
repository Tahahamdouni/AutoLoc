package tn.esprit.tpautoloc.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.tpautoloc.domain.enums.CategorieVehicule;
import tn.esprit.tpautoloc.domain.enums.StatutVehicule;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vehicule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;
    private String immatriculation;
    private String marque;
    private String modele;
    private CategorieVehicule categorie;
    private BigDecimal tarifJournalier;
    private StatutVehicule statut;

    @ManyToOne
    @JsonBackReference("agence-vehicules")
    @JoinColumn(name = "id_agence")
    private Agence agence;


    @OneToMany(mappedBy = "vehicule")
    @JsonManagedReference("vehicule-reservations")
    private List<Reservation> reservations;


    @OneToMany(mappedBy = "vehicule")
    @JsonManagedReference("vehicule-maintenances")
    private List<Maintenance> maintenances;

    @ManyToMany
    @JsonBackReference("equipements-vehicules")
    @JoinTable(
            name = "vehicule_equipements",
            joinColumns = @JoinColumn(name = "id_vehicule"),
            inverseJoinColumns = @JoinColumn(name = "id_equipement")
    )
    private List<Equipement> equipements;
}

