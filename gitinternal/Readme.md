Ce TP suppose une connaissance de l'utilisation de git. Les commandes suivantes sont supposées connues :

Refs :
 https://medium.com/@shalithasuranga/how-does-git-work-internally-7c36dcb1f2cf
 https://git-scm.com/book/en/v2/Git-Internals-Git-Objects
 http://etnbrd.github.io/git-cheatsheet/

```bash
  git clone
  git add
  git commit
  git push
  git pull
```
Eventuellement les commandes suivantes sont également connues.
```bash
  git checkout
  git stash
```

L'objectif de ce TP est de comprendre l'architecture de fonctionnement interne de git dans certains détails.

Q1 : Proposez une définition précise de l'objectif de git
Q2 : Proposez une architecture de fonctionnement générale. En répondant par exemple aux questions suivantes.
  - Comment faire pour gérer l'aspect multi-développement du code ?
  - Comment faire pour stocker l'information d'un projet ?
  - Comment faire pour fonctionner sans connexion ?
  - Quels sont les outils de base nécessaires ?

----
Nous allons maintenant étudier le fonctionnement interne de git.

Git est un système de fichiers orienté clé-valeur. C'est à dire que les constituants classiques d'un système de fichiers sont réorganisés autour d'un concept de stockage 'clé/valeur'.

Q3 : Quel structure et quel algorithme permettent de gérer la notion de clé / valeur
Q4 : Donnez pour un fichier dans le répertoire courant la liste des clés nécessaires que vous imaginez

---
Testez vos propositions en créant un dépôt et en contrôlant ce qui est fabriqué.

```bash
 mkdir /tmp/toto
 cd /tmp/toto
 git init
 echo "coucou" > test
 git add test
 git commit -am"Ajout d'un premier fichier"
```

Allez dans le répertoire `.git` et essayez de comprendre la structure du système de fichiers. Le répertoire `branches` n'est plus utilisé, vous pouvez l'ignorer.

Q5 : Donnez une explication des différents répertoires et de de leur contenu.

Git possède des fonction de haut-niveau pour manipuler le système.
`git add / git commit` par exemple. Ces commandes reposent sur des d'autres commandes unitaires pour manipuler la structure.

---
La commande `git cat-file -p <SHA-1 Signature>` permet d'afficher le contenu d'un fichier dont la clé est donné en paramètre.

Q6 : Vérifiez que vous arrivez à tracer les différents fichiers du système de stockage git.

---
La commande `git hash-object -w <File>`, permet de stocker un fichier dans la base d'objets. Elle calcule le SHA-1 du fichier et en place.

Q7 : Comment afficher le contenu du fichier obtenu ? Quel est le format ?

---
Pour l'instant votre fichier est ajouté à la base de donnée de stockage. Mais il n'est pas référencé dans votre arbre de fichier.

Q8 : Quelles sont les commandes à exécuter pour trouver le fichier de description de l'arbre ?

--
L'ajout d'un nouveau fichier dans un projet git se fait avec les commandes de haut-niveau suivantes :
```
1- Création du fichier
2- Ajout du fichier dans l'index : git add <nom du fichier>
3- Ecriture de l'histoire dans le local repository : git commit -m'<Description du commit>'
```

Q9 : Testez l'ajout d'un fichier dans votre historique git.
--
Nous allons réaliser la même action avec les commandes de base.

Vous avez déjà ajouté un fichier à votre repository local en utilisant la commande `git hash-object`. Voici les commandes permettant d'écrire l'histoire.

```
git update-index --add --cacheinfo 100644 <Sig> <name> : Ajoute le fichier de signature Sig et de nom name dans l'index.
git write-tree : Ecrit l'index dans le repository local.
git ls-files --stage : Liste les fichiers de l'index (stage)
git commit-tree <NewSig> -p <PrevSig> : Ecrit l'histoire de commit, en reliant le nouvel arbre à l'arbre précédent.
```

La commande `git log` permet de lister l'ordre des commits réalisés dans un repository. Attention le point d'entrée du log se trouve indiqué dans un fichier particulier...

Exercice :
Partez d'un nouveau repository vide, dans lequel vous commiterez un fichier test.txt, vide.

git log doit produire la sortie suivante :
```
commit 1d98175a10e983f63be300019106635863ef912a (HEAD -> master)
Author: Stephane Frenot <stephane.frenot@insa-lyon.fr>
Date:   Fri Jan 11 14:42:35 2019 +0100

    Ajout fichier
```

Ajoutez un second fichier dans votre worspace, puis integrez le dans votre histoire git. Avec les commandes suivantes :

```
git hash-object
git update-index
git write-tree
git commit-tree
```

Vous testerez votre résultat de deux manières. En lançant la commande `git log` ainsi qu'avec la commande git checkout, une fois avoir tout supprimé dans le répertoire courant (sauf le sous-répertoire .git)
