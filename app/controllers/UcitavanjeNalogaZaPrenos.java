package controllers;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.xmlws.Nalozi;
import models.Radnik;
import play.mvc.Controller;

public class UcitavanjeNalogaZaPrenos extends Controller{

	
	public static void show(String mode)
	{
		if(mode == null || mode.equals(""))
			mode = "obradi";
		render(mode);
	}
	
	
	public static void obradi(String nazivFajla)
	{
		
		
		System.out.println("Naziv fajla: " +nazivFajla);
		
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		
		
		try {
			
			System.out.println("JAXB unmarshalling.\n");
			
			// Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("model.xmlws");
			
			// Unmarshaller je objekat zadužen za konverziju iz XML-a u objektni model
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			// Unmarshalling generiše objektni model na osnovu XML fajla 
			//Nalozi nalozi = (Nalozi) unmarshaller.unmarshal(new File(nazivFajla/*primjerXml.xml"*/));
			Nalozi nalozi = (Nalozi) unmarshaller.unmarshal(new File(System.getProperty("user.dir")+"/public/xmlovi/"+nazivFajla/*primjerXml.xml"*/));

			// Prikazuje unmarshallovan objekat
			printNalozi(nalozi);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		/*
		System.out.println("Radnik: "+radnik.nadimak+" , "+radnik.sifra);
		
		//List<Radnik> rr= Radnik.find("byNadimakAndSifra", "%"+radnik.nadimak+"%","%"+radnik.sifra+"%").fetch();
		
		List<Radnik> rr = Radnik.findAll();
		boolean naslo = false;
		
		for(Radnik r : rr)
		{
			//System.out.println("NADJENO: "+ r.id);
			if(r.nadimak.equals(radnik.nadimak) && r.sifra.equals(radnik.sifra))
			{
				System.out.println("R ID:"+r.id);
				naslo = true;
				session.put("radnik", r);
				session.put("banka", r.banka);
				String mode = "obradi";
				renderTemplate("UcitavanjeNalogaZaPrenos/show.html", mode );
			}
		}
		
		String mode = "login";
		renderTemplate("Logovanje/show.html", mode );
		*/
		/*
		if(rr!=null && rr.size()!=0)
		{
			System.out.println("R ID:"+rr.get(0).id);
		}
		else
		{
			System.out.println("Nije moglo naci..");
		}
		*/
		//session.
		//OVDE JOS DORADITI
		//List<Klijent> racuni = Klijent.find("byBrojRacunaLikeAndStatusLike", "%"+racun.brojRacuna+"%", "%"+racun.status+"%").fetch();
		//String mode = "edit";
		//renderTemplate("Racuni/show.html", racuni, mode );
	}
	
	private static void printNalozi(Nalozi nalozi) {
		
		// Prikaz svih odseka
		for(Nalozi.NalogZaPrenos nalogZaPrenos : nalozi.getNalogZaPrenos())
			System.out.println(nalogZaPrenos.getPodaciODuzniku().getIme());
		
	}
	
}
