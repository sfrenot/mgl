mkdir tmp
cd tmp
git init
echo "A" > empty
git add empty
git commit -m'Ajout A dans empty/main'

git branch feature
git checkout feature

echo "B" > empty2
git add empty2
git commit -m'Ajout B dans empty/feature'

git checkout main
echo "C" > empty
git add empty
git commit -m'Ajout C dans main'

git checkout feature
echo "Comment valider la modification ?"

