# Références
GIT cheat sheet http://etnbrd.github.io/git-cheatsheet/    
ChatGPT est votre ami  
Livre de référence : https://git-scm.com/book/fr/v2   

# Principes généraux
- Ingénieur --> Gère des projets   
  - Projet      --> Avant tout de la documentation  
    - Documentation --> Temps long, Multi-auteurs, Problème de l'expression...  

**Dans le cas de l'informatique**
- Le code est de la documentation, tout peut être code   
- Dualité code source, code compilé  


> C -> objet  
> java, bytecode -> bytecode  
> latex -> ps / pdf   ---> Donnez example  
> html -> web  
> scheduler.c -> linux   

### 💡 Idée
Idée : Si je gère mon code source, alors je gère mon projet.

#  Les 5 outils de base pour la gestion de code  
```diff``` ```patch```
```md5```  
```wget | scp```   


# Git vs Github
Exécuter les commandes suivantes  
`git clone https://github.com/sfrenot/mgl`   
`cd mgl`  

Naviguer rapidement dans les fichiers pour voir à quoi ressemble ce code.  

Attention il faut être sûr de ce que vous faites avec la commande suivante :   
`rm -rf *`    
Malheur que venez-vous de faire ?  

`git checkout .`  

Que vient-il de se passer ?   

## Jeter un oeil dans le répertoire .git
`find .git`

## Manipulation de git pour faire progresser l'Histoire de l'évolution de votre code
Le principe est de raconter une histoire sur l'évolution de votre code. Le fonctionnement est le suivant : 
1. Ecrire le nouveau code qui peut être :
  - Ajout / Modification / Suppression de ligne --> (qui se résume à un `diff / patch` sur un fichier)
  - Ajout / Suppression de fichier ou de répertoire --> (qui se résume à un diff patch dans une arborescence `diff -r | patch -r`)

2. Choisir les fichiers à ajouter pour la prochaine partie de l'histoire. 
  - Les fichiers modifiés ne font pas toujours tous partie de l'histoire (fichiers de test, intermédiaires, etc..)
  
  `git status` permet de connaitre tous les fichiers modifiés **et** l'histoire qui sera racontée à la prochaine étape. Il y a trois cas : 
     - des fichiers et repertoires modifiés qui seront ajoutés à la suite de l'histoire *to be committed*
     - des fichiers et répertoires modifiés qui ne seront pas ajoutés à la suite de l'histoire *not staged for commit*
     - des fichiers et répertoires qui ne sont pas suivis *Untracked files*
  `git diff` permet de connaitre les modifications réelles
  `git add` permet d'ajouter les fichiers qui devront être insérés dans la suite de l'histoire de l'évolution du code
  `git rm` permet de d'indiquer les fichiers qui ne feront plus partie de la suite de l'histoire

  Attention `git add` et `git rm` ne modifient pas directement l'histoire. Ils préparent la suite de l'histoire dans un espace temporaire. 

3. Ecrire (`Committer`) la suite de l'histoire
Le principe de git est de décrire l'évolution du code source par une serie de points de contrôle ou points d'évolution de l'histoire. Chaque point s'appelle un `commit` et possède un numéro spécifique. Il est ainsi possible de revenir à n'importe quel point de l'histoire, de la dernière à la première action de `commit`. L'histoire raconte donc l'évolution du code par étapes successives, et tout est enregistré **localement** sur la machine où vous taper la commande gie commit (dans le répertoire `.git`). 

`git reflog` et al. : vous indiquent le déroulement de l'histoire.
`git status` : vous indique (entre autres, dans la première partie) ce qui va être ajouté à l'histoire.
`git commit` : valide la nouvelle partie de l'histoire. Git demande alors un petit texte explicatif qui résume ce chapitre de l'histoire.

Il existe un raccourci pour les étapes 2, 3.  :
`git commit -am"Demande de suppression du code more d'avant-hier"`.
Il considère que tous les fichiers modifiés (pas les ajouts de fichiers) depuis le dernier commit appartiennent à la suite de l'histoire `-a` et requiert un texte explicatif qui tient sur une ligne.

Cette commande est un raccourci qu'il faut éviter d'utiliser au début de l'utilisation de git. De toute façon je recommande de toujours faire un `git status` et/ou `git log` avant tout commit. Un commentaire ou une faute évidente est souvent oubliée. De plus, cela vous permet aussi de faire un rapide retour sur le code écrit pendant la session courante. 

