# Fejlesztés mobil eszközökre II. – Beadandó feladat

## Téma
Receptkereső Android alkalmazás

## Leírás

Az alkalmazás célja, hogy a felhasználók receptekre kereshessenek, majd megtekinthessék azok részletes elkészítési módját és a szükséges hozzávalókat.

Az alkalmazás a **TheMealDB** nyilvános REST API-ját használja  
(https://www.themealdb.com/api.php), amely lehetővé teszi különböző receptek keresését és azok adatainak lekérdezését.

## Funkciók

- Recepteket lehet keresni a TheMealDB API segítségével.
- A keresési találatok listájából egy recept kiválasztásával megjelennek annak részletei.
- A részletek között láthatók az elkészítési instrukciók és a szükséges hozzávalók.
- A felhasználó megadhatja, hogy az adott ételt hány főre szeretné elkészíteni, így a hozzávalók mennyisége automatikusan módosul.
- A hozzávalók egy bevásárlólistához adhatók.
- A felhasználó elmentheti a recepteket a **Kedvencek** közé, illetve törölheti azokat onnan.
- Az alkalmazás eltárolja a **legutóbbi kereséseket**, amelyeket a felhasználó később visszanézhet.
