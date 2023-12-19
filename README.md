# Références
GIT cheat sheet http://etnbrd.github.io/git-cheatsheet/  


# Principes généraux
- Gestion de projet  
  - Gestion de version de documents (souvent du code)  
    - Outils fondamentaux pour faire du code
-----
<pre>
  Environnement de dev
    <--- Code --->
  Environnement d'exécution  
    <--- Code --->
  Environnement d'exploitation du code   
    <--- Code --->
</pre>

Offrir les garanties suivantes :
> Garantir que le code exécuté soit le même.  
>   Garantir que les modification d'un code soit connu.  
>     Garantir que le code progresse dans un sens maîtrisé  

Besoin d'outils de gestion de code
----
<pre>
Environnement de dev  
    Gestion de code
      <--- Code --->
Environnement d'exécution  
    Gestion de code
      <--- Code --->
Environnement d'exploitation du code   
    Gestion de code
      <--- Code --->
</pre>

# Outils de base pour la gestion de code
## vision utilisateur
```diff / patch / zip```

## vision groupe utilisateurs
```scp / wget```

# Git
Exécuter les commandes suivantes
`git clone https://github.com/sfrenot/mgl` 
`cd mgl`

Naviguer rapidement dans les fichiers pour voir à quoi ressemble ce code.  

Attention il faut être sur de ce que vous faites avec la commande suivante : 
`rm -rf *`
Malheur que vennez-vous de faire ?

`git checkout .`

Que vient-il de se passer ?

## Lancer un oeil dans .git
`find .git`


## Manipulation de git  
Le principe est de raconter une histoire sur l'évolution du code. Le fonctionnement est le suivant : 
1. Ecrire le nouveau code qui peut être 
  - Ajout / Modification / Suppression de ligne --> `diff / patch` sur un fichier
  - Ajout / Suppression de fichier ou de repertoire --> diff patch dans une arborescence `diff -r | patch -r`

2. Choisir les fichiers à ajouter pour la prochaine partie de l'histoire. 
  - Tous les fichiers modifiés ne font pas toujours partie de l'histoire modifiée. (fichiers de test, intermédiaires, etc..)
  
  `git status` permet de connaitre tous les fichiers modifiés
  `git diff` permet de connaitre les modifications








