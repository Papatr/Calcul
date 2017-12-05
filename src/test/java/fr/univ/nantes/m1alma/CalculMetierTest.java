package fr.univ.nantes.m1alma;

import junit.framework.TestCase;

public class CalculMetierTest extends TestCase {

	//Declaration de la classe qu'on doit tester
	
		private CalculMetier metier;
		
		
		//SetUp() = Methode qui s'execute au moment de l'initialisation
		//Cad quand on lance un test unitaire, la classe CalculMetier va etre instancier par junit,
		//et quand il va etre instancier automatiquement il appelle la methode setUp() pour instancier 
		//CalculMetier => metier = new CalculMetier()
		protected void setUp() throws Exception {
			super.setUp();
			metier = new CalculMetier();
		}

		
		//Apres l'instanciation,junit appelle les methodes qui vont tester la classe,cest metohdes sont:
		
		public void testSomme(){
			//Pour tester, nous avons des methodes assert qui vont nous indiquer quand est ce que 
			//nous allons considere ou pas que les test ont  reussi
			
			assertTrue(metier.somme(6,6)==12); //Je considere que le test de somme(a,b) a reussi si somme(a,b)=a+b 
		}
		
		
		public void testProduit(){
			
			assertTrue(metier.produit(6,6)==36); //Je considere que le test de produit(a,b) a reussi si produit(a,b)=a*b 
		}
		
		//Pour verifier les test, on fait appel a Maven
		
		//Cycle de vie d'un logiciel
		
		//COMPILATION : pour s'assurer que tout est bien compiler
		//mvn compile pour compiler le code source => Maven appellera le plugon de compilattion apres l'avoir telecharger
		//Apres la compilation, Maven genere un dossier Target qui contient les classes et les tests
		
		//TEST: pour m'assurer que tout les tests unitaires qui se trouve dans le projet doivent etre executes
		//mvn test pour tester le code source => Maven appellera le plugon de test apres l'avoir telecharger
		
		//PACKAGING : pour demander a maven de generer un fichier .jar
		//mvn package pour generer un .jar => Maven appellera le plugin package apres l'avoir telecharger
		
		//Une fois que le packaging est fait, on peux voir sur notre projet le fichier : Calcul-1.0-SNAPSHOT.jar => artifactId-version.jar (structure du fichier)

		//Apres la generation du jar, si on veux qu'il soit utilise par les autres projets, on demande a Maven de 
		//l'envoyer dans le repository (local ou distant)
		// - envoie vers repository local => mvn install qui prends le fichier .jar et l'installe sur le repository
		
		//Pour voir ce qui s'est passe, il faudra aller dans le dossier repository dans le dossier qui porte le nom de notre package => fr->univ->Calcul->version
		//et on retrouvera le fichier .jar
		
		//Tout ces operation qu'on a fait jusqu'a arriver au fichier .jar signifie qu'on livre, on a un livrable.
		//Utilite: Imaginons qu'on travail dans un groupe de production, si on a un travail a faire,
			//	- on l'a cree
			//	- on l'a compile
			//	- on l'a installer dans le repository
		//A partir de ce moment, les autres personnes de l'equipe peuvent utiliser ce fichier .jar
		
		//NB: par analogie dans l'execution des differents commandes:
				//	- mvn compile => compile
				//	- mvn test => compile et test ou mvn test -Dtest=*Test (test toute Classe se terminant par Test)
				//	- mvn packaging => compile, test et genere le .jar
				//	- mvn install => compile,test, genere le .jar et envoie le .jar dans le repository
				
				// Donc DEPUIS LE DEBUT ON AURAIT DIRECTEMENT PU APPELLER mvn installs
		
		
		
		
		
		//Dans le projet qui va suivre, on montrera comment utiliser notre fichier .jar dans un autres projet
		//Le projet sera une application web jee dans la quelle on creera une simple page JSP qui permettra uniquement de sdaisir a et b et apres
		//de faire le calcul Somme et Produit en utilisant les methodes de calculs Somme et Produit de notre Projet Calcul du fichier .jar
		
		//Procedure
		//DIRECTEMENT SUR ECLIPSE => CREER UN PROJET DYNAMICS WEB PROJECT
		
