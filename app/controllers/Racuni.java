package controllers;

import java.util.ArrayList;
import java.util.List;

import models.AnalitikaIzvoda;
import models.Banka;
import models.Klijent;
import models.NaseljenoMesto;
import models.Racun;
import models.ZatvaranjeRacuna;
import play.mvc.Controller;

public class Racuni extends Controller{
		
	public static void show(String mode, Long selectedIndex)
	{
		List<Klijent> klijenti = Klijent.findAll();
		List<Banka> banke = Banka.findAll();
		List<Racun> racuni = Racun.findAll();
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(racuni, klijenti, banke,mode,selectedIndex);
	}
	
	public static void nextMehanizam(Long id,String sta)
	{
		System.out.println(id);
		List<Klijent> klijenti = Klijent.findAll();
		List<Banka> banke = Banka.findAll();
		
		if(sta.equals("kli"))
		{
			
			Klijent klijent = Klijent.findById(id);
			List<Racun> racuni = Racun.findAll();
			List<Racun> racuniZaPrikaz = new ArrayList<Racun>();
			
			for(Racun nm : racuni)
			{
				if(nm.getKlijent().id == klijent.id)
				{
					racuniZaPrikaz.add(nm);
					System.out.println("naslo neko racuna ..");
				}
			}
			String mode = "edit";
			racuni.clear();
			racuni.addAll(racuniZaPrikaz);
			//render(drzave,mestaZaPrikaz,"edit",0);
			Long idZaPrikaz = id;
			renderTemplate("Racuni/show.html",racuni,klijenti,banke,mode,0,idZaPrikaz);
		}
		else
		{
			Banka banka = Banka.findById(id);
			List<Racun> racuni = Racun.findAll();
			List<Racun> racuniZaPrikaz = new ArrayList<Racun>();
			
			for(Racun nm : racuni)
			{
				if(nm.getBanka().id == banka.id)
				{
					racuniZaPrikaz.add(nm);
					System.out.println("naslo neko racun ..");
				}
			}
			String mode = "edit";
			racuni.clear();
			racuni.addAll(racuniZaPrikaz);
			//render(drzave,mestaZaPrikaz,"edit",0);
			Long idZaPrikaz = id;
			renderTemplate("Racuni/show.html",racuni,klijenti,banke,mode,0,idZaPrikaz);
		}
		
		
		
	}
	
	
	public static void create(Racun racun/*, Long banka*/, Long klijent )
	{
		System.out.println("CREATE: "+racun.id+", "+racun.brojRacuna);
		Klijent kl = Klijent.findById(klijent);
		Banka b = kl.getBanka(); /*Banka.findById(banka);*/
		racun.setBanka(b);
		racun.setKlijent(kl);
		racun.save();
		show("add",racun.id);
	}
	
	public static void edit(Racun racun/*,  Long banka*/, Long klijent)
	{
		
		Klijent kl = Klijent.findById(klijent);
		Banka b = kl.getBanka(); /*Banka.findById(banka);*/
		System.out.println("EDIT: "+racun.id+" OTVARANJE: "+racun.datumOtvaranja+" ZATVARANJE: "+racun.datumZatvaranja+" STAT: "+racun.status);
		System.out.println("KLIJENT: "+kl.ime+" BANKA: "+b.nazivBanke);
		
		Banka oldBank = racun.getBanka();
		Klijent oldKlijent= racun.getKlijent();
		if(oldBank==b && oldKlijent==kl)
		{
			racun.save();
			show("edit",racun.id);
		}
		else
		{
			racun.setBanka(b);
			racun.setKlijent(kl);
			racun.save();
			show("edit",racun.id);
		}
		
		
		
		//System.out.println("OVo je klijent: " + klijent);
		//System.out.println("OVo je banka: " + banka);
	}
	
	public static void filter(Racun racun)
	{
		//OVDE JOS DORADITI
		List<Klijent> racuni = Klijent.find("byBrojRacunaLikeAndStatusLike", "%"+racun.brojRacuna+"%", "%"+racun.status+"%").fetch();
		String mode = "edit";
		renderTemplate("Racuni/show.html", racuni, mode );
	}
	
	public static void delete(Long id)
	{
		Racun rac = Racun.findById(id);
		rac.delete();
		show("edit", rac.id-1);
	}
}
