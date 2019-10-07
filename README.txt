Nume, prenume: Calatoaie Iulia-Adriana
Grupa, seria: 325CA

Algoritmul rezolvarii:

-folderul utils:
   ->clasa Context:
	-am adaugat operatiile trackable in staging
   ->clasa OperationFactory:
	-am adaugat operatiile vcs descrise in cerinta cu adaugarea operatiei
	InvalidVcsOperation, in cazul in care comanda data nu se afla in registrul
	cerintei; fiecare caz returneaza un obiect de tipul comenzii specifice
   ->clasa OperationParser:
	-am completat metoda private AbstractOperation parseVcsOperation(String 
	newOperation, ArrayList<String> operationArgs) dupa modelul celei ce
	prelucreaza type-ul si parametrii comenzilor referitoare la prelucrarea
	sistemului de fisiere pentru a genera un obiect de tipul comenzii vcs, prin
	intermediul clasei OperationFactory; daca nu exista un tip valid, se va crea
	un obiect de tip InvalidVcsOperation 
   ->enumerarea OperationType:
	->am adaugat comenzile vcs

-folderul vcs:
   ->clasa Branch:
	-agrega clasa Commit si e agregat de clasa Vcs; un branch contine mai multe
	commit-uri si un nume
   ->clasa Commit:
	-e agregata de clasa Branch; un commit incapsuleaza un mesaj, un id generat
	cu ajutorul clasei IDGenerator(nu e nevoie instantierea ei, fapt pentru care
	s-a folosit pattern-ul singletone) si starea sistemului de fisiere in momentul
	in care s-a dat commit
   ->clasa Vcs:
	-un vcs contine starea sistemului de fisiere, una/mai multe branch-uri, o
	lista de operatii de staging si un head ce pointeaza la commit-ul curet;
	primul brancheste master; o operatie vcs lucreaza pe instanta curenta vcs
   ->in continuare fiecare comanda vcs descrisa in cerinta va mosteni clasa
   VcsOperation si va avea tipul final, pentru ca nu este destinata in
   continuare mostenirii;doar clasa InvalidVcsOperation in metoda de execute
   va returna codul de eruare pentru comanda invalida 