		// EN LIGNE DE COMMANDE
		//- Creation d'un nouveau projet (projet web) => generation d'un nouveau projet maven de type webapp
		// mvn archetype:generate >test.txt.
		//>test.txt va permettre de rediriger la sortie de l'ecran (cad la generation) via un fichier test.txt
		//attendre 1à sec environ le temps de generer et faire CTRL+C plusieurs fois,et apres aller dans le dossier TPMVN pour voir la generation.
		//En ouvrant le fichier on verra que chaque type de projet correspond a un numero, donc on pourra utiliser CTRL+F et en renseignant 
		//"maven-archetype-webapp" dans la zone de recherche,ca sera plus facile de trouver le numero associe a un projet web afin de pouvoir 
		//creer notre projet web jee.
		//Dans notre cas il correspond au numero "1085" => on peut creer notre projet maintenant
			//	- mvn archetype:generate
			//	- on renseigne le numero du type de projet => 1085
			//	- on Tape sur entrer pour choisir la version 1.0 correpondant a 5 suggerer par mvn
			//	- on renseigne le groupId => fr.univ.nantes
			//	- on renseigne le artifactId (cad le nom du projet) => CalculWeb
			//	- on renseigne la version: maven nous propose la version 1.0-SNAPSHOT, on le valide
			//	- on renseigne le nom du package (qu'on va utiliser dans le projet): par defaut maven 
			//	nous propose fr.univ.nantes, mais on peux le changer => fr.univ.nantes.m1alma
			//	- Maven nous demandera à la fin si on souhaite generer, ce que l'on approuve par Y
		
			//Et le projet se genere avec succes et si on se dirige vers le dossier ou on a generer le projet, on trouvera notre projet => C:\Documents\java\TPMVN
				//	- Dans ce dossier on trouvera:
				//	- Le nom du projet CalculWeb avec le fichier pom.xml et le dossier src. 
				//	- pom.xml  contient les info de projet et les dependances 
				//	- src contient main qui contient deux dossiers (ressources et webapp(dans lequel on a notre page jsp et notre fichier web.xml))
		
			// Il nous restera qu'a l'importer avec eclipse;Pour se faire il faudra lui ajouter la facette eclipse
			// => mvn eclipse:eclipse
			// ensuite ouvrir eclipse et importer notre projet CalculWeb
			// Une fois qu'on aura importer, nous pourrons creer une page JSP qui permettra de saisir "a" et "b" et 
			// faire la somme et le produit en utilisant le .jar de notre projet Calcul => Nous creons un projet qui depebnd d'un autre projet
			//Comment on va utiliser l'autre projet? => utilisation des dependances. Il faudra alors declarer/ajouter les dependances dans notre fichier 
			// pom.xml de notre projet web. Par default on a une seule dependance => dependance avec JUNIT
			//Ajout d'une dependance:
				//voir dans le fichier pom.xml de CalculWeb dans la partie: 
				// <dependencies><dependency>...</dependency></dependencies>
		
			//Une fois que la dependance ajoutee, en faisant un refresh, nous pouvons voir directement dans Referenced Librairies le fichier Calcul-1.0-SNAPSHOT.jar
			//apparaitre. Donc nous dependons d'un autre projet sans meme connaitre un seul bout de code de ce projet.
			// Tout ce dont on a besoi n c'est la dependance 
			// Donc si on veux utiliser un module dans un projet on declare la dependance.
		
			
			//Creation des autres dependances
			// Dans un projet web on aura toujour besoin de: Servlet, JSP, JSTL => donc il nous faudra creer ces dependances
			//Ajout de ces dependances
				//voir dans le fichier pom.xml de CalculWeb dans la partie: 
				// <dependencies><dependency>...</dependency></dependencies>
			//Une fois que la dependance ajoutee, en faisant un refresh, nous pouvons voir directement dans Referenced Librairies
			// les dependance apparaitre (jsp-api, servlet-api, jstl-1.2)
			
			//Maintenant on peux creer notre page JSP (voir index.jsp)
		
			//Apres creation de notre page index.jsp, pour tester notre projet, nous devons faire la procedure:
				// compile, test, packaging, install
			//Mais on avait vu haut precedemment qu'on pouvais faire appel uniquement a install pour faire tout le travail
			// => tester notre projet => mvn intall
		
			// Si on a un BUILD SUCCES caveux dire que normalement tous c'est bein passé.
			// On pourra regarder dans le repository qui se trouvera dans le repertoire "C:\Users\hp\.m2\repository\fr\\univ\nantes\CalculWeb\1.0-SNAPSHOT"
			//pour s'assurer que le .war (au lieu d'un .jar pour dire que c'est un projet web) a ete cree pour => projet marche
		
			
			//Pour tester , on utilisera le plugin tomcat
			// mvn tomcat7:run
				//Telecharge le plugin Tomcat
				//Demarre une version de tomcat dans laquelle il va deployer le fichier .war et apres on pourra le tester
		
			// NB: Ce qui est interessant c'est que on utilise pas de Tomcat mais une version de Tomcat qui est fournit par Maven
			// et qui fait partie de ce plugin.
		
