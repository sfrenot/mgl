mkdir tmp
cd tmp
git init
touch empty
git add empty
git commit -m'Ajout empty dans main'


git branch feature
git checkout feature
echo "prout" > toto
git add toto
git commit -m'Ajout toto dans feature'

git checkout main
echo "Coucou" > toto
git add toto
git commit -m'Ajout toto dans main'

git checkout feature
echo "Comment valider la modification ?"

#git branch branchrebase
#git checkout branchrebase
#touch toto_brancherebase
#git add toto_brancherebase
#git commit -m'Ajout toto_brancherebase'
#git checkout main
#git rebase branchrebase

#touch toto_main
#git add toto_main
#git commit -m'Ajout toto'