De base, le code local suit donc le parcours suivant : 
>Working Space   -A->         Index Space           -B-> Storage Space  
>Zone de travail -A-> Zone de préparation d'histoire -B-> Zone d'histoires validées  

L'appel `git add|remove` transfert les fichiers du Working space à l'Index space. L'appel `git commit` transfert les fichiers de l'Index vers le Storage. Les deux espaces Index et Storage sont gérés dans le repertoire `.git` de votre machine.

## Manipulation de git pour revenir en arrière
On a le droit de faire des erreurs... Il est donc logique et simple de revenir en arrière dans l'écriture de l'histoire. Bien évidemment, plus on est loin dans l'écriture de l'histoire, plus le retour en arrière peut être compliqué. 

### Dans le working space / espace de travail
"J'ai modifié un fichier, mais je veux revenir à la version initiale de l'histoire". 

`git diff <nomdufichier>` : vous indique toutes les modifications, entre la version courante de l'histoire et votre fichier actuel. Vous avez normalement l'ensemble des modifications. 
`git checkout <nomdufichier>` : c'est l'arme universelle. Elle rétablit le fichier dans la dernière version committée. Vous avez déjà utilisé cette commande en faisant un `rm -rf *` à la racine du projet. Un `git checkout .` va remonter de manière récursive tous les fichiers et répertoires du projet dans leur dernière version committée. Il est également possible de revenir vers une version particulière de l'histoire du fichier. `git log` indique les versions accessibles de l'histoire.

`git checkout 17446f4ee9a README.md` : récupère une version particulière de l'histoire
`git checkout HEAD README.md` : récupère la version courante de l'histoire
`git checkout HEAD^ README.md` : remonte dans les versions précédentes
`git checkout HEAD^^ README.md`
`git checkout HEAD^^^ README.md`

### Dans l'index space  
"J'ai ajouté par erreur des fichiers dans l'index space, mais je ne veux pas les mettre". 

`git reset toto.pdf` annule la préparation d'un fichier pour le commit suivant. 

*Il existe de très nombreuses commandes pour manipuler ces deux espaces* 
*Toujours faire attention aux fichiers qui vont être impactés**
*Sans le vouloir fortement, on ne peut rien perdre dans le Storage Space*


### Depuis le storage space
"J'ai committé le fichier, mais l'histoire n'est pas bonne"

`git commit --amend` : modifie la dernière histoire committée
`git reset --hard HEAD^^` : revient 'officiellement' sur l'antepénultième version. 

A partir de ce point, vous avez compris comment manipuler git d'un point de vue local. 

Par exemple si vous souhaiter démarrer un nouveau projet git. La commande initiale est `git init`.

Vous pouvez compléter vos connaissances soit en regardant le fonctionnement de bas niveau (cf td qui accompagne ce Readme), soit regarder la partie distribuée de git. 

