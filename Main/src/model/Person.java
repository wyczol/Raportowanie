package model;

public class Person {
String imie;
String nazwisko;
String numerPokoju;
Integer godzinaRozpoczecia;
Integer godzinaZakonczenia;




public Person(String imie, String nazwisko, String numerPokoju, Integer godzinaRozpoczecia,
		Integer godzinaZakonczenia) {
	this.imie = imie;
	this.nazwisko = nazwisko;
	this.numerPokoju = numerPokoju;
	this.godzinaRozpoczecia = godzinaRozpoczecia;
	this.godzinaZakonczenia = godzinaZakonczenia;
}


public String getImie() {
	return imie;
}
public void setImie(String imie) {
	this.imie = imie;
}
public String getNazwisko() {
	return nazwisko;
}
public void setNazwisko(String nazwisko) {
	this.nazwisko = nazwisko;
}
public String getNumerPokoju() {
	return numerPokoju;
}
public void setNumerPokoju(String numerPokoju) {
	this.numerPokoju = numerPokoju;
}
public Integer getGodzinaRozpoczecia() {
	return godzinaRozpoczecia;
}

public String getGodzinaRozpoczeciaTxt() {
	return godzinaRozpoczecia.toString();
}


public void setGodzinaRozpoczecia(Integer godzinaRozpoczecia) {
	this.godzinaRozpoczecia = godzinaRozpoczecia;
}
public Integer getGodzinaZakonczenia() {
	return godzinaZakonczenia;
}

public String getGodzinaZakonczeniaTxt() {
	return godzinaZakonczenia.toString();
}
public void setGodzinaZakonczenia(Integer godzinaZakonczenia) {
	this.godzinaZakonczenia = godzinaZakonczenia;
}



//public int compare(Person p1, Person p2) {
//    if(p2 == null) return -1;
//    if((p1.godzinaZakonczenia-p1.godzinaRozpoczecia) > (p2.godzinaZakonczenia-p2.godzinaRozpoczecia)) return 1;
//    else if((p1.godzinaZakonczenia-p1.godzinaRozpoczecia) < (p2.godzinaZakonczenia-p2.godzinaRozpoczecia)) return -1;
//    else return 0;
//}

@Override
public String toString() {
	return "Person [imie=" + imie + ", nazwisko=" + nazwisko + ", numerPokoju=" + numerPokoju + ", godzinaRozpoczecia="
			+ godzinaRozpoczecia + ", godzinaZakonczenia=" + godzinaZakonczenia + "]";
}




}
