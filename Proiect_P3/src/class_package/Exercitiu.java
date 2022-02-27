package class_package;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Exercitiu implements Serializable{

	String nume;
	List <Date> date = new ArrayList<>();
	List <Integer> serii = new ArrayList<>();
	
	public Exercitiu(){

	}
	
	public Exercitiu(String nume, List<Date> date, List <Integer> serii) {
		
		this.nume = nume;
		this.date = date;
		this.serii = serii;
	}
	
	public Exercitiu(String nume, Date date, int serii) {
		this.nume = nume;
		this.date.add(date);
		this.serii.add(serii);
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public List<Date> getDate() {
		return date;
	}

	public void setDate(List<Date> date) {
		this.date = date;
	}

	public List<Integer> getSerii() {
		return serii;
	}

	public void setSerii(List<Integer> serii) {
		this.serii = serii;
	}

	public void afisare() {
		System.out.println("Exercitiu: "+ nume);
		for(Date i: date) {
			System.out.print(i + " ");
		}
		System.out.println();
		for(int i: serii) {
			System.out.print(i + " ");
		}
		System.out.println();
		
	}
	
	public String getSimpleName() {
		return "Exerciti";
	}
	
	@Override
	public String toString() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		return "Exercitiu: " + nume + "\nZile: " + formatter.format(date) + "\nNr. Serii: " + serii;
	}
	
	public String toFile() {
		return nume + date + serii;
	}
	
	public void add(Date new_date, int new_serii, List<Integer> new_gr, List<Integer> new_rep, List<Integer> new_t) {
		date.add(new_date);
		serii.add(new_serii);
	}
	
}
