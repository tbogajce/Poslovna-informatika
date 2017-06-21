package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import models.AnalitikaIzvoda;
import models.Banka;
import models.DnevnoStanjeRacuna;
import models.Klijent;
import models.NaseljenoMesto;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import play.mvc.Controller;

public class Klijenti extends Controller{
	
	public static void show(String mode, Long selectedIndex)
	{
		if(session.get("banka_id")!=null)
		{
			List<NaseljenoMesto> naseljenaMesta = NaseljenoMesto.findAll();
			List<Banka> banke = Banka.findAll();
			List<Klijent> klijentix = Klijent.findAll();
			ArrayList<Klijent> klijenti = new ArrayList<Klijent>();
			for(Klijent k : klijentix)
			{
				if(k.getBanka().getId()==Long.valueOf(session.get("banka_id")))
				{
					klijenti.add(k);
				}
			}
			if(mode == null || mode.equals(""))
				mode = "edit";
			render(klijenti, naseljenaMesta, banke,mode,selectedIndex);
		}
		else
		{
			List<NaseljenoMesto> naseljenaMesta = NaseljenoMesto.findAll();
			List<Banka> banke = Banka.findAll();
			List<Klijent> klijenti = Klijent.findAll();
			if(mode == null || mode.equals(""))
				mode = "edit";
			render(klijenti, naseljenaMesta, banke,mode,selectedIndex);
		}
		
	}
	
	
	public static void nextMehanizam(Long id)
	{
		System.out.println(id);
		
		NaseljenoMesto naseljenoMesto = NaseljenoMesto.findById(id);
		List<NaseljenoMesto> naseljenaMesta = NaseljenoMesto.findAll();
		List<Banka> banke = Banka.findAll();
		List<Klijent> klijenti = Klijent.findAll();
		List<Klijent> klijentiZaPrikaz = new ArrayList<Klijent>();
		//List<DnevnoStanjeRacuna> dnevnaStanjaRacuna = DnevnoStanjeRacuna.findAll();
		
		for(Klijent nm : klijenti)
		{
			if(nm.getNaseljenoMesto().id == naseljenoMesto.id)
			{
				klijentiZaPrikaz.add(nm);
				System.out.println("naslo neku analitiku izvoda..");
			}
		}
		String mode = "edit";
		klijenti.clear();
		klijenti.addAll(klijentiZaPrikaz);
		//render(drzave,mestaZaPrikaz,"edit",0);
		Long idZaPrikaz = id;
		renderTemplate("Klijenti/show.html",naseljenaMesta,banke,klijenti,mode,0,idZaPrikaz);
		
	}
	
	
	
	public static void create(Klijent klijent, Long klijent_naseljenoMesto, Long klijent_banka )
	{
		validation.required(klijent.jmbg);
		validation.required(klijent.ime);
		validation.required(klijent.prezime);
		validation.maxSize(klijent.jmbg, 13);
		validation.minSize(klijent.jmbg, 13);
		
		validation.maxSize(klijent.ime, 70);
		validation.maxSize(klijent.prezime, 70);
		validation.maxSize(klijent.adresa, 70);
		validation.maxSize(klijent.telefon, 25);
		validation.email(klijent.email);

		if (validation.hasErrors()) {
			for (play.data.validation.Error error : validation.errors()) {
				System.out.println(error.message());
				validation.keep();
				show("add",null);
			}
		}
		
		System.out.println("CREATE: "+klijent.id+", "+klijent.jmbg);
		//System.out.println("NAS MJ: "+ klijent.getNaseljenoMesto().getId());
		System.out.println("NASELJENO MJESTO ID: "+klijent_naseljenoMesto);
		NaseljenoMesto nm = NaseljenoMesto.findById(klijent_naseljenoMesto);
		Banka b = Banka.findById(klijent_banka);
		System.out.println("JEL PRAVNO: "+klijent.getPravno());
		if(klijent.getPravno()!=null && klijent.getPravno()==true)
		{
			
		}
		else
		{
			klijent.setPravno(false);
		}
		klijent.setBanka(b);
		klijent.setNaseljenoMesto(nm);
		klijent.save();
		show("add",klijent.id);
	}
	
