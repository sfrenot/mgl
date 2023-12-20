# Références
GIT cheat sheet http://etnbrd.github.io/git-cheatsheet/  
ChatGPT / Stackoverflow est votre ami


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

## le hashing
```md5```

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

## Manipulation de git pour faire progresser l'Histoire de l'évolution du code
Le principe est de raconter une histoire sur l'évolution du code. Le fonctionnement est le suivant : 
1. Ecrire le nouveau code qui peut être 
  - Ajout / Modification / Suppression de ligne --> (qui se résume à un `diff / patch` sur un fichier)
  - Ajout / Suppression de fichier ou de repertoire --> (qui se résume à un diff patch dans une arborescence `diff -r | patch -r`)

2. Choisir les fichiers à ajouter pour la prochaine partie de l'histoire. 
  - Tous les fichiers modifiés ne font pas toujours partie de l'histoire modifiée. (fichiers de test, intermédiaires, etc..)
  
  `git status` permet de connaitre tous les fichiers modifiés **et** l'histoire qui sera racontée à la prochaine étape. Il y a trois cas : 
     - des fichiers et repertoires modifiés qui seront ajoutés à la suite de l'histoire *to be commited*
     - des fichiers et répertoires modifiés qui ne seront pas ajoutés à la suite de l'histoire *not staged for commit*
     - des fichiers et répertoires qui ne sont pas suivis *Untracked files*
  `git diff` permet de connaitre les modifications réelles
  `git add` permet d'ajouter les fichiers qui doivent faire partie de la suite de l'histoire de l'évolution du code
  `git rm` permet de supprimer des fichiers qui ne font plus partie de l'histoire

  Attention `git add` et `git rm` n'ajoutent pas directement les modifications. Ils sont ajouté dans un espace temporaire pour préparer la suite de l'histoire. 

3. `Commiter` la suite de l'histoire
Le principe de git est de décrire l'évolution du code source par une serie de points de contrôle ou points d'évolution de l'histoire. Chaque point s'appelle un `commit` et possède un numéro spécifique. Il est ainsi possible revenir à n'importe quel point de l'histoire de la dernière à la première action de `commit`. L'histoire raconte donc l'évolution du code par étapes successives, et tout est enregistré localement sur la machine (dans le répertoire `.git`) discuté initialement. 

`git reflog` et al. : vous indiquent le déroulement de l'histoire.
`git status` : vous indique ce qui va être ajouté à l'histoire dans la première section.
`git commit` : valide la nouvelle partie de l'histoire. Git demande alors une explication d'une ligne qui résume ce chapitre de l'histoire.

Il existe un raccourci pour les étapes 2, 3. Il considère que tous les fichiers modifiés, sans ajout, appartiennent à la suite de l'histoire `-a` et que l'explication tien sur une ligne :
`git commit -am"Demande de suppression du code more d'avant-hier"`.

Cette commande est un raccourci qu'il faut éviter de faire au début de l'utilisation de git. De toute façon je recommande de toujours faire un `git status` et/ou `git log` avant tout vos commit. Un commentaire ou une faute évidente est souvent oubliée. De plus, cela fait un rapide retour sur le code écrit pendant la session courante. 

De base le code local suit donc le parcours suivant : 
>Working Space   -A->         Index Space           -B-> Storage Space
>Zone de travail -A-> Zone de prépartion d'histoire -B-> Zone d'histoires validées

L'appel `git add|remove` transfert les fichiers du Working space à l'index space. L'appel `git commit` transfert le fichier de l'Index vers le commit. Les deux espaces Index et Storage sont gérés dans le repertoire `.git`

## Manipulation de git pour revenir en arrière
On a le droit de faire des erreurs... Il est donc logique est simple de revenir en arrière dans l'écriture de l'histoire. Bien évidemment, plus on est loin dans l'écriture de l'histoire, plus le retour en arrière peut être compliqué. 

### Dans le working space / espace de travail
"J'ai modifié un fichier, mais je veux revenir à la version initiale de l'histoire". 

`git diff <nomdufichier>` : vous indique toutes les modifications, entre la version courante de l'histoire et votre fichier actuel. Vous avez normalement l'ensemble des modifications. 
`git checkout <nomdufichier>` : c'est l'arme universelle. Elle rétablie le fichier dans la dernière version commitée. Vous avez déjà utilisé cette commande en faisant un `rm -rf *` à la racine du projet. Un `git checkout .` va remonter de manière récursive tous les fichiers et répertoires du projet dans leur dernière version commitée. Il est également possible de revenir vers une version particulière de l'histoire du fichier. `git log` indique les versions accessibles de l'histoire.

`git checkout 17446f4ee9a README.md` : récupère une version particulière de l'histoire
`git checkout HEAD README.md` : récupère la version courante de l'histoire
`git checkout HEAD^ README.md` : remonte dans les versions précédentes
`git checkout HEAD^^ README.md`
`git checkout HEAD^^^ README.md`

