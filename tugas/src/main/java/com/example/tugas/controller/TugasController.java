package com.example.tugas.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas.model.KecamatanModel;
import com.example.tugas.model.KeluargaModel;
import com.example.tugas.model.KelurahanModel;
import com.example.tugas.model.KotaModel;
import com.example.tugas.model.PendudukModel;
import com.example.tugas.service.SidukService;


@Controller
public class TugasController
{
    
	 @Autowired
	 SidukService sidukDAO;

    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }

    @RequestMapping("/panduan")
    public String panduan ()
    {
        return "panduan";
    }
    
    @RequestMapping("/penduduk")
    public String findPenduduk (@RequestParam(value = "nik", required=true) String nik, Model model)
    {
    	
    	PendudukModel a = sidukDAO.selectPenduduk(nik);
    	
    	
    	String bulan = "";
    	String[] ttl = a.tanggal_lahir.split("-");
    	
    	if(ttl[1].equals("01")) {
    		bulan = "Januari";
    	}else if(ttl[1].equals("02")) {
    		bulan = "Februari";
    	}else if(ttl[1].equals("03")) {
    		bulan = "Maret";
    	}else if(ttl[1].equals("04")) {
    		bulan = "April";
    	}else if(ttl[1].equals("05")) {
    		bulan = "Mei";
    	}else if(ttl[1].equals("06")) {
    		bulan = "Juni";
    	}else if(ttl[1].equals("07")) {
    		bulan = "Juli";
    	}else if(ttl[1].equals("08")) {
    		bulan = "Agustus";
    	}else if(ttl[1].equals("09")) {
    		bulan = "September";
    	}else if(ttl[1].equals("10")) {
    		bulan = "Oktober";
    	}else if(ttl[1].equals("11")) {
    		bulan = "November";
    	}else if(ttl[1].equals("12")) {
    		bulan = "Desember";
    	}
    	
    	a.tanggal_lahir = ttl[2] + " " + bulan + " " + ttl[0];
    	model.addAttribute("penduduk", a);
    	return "penduduk";
    }
    
    
    @RequestMapping("/keluarga")
    public String findKeluarga (@RequestParam(value = "nkk", required=true) String nkk, Model model)
    {
   
    	KeluargaModel a = sidukDAO.cariKeluarga(nkk);
    	
    	model.addAttribute("keluarga", a);
    	
    	return "keluarga";
    }
    
    @GetMapping("/penduduk/tambah")
    public String tambahPenduduk(Model model)
    {
    	
    	model.addAttribute("penduduk", new PendudukModel());
    	return "tambahpenduduk";
    }
    
    @PostMapping(value= "/penduduk/tambah")
    public String tambahSubmit (@ModelAttribute PendudukModel penduduk, Model model)
    {
    	
    	
    	KeluargaModel keluarga = sidukDAO.selectKeluarga(penduduk.id_keluarga);
    	
    	if(keluarga!=null) {
    		
    		
    		penduduk.is_wafat = 0;
        	String kecamatan = keluarga.lurah.camat.kode_kecamatan.substring(0, 6);
        	String[] lahir = penduduk.tanggal_lahir.split("-");
        	String bulan = lahir[1];
        	String tahun = lahir[0].substring(2, 4);
        	String tanggal = lahir[2];
        	
        	int index= 0;
        	
        	if(penduduk.jenis_kelamin==1) {
        		int masuk = Integer.parseInt(tanggal);
        		masuk+=40;
        		tanggal = masuk+"";
        	}
        	
        	
        	
        	String tengah = tanggal + bulan + tahun;
        	penduduk.nikcheck = kecamatan + tengah;
        	
        	PendudukModel a = sidukDAO.cekPenduduk(penduduk.nikcheck);
        	
        	if(a!=null) {
        		index += Integer.parseInt(a.nik.substring(12, 16)); 
        		index++;
        	}else {
        		index = 0;
        	}
        	
        	 
        	
        	String akhir = String.format("%04d", index);
        	penduduk.nik = kecamatan + tengah + akhir;
        	sidukDAO.addPenduduk(penduduk);
        	model.addAttribute("penduduk", penduduk);
        	
        	
    	}
    	
    	return "success";
    	
    }

    @GetMapping("/keluarga/tambah")
    public String tambahKeluarga(Model model)
    {
    	List<KelurahanModel> lurah = sidukDAO.selectAllKelurahan();
    	model.addAttribute("kelurahan", lurah);
    	model.addAttribute("keluarga", new KeluargaModel());
    	return "tambahkeluarga";
    }
    
    @PostMapping(value= "/keluarga/tambah")
    public String tambahKeluargaSubmit (@ModelAttribute KeluargaModel keluarga, Model model)
    {
    	
    		
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    		LocalDate ld = LocalDate.now();
    		String[] today = dtf.format(ld).split("-");
    		KelurahanModel lrh = sidukDAO.selectKelurahan(keluarga.id_kelurahan);
    		String awal = lrh.camat.kode_kecamatan.substring(0, 6);
    		String tengah = today[0] + today[1] + today[2].substring(2, 4);
    		
    		keluarga.is_tidak_berlaku = 0;
    		
    		keluarga.nkkcheck = awal + tengah;
    		
    		int b1= Integer.parseInt(keluarga.RT);
    		int b= Integer.parseInt(keluarga.RW); 
    		
    		keluarga.RT = String.format("%03d", b1);
    		keluarga.RW = String.format("%03d", b);
    		
    		KeluargaModel a = sidukDAO.cekNKK(keluarga.nkkcheck);
        	int index = 0;
    		
        	if(a!=null) {
        		index += Integer.parseInt(a.nomor_kk.substring(12, 16)); 
        		index++;
        	}else {
        		index = 0;
        	}
        	
        	String akhir = String.format("%04d", index);
        	keluarga.nomor_kk = awal + tengah + akhir;
    		
        	sidukDAO.addKeluarga(keluarga);
        	model.addAttribute("keluarga",keluarga);
    	
        	return "success-keluarga";
    	
    }
    
    
    @GetMapping("/penduduk/ubah/{NIK}")
    public String ubahPenduduk( Model model, @PathVariable(value = "NIK") String NIK)
    {
    	PendudukModel a = sidukDAO.selectPenduduk(NIK);
    	if(a.is_wafat==0) {
	    	model.addAttribute("penduduk", a);
	    	model.addAttribute("nik", NIK);
	    	return "ubahpenduduk";
    	}else {
    		model.addAttribute("nik", NIK);
    		return "mati";
    	}
    }
    
    @PostMapping(value= "/penduduk/ubah/{NIK}")
    public String ubahPendudukSubmit (@ModelAttribute PendudukModel penduduk, Model model, @PathVariable(value = "NIK") String NIK)
    {
    		
    		
    		PendudukModel p = sidukDAO.selectPenduduk(NIK);
    		String niklama = p.nik;
    		String[] lahir = penduduk.tanggal_lahir.split("-");
    		String awal = penduduk.nik.substring(0,6);
    		String tengah = lahir[2] + lahir[1] + lahir[0].substring(2, 4);
    		String akhir = penduduk.nik.substring(11, 16);
    		int tl = 0;
    		
    		
    		KeluargaModel keluarga = sidukDAO.selectKeluarga(penduduk.id_keluarga);
    		
    		if(keluarga!=null) {
    			if(p.tanggal_lahir!=penduduk.tanggal_lahir) {
        			
                	String bulan = lahir[1];
                	String tahun = lahir[0].substring(2, 4);
                	String tanggal = lahir[2];
                	tengah = tanggal + bulan + tahun;
                	tl= Integer.parseInt(tanggal);
        		}
        		
        		if(p.id_keluarga!=penduduk.id_keluarga) {
        			awal = keluarga.lurah.camat.kode_kecamatan.substring(0, 6); 
        		}
        		
        		if(p.jenis_kelamin!=penduduk.jenis_kelamin) {
        			if(penduduk.jenis_kelamin==1) {
        				tl = Integer.parseInt(lahir[2]);
        				tl+=40;
        				tengah = tl + "" + lahir[1] + lahir[0];
        				
        			}
        		}
        		
        		
        		String nikcheck = awal+tengah;
        		
        		if(nikcheck!=p.nik.substring(0, 12)) {
        			PendudukModel p1 = sidukDAO.cekPenduduk(nikcheck);
            		int index =0;
            		
            		if(p1!=null) {
                		index += Integer.parseInt(p1.nik.substring(12, 16)); 
                		index++;
                	}else {
                		index = 0;
                	}
                	            	         
                	akhir = String.format("%04d", index);
                	penduduk.nik = awal + tengah + akhir;
        		}else {
        			penduduk.nik = niklama;
        		}
        		
        		sidukDAO.updatePenduduk(penduduk);
        		model.addAttribute("nik", NIK);
        		model.addAttribute("baru", penduduk);
            	model.addAttribute("lama", niklama);
        		
        		

    		}
    		    		
        	return "ubahpenduduk-sukses";
    	
    }
    
    
    @GetMapping("/keluarga/ubah/{NKK}")
    public String ubahKeluarga( Model model, @PathVariable(value = "NKK") String NKK)
    {
    	KeluargaModel a = sidukDAO.cariKeluarga(NKK);
    	if(a.is_tidak_berlaku==0) {
	    	List<KelurahanModel> lurah = sidukDAO.selectAllKelurahan();
	    	model.addAttribute("kelurahan", lurah);
	    	model.addAttribute("keluarga", a);
	    	model.addAttribute("nkk", NKK);
	    	return "ubahkeluarga";
    	}else {
    		return "mati";
    	}
    }
    
    @PostMapping(value= "/keluarga/ubah/{NKK}")
    public String ubahKeluargaSubmit (@ModelAttribute KeluargaModel keluarga, Model model, @PathVariable(value = "NKK") String NKK)
    {
    		
    		KeluargaModel p = sidukDAO.cariKeluarga(NKK);
    		String nkklama = p.nomor_kk;
    		
    		String awal = keluarga.nomor_kk.substring(0,6);
    		String tengah = keluarga.nomor_kk.substring(6, 12);
    		String akhir = keluarga.nomor_kk.substring(12, 16);
    		int tl = 0;
    		
    		
    		
    			
        		if(p.id_kelurahan!=keluarga.id_kelurahan) {
        			KelurahanModel lurah = sidukDAO.selectKelurahan(keluarga.id_kelurahan);
        			awal = lurah.camat.kode_kecamatan.substring(0, 6);
        		}
        		
        		if(p.RT!=keluarga.RT || p.RW!=keluarga.RW) {
        			int b1= Integer.parseInt(keluarga.RT);
            		int b= Integer.parseInt(keluarga.RW); 
            		
            		keluarga.RT = String.format("%03d", b1);
            		keluarga.RW = String.format("%03d", b);
        		}
        		
        		String nkkcheck = awal+tengah;
        		
        		if(nkkcheck!=p.nomor_kk.substring(0, 12)) {
        			KeluargaModel p1 = sidukDAO.cekNKK(nkkcheck);
            		int index =0;
            		
            		if(p1!=null) {
                		index += Integer.parseInt(p1.nomor_kk.substring(12, 16)); 
                		index++;
                	}else {
                		index = 0;
                	}
                	            	         
                	akhir = String.format("%04d", index);
                	keluarga.nomor_kk = awal + tengah + akhir;
        		}else {
        			keluarga.nomor_kk = nkklama;
        		}
        		
        		sidukDAO.updateKeluarga(keluarga);
        		model.addAttribute("nkk", NKK);
        		model.addAttribute("baru", keluarga);
            	model.addAttribute("lama", nkklama);
        		
        		

    		
    		    		
        	return "ubahkeluarga-sukses";
    	
    }
    
    
    
    
    
    
    @RequestMapping("/penduduk/cari")
	public String cari(@RequestParam(value = "kt", required=false) Integer kt, 
			@RequestParam(value = "kc", required=false) Integer kc, 
			@RequestParam(value = "kl", required=false) Integer kl, Model model) {
		
    	String KT="";
    	String KC="";
    	
    	if(kt==null&&kc==null&&kl==null) {
    		
    		List<KotaModel> kotas = sidukDAO.selectAllKota();
        	model.addAttribute("lkota", kotas);
    		return "cari";
    		
    	}else if(kt!=null&&kc==null&&kl==null){
    		
    		List<KecamatanModel> kecamatans = sidukDAO.selectAllKecamatan(kt);
    		KT = kt+"";
    		model.addAttribute("kt", KT);
    		KotaModel kota = sidukDAO.selectKota(kt);
        	model.addAttribute("kecamatans", kecamatans);
        	model.addAttribute("lkota", kota);
    		return "cari-kota";
    		
    	}else if(kt!=null&&kc!=null&&kl==null){ 
    		
    		KC = kc+"";
    		KT = kt+"";
    		List<KelurahanModel> kelurahans = sidukDAO.selectAllKelurahanID(kc);
    		KecamatanModel kecamatans = sidukDAO.selectKecamatan(kc);
    		KotaModel kota = sidukDAO.selectKota(kt);
    		model.addAttribute("kc", KC);
    		model.addAttribute("kt", KT);
        	model.addAttribute("kecamatans", kecamatans);
        	model.addAttribute("kelurahans", kelurahans);
        	model.addAttribute("lkota", kota);
    		return "cari-camat";
    		
    	}else{
    		
    		KecamatanModel kecamatans = sidukDAO.selectKecamatan(kc);
    		KotaModel kota = sidukDAO.selectKota(kt);
    		KelurahanModel kelurahans = sidukDAO.selectKelurahan(kl);
    		List<PendudukModel> keluargas = sidukDAO.cariPendudukKelurahan(kl);
    		PendudukModel muda = keluargas.get(keluargas.size()-1);
    		PendudukModel tua = keluargas.get(0);
    		model.addAttribute("keluargas", keluargas);
    		model.addAttribute("tua", tua);
    		model.addAttribute("muda", muda);
    		model.addAttribute("kecamatans", kecamatans);
        	model.addAttribute("kelurahans", kelurahans);
        	model.addAttribute("lkota", kota);
    		return "cari-lurah";
    		
    	}
    	
	}
    
    @RequestMapping("/penduduk/mati/{NIK}")
    public String ubahPendudukNonaktif(@PathVariable(value = "NIK") String NIK, Model model)
    {
    	
    	PendudukModel a = sidukDAO.selectPenduduk(NIK);
    	List<PendudukModel> b = sidukDAO.cariAnggotaHidup(a.id_keluarga);
    	if(b.size()==1) {
    		a.keluarga.is_tidak_berlaku = 1;
    	}
    	a.is_wafat = 1;
    	
    	sidukDAO.updateKeluarga(a.keluarga);
    	sidukDAO.updatePenduduk(a);
    	model.addAttribute("penduduk", a);
    	model.addAttribute("keluarga", a.keluarga);
    	model.addAttribute("nik", a.nik);
    	return "mati";
    }
    
    
}
