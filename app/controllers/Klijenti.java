package controllers;

import java.util.List;

import models.Banka;
import models.Klijent;
import models.NaseljenoMesto;
import play.mvc.Controller;

public class Klijenti extends Controller{
	
	public static void show(String mode, Long selectedIndex)
	{
		List<NaseljenoMesto> naseljenaMesta = NaseljenoMesto.findAll();
		List<Banka> banke = Banka.findAll();
		List<Klijent> klijenti = Klijent.findAll();
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(klijenti, naseljenaMesta, banke,mode,selectedIndex);
	}
	
	
	
	public static void create(Klijent klijent, Long naseljenoMesto, Long banka )
	{
		System.out.println("CREATE: "+klijent.id+", "+klijent.jmbg);
		NaseljenoMesto nm = NaseljenoMesto.findById(naseljenoMesto);
		Banka b = Banka.findById(banka);
		klijent.setBanka(b);
		klijent.setNaseljenoMesto(nm);
		klijent.save();
		show("add",klijent.id);
	}
	
	public static void edit(Klijent klijent,  Long klijent_naseljenoMesto, Long klijent_banka)
	{
		System.out.println("EDIT: "+klijent.id+", "+klijent.jmbg);
//		NaseljenoMesto nm = NaseljenoMesto.findById(naseljenoMesto);
//		Banka b = Banka.findById(banka);
//		klijent.setBanka(b);
//		klijent.setNaseljenoMesto(nm);
//		klijent.save();
//		show("edit",klijent.id);
		System.out.println("OVo je naseljeno mjesto: " + klijent_naseljenoMesto);
		System.out.println("OVo je banka: " + klijent_banka);
	}
	
	public static void filter(Klijent klijent)
	{
		//OVDE JOS DORADITI
		List<Klijent> klijenti = Klijent.find("byOznakaLikeAndNazivLike", "%"+klijent.jmbg+"%", "%"+klijent.ime+"%").fetch();
		String mode = "edit";
		renderTemplate("Klijenti/show.html", klijenti, mode );
	}
	
	public static void delete(Long id)
	{
		Klijent kli = Klijent.findById(id);
		kli.delete();
		show("edit", kli.id-1);
	}
}
