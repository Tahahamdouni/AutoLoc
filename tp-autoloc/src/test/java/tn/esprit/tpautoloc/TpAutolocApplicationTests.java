package tn.esprit.tpautoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpautoloc.domain.Agence;
import tn.esprit.tpautoloc.domain.Client;
import tn.esprit.tpautoloc.domain.Contrat;
import tn.esprit.tpautoloc.domain.Reservation;
import tn.esprit.tpautoloc.domain.Vehicule;
import tn.esprit.tpautoloc.domain.enums.CategorieVehicule;
import tn.esprit.tpautoloc.domain.enums.StatuReservation;
import tn.esprit.tpautoloc.domain.enums.StatutVehicule;
import tn.esprit.tpautoloc.repository.*;
import tn.esprit.tpautoloc.service.*;
import tn.esprit.tpautoloc.web.dto.AgenceDTO;
import tn.esprit.tpautoloc.web.dto.ClientDTO;
import tn.esprit.tpautoloc.web.dto.ContratDTO;
import tn.esprit.tpautoloc.web.mapper.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TpAutolocApplicationTests {

    @Autowired private IAgenceRepository agenceRepository;
    @Autowired private IClientRepository clientRepository;
    @Autowired private IVehiculeRepository vehiculeRepository;
    @Autowired private IContratRepository contratRepository;
    @Autowired private IReservationRepository reservationRepository;

    @Autowired private IAgenceService agenceService;
    @Autowired private IClientService clientService;
    @Autowired private IVehiculeService vehiculeService;
    @Autowired private IContratService contratService;
    @Autowired private IReservationService reservationService;

    // Injection des Mappers pour les tests
    @Autowired private AgenceMapper agenceMapper;
    @Autowired private ClientMapper clientMapper;
    @Autowired private VehiculeMapper vehiculeMapper;
    @Autowired private ContratMapper contratMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testAgenceRepository() {
        Agence agence = new Agence();
        agence.setNom("Agence Tunis Centre");
        agence.setVille("Tunis");
        agence.setAdresse("Avenue Habib Bourguiba");
        agence.setTelephone("71123456");

        Agence agenceSaved = agenceRepository.save(agence);
        Long id = agenceSaved.getIdAgence();

        List<Agence> agences = agenceRepository.findAll();
        assertTrue(agences.size() > 0);

        Optional<Agence> agenceById = agenceRepository.findById(id);
        assertTrue(agenceById.isPresent());

        boolean existe = agenceRepository.existsById(id);
        assertTrue(existe);

        agenceRepository.deleteById(id);
        boolean existeApresSuppression = agenceRepository.existsById(id);
        assertFalse(existeApresSuppression);
    }

    @Test
    void testClientRepository() {
        Client client1 = new Client();
        client1.setNom("Ben Ali");
        client1.setPrenom("Mohamed");
        client1.setEmail("mohamed.benali@email.com");
        client1.setTelephone("98123456");
        client1.setNumPermis("P123456");
        client1.setDateInscription(LocalDate.now());

        Client client1Saved = clientRepository.save(client1);
        Long id1 = client1Saved.getIdClient();

        Optional<Client> clientById = clientRepository.findById(id1);
        assertTrue(clientById.isPresent());
        assertEquals("Ben Ali", clientById.get().getNom());

        boolean existeId1 = clientRepository.existsById(id1);
        assertTrue(existeId1);
    }

    @Test
    void testVehiculeRepository() {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("123TUN456");
        vehicule.setMarque("Renault");
        vehicule.setModele("Clio");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("80.00"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule vehiculeSaved = vehiculeRepository.save(vehicule);
        Long id = vehiculeSaved.getIdVehicule();

        vehiculeSaved.setStatut(StatutVehicule.LOUE);
        Vehicule vehiculeModifie = vehiculeRepository.save(vehiculeSaved);
        assertEquals(StatutVehicule.LOUE, vehiculeModifie.getStatut());

        vehiculeRepository.deleteById(id);
        boolean existeApresSuppression = vehiculeRepository.existsById(id);
        assertFalse(existeApresSuppression);
    }

    // ==========================================
    // TESTS DES SERVICES AVEC DTO (Corrigés)
    // ==========================================

    @Test
    void testAgenceService() {
        // 1. Créer le DTO
        AgenceDTO agenceDTO = new AgenceDTO();
        agenceDTO.setNom("Agence Sousse Centre");
        agenceDTO.setVille("Sousse");
        agenceDTO.setAdresse("Avenue Habib Thameur");
        agenceDTO.setTelephone("73220000");

        // 2. Enregistrer via le Service
        AgenceDTO agenceSaved = agenceService.create(agenceDTO);
        assertNotNull(agenceSaved);
        assertNotNull(agenceSaved.getId());
        assertEquals("Agence Sousse Centre", agenceSaved.getNom());

        Long id = agenceSaved.getId();

        // 3. Récupérer via findById
        AgenceDTO agenceRecuperee = agenceService.findById(id);
        assertNotNull(agenceRecuperee);
        assertEquals(id, agenceRecuperee.getId());

        // 4. Supprimer via le service
        agenceService.deleteById(id);
        boolean existeApresSuppression = agenceRepository.existsById(id);
        assertFalse(existeApresSuppression);
    }

    @Test
    void testClientService() {
        // 1. Créer le DTO
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNom("Hamdouni");
        clientDTO.setPrenom("Sami");
        clientDTO.setEmail("sami.gharbi@email.com");
        clientDTO.setTelephone("20123456");
        clientDTO.setNumPermis("P987654");
        clientDTO.setDateInscription(LocalDate.now());

        // 2. Enregistrer via le Service
        ClientDTO clientSaved = clientService.create(clientDTO);
        assertNotNull(clientSaved);
        assertNotNull(clientSaved.getId());

        Long id1 = clientSaved.getId();

        // 3. Récupérer via findById
        ClientDTO clientRecupere = clientService.findById(id1);
        assertNotNull(clientRecupere);
        assertEquals("Hamdouni", clientRecupere.getNom());

        // 4. Supprimer
        clientService.deleteById(id1);
        boolean existeApresSuppression = clientRepository.existsById(id1);
        assertFalse(existeApresSuppression);
    }

    @Test
    void testContratService() {
        // 1. Créer les données de base via les Repositories (plus simple pour ce test)
        Agence agence = new Agence();
        agence.setNom("Agence Sfax");
        agence = agenceRepository.save(agence);

        Vehicule vehicule = new Vehicule();
        vehicule.setMarque("Peugeot");
        vehicule.setImmatriculation("147TU7890");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("45.00"));
        vehicule.setAgence(agence);
        vehicule = vehiculeRepository.save(vehicule);

        Client client = new Client();
        client.setNom("Trabelsi");
        client.setEmail("ahmed.trabelsi@email.com");
        client = clientRepository.save(client);

        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setVehicule(vehicule);
        reservation.setDateDebut(LocalDate.now());
        reservation.setDateFin(LocalDate.now().plusDays(3));
        reservation.setStatut(StatuReservation.CONFIRMEE);
        reservation = reservationRepository.save(reservation);

        // 2. Créer le Contrat via le DTO
        ContratDTO contratDTO = new ContratDTO();
        contratDTO.setReservationId(reservation.getIdReservation());
        contratDTO.setDateSignature(LocalDate.now());
        contratDTO.setMontantTotal(new BigDecimal("135.00"));
        contratDTO.setValide(true);

        // 3. Tester la création
        ContratDTO contratSaved = contratService.create(contratDTO);
        assertNotNull(contratSaved);
        assertNotNull(contratSaved.getId());

        Long idContrat = contratSaved.getId();

        // 4. Modifier et tester
        contratSaved.setMontantTotal(new BigDecimal("150.00"));
        ContratDTO contratModifie = contratService.update(idContrat, contratSaved);
        assertEquals(new BigDecimal("150.00"), contratModifie.getMontantTotal());

        // 5. Supprimer
        contratService.deleteById(idContrat);
    }
}