package com.vr.user.service.entities;

import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "countries")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private Name name;

	@ElementCollection
	@CollectionTable(name = "country_tld", joinColumns = @JoinColumn(name = "country_id"))
	@Column(name = "tld")
	private List<String> tld;

	private String cca2;
	private String ccn3;
	private String cca3;
	private boolean independent;
	private String status;
	private boolean unMember;

	@ElementCollection
	@CollectionTable(name = "country_currency", joinColumns = @JoinColumn(name = "country_id"))
	@MapKeyColumn(name = "currency_code")
	private Map<String, Currency> currencies;

	@Embedded
	private Idd idd;

	@ElementCollection
	@CollectionTable(name = "country_capital", joinColumns = @JoinColumn(name = "country_id"))
	@Column(name = "capital")
	private List<String> capital;

	@ElementCollection
	@CollectionTable(name = "country_alt_spellings", joinColumns = @JoinColumn(name = "country_id"))
	@Column(name = "alt_spelling")
	private List<String> altSpellings;

	private String region;

	@ElementCollection
	@CollectionTable(name = "country_languages", joinColumns = @JoinColumn(name = "country_id"))
	@MapKeyColumn(name = "language_code")
	@Column(name = "language")
	private Map<String, String> languages;

	@ElementCollection
	@CollectionTable(name = "country_translations", joinColumns = @JoinColumn(name = "country_id"))
	@MapKeyColumn(name = "translation_key")
	private Map<String, Translation> translations;

	@ElementCollection
	@CollectionTable(name = "country_latlng", joinColumns = @JoinColumn(name = "country_id"))
	@Column(name = "latlng")
	private List<Double> latlng;

	private boolean landlocked;
	private double area;

	@Embedded
	private Demonyms demonyms;

	private String flag;

	@Embedded
	private Maps maps;

	private int population;

	@Embedded
	private Car car;

	@ElementCollection
	@CollectionTable(name = "country_timezones", joinColumns = @JoinColumn(name = "country_id"))
	@Column(name = "timezone")
	private List<String> timezones;

	@ElementCollection
	@CollectionTable(name = "country_continents", joinColumns = @JoinColumn(name = "country_id"))
	@Column(name = "continent")
	private List<String> continents;

	@Embedded
	private Flag flags;

	@Embedded
	private CoatOfArms coatOfArms;

	private String startOfWeek;

	@Embedded
	private CapitalInfo capitalInfo;

	// Inner classes with JPA annotations if necessary

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Name {
		private String common;
		private String official;

		@ElementCollection
		@CollectionTable(name = "native_names", joinColumns = @JoinColumn(name = "country_id"))
		@MapKeyColumn(name = "language")
		private Map<String, NativeName> nativeName;
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class NativeName {
		private String official;
		private String common;
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Currency {
		private String name;
		private String symbol;
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Idd {
		private String root;

		@ElementCollection
		@CollectionTable(name = "idd_suffixes", joinColumns = @JoinColumn(name = "country_id"))
		@Column(name = "suffix")
		private List<String> suffixes;
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Demonyms {
		@Embedded
		private Eng eng;

		@Embeddable
		@Data
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Eng {
			private String f;
			private String m;
		}
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Maps {
		private String googleMaps;
		private String openStreetMaps;
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Car {
		@ElementCollection
		@CollectionTable(name = "car_signs", joinColumns = @JoinColumn(name = "country_id"))
		@Column(name = "sign")
		private List<String> signs;
		private String side;
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Flag {
		private String png;
		private String svg;
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	public static class CoatOfArms {
		// Placeholder for coat of arms data
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Translation {
		private String official;
		private String common;
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CapitalInfo {
		@ElementCollection
		@CollectionTable(name = "capital_latlng", joinColumns = @JoinColumn(name = "country_id"))
		@Column(name = "latlng")
		private List<Double> latlng;
	}
}
