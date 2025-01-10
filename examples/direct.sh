mkdir tmp
cd tmp
git init
echo "A" > empty
git add empty
git commit -m'Ajout A dans empty/main'

echo "B" >> empty
git add empty
git commit -m'Ajout B dans empty/feature'

echo "C" > empty
git add empty
git commit -m'Ajout C dans main'

