#TogoJUG
## G�n�ralit�s

Ce projet permet de voir les codes sources utilis�s lors des d�mos de Selenium, TestNG et Selenium Grid (dans le package fr.javafreelance.selenium) et FluentLenium (fr.javafreelance.fluentlenium).

###Pr�requis
Pour lancer le projet de test, installer Play2 .
Aller dans /pat/to/play2/samples/computer-base
Lancer un play run

Se rendre sur l'application : http://localhost:9000 et executer le script tel qu'indiqu� sur la page.
Plus d'infos sur : https://github.com/playframework/Play20/wiki/Samples

### Lancer les tests

####TestNG
Utiliser si Eclipse le plugin testNg pour eclipse pour pouvoir lancer directement les tests via l'IDE.
Rien a faire si vous utilisez IntelliJ.

####Selenium Grid
Il faut d'abord t�l�charger le jar de selenium server : http://code.google.com/p/selenium/downloads/detail?name=selenium-server-standalone-2.20.0.jar&can=2&q=
Lancement du hub :
java -jar /path/to/selenium-server-standalone-2.xx.x.jar -role hub
Lancement du/des nodes
java -jar /path/to/selenium-server-standalone-2.xx.x.jar -role node -hub http://localhost:4444/grid/register

Pour modifier les browsers dispo sur les nodes :
java -jar /path/to/selenium-server-standalone-2.xx.x.jar -role node -hub http://localhost:4444/grid/register  -browser browser='internet explorer',maxInstances=5
Possibilit� de d�finir �galement la plateforme ...

Pour v�rifier que le hub et ses nodes sont bien configur�s : http://localhost:4444



##Source de la presentation Selenium, TestNG, Selenium GRID : https://docs.google.com/present/view?id=df76zb48_14dwvk38c5

##Source de la presentation FluentLenium :  https://docs.google.com/present/view?id=df76zb48_47hmp8bkcp