### Dans l'index space  
"J'ai ajouté par erreur des fichiers dans l'index space, mais je ne veux pas les mettre". 

`git reset toto.pdf` annule un fichier préparé pour le commit suivant. 

*IL existe de très nombreuses commandes pour manipuler ces deux espaces* 
*Toujours faire attention aux fichiers qui vont être impactés**
*Sans le vouloir fortement, on peut rien perdre dans le Storage Space*


### Depuis le store space
"J'ai commité le fichier, mais l'histoire n'est pas bonne"

`git commit --amend` : modifie la dernière histoire commitée
`git reset --hard HEAD^^` : revient 'offficiellement' sur l'antepénultième version. 

A partir de ce point, vous avez compris comment manipuler git d'un point de vue local. 

Par exemple si vous souhaiter démarrer un nouveau projet git. La commande initiale est `git init`.

Vous pouvez compléter soit en regardant le fonctionnement de bas-niveau (cf td qui accompagne ce Readme), soit regarder la partie distribuée de git. 


# Ecrire l'histoire à plusieurs
Git est un outil extrêmement puissant pour gérer l'évolution du code source d'un projet. Par l'établissement d'un journal de bord qui exprime toutes les évolutions du code. 

Un cas particulier d'utilisation survient lorsque le code est partagé entre plusieurs utilisateurs. 
Dans ce cas, on va utiliser un site de dépot distant qui est capable dans les grandes lignes de stocker le répertoire `.git` du projet et de l'envoyer aux participants du projet. Le service minimal est donc un serveur ssh sécurisé pour accueillir les nouvelles version de la base de données du projet (le repertoire .git).

Le site le plus connu pour gérer ces bases de données est github. Il offre toutes les fonctions suplémentaires que l'ont peut souhaiter dans le co-développement de logiciels ou de manière général de fichiers sources. Git est utilisé pour partager des plans de fabrications, des lois, des résultats de recherche, etc...

A partir de maintenant l'histoire reprend à partir des commit d'un utilisateur et met en relation une machine locale et une machine distante. 

Dans une durée courte il est difficile de créer des comptes github et d'inscrire les participants au projet. Ce qui est la démarche classique. 
Nous allons faire différemment, pour l'exercice, nous allons passer par un accès par clé public/privé au dépot git. 

Je ne détaillerais pas le principe des accès ssh, mais dans l'immédiat, si vous n'avez pas de clé public sur votre machine (dans le répartoire .ssh, le fichier id_rsa.pub ou id_dsa.pub), vous allez créer une paire de clés privé/public. Vous déposerez votre clé public dans un fichier partagé google mis à disposition pendant la séance.

Une fois installée par l'enseignant, cette clé vous permet de récupérer le projet `.git` comme si vous êtiez membre du projet. 
Repartez d'un repertoire vide. 

L'interraction dans un projet se fait dans la démarche suivante :
1. Récupération initiale
   - La première action consiste à récupérer la base initiale avec la commande 'git clone'. 
   - Nous allons faire un clonage particulier par token dédié. 
  


2. A chaque période de développement, commencer à récupérer les modification des autres utilisateurs
   - `git pull`

3. A chaque commit, pousser les données vers le serveur
   - `git push`
git clone https://sfrenot:<token>@github.com/sfrenot/mgl.git

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

Essayez d'avoir toujours une idée en tête de l'état de votre projet par rapport à votre base de données locale : `git log | git reflog`, `git status` et `git diff` sont vos amis. 

Essayez également d'imaginer que toutes les actions associées commit, log, etc. Sont des visualisations de la base de données cachée dans le répertoire `.git`. N'hésitez-pas à y faire un tour par curiosité et voir comment elle évolue lorsque vous faite des actions locales. 

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

# Oui mais, les branches, les tags, rebase, les merges request, le CI/CD et les bissect ?
Si vous commencez à participer à des projets git, vous aller  être confronté à quelques éléments de fonctionnement avancé classiques. 

## Les branches
Les branches sont un mécanisme simple, qui peut devenir cauchemardesque s'il est mal utilisé. Elles sont systématiquement utilisées sur des grands et gros projet afin d'éviter de mélanger l'états des différentes fonctionnalités d'un projet. 

Une branche consiste donc à dévier le projet pour développer une fonction spécifique sur un temps donné, sans bloquer le développement principal. Le principe consiste à ce que le développeur 'branche' le code initial vers un code particulier, qu'il fasse les modification sur ce code sans crainte de casser le code principal, puis de réaliser une action de regroupement de la branche de développement avec sa branche. 

Listing : 
`git branch`
`git branch -a`

Création :
`git branch toto`

Bascule :
`git checkout toto`  --> La commande checkout est donc importante. Elle demande à l'utilisateur de se placer à un endroit particulier avec un pointeur initial


L'option `--dry-run` permet de voir les commandes sans les exécuter. 

