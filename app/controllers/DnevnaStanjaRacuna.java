package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Banka;
import models.DnevnoStanjeRacuna;
import models.Drzava;
import models.NaseljenoMesto;
import models.Racun;
import play.mvc.Controller;

public class DnevnaStanjaRacuna extends Controller {

	public static void show(String mode, Long selectedId) {
		if (session.get("banka_id") != null) {
			List<Racun> racuni = Racun.findAll();
			List<DnevnoStanjeRacuna> dnevnaStanjaRacunax = DnevnoStanjeRacuna.findAll();
			ArrayList<DnevnoStanjeRacuna> dnevnaStanjaRacuna = new ArrayList<DnevnoStanjeRacuna>();
			for (DnevnoStanjeRacuna dsr : dnevnaStanjaRacunax) {
				if (dsr.getRacun().getBanka().getId() == Long.valueOf(session.get("banka_id"))) {
					dnevnaStanjaRacuna.add(dsr);
				}
			}
			if (mode == null || mode.equals(""))
				mode = "edit";
			render(racuni, dnevnaStanjaRacuna, mode, selectedId);
		} else {
			List<Racun> racuni = Racun.findAll();
			List<DnevnoStanjeRacuna> dnevnaStanjaRacuna = DnevnoStanjeRacuna.findAll();
			if (mode == null || mode.equals(""))
				mode = "edit";
			render(racuni, dnevnaStanjaRacuna, mode, selectedId);
		}

	}

	public static void nextMehanizam(Long id) {
		System.out.println(id);

		Racun rac = Racun.findById(id);
		List<DnevnoStanjeRacuna> dnevnaStanjaRacuna = DnevnoStanjeRacuna.findAll();
		List<DnevnoStanjeRacuna> dnevnaStanjaZaPrikaz = new ArrayList<DnevnoStanjeRacuna>();
		List<Racun> racuni = Racun.findAll();

		for (DnevnoStanjeRacuna nm : dnevnaStanjaRacuna) {
			if (nm.racun.id == rac.id) {
				dnevnaStanjaZaPrikaz.add(nm);
				System.out.println("naslo dnevno stanje racuna..");
			}
		}
		String mode = "edit";
		dnevnaStanjaRacuna.clear();
		dnevnaStanjaRacuna.addAll(dnevnaStanjaZaPrikaz);
		// render(drzave,mestaZaPrikaz,"edit",0);
		Long idZaPrikaz = id;
		renderTemplate("DnevnaStanjaRacuna/show.html", racuni, dnevnaStanjaRacuna, mode, 0, idZaPrikaz);

	}

	public static void create(DnevnoStanjeRacuna dnevnoStanjeRacuna, Long racun) {
		// validation.required(dnevnoStanjeRacuna.datum);
		// validation.required(dnevnoStanjeRacuna.prethodnoStanje);
		// validation.required(dnevnoStanjeRacuna.prometNaTeret);
		// validation.required(dnevnoStanjeRacuna.prometUKorist);
		// validation.required(dnevnoStanjeRacuna.novoStanje);
		//
		// if (validation.hasErrors()) {
		// for (play.data.validation.Error error : validation.errors()) {
		// System.out.println(error.message());
		// validation.keep();
		// show("add",null);
		// }
		// }

		System.out.println("EDIT: " + dnevnoStanjeRacuna.id);
		Racun racunx = Racun.findById(racun);
		dnevnoStanjeRacuna.racun = racunx;
		dnevnoStanjeRacuna.save();
		show("add", dnevnoStanjeRacuna.id);
	}

	public static void edit(DnevnoStanjeRacuna dnevnoStanjeRacuna, Long racun) {
		// validation.required(dnevnoStanjeRacuna.datum);
		// validation.required(dnevnoStanjeRacuna.prethodnoStanje);
		// validation.required(dnevnoStanjeRacuna.prometNaTeret);
		// validation.required(dnevnoStanjeRacuna.prometUKorist);
		// validation.required(dnevnoStanjeRacuna.novoStanje);
		//
		// if (validation.hasErrors()) {
		// for (play.data.validation.Error error : validation.errors()) {
		// System.out.println(error.message());
		// validation.keep();
		// show("add",null);
		// }
		// }

		System.out.println("EDIT: " + dnevnoStanjeRacuna.id);
		Racun racunx = Racun.findById(racun);
		dnevnoStanjeRacuna.racun = racunx;
		dnevnoStanjeRacuna.save();
		show("edit", dnevnoStanjeRacuna.id);

	}

	public static void filter(DnevnoStanjeRacuna dnevnoStanjeRacuna) {
		if (session.get("banka_id") != null) {
			List<DnevnoStanjeRacuna> dsr = DnevnoStanjeRacuna.find("byDatumLikeAndRacunLike",
					"%" + dnevnoStanjeRacuna.datum + "%", "%" + dnevnoStanjeRacuna.racun + "%").fetch();
			List<DnevnoStanjeRacuna> dnevnaStanjaRacuna = new ArrayList<DnevnoStanjeRacuna>();
			for(int i=0; i<dsr.size();i++) {
				if(dsr.get(i).getRacun().getBanka().getId().equals(Long.valueOf(session.get("banka_id"))))
						dnevnaStanjaRacuna.add(dsr.get(i));
			}
			String mode = "edit";
			renderTemplate("DnevnaStanjaRacuna/show.html", dnevnaStanjaRacuna, mode);

		} else {
			List<DnevnoStanjeRacuna> dnevnaStanjaRacuna = DnevnoStanjeRacuna.find("byDatumLikeAndRacunLike",
					"%" + dnevnoStanjeRacuna.datum + "%", "%" + dnevnoStanjeRacuna.racun + "%").fetch();
			String mode = "edit";
			renderTemplate("DnevnaStanjaRacuna/show.html", dnevnaStanjaRacuna, mode);
		}
	}

	public static void delete(Long id) {
		DnevnoStanjeRacuna dnevnoStanjeRacuna = DnevnoStanjeRacuna.findById(id);
		dnevnoStanjeRacuna.delete();
		show("edit", dnevnoStanjeRacuna.id - 1);
	}

}
