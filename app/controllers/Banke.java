package controllers;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import models.Banka;
import models.Drzava;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import play.mvc.Controller;

public class Banke extends Controller {

	public static void show(String mode, Long selectedIndex) {
		if (session.get("banka_id") != null) {

			List<Banka> bankex = Banka.findAll();
			ArrayList<Banka> banke = new ArrayList<Banka>();
			for (Banka b : bankex) {
				if (b.getId() == Long.valueOf(session.get("banka_id"))) {
					banke.add(b);
				}
			}
			if (mode == null || mode.equals(""))
				mode = "edit";
			render(banke, mode, selectedIndex);
		} else {
			List<Banka> banke = Banka.findAll();
			if (mode == null || mode.equals(""))
				mode = "edit";
			render(banke, mode, selectedIndex);
		}

	}

	public static void create(Banka banka) {
		validation.required(banka.nazivBanke);
		validation.required(banka.obracunskiRacun);
		validation.required(banka.sifraBanke);
		validation.required(banka.swiftKod);

		validation.maxSize(banka.nazivBanke, 50);
		validation.maxSize(banka.obracunskiRacun, 18);
		validation.maxSize(banka.sifraBanke, 3);
		validation.maxSize(banka.swiftKod, 8);

		if (validation.hasErrors()) {
			for (play.data.validation.Error error : validation.errors()) {
				System.out.println(error.message());
				validation.keep();
				show("add", null);
			}
		}
		System.out.println("CREATE: " + banka.nazivBanke);
		// System.out.println("Banka: "+banka);
		banka.save();
		show("add", banka.id);
	}

	public static void edit(Banka banka) {
		validation.required(banka.nazivBanke);
		validation.required(banka.obracunskiRacun);
		validation.required(banka.sifraBanke);
		validation.required(banka.swiftKod);

		validation.maxSize(banka.nazivBanke, 50);
		validation.maxSize(banka.obracunskiRacun, 18);
		validation.maxSize(banka.sifraBanke, 3);
		validation.maxSize(banka.swiftKod, 8);

		if (validation.hasErrors()) {
			for (play.data.validation.Error error : validation.errors()) {
				System.out.println(error.message());
				validation.keep();
				show("add", null);
			}
		}

		System.out.println("EDIT: " + banka.nazivBanke + ", " + banka.id);
		banka.save();
		show("edit", banka.id);

	}

	public static void filter(Banka banka) {
		if (session.get("banka_id") != null) {
			List<Banka> banke1 = Banka.find("bySifraBankeLikeAndNazivBankeLike", "%" + banka.sifraBanke + "%",
					"%" + banka.nazivBanke + "%").fetch();
			List<Banka> banke = new ArrayList<Banka>();
			for(int i=0; i<banke1.size();i++) {
				if(banke1.get(i).getId().equals(Long.parseLong(session.get("banka_id")))){
					banke.add(banke1.get(i));
				}
			}
			String mode = "edit";
			renderTemplate("Banke/show.html", banke, mode);
		} else {
			List<Banka> banke = Banka.find("bySifraBankeLikeAndNazivBankeLike", "%" + banka.sifraBanke + "%",
					"%" + banka.nazivBanke + "%").fetch();
			String mode = "edit";
			renderTemplate("Banke/show.html", banke, mode);
		}
	}

	public static void delete(Long id) {
		Banka ban = Banka.findById(id);
		ban.delete();
		show("edit", ban.id - 1);
	}

	public void exportToPdf(Long id) {
		try {
			Banka bank = Banka.findById(id);

			Properties connectionProps = new Properties();

			connectionProps.put("user", "root");
			connectionProps.put("password", "cuko");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poslovna", connectionProps);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("ID_Banke", bank.getId());

			File file = new File(System.getProperty("user.dir") + "/Izvestaji/Racuni.jasper");
			JasperPrint jp = JasperFillManager.fillReport(new FileInputStream(file), parameters, conn);
			String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
			JasperExportManager.exportReportToPdfFile(jp,
					"./Izvestaji/Izvestaj_" + bank.getNazivBanke() + "_" + timeStamp + ".pdf");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
