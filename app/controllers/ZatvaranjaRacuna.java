package controllers;

import java.util.ArrayList;
import java.util.List;

import models.AnalitikaIzvoda;
import models.DnevnoStanjeRacuna;
import models.Racun;
import models.ZatvaranjeRacuna;
import play.mvc.Controller;

public class ZatvaranjaRacuna extends Controller{
	
	public static void show(String mode, Long selectedId)
	{
		
		List<ZatvaranjeRacuna> zatvaranjaRacuna = ZatvaranjeRacuna.findAll();
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(zatvaranjaRacuna,mode,selectedId);
	}
	
	
	public static void nextMehanizam(Long id,String sta)
	{
		System.out.println(id);
		
		if(sta.equals("rac"))
		{
			Racun rac = Racun.findById(id);
			List<ZatvaranjeRacuna> zatvaranjaRacuna = ZatvaranjeRacuna.findAll();
			List<ZatvaranjeRacuna> zatvaranjaRacunaZaPrikaz = new ArrayList<ZatvaranjeRacuna>();
			
			for(ZatvaranjeRacuna nm : zatvaranjaRacuna)
			{
				if(nm.racun.id == rac.id)
				{
					zatvaranjaRacunaZaPrikaz.add(nm);
					System.out.println("naslo neko zatvaranje racuna ..");
				}
			}
			String mode = "edit";
			zatvaranjaRacuna.clear();
			zatvaranjaRacuna.addAll(zatvaranjaRacunaZaPrikaz);
			//render(drzave,mestaZaPrikaz,"edit",0);
			Long idZaPrikaz = id;
			renderTemplate("ZatvaranjaRacuna/show.html",zatvaranjaRacuna,mode,0,idZaPrikaz);
		}
		else
		{
			AnalitikaIzvoda ai = AnalitikaIzvoda.findById(id);
			List<ZatvaranjeRacuna> zatvaranjaRacuna = ZatvaranjeRacuna.findAll();
			List<ZatvaranjeRacuna> zatvaranjaRacunaZaPrikaz = new ArrayList<ZatvaranjeRacuna>();
			
			for(ZatvaranjeRacuna nm : zatvaranjaRacuna)
			{
				if(nm.racunIspraznjenNalogom.id == ai.id)
				{
					zatvaranjaRacunaZaPrikaz.add(nm);
					System.out.println("naslo neko zatvaranje racuna ..");
				}
			}
			String mode = "edit";
			zatvaranjaRacuna.clear();
			zatvaranjaRacuna.addAll(zatvaranjaRacunaZaPrikaz);
			//render(drzave,mestaZaPrikaz,"edit",0);
			Long idZaPrikaz = id;
			renderTemplate("ZatvaranjaRacuna/show.html",zatvaranjaRacuna,mode,0,idZaPrikaz);
		}
		
		
		
	}

}
