package com.ftn.student.service.utils;

import java.util.ArrayList;
import java.util.List;
import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Korisnik;
import com.ftn.student.service.models.Zamena;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.rest.responses.UserLoginResponse;

public class FormularDBUtils {

	
	public static UserLoginResponse koordFormulariResponse(Korisnik k, FormularRepository repoFormular) {
		
		UserLoginResponse response = new UserLoginResponse();
		List<Formular> sviFormulari = repoFormular.findAll();
		List<Formular> koordFormulari = new ArrayList<Formular>();
		for (Formular f: sviFormulari) {
			if (f.getOdobrenjeKoord() == null && 
					f.getStudent().getStudije().getDepartman().getKoordinator().equals(k)) {
				koordFormulari.add(f);
			}
		}
		
		response.setKorisnik(k);
		response.setFormulari(koordFormulari);
		
		return response;
		
	}
	
	public static UserLoginResponse sefFormulariResponse(Korisnik k, FormularRepository repoFormular) {
		
		UserLoginResponse response = new UserLoginResponse();
		List<Formular> sviFormulari = repoFormular.findAll();
		List<Formular> sefFormulari = new ArrayList<Formular>();
		
		for (Formular f: sviFormulari) {
			if (f.getOdobrenjeSef() == null && f.getOdobrenjeKoord() != null 
					&& f.getOdobrenjeKoord().equals("Y") && f.getStudent().getStudije().getSef().equals(k)) {
				List<Zamena> zamene = f.getZamene();
				boolean sve = true;
				for(Zamena z: zamene) {
					if (z.getOdobreno() == null || z.getOdobreno().equals("N")) {
						sve = false;
						break;
					}
				}
				if (sve) {
					sefFormulari.add(f);
				}
			}
		}
		
		response.setKorisnik(k);
		response.setFormulari(sefFormulari);
		
		return response;
		
	}

}
