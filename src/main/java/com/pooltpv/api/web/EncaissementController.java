package com.pooltpv.api.web;

import com.pooltpv.api.dto.EncaissementDTO;
import com.pooltpv.api.dto.PrimeDTO;
import com.pooltpv.api.exception.DateDiffException;
import com.pooltpv.api.service.EncaissementService;
import com.pooltpv.api.service.PrimeService;
import com.pooltpv.api.utilities.DateValidatorUsingDateTimeFormatter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@RestController
@RequestMapping("/pooltpv/api/")
public class EncaissementController {

    private final EncaissementService encaissementService;
    private int codeCompagnie;

    public EncaissementController(EncaissementService encaissementService) {
        this.encaissementService = encaissementService;
    }

    @GetMapping(path = "/encaissement/code_demandeur={codeDemandeur}&date_debut={dateDebut}&date_fin={dateFin}")
    public List<EncaissementDTO> getListSouscripteur(@PathVariable(name = "codeDemandeur") String  codeDemandeur,
                                                     @PathVariable(name = "dateDebut")  @DateTimeFormat(pattern = "dd-MM-yyyy") String  dateDebut,
                                                     @PathVariable(name = "dateFin")  @DateTimeFormat(pattern = "dd-MM-yyyy") String  dateFin) throws Exception {

        if(codeDemandeur.equals("fSwtr6IBYpVy8TIb")){
            codeCompagnie=20000;
        }else if(codeDemandeur.equals("22cDv9B3lxt6keKM")){
            codeCompagnie=40000;
        }else if(codeDemandeur.equals("HtbUraVTXGUO9HZh")){
            codeCompagnie=65000;
        }else if(codeDemandeur.equals("IZgHUBtvC6Esqqhx")){
            codeCompagnie=90000;
        }else if(codeDemandeur.equals("MNBgcZOMdMsTfO17")){
            codeCompagnie=95000;
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        dateFormatter.parse(dateDebut);
        dateFormatter.parse(dateFin);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.FRENCH);
        formatter.setTimeZone(TimeZone.getTimeZone("Africa/Douala"));
        Date startDate = formatter.parse(dateDebut + " 00:00:00");
        Date endDate = formatter.parse(dateFin + " 23:59:59");
        try {
            DateValidatorUsingDateTimeFormatter dateValidatorUsingDateTimeFormatter = new DateValidatorUsingDateTimeFormatter(startDate, endDate);
            if (dateValidatorUsingDateTimeFormatter.isDatediffValid(startDate, endDate) == true)
                return encaissementService.listEncaissement(codeCompagnie, startDate, endDate);
        }
        catch (DateDiffException e){

             throw new DateDiffException(e.getMessage());
        }

        return null;
    }

}
