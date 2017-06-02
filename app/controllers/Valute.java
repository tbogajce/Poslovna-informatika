package controllers;

import java.util.List;

import models.Drzava;
import models.Valuta;
import play.mvc.Controller;

public class Valute extends Controller {
	
	public static void show(String mode, Long selectedIndex)
	{
		List<Valuta> valute = Valuta.findAll();
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(valute,mode,selectedIndex);
	}
	
	public static void create(Valuta valuta )
	{
		System.out.println("CREATE: "+valuta.naziv+", "+valuta.id);
		valuta.save();
		show("add",valuta.id);
	}
	
	public static void edit(Valuta valuta)
	{
		System.out.println("EDIT: "+valuta.naziv+", "+valuta.id);
		valuta.save();
		show("edit",valuta.id);
		
	}

	public static void filter(Valuta valuta)
	{
		List<Valuta> valute = Valuta.find("bySifraLikeAndNazivLike", "%"+valuta.sifra+"%", "%"+valuta.naziv+"%").fetch();
		String mode = "edit";
		renderTemplate("Valute/show.html", valute, mode );
	}
	
	public static void delete(Long id)
	{
		Valuta valuta = Valuta.findById(id);
		valuta.delete();
		show("edit", valuta.id-1);
	}
	
	
}
