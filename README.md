# apicom-referentiels

API qui retournent des référentiels sous forme de listes (Langues, Pays, Geonames, etc.).

Les référentiels sont accessibles en JSON aux adresses suivantes : 
- https://apicom.sudoc.fr/wsReferentiels/wsReferentiels/langues.json
- https://apicom.sudoc.fr/wsReferentiels/wsReferentiels/pays.json
- https://apicom.sudoc.fr/wsReferentiels/wsReferentiels/languris.json
- https://apicom.sudoc.fr/wsReferentiels/wsReferentiels/geonames.json

Il est également possible d'obtenir les valeurs au format XML :
- https://apicom.sudoc.fr/wsReferentiels/wsReferentiels/langues.xml
- https://apicom.sudoc.fr/wsReferentiels/wsReferentiels/pays.xml
- https://apicom.sudoc.fr/wsReferentiels/wsReferentiels/languris.xml
- https://apicom.sudoc.fr/wsReferentiels/wsReferentiels/geonames.xml

Le but premier de ces API est de mutualiser l'usage de ces référentiels dans les applications développées par l'Abes.

## Architecture

Ces API viennent exposer les données contenues dans les tables Oracle de l'Abes :
- LANG_LABEL : pour le endpoint "langues"
- COUNTRY_LABEL : pour le endpoint "pays"
- LANG_URI : pour le endpoint "languris"
- COUNTRY_URI : pour le endpoint "geonames"

A noter : la structure ces tables n'est pas actuellement documenté dans le github.

Un système de cache est en place. Il permet d'éviter de solliciter la base Oracle à chaque appel des API. Le cache a une durée de vie qui est réglée par la variable `cron.majCache`. Cette variable permet de vider le cache à interval de temps régulier. Exemple ici :
https://github.com/abes-esr/apicom-referentiels/blob/master/src/main/resources/application.properties#L1