# Ignorer les fichiers
Ajouter le fichier `.gitignore` dans un répertoire à partir duquel vérifier les fichiers à ignorer (binaires, codes d'accès...) 

# Ecrire l'histoire à plusieurs
Git est un outil extrêmement puissant pour gérer l'évolution du code source d'un projet. Il le fait par l'établissement d'un journal de bord qui exprime toutes les évolutions du code. 

Un cas particulier et fréquent d'utilisation de Git survient lorsque le code est partagé entre plusieurs utilisateurs. 
Dans ce cas, on va utiliser un site de dépôt distant qui est capable dans les grandes lignes de stocker le répertoire `.git` du projet et de l'envoyer aux participants du projet. Le service minimal est donc un serveur ssh sécurisé pour accueillir les nouvelles version de la base de données du projet (le repertoire .git).

Le site le plus connu pour gérer ces bases de données est github. Il offre en plus de nombreuses fonctions supplémentaires utiles dans le co-développement de logiciels ou de manière générale de fichiers sources. Git est utilisé pour partager des plans de fabrication, des lois, des résultats de recherche, etc...

A partir de maintenant l'histoire reprend à partir des commit d'un utilisateur et met en relation une machine locale et une machine distante. 

Dans une durée courte, il est difficile de créer des comptes github et d'inscrire les participants au projet. Ce qui est la démarche classique. 
Nous allons faire différemment, pour l'exercice, nous allons passer par un accès par clé publique/privée au dépot git. 

Je ne détaillerai pas le principe des accès ssh, mais dans l'immédiat, si vous n'avez pas de clé publique sur votre machine (dans le répartoire .ssh, le fichier id_rsa.pub ou id_dsa.pub), vous allez créer une paire de clés privée/publique. Vous déposerez votre clé publique dans un fichier partagé google mis à disposition pendant la séance.

Une fois installée par l'enseignant, cette clé vous permet de récupérer le projet `.git` comme si vous étiez membre du projet. 
Repartez d'un repertoire vide. 

L'interaction dans un projet se fait dans la démarche suivante :
1. Récupération initiale
   - La première action consiste à récupérer la base initiale avec la commande 'git clone'. 
   - Nous allons faire un clonage particulier par token dédié. 
  
  `git clone https://sfrenot:<token>@github.com/sfrenot/mgl.git`
  Le token sera déposé dans ce document pendant le cours. 
  https://docs.google.com/document/d/1cWD2do3I2KzsKROWc26XSqC7xLOvCoQ0BiMnhe5o1io/edit?usp=sharing
  
1. A chaque période de développement, commencer à récupérer les modification des autres utilisateurs
   - `git pull`

2. A chaque commit, pousser les données vers le serveur
   - `git push`  


--> Si un push se passe mal, pas de panique. Voici ce qui se passe.
<pre>
➜  mgl-git git:(master) ✗ git push
To github.com:sfrenot/mgl.git
 ! [rejected]        master -> master (fetch first)
error: failed to push some refs to 'github.com:sfrenot/mgl.git'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
</pre>

Prenez le temps de comprendre ce qui se passe et cela va repartir tranquillement. Ne perdez jamais votre sang-froid, mais essayez de comprendre ce qui se passe !!! Au pire vous allez dans un nouveau répertoire et vous faite un clone du projet pour faire des comparaisons par la suite. Votre souci ne peut venir que de votre base locale qui n'est plus compatible ou moins compatible avec la base distante, et git vous protège pour ne rien détruire. 

Essayez d'avoir toujours en tête une idée de l'état de votre projet par rapport à votre base de données locale : `git log | git reflog` `git log --oneline --graph --decorate --all` , `git status` et `git diff` sont vos amis. 

Essayez également d'imaginer que toutes les actions associées (commit, log, etc.) sont des visualisations de la base de données cachée dans le répertoire `.git`. N'hésitez-pas à y faire un tour par curiosité et voir comment elle évolue lorsque vous faite des actions locales. 

A ce point vous pouvez utiliser git comme un développeur standard. Demandez à votre boss dans quelle branche vous travaillez et toutes les commandes indiquées jusqu'à présent fonctionnent. Vous devez les connaitre et les comprendre. Notez les rôles de chacune.

>git clone  
git status  
git diff  
git add  
git commit  
git log  
git push  
git pull  
git checkout  
git reset  

10 commandes !!!

`git init`, n'est quasiment jamais utilisée car git s'utilise souvent avec un site de gestion gitlab/github qui vous simplifiera la création intiale de votre projet. Une fois créé, seule la commande `git clone` est utile pour manipuler un projet.

### Comment utiliser git
- Tout le temps
- Toujours pour un code qui fonctionne. Ne poussez pas un code faux !
- Supprimer vos commentaires inutiles et vos codes morts. Git sert justement à gérer cela.
- Jamais de binaire
- Jamais de données de connexion  / mots de passe / clé, etc...
- Jamais de `git add -r *` 
- Tout est enregistré depuis l'origine du projet
- Si votre projet est trop lourd, faites un nouveau projet
- Si votre projet contient des données qu'il ne devrait pas, faites un nouveau projet

## Pensez à git vs github vs gitlab
  
# Oui mais, les branches, les tags, rebase, les merges request, le CI/CD et les bissect ?
Si vous commencez à participer à des projets git, vous allez être confrontés à quelques éléments de fonctionnement avancé classiques. 

## Les branches
Les branches sont un mécanisme simple, qui peut devenir cauchemardesque s'il est mal utilisé. Elles sont systématiquement utilisées sur des grands et gros projets afin d'éviter de mélanger l'état d'avancement des différentes fonctionnalités d'un projet. 

Une branche consiste donc à dévier le projet pour développer une fonction spécifique sur un temps donné, sans bloquer la progression générale du projet principal. Le principe consiste à ce que le développeur 'branche' le code initial vers un code particulier, qu'il fasse les modification sur ce code sans crainte de casser le code principal, puis de réaliser une action de regroupement de la branche de développement avec la branche principale. 

Listing : 
`git branch`
`git branch -a`

Création :
`git branch toto`

Bascule :
`git checkout toto`
--> La commande checkout est donc importante. Elle demande à l'utilisateur de se placer à un endroit particulier avec un pointeur initial   
--> le reste est classique : git add/commit/push   
--> Le push demande à connaitre la branche distante associée   

## Une fois la *feature* développées il faut l'intégrer et la tester dans le programme principal
`git rebase main` permet de remettre le pointeur de la branche *main* au plus proche de votre branche *feature*. Cette opération peut soulever des conflits qu'il faudra résoudre étape par étape. 

Lorsque le rebase est fait dans votre branche, cela veut dire que vous êtes à jour de votre fonctionnalité. Vous pouvez alors l'intégrer dans le projet principal. (Souvent après une étape de contrôle par l'intégrateur du projet).   
`git checkout main`  
`git merge --no-ff <feature>`   

