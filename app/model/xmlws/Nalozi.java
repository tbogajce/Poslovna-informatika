//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.02 at 02:05:11 AM CEST 
//


package model.xmlws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="nalog_za_prenos">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="podaci_o_duzniku" type="{http://www.ftn.uns.ac.rs/nalog}TFizicko_lice"/>
 *                   &lt;element name="podaci_o_poveriocu" type="{http://www.ftn.uns.ac.rs/nalog}TPravno_lice"/>
 *                   &lt;element name="svrha_placanja" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="podaci_o_prenosu">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="sifra_placanja" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                             &lt;element name="valuta" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                             &lt;element name="iznos" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="transfer_podaci_duznik">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="racun_duznika" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                             &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                             &lt;element name="poziv_na_broj_duznika" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="transfer_podaci_poverioc">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="racun_poverioca" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                             &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                             &lt;element name="poziv_na_broj_poverioca" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="hitno" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="datum_valute" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nalogZaPrenos"
})
@XmlRootElement(name = "nalozi")
public class Nalozi {

    @XmlElement(name = "nalog_za_prenos", required = true)
    protected List<Nalozi.NalogZaPrenos> nalogZaPrenos;

    /**
     * Gets the value of the nalogZaPrenos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nalogZaPrenos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNalogZaPrenos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Nalozi.NalogZaPrenos }
     * 
     * 
     */
    public List<Nalozi.NalogZaPrenos> getNalogZaPrenos() {
        if (nalogZaPrenos == null) {
            nalogZaPrenos = new ArrayList<Nalozi.NalogZaPrenos>();
        }
        return this.nalogZaPrenos;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="podaci_o_duzniku" type="{http://www.ftn.uns.ac.rs/nalog}TFizicko_lice"/>
     *         &lt;element name="podaci_o_poveriocu" type="{http://www.ftn.uns.ac.rs/nalog}TPravno_lice"/>
     *         &lt;element name="svrha_placanja" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="podaci_o_prenosu">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="sifra_placanja" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                   &lt;element name="valuta" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                   &lt;element name="iznos" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="transfer_podaci_duznik">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="racun_duznika" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                   &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                   &lt;element name="poziv_na_broj_duznika" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="transfer_podaci_poverioc">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="racun_poverioca" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                   &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                   &lt;element name="poziv_na_broj_poverioca" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="hitno" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="datum_valute" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "podaciODuzniku",
        "podaciOPoveriocu",
        "svrhaPlacanja",
        "podaciOPrenosu",
        "transferPodaciDuznik",
        "transferPodaciPoverioc",
        "hitno",
        "datumValute"
    })
    public static class NalogZaPrenos {

        @XmlElement(name = "podaci_o_duzniku", required = true)
        protected TFizickoLice podaciODuzniku;
        @XmlElement(name = "podaci_o_poveriocu", required = true)
        protected TPravnoLice podaciOPoveriocu;
        @XmlElement(name = "svrha_placanja", required = true)
        protected String svrhaPlacanja;
        @XmlElement(name = "podaci_o_prenosu", required = true)
        protected Nalozi.NalogZaPrenos.PodaciOPrenosu podaciOPrenosu;
        @XmlElement(name = "transfer_podaci_duznik", required = true)
        protected Nalozi.NalogZaPrenos.TransferPodaciDuznik transferPodaciDuznik;
        @XmlElement(name = "transfer_podaci_poverioc", required = true)
        protected Nalozi.NalogZaPrenos.TransferPodaciPoverioc transferPodaciPoverioc;
        protected boolean hitno;
        @XmlElement(name = "datum_valute", required = true)
        protected Object datumValute;

        /**
         * Gets the value of the podaciODuzniku property.
         * 
         * @return
         *     possible object is
         *     {@link TFizickoLice }
         *     
         */
        public TFizickoLice getPodaciODuzniku() {
            return podaciODuzniku;
        }

        /**
         * Sets the value of the podaciODuzniku property.
         * 
         * @param value
         *     allowed object is
         *     {@link TFizickoLice }
         *     
         */
        public void setPodaciODuzniku(TFizickoLice value) {
            this.podaciODuzniku = value;
        }

        /**
         * Gets the value of the podaciOPoveriocu property.
         * 
         * @return
         *     possible object is
         *     {@link TPravnoLice }
         *     
         */
        public TPravnoLice getPodaciOPoveriocu() {
            return podaciOPoveriocu;
        }

        /**
         * Sets the value of the podaciOPoveriocu property.
         * 
         * @param value
         *     allowed object is
         *     {@link TPravnoLice }
         *     
         */
        public void setPodaciOPoveriocu(TPravnoLice value) {
            this.podaciOPoveriocu = value;
        }

        /**
         * Gets the value of the svrhaPlacanja property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSvrhaPlacanja() {
            return svrhaPlacanja;
        }

        /**
         * Sets the value of the svrhaPlacanja property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSvrhaPlacanja(String value) {
            this.svrhaPlacanja = value;
        }

        /**
         * Gets the value of the podaciOPrenosu property.
         * 
         * @return
         *     possible object is
         *     {@link Nalozi.NalogZaPrenos.PodaciOPrenosu }
         *     
         */
        public Nalozi.NalogZaPrenos.PodaciOPrenosu getPodaciOPrenosu() {
            return podaciOPrenosu;
        }

        /**
         * Sets the value of the podaciOPrenosu property.
         * 
         * @param value
         *     allowed object is
         *     {@link Nalozi.NalogZaPrenos.PodaciOPrenosu }
         *     
         */
        public void setPodaciOPrenosu(Nalozi.NalogZaPrenos.PodaciOPrenosu value) {
            this.podaciOPrenosu = value;
        }

        /**
         * Gets the value of the transferPodaciDuznik property.
         * 
         * @return
         *     possible object is
         *     {@link Nalozi.NalogZaPrenos.TransferPodaciDuznik }
         *     
         */
        public Nalozi.NalogZaPrenos.TransferPodaciDuznik getTransferPodaciDuznik() {
            return transferPodaciDuznik;
        }

        /**
         * Sets the value of the transferPodaciDuznik property.
         * 
         * @param value
         *     allowed object is
         *     {@link Nalozi.NalogZaPrenos.TransferPodaciDuznik }
         *     
         */
        public void setTransferPodaciDuznik(Nalozi.NalogZaPrenos.TransferPodaciDuznik value) {
            this.transferPodaciDuznik = value;
        }

        /**
         * Gets the value of the transferPodaciPoverioc property.
         * 
         * @return
         *     possible object is
         *     {@link Nalozi.NalogZaPrenos.TransferPodaciPoverioc }
         *     
         */
        public Nalozi.NalogZaPrenos.TransferPodaciPoverioc getTransferPodaciPoverioc() {
            return transferPodaciPoverioc;
        }

        /**
         * Sets the value of the transferPodaciPoverioc property.
         * 
         * @param value
         *     allowed object is
         *     {@link Nalozi.NalogZaPrenos.TransferPodaciPoverioc }
         *     
         */
        public void setTransferPodaciPoverioc(Nalozi.NalogZaPrenos.TransferPodaciPoverioc value) {
            this.transferPodaciPoverioc = value;
        }

        /**
         * Gets the value of the hitno property.
         * 
         */
        public boolean isHitno() {
            return hitno;
        }

        /**
         * Sets the value of the hitno property.
         * 
         */
        public void setHitno(boolean value) {
            this.hitno = value;
        }

        /**
         * Gets the value of the datumValute property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getDatumValute() {
            return datumValute;
        }

        /**
         * Sets the value of the datumValute property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setDatumValute(Object value) {
            this.datumValute = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="sifra_placanja" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *         &lt;element name="valuta" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *         &lt;element name="iznos" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "sifraPlacanja",
            "valuta",
            "iznos"
        })
        public static class PodaciOPrenosu {

            @XmlElement(name = "sifra_placanja", required = true)
            protected Object sifraPlacanja;
            @XmlElement(required = true)
            protected Object valuta;
            @XmlElement(required = true)
            protected Object iznos;

            /**
             * Gets the value of the sifraPlacanja property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getSifraPlacanja() {
                return sifraPlacanja;
            }

            /**
             * Sets the value of the sifraPlacanja property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setSifraPlacanja(Object value) {
                this.sifraPlacanja = value;
            }

            /**
             * Gets the value of the valuta property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getValuta() {
                return valuta;
            }

            /**
             * Sets the value of the valuta property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setValuta(Object value) {
                this.valuta = value;
            }

            /**
             * Gets the value of the iznos property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getIznos() {
                return iznos;
            }

            /**
             * Sets the value of the iznos property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setIznos(Object value) {
                this.iznos = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="racun_duznika" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *         &lt;element name="poziv_na_broj_duznika" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "racunDuznika",
            "model",
            "pozivNaBrojDuznika"
        })
        public static class TransferPodaciDuznik {

            @XmlElement(name = "racun_duznika", required = true)
            protected Object racunDuznika;
            @XmlElement(required = true)
            protected Object model;
            @XmlElement(name = "poziv_na_broj_duznika", required = true)
            protected Object pozivNaBrojDuznika;

            /**
             * Gets the value of the racunDuznika property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getRacunDuznika() {
                return racunDuznika;
            }

            /**
             * Sets the value of the racunDuznika property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setRacunDuznika(Object value) {
                this.racunDuznika = value;
            }

            /**
             * Gets the value of the model property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getModel() {
                return model;
            }

            /**
             * Sets the value of the model property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setModel(Object value) {
                this.model = value;
            }

            /**
             * Gets the value of the pozivNaBrojDuznika property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getPozivNaBrojDuznika() {
                return pozivNaBrojDuznika;
            }

            /**
             * Sets the value of the pozivNaBrojDuznika property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setPozivNaBrojDuznika(Object value) {
                this.pozivNaBrojDuznika = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="racun_poverioca" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *         &lt;element name="poziv_na_broj_poverioca" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "racunPoverioca",
            "model",
            "pozivNaBrojPoverioca"
        })
        public static class TransferPodaciPoverioc {

            @XmlElement(name = "racun_poverioca", required = true)
            protected Object racunPoverioca;
            @XmlElement(required = true)
            protected Object model;
            @XmlElement(name = "poziv_na_broj_poverioca", required = true)
            protected Object pozivNaBrojPoverioca;

            /**
             * Gets the value of the racunPoverioca property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getRacunPoverioca() {
                return racunPoverioca;
            }

            /**
             * Sets the value of the racunPoverioca property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setRacunPoverioca(Object value) {
                this.racunPoverioca = value;
            }

            /**
             * Gets the value of the model property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getModel() {
                return model;
            }

            /**
             * Sets the value of the model property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setModel(Object value) {
                this.model = value;
            }

            /**
             * Gets the value of the pozivNaBrojPoverioca property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getPozivNaBrojPoverioca() {
                return pozivNaBrojPoverioca;
            }

            /**
             * Sets the value of the pozivNaBrojPoverioca property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setPozivNaBrojPoverioca(Object value) {
                this.pozivNaBrojPoverioca = value;
            }

        }

    }

}
