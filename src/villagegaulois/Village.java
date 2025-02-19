package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	private static class Marche{
		private int nbEtal;
		private Etal[] tab;
		private Marche(int nbEtal) {
			this.nbEtal=nbEtal;
			tab = new Etal[nbEtal] ;
			for (int i=0; i<nbEtal;i++) {
				tab[i]=new Etal();
			}
			
		}

		
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur,
				String produit, int nbProduit) {
			tab[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
		}
		
		int trouverEtalLibre() {
			for (int i=0; i<tab.length;i++) {
				if (tab[i].isEtalOccupe()==false) {
					return i;
				}
			}
		return -1;	
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbetalproduit=0;
			for (int i=0; i<nbEtal;i++) {
				if (tab[i].isEtalOccupe()==true && tab[i].contientProduit(produit)==true) {
					nbetalproduit=nbetalproduit+1;
				}
			}
			
			Etal[] tab2 = new Etal[nbetalproduit];			
			int k=0;
			
			for (int j=0; j<nbEtal;j++) {
				if (tab[j].isEtalOccupe()==true && tab[j].contientProduit(produit)==true) {
					tab2[k]=tab[j];
					k++;
				}
			}
			return tab2;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0; i<nbEtal;i++) {
				if (tab[i].isEtalOccupe()==true && tab[i].getVendeur()==gaulois) {
					return tab[i];
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			int nbretalrestant;
			StringBuilder retour=new StringBuilder();
			for (int i=0; i<nbEtal;i++) {
				if (tab[i].isEtalOccupe()==true) {
					retour.append(tab[i].afficherEtal());
				}
			}
			if (trouverEtalLibre()!=0) {
				retour.append("Il reste "+trouverEtalLibre()+" étals non utilisés dans le marché.\n");
			}
			return retour.toString();
		}
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}