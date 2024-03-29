package com.pooltpv.api.repositories;

import com.pooltpv.api.entities.Polices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface PolicesRepository extends JpaRepository<Polices,String> {

    @Query(value = "select \n" +
            "U_CLE_P, \n"  +
            "U_CLE, \n"  +
            "CONCAT(CONCAT(NUMEPOLI,'-'),CODEINTESOUS) AS NUMEPOLI, \n" +
            "FLOTTE, \n" +
            "LIBTYPAV as MOUVEMENT,\n" +
            "CODTYPAV,\n" +
            "DATESOUS, \n" +
            "DATESOUS AS HEURESOUS, \n" +
            "DATEFFET, \n" +
           "DATEECHE, \n" +
           "CODEASSU, \n" +
            "NVL(DUREE,'-') as DUREE, \n" +
            "IMMATRICULATION,\n" +
            "ASPUISSANCE,\n" +
            "NVL(SE,'-') as SE, \n" +
            "GENRE,\n" +
            "MARQUE,\n" +
            "TYPEVEHI,\n" +
            "PLACE,\n" +
            "DATE_PREM_MEC, \n" +
            "VAL_NEUVE,\n" +
            "ASVAL_VENALE,\n" +
            "CATEGORIE ,\n" +
            "SOUS_CAT_NOM,\n" +
            "CHASSIS,\n" +
            "CONCAT(CONCAT(ASSURE_NOM,' '),PRENASSU) AS ASSURE_NOM, \n" +
            "MOBILE ,\n" +
            "DATENAIS,\n" +
            "QUALITE,\n" +
            "SEXERISQ,\n" +
            "LIBEVILL,\n" +
            "NVL(RC,0.0) AS RC,\n" +
            "NVL(DR,0.0) AS DR,\n" +
            "NVL(IC,0.0) AS IC,\n" +
            "NVL(INC,0.0) AS BDG,\n" +
            "NVL(INC,0.0) AS INC,\n" +
            "NVL(VOL,0.0) AS VOL,\n" +
            "NVL(PRIME_NETTE,0.0) AS PRIME_NETTE,\n" +
            "NVL(DROIT_TIMBRE,0.0) AS DROIT_TIMBRE,\n" +
            "NVL(PRIMTOTA,0.0) AS PRIMETTC, \n" +
            "ATTESTATION, \n" +
            "NUMEQUIT \n" +
            "from ORASSADM.CIE_POOL_POL_RISQUE \n"+
            "WHERE code_cie=:codeCompagnie \n"+
            "and DATESOUS between :dateDebut and  :dateFin \n"
            ,nativeQuery = true)
    List<Polices> findRPolices(@Param("codeCompagnie") int codeCompagnie, @Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);
}
