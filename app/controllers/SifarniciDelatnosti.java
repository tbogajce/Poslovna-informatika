package controllers;

import java.util.List;

import models.SifarnikDelatnosti;
import models.Valuta;
import play.mvc.Controller;

public class SifarniciDelatnosti extends Controller {
	

	public static void show(String mode, Long selectedIndex)
	{
		List<SifarnikDelatnosti> sifarniciDelatnosti = SifarnikDelatnosti.findAll();
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(sifarniciDelatnosti,mode,selectedIndex);
	}
	
	public static void create(SifarnikDelatnosti sifarnikDelatnosti )
	{
		System.out.println("CREATE: "+sifarnikDelatnosti.naziv+", "+sifarnikDelatnosti.id);
		sifarnikDelatnosti.save();
		show("add",sifarnikDelatnosti.id);
	}
	
	public static void edit(SifarnikDelatnosti sifarnikDelatnosti)
	{
		System.out.println("EDIT: "+sifarnikDelatnosti.naziv+", "+sifarnikDelatnosti.id);
		sifarnikDelatnosti.save();
		show("edit",sifarnikDelatnosti.id);
		
	}
	
	public static void filter(SifarnikDelatnosti sifarnikDelatnosti)
	{
		List<SifarnikDelatnosti> sifarniciDelatnosti = SifarnikDelatnosti.find("bySifraLikeAndNazivLike", "%"+sifarnikDelatnosti.sifra+"%", "%"+sifarnikDelatnosti.naziv+"%").fetch();
		String mode = "edit";
		renderTemplate("SifarniciDelatnosti/show.html", sifarniciDelatnosti, mode );
	}
	
	public static void delete(Long id)
	{
		SifarnikDelatnosti sifarnikDelatnosti = SifarnikDelatnosti.findById(id);
		sifarnikDelatnosti.delete();
		show("edit", sifarnikDelatnosti.id-1);
	}
}
