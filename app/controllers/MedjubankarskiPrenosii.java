package controllers;

import java.util.ArrayList;
import java.util.List;

import models.AnalitikaIzvoda;
import models.Banka;
import models.MedjubankarskiPrenos;
import models.Racun;
import models.ZatvaranjeRacuna;
import play.mvc.Controller;

public class MedjubankarskiPrenosii  extends Controller{

	public static void show(String mode, Long selectedId)
	{
		if(session.get("banka_id")!=null)
		{
			
			List<MedjubankarskiPrenos> medjubankarskiPrenosix = MedjubankarskiPrenos.findAll();
			ArrayList<MedjubankarskiPrenos> medjubankarskiPrenosi = new ArrayList<MedjubankarskiPrenos>();
			for(MedjubankarskiPrenos mp : medjubankarskiPrenosix)
			{
				if(mp.getBankaPosiljalac().getId()==Long.valueOf(session.get("banka_id")) ||
						mp.getBankaPrimalac().getId()==Long.valueOf(session.get("banka_id")))
				{
					medjubankarskiPrenosi.add(mp);
				}
			}
			if(mode == null || mode.equals(""))
				mode = "edit";
			render(medjubankarskiPrenosi,mode,selectedId);
		}
		else
		{
			List<MedjubankarskiPrenos> medjubankarskiPrenosi = MedjubankarskiPrenos.findAll();
			if(mode == null || mode.equals(""))
				mode = "edit";
			render(medjubankarskiPrenosi,mode,selectedId);
		}
		
	}
	
	
	public static void nextMehanizam(Long id,String sta, String kako)
	{
		System.out.println(id);
		
		if(sta.equals("bpos"))
		{
			if(kako.equals("rtgs"))
			{
				Banka banka = Banka.findById(id);
				List<MedjubankarskiPrenos> medjubankarskiPrenosi = MedjubankarskiPrenos.findAll();
				List<MedjubankarskiPrenos> mpsiZaPrikaz = new ArrayList<MedjubankarskiPrenos>();
				
				for(MedjubankarskiPrenos nm : medjubankarskiPrenosi)
				{
					if(nm.getBankaPosiljalac().id == banka.id)
					{
						if(nm.isRtgs()==true)
						{
							mpsiZaPrikaz.add(nm);
							System.out.println("naslo neko zatvaranje racuna 1 1..");
						}
						
					}
				}
				String mode = "edit";
				medjubankarskiPrenosi.clear();
				medjubankarskiPrenosi.addAll(mpsiZaPrikaz);
				//render(drzave,mestaZaPrikaz,"edit",0);
				Long idZaPrikaz = id;
				for(MedjubankarskiPrenos mx : medjubankarskiPrenosi)
				{
					System.out.println("MPX: "+mx.getId());
				}
				renderTemplate("MedjubankarskiPrenosii/show.html",medjubankarskiPrenosi,mode,0,idZaPrikaz);
			}
			else
			{
				Banka banka = Banka.findById(id);
				List<MedjubankarskiPrenos> medjubankarskiPrenosi = MedjubankarskiPrenos.findAll();
				List<MedjubankarskiPrenos> mpsiZaPrikaz = new ArrayList<MedjubankarskiPrenos>();
				
				for(MedjubankarskiPrenos nm : medjubankarskiPrenosi)
				{
					if(nm.getBankaPosiljalac().id == banka.id)
					{
						if(nm.isRtgs()==false)
						{
							mpsiZaPrikaz.add(nm);
							System.out.println("naslo neko zatvaranje racuna 1 2..");
						}
						
					}
				}
				String mode = "edit";
				medjubankarskiPrenosi.clear();
				medjubankarskiPrenosi.addAll(mpsiZaPrikaz);
				//render(drzave,mestaZaPrikaz,"edit",0);
				Long idZaPrikaz = id;
				for(MedjubankarskiPrenos mx : medjubankarskiPrenosi)
				{
					System.out.println("MPX: "+mx.getId());
				}
				renderTemplate("MedjubankarskiPrenosii/show.html",medjubankarskiPrenosi,mode,0,idZaPrikaz);
			}
			
		}
		else
		{
			if(kako.equals("rtgs"))
			{
				Banka banka = Banka.findById(id);
				List<MedjubankarskiPrenos> medjubankarskiPrenosi = MedjubankarskiPrenos.findAll();
				List<MedjubankarskiPrenos> mpsiZaPrikaz = new ArrayList<MedjubankarskiPrenos>();
				
				for(MedjubankarskiPrenos nm : medjubankarskiPrenosi)
				{
					if(nm.getBankaPrimalac().id == banka.id)
					{
						if(nm.isRtgs()==true)
						{
							mpsiZaPrikaz.add(nm);
							System.out.println("naslo neko zatvaranje racuna 2 1..");
						}
						
					}
				}
				String mode = "edit";
				medjubankarskiPrenosi.clear();
				medjubankarskiPrenosi.addAll(mpsiZaPrikaz);
				//render(drzave,mestaZaPrikaz,"edit",0);
				Long idZaPrikaz = id;
				renderTemplate("MedjubankarskiPrenosii/show.html",medjubankarskiPrenosi,mode,0,idZaPrikaz);
			}
			else
			{
					Banka banka = Banka.findById(id);
					List<MedjubankarskiPrenos> medjubankarskiPrenosi = MedjubankarskiPrenos.findAll();
					List<MedjubankarskiPrenos> mpsiZaPrikaz = new ArrayList<MedjubankarskiPrenos>();
					
					for(MedjubankarskiPrenos nm : medjubankarskiPrenosi)
					{
						if(nm.getBankaPrimalac().id == banka.id)
						{
							if(nm.isRtgs()==false)
							{
								mpsiZaPrikaz.add(nm);
								System.out.println("naslo neko zatvaranje racuna 2 2..");
							}
							
						}
					}
					String mode = "edit";
					medjubankarskiPrenosi.clear();
					medjubankarskiPrenosi.addAll(mpsiZaPrikaz);
					//render(drzave,mestaZaPrikaz,"edit",0);
					Long idZaPrikaz = id;
					renderTemplate("MedjubankarskiPrenosii/show.html",medjubankarskiPrenosi,mode,0,idZaPrikaz);
			}
			
		}
		
		
		
	}
}
