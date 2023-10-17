package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FournisseurServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class FournisseurTest {

    @MockBean
    private FournisseurRepository fournisseurRepository;

    @MockBean
    private ProduitRepository produitRepository;

    @MockBean
    private DetailFournisseurRepository detailFournisseurRepository;

    @MockBean
    private SecteurActiviteRepository secteurActiviteRepository;

    @Autowired
    private FournisseurServiceImpl fournisseurServiceImpl;

    @Test
    void testRetrieveAllFournisseurs() {
        ArrayList<Fournisseur> fournisseurList = new ArrayList<>();
        when(fournisseurRepository.findAll()).thenReturn(fournisseurList);
        List<Fournisseur> actualRetrieveAllFournisseursResult = fournisseurServiceImpl.retrieveAllFournisseurs();
        assertSame(fournisseurList, actualRetrieveAllFournisseursResult);
        assertTrue(actualRetrieveAllFournisseursResult.isEmpty());
        verify(fournisseurRepository).findAll();
        System.out.println("Fournisseur Retrieved!");
    }

    @Test
    void testDeleteFournisseur() {
        doNothing().when(fournisseurRepository).deleteById((Long) any());
        fournisseurServiceImpl.deleteFournisseur(123L);
        verify(fournisseurRepository).deleteById((Long) any());
        System.out.println("Fournisseur Deleted !");
    }
    @Test
    void testAddFournisseur() {
        // Créez un fournisseur factice que vous souhaitez ajouter
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCode("ABC123");
        fournisseur.setLibelle("Fournisseur XYZ");
        fournisseur.setCategorieFournisseur(CategorieFournisseur.CONVENTIONNE); // Remplacez par la valeur réelle

        // Définissez le comportement du mock ProduitRepository
        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);

        // Appelez la méthode d'ajout de fournisseur
        Fournisseur addedFournisseur = fournisseurServiceImpl.addFournisseur(fournisseur);

        // Vérifiez que la méthode d'ajout a été appelée avec le fournisseur factice
        verify(fournisseurRepository).save(fournisseur);

        // Vérifiez que la méthode a renvoyé le fournisseur ajouté
        assertNotNull(addedFournisseur);
        // Vous pouvez également effectuer d'autres vérifications en fonction de votre logique métier

        // Par exemple, pour vérifier que les propriétés ont été correctement enregistrées
        assertEquals("ABC123", addedFournisseur.getCode());
        assertEquals("Fournisseur XYZ", addedFournisseur.getLibelle());
        assertEquals(CategorieFournisseur.CONVENTIONNE, addedFournisseur.getCategorieFournisseur());
        System.out.println("add Fournisseur !");
    }

}