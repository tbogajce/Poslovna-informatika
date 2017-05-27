package controllers;

import java.util.ArrayList;
import java.util.List;

import play.mvc.Controller;
import models.Drzava;
import models.NaseljenoMesto;

public class NaseljenaMesta extends Controller{

	public static void show(String mode, Long selectedId)
	{
		List<Drzava> drzave = Drzava.findAll();
		List<NaseljenoMesto> naseljenaMesta = NaseljenoMesto.findAll();
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(drzave,naseljenaMesta,mode,selectedId);
	}
	
	public static void nextMehanizam(Long id)
	{
		System.out.println(id);
		
		Drzava drz = Drzava.findById(id);
		List<NaseljenoMesto> naseljenaMesta = NaseljenoMesto.findAll();
		List<NaseljenoMesto> mestaZaPrikaz = new ArrayList<NaseljenoMesto>();
		List<Drzava> drzave = Drzava.findAll();
		
		for(NaseljenoMesto nm : naseljenaMesta)
		{
			if(nm.drzava.id == drz.id)
			{
				mestaZaPrikaz.add(nm);
				System.out.println("naslo mjesto neko..");
			}
		}
		String mode = "edit";
		naseljenaMesta.clear();
		naseljenaMesta.addAll(mestaZaPrikaz);
		//render(drzave,mestaZaPrikaz,"edit",0);
		Long idZaPrikaz = id;
		renderTemplate("NaseljenaMesta/show.html",drzave,naseljenaMesta,mode,0,idZaPrikaz);
		
	}
	
	public static void create(NaseljenoMesto naseljenoMesto, Long drzava)
	{
		System.out.println("EDIT: "+naseljenoMesto.naziv+", "+naseljenoMesto.id);
		Drzava drzavax = Drzava.findById(drzava);
		naseljenoMesto.drzava=drzavax;
		naseljenoMesto.save();
		show("add",naseljenoMesto.id);
	}
	
	public static void edit(NaseljenoMesto naseljenoMesto, Long drzava)
	{
		System.out.println("EDIT: "+naseljenoMesto.naziv+", "+naseljenoMesto.id);
		Drzava drzavax = Drzava.findById(drzava);
		naseljenoMesto.drzava=drzavax;
		naseljenoMesto.save();
		show("edit",naseljenoMesto.id);
		
	}
	
	public static void filter(NaseljenoMesto naseljenoMesto)
	{
		List<NaseljenoMesto> naseljenaMesta = NaseljenoMesto.find("byOznakaLikeAndNazivLike", "%"+naseljenoMesto.oznaka+"%", "%"+naseljenoMesto.naziv+"%").fetch();
		String mode = "edit";
		renderTemplate("NaseljenaMesta/show.html", naseljenaMesta, mode );
	}
	
	public static void delete(Long id)
	{
		NaseljenoMesto naseljenoMesto = NaseljenoMesto.findById(id);
		naseljenoMesto.delete();
		show("edit",naseljenoMesto.id-1);
	}
	
}