			//Pour utiliser le vraie Tomcat qui est installe sur notre notre machine
				//Demarrer tomcat en executant le fichier startup.bash dans le repertoire de tomcat. => C:\apache-tomcat-9.0.1\bin
				// ou en ligne de commande => cd C:\apache-tomcat-9.0.1\bin et demarrer en tapant startup.bat
				//taper sur un navigateur localhost:8080, si la page de tomcat s'affiche => c'est ok
				//on clique sur Manager App (page qui permet de gerer des applications 
				// on saisi notre id&pwd pour acceder. (dans cette version il ya pas d'user par default) => faudra le creer
				// pour se faire, aller dans le dossier conf => C:\apache-tomcat-9.0.1\conf et editer tomcat-users.xml
				// ensuite creer un role Manager et les utilisateurs (voir fichier tomcat-users.xml)  pour administrer a distance tomcat
				//Arreter et redemarrer tomcat pour prendre en compte les modificatons
				//rafraichir localhost:8080 et repartir sur Manager App, taper id&pwd, à partir de ce moment on peux deployer en .war
				//Pour se faire,  
					
					// MANUELLEMENT: => aller dans l'onglet Fichier war a deployer, 
					//aller chercher notre fichier war dans notre repository => C:\Users\hp\.m2\repository\fr\\univ\nantes\CalculWeb\1.0-SNAPSHOT
					//et deployer 
					//On peux la tester en cliquant sur le lien de notre application sur la liste des applications deployees
	
		
					//AVEC MAVEN: => deployer en ligne de commande avec notre propre tomcat sur le repertoire du projet => C:\TPMVN\CalculWeb
					//mvn tomcat7:deploy -Dtomcat.password=password -Dtomcat.usernanme=username
					//=> mvn tomcat7:deploy -Dtomcat.password=Mamebou1988* -Dtomcat.username=papatraore
					// Il faudra souligner que le plugin tomcat dans ce cas precis n'existe pas (avec maven), donc a l'execution de la ligne de commande
					// il sera telecharger.
					//Sauf que ca marche pas car maven ne trouvera pas le plugin. Pour regler le probleme, ca sera a nous d'ajouter le plugin dans le fichier 
					//pom.xml ce qui permettra de montrer a maven quelle est l'adresse qu'il doit utiliser pour aller telecharger le plugin.
					//Ajout du plugin dans <build>...<build> et <pluginRepositories>...</pluginRepositories>de notre fichier pom.xml(voir pom.xml du projet CalculWeb)
					//Nous avons un BUILD SUCCES apres execution de la commande cette fois ci => OK telechargement reussi. car on specifie dans lme repositories dans 
					//lequel maven devrait telecharger le plugin
		
					//Maintenant on va taper dans le navigateur localhost:8080 et partir dans Manager App (page qui permet de gerer des applications ) et eventuellement
					//renseigner l'id/pwd pour y acceder, et nous verrons deirectement que notre projet CalculWeb a ete deploye
		
					// NB: depoly dans la ligen de commande signifie que maven va aller dans le repository => C:\Users\hp\.m2\repository\fr\\univ\nantes\CalculWeb\1.0-SNAPSHOT
					// chercher le fichier .war dans notre machine 
					// il va l'envoyer avec un upload a tomcat => C:\apache-tomcat-9.0.1\webapps
		
					//Si maintenant on execute un mvn tomcat7:undeploy -Dtomcat.password=Mamebou1988* -Dtomcat.username=papatraore
					//maven va envoyer une requete http (car serveur tomcat dans une autre machine) pour lui demander de suopprimer 
					// ce qui supprimera le fichier .war dans C:\apache-tomcat-9.0.1\webapps
					//Et à partir de ce moment l'application ne sera plus disponible, si on essaye d'y acceder => Etat HTTP 404 -/CalculWeb (ressource demandee pas dispo)
					
		
					//AVEC MAVEN: mais une propre version tomcat de maven => mvn tomcat7:run 
		
					//A RETENIR: Avec maven on n'a plus besoin de faire les choses manuellement. Que ca soit:
						// - compilation
						// - test unitaire
						// - packaging
						// - install (repository)
						// - deploy
						// - undeploy
		
		
		
		//Generation de la documentation => mvn site
		//Qu'est ce qui se passe, maven va nous generer la documentation pour ce projet,cad un site (page html qui qui par la suite etre publiee), ce qui permettra aux developpeurs de consulter 
		//notre projet
		//BUILD SUCCES => generation terminee
		
		//Pour voir la documentation, on aura qu'a regarder notre projet => dans Target on aura un dossier site
		//qui contiendra la structure cad un ensemble de page html. Si on ouvre index.html sur un navigateur on trouvera toute les informations sur le projet cad:
			//  - Distributions
			//	- Dependances, etc...
		//Ce qui permettra a quelqu'un qui voudra utiliser votre projet de savoir qu'est ce qu'il doit declarer dans son fichier pom.xml
		
		//La documentation montre aussi les dependances de notre projet (ici c'est le fait que notre projet depende de Calcul, junit, servlet, jsp, jslt)
		//Dans la doc si dans l'etape de notre dev on a utilise des commentaires etc.. ces commentaires seront visibles aussi
		
		
		
		
				
		
}
