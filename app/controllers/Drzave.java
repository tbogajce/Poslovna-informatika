package controllers;

import java.util.List;

import models.Drzava;
import play.mvc.Controller;

public class Drzave extends Controller{
	
	public static void show(String mode, Long selectedIndex)
	{
		List<Drzava> drzave = Drzava.findAll();
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(drzave,mode,selectedIndex);
	}
	
	
	
	public static void create(Drzava drzava )
	{
		//System.out.println(drzava.naziv);
		drzava.save();
		show("add",drzava.id);
	}
	
	public static void edit(Drzava drzava)
	{
		drzava.save();
		show("edit",drzava.id);
		
	}
	
	public static void filter(Drzava drzava)
	{
		List<Drzava> drzave = Drzava.find("byOznakaLikeAndNazivLike", "%"+drzava.oznaka+"%", "%"+drzava.naziv+"%").fetch();
		String mode = "edit";
		renderTemplate("Drzave/show.html", drzave, mode );
	}
	
	public static void delete(Long id)
	{
		Drzava drz = Drzava.findById(id);
		drz.delete();
		show("edit", drz.id-1);
	}
	
	

}
