# apicom-referentiels

API qui retournent des référentiels sous forme de listes (Langues, Pays, Geonames, PcpLibs, etc.).

Les référentiels sont accessibles en JSON aux adresses suivantes (url de prod) : 
- https://apicom.sudoc.fr/wsReferentiels/v1/langues.json
- https://apicom.sudoc.fr/wsReferentiels/v1/pays.json
- https://apicom.sudoc.fr/wsReferentiels/v1/languris.json
- https://apicom.sudoc.fr/wsReferentiels/v1/geonames.json
- https://apicom.sudoc.fr/wsReferentiels/v1/pcplibs.json
- https://apicom.sudoc.fr/wsReferentiels/v1/iso639-2B.json
- https://apicom.sudoc.fr/wsReferentiels/v1/iso639-3.json
- https://apicom.sudoc.fr/wsReferentiels/v1/ecritures.json
- https://apicom.sudoc.fr/wsReferentiels/v1/translitterations.json
- https://apicom.sudoc.fr/wsReferentiels/v1/roles.json
- https://apicom.sudoc.fr/wsReferentiels/v1/musicaux.json

Il est également possible d'obtenir les valeurs au format XML :
- https://apicom.sudoc.fr/wsReferentiels/v1/langues.xml
- https://apicom.sudoc.fr/wsReferentiels/v1/pays.xml
- https://apicom.sudoc.fr/wsReferentiels/v1/languris.xml
- https://apicom.sudoc.fr/wsReferentiels/v1/geonames.xml
- https://apicom.sudoc.fr/wsReferentiels/v1/pcplibs.xml
- https://apicom.sudoc.fr/wsReferentiels/v1/iso639-2B.xml
- https://apicom.sudoc.fr/wsReferentiels/v1/iso639-3.xml
- https://apicom.sudoc.fr/wsReferentiels/v1/ecritures.xml
- https://apicom.sudoc.fr/wsReferentiels/v1/translitterations.xml
- https://apicom.sudoc.fr/wsReferentiels/v1/roles.xml
- https://apicom.sudoc.fr/wsReferentiels/v1/musicaux.xml

Le but premier de ces API est de mutualiser l'usage de ces référentiels dans les applications développées par l'Abes.

Description OpenAPI : https://apicom.sudoc.fr/wsReferentiels/swagger-ui.html 

## Architecture

Ces API viennent exposer les données contenues dans les tables Oracle de l'Abes :
- LANG_LABEL et LANG_ISO_639_2_TO_1 : pour le endpoint "langues" et "iso639-2B"
- LANG_ISO_639_3 : pour le endpoint "iso639-3"
- COUNTRY_LABEL : pour le endpoint "pays"
- LANG_URI : pour le endpoint "languris"
- COUNTRY_URI : pour le endpoint "geonames"
- VIEW_LIST_BIBS : pour le endpoint "pcplibs"
- CODE_ECRITURE : pour le endpoint "ecritures"
- CODE_TRANSLITTERATION : pour le endpoint "translitterations"
- FNCT_MARC21 : pour le endpoint "roles"
- CODE_MUSICAL : pour le endpoint "musicaux"

A noter : la structure de ces tables n'est pas actuellement documentée dans le github.

Un système de cache est en place. Il permet d'éviter de solliciter la base Oracle à chaque appel des API. Le cache a une durée de vie qui est réglée par la variable `cron.majCache`. Cette variable permet de vider le cache à interval de temps régulier. Exemple ici :
https://github.com/abes-esr/apicom-referentiels/blob/master/src/main/resources/application.properties#L1

