package controllers;

import java.util.List;

import models.Banka;
import models.Klijent;
import models.NaseljenoMesto;
import models.Racun;
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
	
	
	
	public static void create(Racun racun, Long banka, Long klijent )
	{
		System.out.println("CREATE: "+racun.id+", "+racun.brojRacuna);
		Klijent kl = Klijent.findById(klijent);
		Banka b = Banka.findById(banka);
		racun.setBanka(b);
		racun.setKlijent(kl);
		racun.save();
		show("add",racun.id);
	}
	
	public static void edit(Racun racun,  Long banka, Long klijent)
	{
		System.out.println("EDIT: "+racun.id+", "+racun.brojRacuna);
//		Klijent kl = Klijent.findById(klijent);
//		Banka b = Banka.findById(banka);
//		racun.setBanka(b);
//		racun.setKlijent(kl);
//		racun.save();
//		show("edit",racun.id);
		System.out.println("OVo je klijent: " + klijent);
		System.out.println("OVo je banka: " + banka);
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