	public static void edit(Klijent klijent,  Long klijent_naseljenoMesto, Long klijent_banka)
	{
		validation.required(klijent.jmbg);
		validation.required(klijent.ime);
		validation.required(klijent.prezime);
		validation.maxSize(klijent.jmbg, 13);
		validation.minSize(klijent.jmbg, 13);
		
		validation.maxSize(klijent.ime, 70);
		validation.maxSize(klijent.prezime, 70);
		validation.maxSize(klijent.adresa, 70);
		validation.maxSize(klijent.telefon, 25);
		validation.email(klijent.email);

		if (validation.hasErrors()) {
			for (play.data.validation.Error error : validation.errors()) {
				System.out.println(error.message());
				validation.keep();
				show("edit",null);
			}
		}
		
		System.out.println("EDIT: "+klijent.id+", "+klijent.jmbg);
		NaseljenoMesto nm = NaseljenoMesto.findById(klijent_naseljenoMesto);
		Banka b = Banka.findById(klijent_banka);
		System.out.println("JEL PRAVNO: "+klijent.getPravno());
		if(klijent.getPravno())
		{
			klijent.setPravno(true);
		}
		else
		{
			klijent.setPravno(false);
		}
		klijent.setBanka(b);
		klijent.setNaseljenoMesto(nm);
		klijent.save();
		show("edit",klijent.id);
		//System.out.println("OVo je naseljeno mjesto: " + klijent_naseljenoMesto);
		//System.out.println("OVo je banka: " + klijent_banka);
	}
	
	public static void filter(Klijent klijent)
	{
		//OVDE JOS DORADITI
		List<Klijent> klijenti = Klijent.find("byJmbgLikeAndImeLikeAndPrezimeLikeAndAdresaLikeAndTelefonLikeAndEmailLike", "%"+klijent.jmbg+"%", "%"+klijent.ime+"%", "%"+klijent.prezime+"%", "%"+klijent.adresa+"%", "%"+klijent.telefon+"%", "%"+klijent.email+"%").fetch();
		String mode = "edit";
		List<NaseljenoMesto> naseljenaMesta = NaseljenoMesto.findAll();
		List<Banka> banke = Banka.findAll();
		renderTemplate("Klijenti/show.html", klijenti,naseljenaMesta, banke, mode );
	}
	
	public static void delete(Long id)
	{
		Klijent kli = Klijent.findById(id);
		kli.delete();
		show("edit", kli.id-1);
	}
	
	public void exportToPdf(Long id,String dod, String ddo) {
		try {
			Klijent klijent = Klijent.findById(id);

			Properties connectionProps = new Properties();

			connectionProps.put("user", "root");
			connectionProps.put("password", "cuko");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poslovna", connectionProps);
			
			dod = dod+" 00:00:00";
			ddo = ddo+" 00:00:00";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date parsedDate1 = dateFormat.parse(dod);
			Date parsedDate2 = dateFormat.parse(ddo);
			Timestamp timestamp1 = new java.sql.Timestamp(parsedDate1.getTime());
			Timestamp timestamp2 = new java.sql.Timestamp(parsedDate2.getTime());
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("ID_Klijent", klijent.getId());
			parameters.put("datumOd", timestamp1);
			parameters.put("datumDo", timestamp2);
			
			File file = new File(System.getProperty("user.dir")+"/Izvestaji/Izvod.jasper");
			JasperPrint jp = JasperFillManager.fillReport(new FileInputStream(file),
					parameters, 
					conn);
			String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
			JasperExportManager.exportReportToPdfFile(jp, "./Izvestaji/Izvestaj_" + klijent.getIme() + "_" + timeStamp +".pdf");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
