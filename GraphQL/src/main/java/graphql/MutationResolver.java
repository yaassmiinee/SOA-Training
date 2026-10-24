package graphql;

// Import the business logic class responsible for managing "UniteEnseignement" data
import business.ModuleBusiness;
import business.UniteEnseignementBusiness;
import entities.Module;

// Import the GraphQL library class used to define the root of GraphQL mutations
import com.coxautodev.graphql.tools.GraphQLRootResolver;

// Import the entity class representing a Teaching Unit (Unité d’Enseignement)
import entities.Module;
import entities.UniteEnseignement;

/**
 * This class defines all GraphQL mutations related to the "UniteEnseignement" entity.
 * A mutation in GraphQL is used to perform write operations (create, update, delete)
 * as opposed to queries, which are for read operations.
 */
public class MutationResolver implements GraphQLRootResolver {

    // Attribute used to access the business logic (service layer)
    public UniteEnseignementBusiness helper;
    public ModuleBusiness moduleHelper;

    /**
     * Constructor of the resolver.
     * It initializes the business helper that manages the teaching units.
     */
    public MutationResolver() {
        helper = new UniteEnseignementBusiness();
        moduleHelper = new ModuleBusiness();
    }

    /**
     * GraphQL Mutation: addUniteEnseignement
     * <p>
     * This method allows clients to add a new "Unité d’Enseignement" to the system.
     * <p>
     * When a GraphQL mutation like the following is executed:
     * <p>
     * mutation {
     * addUniteEnseignement(
     * code: 101,
     * domaine: "Informatique",
     * responsable: "Dr. Smith",
     * credits: 4,
     * semestre: 2
     * )
     * }
     * <p>
     * GraphQL calls this Java method to create and save a new teaching unit.
     */
    public boolean addUniteEnseignement(int code, String domaine, String responsable,
                                        int credits, int semestre) {
        UniteEnseignement ue = new UniteEnseignement(code, domaine, responsable, credits, semestre);
        return helper.addUniteEnseignement(ue);
    }

    public boolean addModule(String matricule, String libelle, int coef,
                             int volumeHoraire, Module.TypeModule type, Integer codeUE) {
        if (codeUE == null) {
            return false;
        }
        UniteEnseignement ue = helper.getUEByCode(codeUE);
        if (ue == null) {
            return false;
        }
        Module m = new Module(matricule, libelle, coef, volumeHoraire, type, ue);
        return moduleHelper.addModule(m);
    }
    /**
     * GraphQL Mutation: updateModule
     * Le matricule identifie le module à modifier, les autres paramètres sont les nouvelles valeurs.
     * Retourne le module modifié, ou null si le matricule ou l'UE n'existe pas.
     */
    public Module updateModule(String matricule, String libelle, int coef,
                               int volumeHoraire, Module.TypeModule type, Integer codeUE) {
        if (codeUE == null) {
            return null;
        }
        UniteEnseignement ue = helper.getUEByCode(codeUE);
        if (ue == null) {
            return null;
        }
        Module m = new Module(matricule, libelle, coef, volumeHoraire, type, ue);
        return moduleHelper.updateModule(matricule, m) ? m : null;
    }
    public String deleteModule(String matricule) {
        return moduleHelper.deleteModule(matricule) ? "deleted successfully" : "module not found";
    }
    public UniteEnseignement updateUniteEnseignement(int code, String domaine, String responsable,
                                                     int credits, int semestre) {
        UniteEnseignement ue = new UniteEnseignement(code, domaine, responsable, credits, semestre);
        return helper.updateUniteEnseignement(code, ue) ? ue : null;
    }

    public String deleteUniteEnseignement(int code) {
        return helper.deleteUniteEnseignement(code)
                ? "deleted successfully"
                : "unite enseignement not found";
    }
}


