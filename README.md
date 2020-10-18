# Kalkulato plikowy
# Założenia
- Plik musi zawierać conajmniej jeden wiersz.
- Plik musi zawierać słowo kluczowe 'apply'. Operacje zawarte ponieżej tej linijki będą ignorowane
- Każda linia musi zawierać nazwę operatora (add, multiply, divide, subtract, apply) i tylko jedną liczbę. Muszą one być oddzielone spacją.
- Aby dodać nową operację nalezy stworzyc nową klasę implementującą interfejs Operation i oznaczyć ją adnotacją @Operator
 # Uruchomienie aplikacji
Do uruchomienia aplikacji potrzebny jest zainstalowany Maven 4 oraz Java w wersji 8. Następnie z głownego katalogu aplikacji należy uruchomić komendę 'mvn package'. Następnie z katalogu /target należy uruchomić polecenie java -jar storware-1.0-SNAPSHOT.jar "<nazwa pliku>" , gdzie nazwa pliku to ścieżka do pliku zawierającego instrukcje dla kalkulatora.

# Zewnętrzne biblioteki
 - Lombok 1.18
 - Log4j 2.6
 - Apache.commons 3.9
 - jUnit 5
 - Mockito 3.4
