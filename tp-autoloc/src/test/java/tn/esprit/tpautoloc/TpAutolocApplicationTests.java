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
import tn.esprit.tpautoloc.service.IAgenceService;
import tn.esprit.tpautoloc.service.IClientService;
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

    @Autowired
    private IAgenceService agenceService;

    @Autowired
    private IClientService clientService;

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

    @Test
    void testAgenceService() {

        // 1. Créer une instance d'Agence
        Agence agence = new Agence();
        agence.setNom("Agence Sousse Centre");
        agence.setVille("Sousse");
        agence.setAdresse("Avenue Habib Thameur");
        agence.setTelephone("73220000");

        // 2. Enregistrer l'agence
        Agence agenceSaved = agenceService.create(agence);

        // 3. Afficher l'objet retourné
        System.out.println("Agence enregistrée : " + agenceSaved);
        System.out.println("ID : " + agenceSaved.getIdAgence());
        System.out.println("Nom : " + agenceSaved.getNom());
        System.out.println("Ville : " + agenceSaved.getVille());

        assertNotNull(agenceSaved);
        assertNotNull(agenceSaved.getIdAgence());
        assertEquals("Agence Sousse Centre", agenceSaved.getNom());

        // Récupérer l'ID généré
        Long id = agenceSaved.getIdAgence();

        // 4. Afficher toutes les agences avec findAll()
        List<Agence> agences = agenceService.findAll();

        System.out.println("\nToutes les agences :");
        agences.forEach(a -> System.out.println("  - " + a.getNom() + " (" + a.getVille() + ")"));

        assertTrue(agences.size() >= 1);

        // 5. Récupérer une agence avec findById()
        Agence agenceRecuperee = agenceService.findById(id);

        System.out.println("\nAgence récupérée par ID:");
        System.out.println("  ID : " + agenceRecuperee.getIdAgence());
        System.out.println("  Nom : " + agenceRecuperee.getNom());
        System.out.println("  Ville : " + agenceRecuperee.getVille());
        System.out.println("  Adresse : " + agenceRecuperee.getAdresse());

        assertNotNull(agenceRecuperee);
        assertEquals(id, agenceRecuperee.getIdAgence());
        assertEquals("Agence Sousse Centre", agenceRecuperee.getNom());

        // 6. Afficher le nombre d'agences avec count()
        long nombreAgences = agenceRepository.count();

        System.out.println("\nNombre total d'agences : " + nombreAgences);

        assertTrue(nombreAgences >= 1);

        // 7. Supprimer l'agence avec deleteById()
        agenceService.deleteById(id);

        System.out.println("\nAgence supprimée avec l'ID : " + id);

        // 8. Vérifier qu'elle n'existe plus
        boolean existeApresSuppression = agenceRepository.existsById(id);

        System.out.println("L'agence existe après suppression : " + existeApresSuppression);

        assertFalse(existeApresSuppression);
    }

    @Test
    void testClientService() {

        // 1. Créer deux clients
        Client client1 = new Client();
        client1.setNom("Hamdouni");
        client1.setPrenom("Sami");
        client1.setEmail("sami.gharbi@email.com");
        client1.setTelephone("20123456");
        client1.setNumPermis("P987654");
        client1.setDateInscription(LocalDate.now());

        Client client2 = new Client();
        client2.setNom("Khelifi");
        client2.setPrenom("Nadia");
        client2.setEmail("nadia.khelifi@email.com");
        client2.setTelephone("22345678");
        client2.setNumPermis("P456789");
        client2.setDateInscription(LocalDate.now());

        // 2. Enregistrer les deux clients
        Client client1Saved = clientService.create(client1);
        Client client2Saved = clientService.create(client2);

        System.out.println("Client 1 enregistré : " + client1Saved);
        System.out.println("  ID : " + client1Saved.getIdClient());
        System.out.println("  Nom : " + client1Saved.getNom() + " " + client1Saved.getPrenom());
        System.out.println("  Email : " + client1Saved.getEmail());

        System.out.println("\nClient 2 enregistré : " + client2Saved);
        System.out.println("  ID : " + client2Saved.getIdClient());
        System.out.println("  Nom : " + client2Saved.getNom() + " " + client2Saved.getPrenom());
        System.out.println("  Email : " + client2Saved.getEmail());

        assertNotNull(client1Saved);
        assertNotNull(client1Saved.getIdClient());
        assertNotNull(client2Saved);
        assertNotNull(client2Saved.getIdClient());

        // Récupérer les IDs générés
        Long id1 = client1Saved.getIdClient();
        Long id2 = client2Saved.getIdClient();

        // 3. Afficher la liste avec findAll()
        List<Client> clients = clientService.findAll();

        System.out.println("\nTous les clients :");
        clients.forEach(c -> System.out.println("  - " + c.getNom() + " " + c.getPrenom() + " (" + c.getEmail() + ")"));

        assertTrue(clients.size() >= 2);

        // 4. Rechercher un client avec findById()
        Client clientRecupere = clientService.findById(id1);

        System.out.println("\nClient récupéré par ID:");
        System.out.println("  ID : " + clientRecupere.getIdClient());
        System.out.println("  Nom : " + clientRecupere.getNom());
        System.out.println("  Prénom : " + clientRecupere.getPrenom());
        System.out.println("  Email : " + clientRecupere.getEmail());
        System.out.println("  Téléphone : " + clientRecupere.getTelephone());
        System.out.println("  Numéro de permis : " + clientRecupere.getNumPermis());

        assertNotNull(clientRecupere);
        assertEquals(id1, clientRecupere.getIdClient());
        assertEquals("Gharbi", clientRecupere.getNom());

        // 5. Afficher le nombre de clients avec count()
        long nombreClients = clientRepository.count();

        System.out.println("\nNombre total de clients : " + nombreClients);

        assertTrue(nombreClients >= 2);

        // 6. Supprimer les clients avec deleteById()
        clientService.deleteById(id1);
        clientService.deleteById(id2);

        System.out.println("\nClients supprimés avec les IDs : " + id1 + " et " + id2);

        // 7. Vérifier qu'ils n'existent plus
        boolean existe1ApresSuppression = clientRepository.existsById(id1);
        boolean existe2ApresSuppression = clientRepository.existsById(id2);

        System.out.println("Le client 1 existe après suppression : " + existe1ApresSuppression);
        System.out.println("Le client 2 existe après suppression : " + existe2ApresSuppression);

        assertFalse(existe1ApresSuppression);
        assertFalse(existe2ApresSuppression);
    }
}
