package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.mbp.BankaPosiljalac;
import model.mbp.BankaPrimalac;
import model.mbp.DetaljiPrenosa;
import model.mbp.MedjubankarskiPrenosi;
import model.xmlws.Nalozi;
import models.AnalitikaIzvoda;
import models.Banka;
import models.DnevnoStanjeRacuna;
import models.MedjubankarskiPrenos;
import models.Racun;
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
		
		if(session.get("radnik_id")!=null && session.get("banka_id")!=null)
		{
			try {
				//
				
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
		{
			System.out.println("DUZNIK: "+nalogZaPrenos.getPodaciODuzniku().getIme() + " " + nalogZaPrenos.getPodaciODuzniku().getPrezime());
			System.out.println("POVERIOC: "+nalogZaPrenos.getPodaciOPoveriocu().getNaziv());
			System.out.println("DUZNIK RACUN: "+  nalogZaPrenos.getTransferPodaciDuznik().getRacunDuznika());
			System.out.println("PRIMAOC RACUN: "+  nalogZaPrenos.getTransferPodaciPoverioc().getRacunPoverioca());
			
			java.util.List<Racun> racuni1 =  Racun.find("byBrojRacuna", nalogZaPrenos.getTransferPodaciDuznik().getRacunDuznika()).fetch();
			java.util.List<Racun> racuni2=  Racun.find("byBrojRacuna", nalogZaPrenos.getTransferPodaciPoverioc().getRacunPoverioca()).fetch();
			
			
			
			/*
			System.out.println("R1 size: "+racuni1.size());
			System.out.println("R2 size: "+racuni2.size());
			
			for(Racun rac : racuni1)
			{
				System.out.println(rac.getId());
			}
			*/
			if(racuni1.size()!=0 && racuni2.size()!=0)
			{
				Racun racun1 = racuni1.get(0);
				Racun racun2 = racuni2.get(0);
				
				String banka_id = session.get("banka_id");
				
				System.out.println("BANKA ID: "+banka_id);
				
				if(String.valueOf(racun1.getBanka().getId()).equals(banka_id))
				{
					//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
					//Date date = new Date();
					//System.out.println(/*dateFormat.format(date)*/);
					
					//System.out.println(cal.get); //2016/11/16 12:08:43
					//odradiAutomatizovaneRadnje( racun1, nalogZaPrenos);
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					Date date = cal.getTime();
					//System.out.println(dateFormat.format(date));
					
					java.util.List<DnevnoStanjeRacuna> dsr_list = DnevnoStanjeRacuna.find("byDatumAndRacun", date,racun1).fetch();
					
					
					DnevnoStanjeRacuna dsr = null;
					if(dsr_list.size()!=0)
					{
						dsr = dsr_list.get(0);
						
						double pare =Double.valueOf(nalogZaPrenos.getPodaciOPrenosu().getIznos());
						double prethodni_p_n_t=dsr.prometNaTeret;
						//double prethodni_p_u_k=dsr.prometUKorist;
						double prethodno_novo_stanje=dsr.novoStanje;
						
						
						dsr.setNovoStanje(prethodno_novo_stanje-pare);
						dsr.setPrometNaTeret(prethodni_p_n_t-pare);
						//dsr.setPrometUKorist(prethodni_p_u_k);
					}
					else
					{
						java.util.List<DnevnoStanjeRacuna> dsr_list2 = DnevnoStanjeRacuna.find("byRacun",racun1).fetch();

						if(dsr_list2.size()==0)
						{
							double pare =Double.valueOf(nalogZaPrenos.getPodaciOPrenosu().getIznos());
							dsr=new DnevnoStanjeRacuna(date,0,0-pare,0,0-pare,racun1);
						}
						else
						{
							DnevnoStanjeRacuna prethodno_dnevno_stanje = dsr_list2.get(0);
							
							for(DnevnoStanjeRacuna dsrx : dsr_list2)
							{
								if(dsrx.getId()>prethodno_dnevno_stanje.getId())
								{
									prethodno_dnevno_stanje=dsrx;
								}
							}
							
							double pare =Double.valueOf(nalogZaPrenos.getPodaciOPrenosu().getIznos());
							double staro_stanje = prethodno_dnevno_stanje.getNovoStanje();
							dsr=new DnevnoStanjeRacuna(date,staro_stanje,0-pare,0,staro_stanje-pare,racun1);
							
						}
						
					}
					dsr.save();
					
					double iznos = Double.valueOf(nalogZaPrenos.getPodaciOPrenosu().getIznos());
					
					
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dateInString = nalogZaPrenos.getDatumValute();
					Date datumAnal=null;
					try {
						datumAnal = sdf.parse(dateInString);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					AnalitikaIzvoda ai = new AnalitikaIzvoda(date,'-',nalogZaPrenos.getPodaciODuzniku().getIme()+" "+nalogZaPrenos.getPodaciODuzniku().getPrezime(),
							nalogZaPrenos.getSvrhaPlacanja(),nalogZaPrenos.getPodaciOPoveriocu().getNaziv(),
							datumAnal,datumAnal,nalogZaPrenos.getTransferPodaciDuznik().getRacunDuznika(),
							nalogZaPrenos.getTransferPodaciDuznik().getModel(),
							nalogZaPrenos.getTransferPodaciDuznik().getPozivNaBrojDuznika(),
							nalogZaPrenos.getTransferPodaciPoverioc().getRacunPoverioca(),
							nalogZaPrenos.getTransferPodaciPoverioc().getModel(),
							nalogZaPrenos.getTransferPodaciPoverioc().getPozivNaBrojPoverioca(),
							iznos,nalogZaPrenos.getPodaciOPrenosu().getValuta(),dsr);
					
					ai.save();
					
					
				}
				else
				{
					//TODO MEDJUBANKARSKI PRENOS VALJDA ... NESTO
					System.out.println("Ovde sad valjda treba eksportovati jer je ovo DUznicki koji je u DRUGOJ banci");
					//mislim da ovde ne treba eksport jer se ovo ne bi trebalo dogoditi
					//nisam siguran, provjeriti
				}
				
				if(String.valueOf(racun2.getBanka().getId()).equals(banka_id))
				{
					//odradiAutomatizovaneRadnje( racun2, nalogZaPrenos);
					//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
					//Date date = new Date();
					//System.out.println(/*dateFormat.format(date)*/);
					
					//System.out.println(cal.get); //2016/11/16 12:08:43
					//odradiAutomatizovaneRadnje( racun1, nalogZaPrenos);
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					//System.out.println("OVO JE: "+cal.getTime());
					//cal.set(Calendar.MILLISECOND,0);
					Date date = cal.getTime();
					
					
					
					//System.out.println(dateFormat.format(date));
					
					java.util.List<DnevnoStanjeRacuna> dsr_list = DnevnoStanjeRacuna.find("byDatumAndRacun", date,racun2).fetch();
					
					
					DnevnoStanjeRacuna dsr = null;
					if(dsr_list.size()!=0)
					{
						dsr = dsr_list.get(0);
						
						double pare =Double.valueOf(nalogZaPrenos.getPodaciOPrenosu().getIznos());
						//double prethodni_p_n_t=dsr.prometNaTeret;
						double prethodni_p_u_k=dsr.prometUKorist;
						double prethodno_novo_stanje=dsr.novoStanje;
						
						
						dsr.setNovoStanje(prethodno_novo_stanje+pare);
						//dsr.setPrometNaTeret(prethodni_p_n_t-pare);
						dsr.setPrometUKorist(prethodni_p_u_k+pare);
					}
					else
					{
						java.util.List<DnevnoStanjeRacuna> dsr_list2 = DnevnoStanjeRacuna.find("byRacun",racun2).fetch();

						if(dsr_list2.size()==0)
						{
							double pare =Double.valueOf(nalogZaPrenos.getPodaciOPrenosu().getIznos());
							dsr=new DnevnoStanjeRacuna(date,0,0,0+pare,0+pare,racun2);
						}
						else
						{
							DnevnoStanjeRacuna prethodno_dnevno_stanje = dsr_list2.get(0);
							
							for(DnevnoStanjeRacuna dsrx : dsr_list2)
							{
								if(dsrx.getId()>prethodno_dnevno_stanje.getId())
								{
									prethodno_dnevno_stanje=dsrx;
								}
							}
							
							double pare =Double.valueOf(nalogZaPrenos.getPodaciOPrenosu().getIznos());
							double staro_stanje = prethodno_dnevno_stanje.getNovoStanje();
							dsr=new DnevnoStanjeRacuna(date,staro_stanje,0,0+pare,staro_stanje+pare,racun2);
						}
						
					}
					dsr.save();
					
					
					double iznos = Double.valueOf(nalogZaPrenos.getPodaciOPrenosu().getIznos());
					
					
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dateInString = nalogZaPrenos.getDatumValute();
					Date datumAnal=null;
					try {
						datumAnal = sdf.parse(dateInString);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					AnalitikaIzvoda ai = new AnalitikaIzvoda(date,'+',nalogZaPrenos.getPodaciODuzniku().getIme()+" "+nalogZaPrenos.getPodaciODuzniku().getPrezime(),
							nalogZaPrenos.getSvrhaPlacanja(),nalogZaPrenos.getPodaciOPoveriocu().getNaziv(),
							datumAnal,datumAnal,nalogZaPrenos.getTransferPodaciDuznik().getRacunDuznika(),
							nalogZaPrenos.getTransferPodaciDuznik().getModel(),
							nalogZaPrenos.getTransferPodaciDuznik().getPozivNaBrojDuznika(),
							nalogZaPrenos.getTransferPodaciPoverioc().getRacunPoverioca(),
							nalogZaPrenos.getTransferPodaciPoverioc().getModel(),
							nalogZaPrenos.getTransferPodaciPoverioc().getPozivNaBrojPoverioca(),
							iznos,nalogZaPrenos.getPodaciOPrenosu().getValuta(),dsr);
					ai.save();
					
				}
				else
				{
					//TODO MEDJUBANKARSKI PRENOS VALJDA ... NESTO
					System.out.println(" OVO SAD VALJDA TREBA EKSPORTOVATI JER JE OVO PRIMAOCKI RACUN U DRUGOJ BANCI");
					/*
					 * Racun poverioca nije iz te banke koja je prijavljena
					 * Sto znaci da je potrebno napraviti medjubankarski prenos za narodnoj banci
					 * */
					
					//AKO JE HITNO ILI IZNOS VECI OD 250000
					String iznosIzXml = nalogZaPrenos.getPodaciOPrenosu().getIznos();
					if(nalogZaPrenos.isHitno() || Double.parseDouble(iznosIzXml)>250000) {
						System.out.println("USAO U IF");
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
						Date date = new Date();
						String datum = dateFormat.format(date);
						String izn = nalogZaPrenos.getPodaciOPrenosu().getIznos();
						Double iznos = Double.parseDouble(izn);
						Banka bankaPosiljalac = racun1.getBanka();
						Banka bankaPrimalac = racun2.getBanka();
						MedjubankarskiPrenos mbp = new MedjubankarskiPrenos("MT103", datum, iznos, bankaPosiljalac, bankaPrimalac);
						//i sada snimiti taj objekat
						//MARSHALLING
						try {
							JAXBContext context = JAXBContext.newInstance("model.mbp");
							// Marshaller je objekat zadužen za konverziju iz objektnog u XML model
							Marshaller marshaller = context.createMarshaller();
							
							// Podešavanje marshaller-a
							marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
							
							MedjubankarskiPrenosi medjBanPrenosi = new MedjubankarskiPrenosi();
							//Izgen ponovo
							BankaPosiljalac bp1 = new BankaPosiljalac();
							bp1.setNaziv(bankaPosiljalac.getNazivBanke());
							bp1.setObracunskiRacun(bankaPosiljalac.getObracunskiRacun());
							bp1.setSifra(bankaPosiljalac.getSifraBanke());
							bp1.setSwiftKod(bankaPosiljalac.getSwiftKod());
							
							BankaPrimalac bp2 = new BankaPrimalac();
							bp2.setNaziv(bankaPrimalac.getNazivBanke());
							bp2.setObracunskiRacun(bankaPrimalac.getObracunskiRacun());
							bp2.setSifra(bankaPrimalac.getSifraBanke());
							bp2.setSwiftKod(bankaPrimalac.getSwiftKod());
							
							DetaljiPrenosa dp = new DetaljiPrenosa();
							dp.setDatum(datum);
							dp.setIznos(izn);
							dp.setPoruka("MT103");
							
							
							medjBanPrenosi.setBankaPosiljalac(bp1);
							medjBanPrenosi.setBankaPrimalac(bp2);
							medjBanPrenosi.setDetaljiPrenosa(dp);
							

						    FileOutputStream fos = new FileOutputStream("MBP "+medjBanPrenosi.getBankaPosiljalac() +".xml");
						    ObjectOutputStream oos = new ObjectOutputStream(fos);
						    oos.writeObject(medjBanPrenosi);
						   
							
							// Umesto System.out-a, može se koristiti FileOutputStream
							marshaller.marshal(medjBanPrenosi, System.out);
							marshaller.marshal(medjBanPrenosi, fos);
							 oos.close();
						} catch(Exception e) {
							e.printStackTrace();
						}
					} else {
						//UKOLIKO JE MANJE I NIJE HITNO, onda mozda sacuvati u bazu pa poslati nekad kasnije
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
						Date date = new Date();
						String datum = dateFormat.format(date);
						String izn = nalogZaPrenos.getPodaciOPrenosu().getIznos();
						Double iznos = Double.parseDouble(izn);
						Banka bankaPosiljalac = racun1.getBanka();
						Banka bankaPrimalac = racun2.getBanka();
						MedjubankarskiPrenos mbp = new MedjubankarskiPrenos("MT103", datum, iznos, bankaPosiljalac, bankaPrimalac);
						mbp._save();
					}
					
					
				}//END ELSE
				
			}
		}
		
	}
	
	
	
}
