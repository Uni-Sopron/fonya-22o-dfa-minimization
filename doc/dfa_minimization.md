
# Automata minimalizálás

## Bevezetés

A formális nyelvekhez kapcsolódóan találkozhatunk a véges automaták elméletével, mely a számelmélet aktívan kutatott ága. A véges automatákon végzett műveletek jól algoritmizálhatók, mert működésük egyszerű. Egy széleskörűen kutatott terület a véges automaták minimalizálása.

Adott véges automata a szükségesnél több állapotot és átmenetet tartalmaz, aminek eredményeként nehezen átlátható és nem optimálisan viselkedik. A minimalizálás eredményeként konstruálunk egy kisebb állapotszámú automatát, ami ugyanazt a nyelvet ismeri fel, mint az eredeti.

Leegyszerűsítve: az automatában található redundanciát megszűntetjük úgy, hogy a felismert nyelvet nem változtatjuk.

A minimalizálás hatékonyan végezhető determinisztikus véges automatákon (DFA), ezért ebben a dokumentumban kizárólag DFA-val foglalkozunk. A nemdeterminisztikus automaták minimalizálási problémája túl mutat ezen jegyzet részletességén és célján.

## Minimalizálás

Adott L nyelvhez készítünk egy DFA-t, azt célszerű a legegyszerűbb formára hozni anélkül, hogy a felismert nyelvből ne veszítsünk szavakat. Az ekvivalens automaták közül válasszuk ki a minimál automatát. Két DFA akkor ekvivalens, ha az általuk felismert nyelv megegyezik. Adott M automata akkor minimális, ha nem létezik M’ automata, ami M-mel ekvivalens és állapotainak száma kisebb. Továbbá egy M automata redukált, ha minden állapota elérhető.

#### Minimalizálás lépései
1.	Redukált DFA készítése. Elérhető állapotok megkeresése, nem elérhetők törlése.
2.	Ekvivalens állapotok megkeresése.
3.	Ekvivalens állapotok összevonása az élek átirányításával.

Állapotok közötti ekvivalenciareláció: p és q állapotot akkor tekintjük ekvivalensnek, ha az automata a p állapotból indítva ugyanazt a nyelvet fogadja el, mint a q állapotból indítva. 

### Gyakran használt algoritmusok
Hopcroft algoritmus hatékonysága miatt elterjedt, a leggyorsabb algoritmus worst-case scenario esetén. Megfelelő implementáció mellet O(nlogn) futási idővel rendelkezik, ha a kiinduló automata állapotainak száma n.

Moorre algoritmusát akkor használják, ha néhány rossz futási idő jelentősége elenyésző az átlagos futási időhöz képest. Habár worst-case futási ideje O(n2), átlagos futási ideje akárO(nlog logn)-re is csökkenhet ami lényegesen jobb idő, mint Hopcroft algoritmusa esetében.

A fent említett algoritmusok jól használhatók a determinisztikus véges automatákon, ám nemdeterminisztikus automatákon nem működnek. Brzozowski algoritmusa habár kevésbé hatékony, megoldást kínál erre a problémára. A DFA éleit megfordítva NFA-t kapunk az eredeti nyelv fordítottjára, amit Rabin-Scott algoritmusával DFA-ra transzformálva egy minimális automatás kapunk a fordított nyelvre. Ha újra elvégezzük a műveletet, egy minimális automatát kapunk az eredeti nyelvre. Érezhető, hogy futási ideje nem túl kedvező: exponenciális. 

## DFA minimalizálás példákkal

#### Táblázat kitöltéses módster, Myphill-Nerode tétel felhasználásával
Mellékelt PDF.

### TODO Java kód minimalizálás


## Érdekesség

#### Hiperminimalizálás
Érdekesség és viszonylag új kutatási terület a hiperminimalizálás, melynek során előre meghatározott hibaprofilnak megfelelően megengedünk eltéréseket az eredeti és a minimál automata által felismert nyelvben. A klasszikus minimalizálás során veszteségmentes tömörítésről beszélünk. A hiperminimalizálás veszteséges tömörítést valósít meg, a felismert nyelv részeinek kárára, de cserébe tovább csökkenthetjük az automata méretét.
