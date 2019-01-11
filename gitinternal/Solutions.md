Q7 : Comment afficher le contenu du fichier obtenu ? Quel est le format ?
https://unix.stackexchange.com/questions/22834/how-to-uncompress-zlib-data-in-unix
https://stackoverflow.com/questions/22968856/what-is-the-file-format-of-a-git-commit-object
https://git-scm.com/book/en/v2/Git-Internals-Git-Objects


printf "\x1f\x8b\x08\x00\x00\x00\x00\x00" |cat - 63/2e4fe73c3da8c9018008dbda117fc6b00e3e83 | gzip -dc

zlib-flate -uncompress < 63/2e4fe73c3da8c9018008dbda117fc6b00e3e83 (package qpdf)

Q8 : Quelles sont les commandes à exécuter pour trouver le fichier de description de l'arbre ?

```
more .git/refs/heads/master // Affiche le point d'entrée du commit
git cat-file -p 444d2b7b49e3131c905f2e0618f6ebac214ecd2d // Affiche le dernier commit
git cat-file -p ea329721883797341b304d0994b0efe54e2a9404 // Affiche la structure de l'arbre
```
Q9 :
```
echo "coucou" > fichier.txt
git hash-object -w ./fichier.txt
git update-index --add --cacheinfo 100644 28d0af969b32e69a389087d7a267a2ecc05f1350 fichier.txt
git write-tree
more .git/refs/heads/master
git commit-tree 640cad9ecf22abbb858359549a5acd32ce034d16 -p 1d98175a10e983f63be300019106635863ef912a
echo f84f74ed8bad237cda91f2be2e96898b978fa1bf > .git/refs/heads/master
```

git log
rm *
git checkout .
