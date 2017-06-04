package controllers;

import java.util.List;

import models.Klijent;
import models.PravnoLice;
import models.SifarnikDelatnosti;
import play.mvc.Controller;

public class PravnaLica extends Controller {

	public static void show(String mode, Long selectedIndex) {
		List<Klijent> klijenti = Klijent.findAll();
		List<SifarnikDelatnosti> sifarnikDelatnosti = SifarnikDelatnosti.findAll();
		List<PravnoLice> pravnaLica = PravnoLice.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(klijenti, sifarnikDelatnosti, pravnaLica, mode, selectedIndex);
	}

	public static void create(PravnoLice pravnoLice, Long klijent, Long sifarnikDelatnosti) {
		System.out.println("CREATE: " + pravnoLice.pib + ", " + pravnoLice.id);
		Klijent klijentx = Klijent.findById(klijent);
		SifarnikDelatnosti sifarnikDelatnostix = SifarnikDelatnosti.findById(sifarnikDelatnosti);
		pravnoLice.klijent = klijentx;
		pravnoLice.sifarnikDelatnosti = sifarnikDelatnostix;
		pravnoLice.save();
		show("add", pravnoLice.id);

	}

	public static void edit(PravnoLice pravnoLice, Long klijent, Long sifarnikDelatnosti) {
		System.out.println("EDIT: " + pravnoLice.pib + ", " + pravnoLice.id);
		Klijent klijentx = Klijent.findById(klijent);
		SifarnikDelatnosti sifarnikDelatnostix = SifarnikDelatnosti.findById(sifarnikDelatnosti);
		pravnoLice.klijent = klijentx;
		pravnoLice.sifarnikDelatnosti = sifarnikDelatnostix;
		pravnoLice.save();
		show("edit", pravnoLice.id);
	}

	public static void filter(PravnoLice pravnoLice) {
		List<PravnoLice> pravnaLica = PravnoLice.find("byPibLikeAndFaxLikeAndOdobrioLike", "%" + pravnoLice.pib + "%",
				"%" + pravnoLice.fax + "%", "%" + pravnoLice.odobrio + "%").fetch();
		String mode = "edit";
		renderTemplate("PravnaLica/show.html", pravnaLica, mode);
	}

	public static void delete(Long id) {
		PravnoLice pravnoLice = PravnoLice.findById(id);
		pravnoLice.delete();
		show("edit", pravnoLice.id - 1);
	}

	//EVO GA KONTROLER ZA PRAVNA LICA KOJI IZ NEKOG RAZLOGA NECE DA SE KOMITUJE O.o
}
