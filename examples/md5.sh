for i in {0..1000}; do
  echo "Bonjour $i" > md5.txt
  cat md5.txt; echo `md5 md5.txt`
done