Pour visualiser vous pouvez ajouter un alias à votre environnement pour l'affichage des logs.      
`git config --list`   
`git config --global alias.lola "log --graph --decorate --pretty=oneline --abbrev-commit --all"`   
ou d'autres alias sont également possibles.    
`alias.glog=log --oneline --decorate --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset'`   
`alias.glogg=!git glog --branches --remotes`   
`alias.glogs=!git glog --stat`  
`alias.gloggs=!git glogg --stat`   


Si votre rebase a bien été fait, cela ne posera pas de problème car vous avez réintégré le développement de la branche principale.    
Si vous avez loupé ou oublié votre rebase, vous allez vous faire gronder... Il faut annuler votre rebase. 

Le script `examples/simple.sh', simule un projet simple avec des conflits. L'objectif est de comparer les différentes commandes à faire pour obtenir le bon résultat final. 

Ne lisez pas la suite si vous voulez essayer de comprendre.  
`git rebase main`   
`git commit -am 'corrections'`   
`git checkout main`    
`git merge --no-ff feature`    


Vous devriez obtenir cela avec un `git lola`
```
*   0231f65 (HEAD -> main) Merge branch 'feature'
|\  
| * 957874b (feature) Ajout B dans empty/feature
|/  
* 859417d Ajout C dans main
* f47e1dd Ajout A dans empty/main
```

Si vous obtenez cela, vous avez loupé votre rebase
```
*   b51b22e (HEAD -> main) Merge branch 'feature'
|\
| * c1acc57 (feature) Ajout B dans empty/feature
* | 34930f1 Ajout C dans main
|/
* 4ceeacb Ajout A dans empty/main
```
C'est trop tard... Comment revenir en arrière ?
Réponse : 
```
git reset --hard 34930f188ad644262a70511f435a5976c1062fd3
git checkout feature
git rebase main
git checkout main
git merge feature --no-ff
```


Une fois votre feature terminée et intégrée à votre projet principal, il est important de finir le ménage en supprimant les branches de feature créées.   
`git branch -d toto` --> Supprimer la branche locale toto    
`git push origin --delete toto` --> Supprimer la branche distante toto   

===> Mais est-ce que le ménage est fini ?

`git branch -a`

L'option `--dry-run` permet de voir les commandes sans les exécuter. 

### Comment utiliser les branches
Il y a autant de gestionnaires de projet que de manières de gérer les branches. Mes conseils :
 - Si vous ne savez pas à quoi cela va vous servir, ne les utilisez pas
 - Si votre projet s'organise sur une structuration en branches, demandez la doc, et suivez les branches
 - Faites le ménage à la fin de vos branches
 - N'attendez pas pour merger si possible
  
## Les tags
Lorsque votre projet atteint une certaine taille, vous pouvez 'tagger' des versions / étapes. Cela permet de checkouter rapidement des étapes.

## Rebase
Rebase permet de réécrire l'histoire des logs. Certains logs sont mal structurés. Vous êtes fatigués, vous avez commité un gros commit qui contient du patch, de l'ajout de fonction, du code de débug, etc... Bref votre commit dit : 'Mise à jour du code'. Le lendemain vous décidez de régorganiser votre commit pour séparer les lignes qui sont du débug des lignes qui concernent des fonctions spécifiques.... L'outil s'appelle `git rebase`. Attention, il peut rendre un peu addictif. Mais ne jamais l'utiliser après un `git push` !!!

## Bisect
"Je suis certain que j'avais écrit cette fonction quelque part, mais elle a disparu..."
L'outil s'appelle `git bisect`, il va chercher de manière dychotomique dans votre espace d'histoire l'information que vous cherchez. 

## Arf, j'ai oublié le stash
"Je suis en train de modifier mon code, et Raoul travaille en même temps que moi. Il faut que je pull son code, mais je ne peux pas commiter ma partie de code, car je suis instable."
La 'cachette/stash' est là pour ça. Avant de faire le pull et avoir un refus car les merges ne sont pas fait, vous pouvez planquer votre code dans le grenier. 
`git stash push | pop` font le taf.

## et github, le CI/CD, le site web... ?

<pre>
  Environnement de développement  
    <--- Code --->  
  Environnement d'exécution (runtime)  
    <--- Code + librairie --->
  Environnement d'exploitation du code (exploitation)  
    <--- Code + librairies + contraintes non-fonctionnelles --->
</pre>

Offrir les garanties suivantes :   
> Garantir que le code exécuté soit le même  
>   Garantir que les modifications d'un code sont connues de tous  
>     Garantir que le code progresse dans un sens maîtrisé (pas d'introduction de bug)   
--> Sur la seule dimension valide du projet : l'exploitation 

Trois niveaux dont deux niveaux inutiles !!!!   
ma machine --> une machine connue --> une autre machine inconnue   

Savoir raisonner sur du très long terme : 10 ans, 20 ans.    
Et sur de nombreux participants : 1, 3, 10, 100 développeurs.   

----
Un point commun : Besoin d'outils de gestion de code
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


Git est donc un outil de gestion de données sources de projet. Github est un site communautaire de projets. Toutes les actions réalisables sous git, peuvent l'être sous Github. Mais la majorité des commandes sont alors directement pushées sur le site distant. En résumé sur github le service est équivalent à git, mais plus lent, non autonome et nécessitant une connexion Internet active. L'utilisation de git en ligne de commande permet d'avancer facilement dans un projet en cours de développement. 

Cela dit github/gitlab offrent des fonctions de groupes incomparables.
### pour votre projet
- Gestion fine des développeurs et de leurs droits d'accès,
- Visualisation du déroulement des versions,
- Gestion de projet avec les issues, les bugs tracking, et le suivi des évolutions,
- Messageries et communications,
- Fourniture automatique d'un site Web autonome associé à votre projet,
- Intégration d'outil de CI/CD (Intégration et Développements continus), notifications, automatisation de chaines de tests, trigger d'action...

### pour vos projets
github/gitlab offre enfin tout ce qui peut être assimilé à un réseau social de développeurs. 
- Mise en relation
- Fork de projet pour proposer des améliorations
- Identification des origines de propriétés de projets
- Sécurisation des projets dépendant de bibliothèques piratées
- Projets publics / privés
- Intégration de copilot et IA génératives

### Semantic versionning

Semver -> Semantic versionning
X.Y.Z-xx
Majeur -> breaking change
Minor -> feature evolution (pas de casse)
patch -> bug fix
xx -> release candidate


### Divers
Git / Mercurial / Subversion  
Les applications à 12 facteurs  : web apps - software as a service : https://12factor.net/  
1.  Un code base, plusieurs déploiements
2.  Déclaration explicite et isolation des dépendances
3.  Stocker les configurations dans l'environnement - variables d'environnement
4.  Traiter les service support comme des ressources
5.  Build / Release / Run sont séparés : Build + config -> Release / Release + Hard -> Run
6.  Exécuter l'application comme un ou plusieurs process stateless  
7.  Les services sont offerts via un binding de port tcp
8.  (Concurrence) L'augmentation de puissance se fait via les processus
9.  (Jetabilité) Augmenter la robustivité en ayant un démarrage rapide et un arrêt propre 
10. Les environnement de dev, préprod et prod doivent être le plus similaire possible
11. Les logs sont traités comme un flot d'événements
12. Exécuter les tâches d’administration/gestion en tant que processus ponctuels 









  


