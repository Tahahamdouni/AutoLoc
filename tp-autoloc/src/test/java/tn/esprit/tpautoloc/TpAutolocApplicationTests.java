package tn.esprit.tpautoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpautoloc.domain.Agence;
import tn.esprit.tpautoloc.domain.Client;
import tn.esprit.tpautoloc.domain.Vehicule;
import tn.esprit.tpautoloc.domain.enums.CategorieVehicule;
import tn.esprit.tpautoloc.domain.enums.StatutVehicule;
import tn.esprit.tpautoloc.repository.IAgenceRepository;
import tn.esprit.tpautoloc.repository.IClientRepository;
import tn.esprit.tpautoloc.repository.IVehiculeRepository;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class TpAutolocApplicationTests {

    @Autowired
    private IAgenceRepository agenceRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IVehiculeRepository vehiculeRepository;

    @Test
    void contextLoads() {

    }

    @Test
    void testAgenceRepository() {

        // 1. Créer une instance d'Agence
        Agence agence = new Agence();

        agence.setNom("Agence Tunis Centre");
        agence.setVille("Tunis");
        agence.setAdresse("Avenue Habib Bourguiba");
        agence.setTelephone("71123456");

        // 2. Enregistrer l'agence avec save()
        Agence agenceSaved = agenceRepository.save(agence);

        // 3. Afficher l'objet retourné par save()
        System.out.println("Agence enregistrée : " + agenceSaved);

        // Récupérer l'ID généré
        Long id = agenceSaved.getIdAgence();

        System.out.println("ID de l'agence : " + id);

        // 4. Afficher toutes les agences avec findAll()
        List<Agence> agences = agenceRepository.findAll();

        System.out.println("Toutes les agences :");
        agences.forEach(System.out::println);

        // 5. Récupérer une agence avec findById()
        Optional<Agence> agenceById = agenceRepository.findById(id);

        System.out.println("Agence récupérée : " + agenceById);

        // Vérification
        assertTrue(agenceById.isPresent());

        // 6. Vérifier l'existence avec existsById()
        boolean existe = agenceRepository.existsById(id);

        System.out.println("L'agence existe : " + existe);

        assertTrue(existe);

        // 7. Afficher le nombre d'agences avec count()
        long nombreAgences = agenceRepository.count();

        System.out.println("Nombre d'agences : " + nombreAgences);

        assertTrue(nombreAgences > 0);

        // 8. Supprimer l'agence avec deleteById()
        agenceRepository.deleteById(id);

        // 9. Vérifier qu'elle n'existe plus
        boolean existeApresSuppression =
                agenceRepository.existsById(id);

        System.out.println(
                "L'agence existe après suppression : "
                        + existeApresSuppression
        );

        assertFalse(existeApresSuppression);
    }

    @Test
    void testClientRepository() {

        // 1. Créer deux clients
        Client client1 = new Client();
        client1.setNom("Ben Ali");
        client1.setPrenom("Mohamed");
        client1.setEmail("mohamed.benali@email.com");
        client1.setTelephone("98123456");
        client1.setNumPermis("P123456");
        client1.setDateInscription(LocalDate.now());

        Client client2 = new Client();
        client2.setNom("Trabelsi");
        client2.setPrenom("Fatma");
        client2.setEmail("fatma.trabelsi@email.com");
        client2.setTelephone("97654321");
        client2.setNumPermis("P654321");
        client2.setDateInscription(LocalDate.now());

        // 2. Enregistrer les deux clients avec save()
        Client client1Saved = clientRepository.save(client1);
        Client client2Saved = clientRepository.save(client2);

        System.out.println("Client 1 enregistré : " + client1Saved);
        System.out.println("Client 2 enregistré : " + client2Saved);

        // Récupérer les IDs générés
        Long id1 = client1Saved.getIdClient();
        Long id2 = client2Saved.getIdClient();

        System.out.println("ID du client 1 : " + id1);
        System.out.println("ID du client 2 : " + id2);

        // 3. Afficher la liste avec findAll()
        List<Client> clients = clientRepository.findAll();

        System.out.println("Tous les clients :");
        clients.forEach(System.out::println);

        assertTrue(clients.size() >= 2);

        // 4. Rechercher un client avec findById()
        Optional<Client> clientById = clientRepository.findById(id1);

        System.out.println("Client récupéré : " + clientById);

        assertTrue(clientById.isPresent());
        assertEquals("Ben Ali", clientById.get().getNom());

        // 5. Tester existsById() avec un identifiant existant
        boolean existeId1 = clientRepository.existsById(id1);

        System.out.println("Le client avec l'ID " + id1 + " existe : " + existeId1);

        assertTrue(existeId1);

        // 6. Tester existsById() avec un identifiant inexistant
        boolean existeIdInexistant = clientRepository.existsById(999999L);

        System.out.println("Le client avec l'ID 999999 existe : " + existeIdInexistant);

        assertFalse(existeIdInexistant);

        // 7. Afficher le nombre de clients avec count()
        long nombreClients = clientRepository.count();

        System.out.println("Nombre de clients : " + nombreClients);

        assertTrue(nombreClients >= 2);
    }

    @Test
    void testVehiculeRepository() {

        // 1. Créer un véhicule
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("123TUN456");
        vehicule.setMarque("Renault");
        vehicule.setModele("Clio");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("80.00"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        // 2. Enregistrer le véhicule avec save()
        Vehicule vehiculeSaved = vehiculeRepository.save(vehicule);

        System.out.println("Véhicule enregistré : " + vehiculeSaved);

        // Récupérer l'ID généré
        Long id = vehiculeSaved.getIdVehicule();

        System.out.println("ID du véhicule : " + id);

        // 3. Afficher tous les véhicules avec findAll()
        List<Vehicule> vehicules = vehiculeRepository.findAll();

        System.out.println("Tous les véhicules :");
        vehicules.forEach(System.out::println);

        assertTrue(vehicules.size() >= 1);

        // 4. Afficher le nombre de véhicules avec count()
        long nombreVehicules = vehiculeRepository.count();

        System.out.println("Nombre de véhicules : " + nombreVehicules);

        assertTrue(nombreVehicules >= 1);

        // 5. Modifier une propriété du véhicule
        vehiculeSaved.setStatut(StatutVehicule.LOUE);
        vehiculeSaved.setTarifJournalier(new BigDecimal("90.00"));

        // Enregistrer la modification avec save()
        Vehicule vehiculeModifie = vehiculeRepository.save(vehiculeSaved);

        System.out.println("Véhicule modifié : " + vehiculeModifie);
        System.out.println("Nouveau statut : " + vehiculeModifie.getStatut());
        System.out.println("Nouveau tarif : " + vehiculeModifie.getTarifJournalier());

        assertEquals(StatutVehicule.LOUE, vehiculeModifie.getStatut());
        assertEquals(new BigDecimal("90.00"), vehiculeModifie.getTarifJournalier());

        // 6. Supprimer le véhicule avec deleteById()
        vehiculeRepository.deleteById(id);

        System.out.println("Véhicule supprimé");

        // Vérifier que le véhicule n'existe plus
        boolean existeApresSuppression = vehiculeRepository.existsById(id);

        System.out.println("Le véhicule existe après suppression : " + existeApresSuppression);

        assertFalse(existeApresSuppression);
    }
}
